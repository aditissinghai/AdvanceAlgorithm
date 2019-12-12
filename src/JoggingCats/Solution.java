package JoggingCats;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws Exception{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line1 = br.readLine();
        String arr[] = line1.split(" ");

        int M = Integer.parseInt(arr[1]);
        int N = Integer.parseInt(arr[0]);

        String inputs[] = new String[M];
        for(int i = 0; i < M; i++) {
            inputs[i] = br.readLine();
        }
        br.close();

        Node nodes[] = new Node[N+1];

        // Initialize the graph
        for(int i = 1; i <= N; i++) {
            nodes[i] = new Node();
            nodes[i].links = new ArrayList<>();
        }

        // Fill the values in the bidirectional graph
        for(int i = 0; i < M; i++) {
            String[] line = inputs[i].split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);

            nodes[x].links.add(y);
            nodes[y].links.add(x);
        }

        long routes = 0;

        for(int i = 1; i <= N; i++) {
            Node cur = nodes[i];
            Map<Integer, Integer> routeCount = new HashMap<>();
            for(int link: cur.links) {
                // Explore their links too
                Node neigh = nodes[link];
                for(int l : neigh.links) {
                    // check if there is no cycle
                    if(l!=i) {
                        routeCount.put(l, routeCount.getOrDefault(l, 0)+1);
                    }
                }
            }

            for(Map.Entry<Integer,Integer> e: routeCount.entrySet()){
                System.out.println("Key: "+e.getKey()+" Value: "+e.getValue());
                routes += choose2(e.getValue());
            }
        }

        System.out.println(routes/4);
    }


    static long choose2(long n){
        return (n*(n-1))/2;
    }

    static class Node {
        List<Integer> links;
    }
}

