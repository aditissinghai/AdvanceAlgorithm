package FloydCityOfBlindingLights;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {


    private static final Scanner scanner = new Scanner(System.in);
    private static final int INF = -1;

    public static void main(String[] args) {
        String[] roadNodesEdges = scanner.nextLine().split(" ");
        int roadNodes = Integer.parseInt(roadNodesEdges[0].trim());
        int roadEdges = Integer.parseInt(roadNodesEdges[1].trim());

        int[][] graph = new int[roadNodes][roadNodes];

        int[] roadFrom = new int[roadEdges];
        int[] roadTo = new int[roadEdges];
        int[] roadWeight = new int[roadEdges];

        for(int i = 0; i < roadNodes; i++) {
            for(int j = 0; j < roadNodes; j++) {
                graph[i][j] = -1;
            }
        }

        for(int i = 0; i < roadNodes; i++) {
            graph[i][i] = 0;
        }

        for (int i = 0; i < roadEdges; i++) {
            String[] roadFromToWeight = scanner.nextLine().split(" ");
            roadFrom[i] = Integer.parseInt(roadFromToWeight[0].trim());
            roadTo[i] = Integer.parseInt(roadFromToWeight[1].trim());
            roadWeight[i] = Integer.parseInt(roadFromToWeight[2].trim());

            graph[roadFrom[i]-1][roadTo[i]-1] = roadWeight[i];
        }

        floydWarshall(graph, roadNodes);

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] xy = scanner.nextLine().split(" ");

            int x = Integer.parseInt(xy[0]);

            int y = Integer.parseInt(xy[1]);

            if(x!=y && graph[x-1][y-1] == 0) {
                System.out.println(INF);
            }else {
                System.out.println(graph[x-1][y-1]);
            }
        }

        scanner.close();
    }

    static void floydWarshall(int[][] graph, int N) {
        for(int k = 0; k < N; k++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(graph[i][k] != -1 && graph[k][j] != -1 && (graph[i][j] > graph[i][k]+graph[k][j] || graph[i][j] == -1) ) {
                        graph[i][j] = graph[i][k]+graph[k][j];
                    }
                }
            }
        }
    }
}
