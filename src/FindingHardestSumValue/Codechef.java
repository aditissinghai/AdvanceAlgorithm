package FindingHardestSumValue;

import java.util.*;
import java.lang.*;
import java.io.*;
/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{

    private static List<Long> results = new ArrayList<>();

    public static void main (String[] args) throws java.lang.Exception
    {
        // your code goes here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());    // Number of test cases

        for(int test = 1; test <= T; test++) {
            // Reading row N and column M
            String[] conf = br.readLine().split(" ");
            int N = Integer.parseInt(conf[0]);
            int M = Integer.parseInt(conf[1]);

            int[][] matrix =  new int[N][M];

            for(int row = 0; row < N; row++) {
                String[] rowVal = br.readLine().split(" ");
                for(int col = 0; col < M; col++) {
                    matrix[row][col] = Integer.parseInt(rowVal[col]);
                }
            }

            results.add(findHardestSum(N, M, matrix));
        }

        for(Long result : results) {
            System.out.println(result);
        }
    }

    private static Long findHardestSum(int N, int M, int[][] a) {

        long[][] dpLeft = new long[N][M];
        long[][] dpRight = new long[N][M];
        long[][] dpTop = new long[N][M];
        long[][] dpBottom = new long[N][M];

        // Finding minimum in Left to Right direction // East
        for(int i = 0; i < N; i++) {
            dpRight[i][0] = a[i][0];
        }

        int row = 0;
        while(row < N) {
            for(int col = 1; col < M; col++) {
                dpRight[row][col] = Math.min(a[row][col], a[row][col]+dpRight[row][col-1]);
            }
            row++;
        }

        // Finding minimum in Right to Left direction // West

        for(int i = 0; i < N; i++) {
            dpLeft[i][M-1] = a[i][M-1];
        }

        row = 0;
        while(row < N) {
            for(int col = M-2; col >= 0; col--) {
                dpLeft[row][col] = Math.min(a[row][col], a[row][col] + dpLeft[row][col+1]);
            }
            row++;
        }

        // Finding minimum in Top to Bottom direction // South

        for(int i = 0; i < M; i++) {
            dpBottom[0][i] = a[0][i];
        }

        int col = 0;
        while(col < M) {
            for(row = 1; row < N; row++) {
                dpBottom[row][col] = Math.min(a[row][col], a[row][col] + dpBottom[row-1][col]);
            }
            col++;
        }

        for(int i = 0; i < M; i++) {
            dpTop[N-1][i] = a[N-1][i];
        }

        col = 0;
        while(col < M) {
            for(row = N-2; row >= 0; row--) {
                dpTop[row][col] = Math.min(a[row][col], a[row][col] + dpTop[row+1][col]);
            }
            col++;
        }

        long min = Long.MAX_VALUE;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                long cur = dpLeft[i][j] + dpRight[i][j] + dpBottom[i][j] + dpTop[i][j] - 3*a[i][j];
                if(cur < min) {
                    min = cur;
                }
            }
        }

        return min;
    }

}