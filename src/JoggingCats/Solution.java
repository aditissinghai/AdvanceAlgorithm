package JoggingCats;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int intersections = sc.nextInt();
        int edges = sc.nextInt();

        ArrayList<ArrayList<Integer>> neighbors = new ArrayList<>();

        for (int i = 0; i < intersections; ++i) {
            neighbors.add(new ArrayList<>());
        }

        for (int i = 0; i < edges; i++) {
            int src = sc.nextInt() - 1;
            int dest = sc.nextInt() - 1;
            neighbors.get(src).add(dest);
            neighbors.get(dest).add(src);
        }

        int[][] paths = new int[intersections][];
        for (int i = 0; i < intersections; i++) {
            paths[i] = new int[neighbors.get(i).size()];
            int index = 0;
            for (int item : neighbors.get(i)) {
                paths[i][index++] = item;
            }
            Arrays.sort(paths[i]);
        }

        int factor = 25;
        long[] neighborsCalculations = new long[edges * factor];
        int len = 0;
        long pathCount = 0;
        for (int i = 0; i < intersections; ++i) {
            if (paths[i].length <= factor) {
                for (int j = 0; j < paths[i].length; j++) {
                    for (int k = j + 1; k < paths[i].length; k++) {
                        neighborsCalculations[len++] = (intersections * paths[i][j]) + paths[i][k];
                    }
                }
            } else {
                boolean[] overFactor = new boolean[intersections];
                for (int j : paths[i]) {
                    overFactor[j] = true;
                }
                for (int j = 0; j < intersections; j++) {
                    if (paths[j].length > factor && j <= i) {
                        continue;
                    }
                    long num = 0;
                    for (int k : paths[j]) {
                        if (overFactor[k]) {
                            num++;
                        }
                    }
                    pathCount += (num * (num - 1)) / 2;
                }
            }
        }

        Arrays.sort(neighborsCalculations, 0, len);

        int i = 0;
        while (i < len) {
            int j = i;
            while (j < neighborsCalculations.length && neighborsCalculations[i] == neighborsCalculations[j]) {
                j++;
            }
            int num = j - i;
            pathCount += (num * (num - 1)) / 2;
            i = j;
        }
        if (pathCount % 2 == 0) {
            System.out.println(pathCount / 2);
        }
    }
}