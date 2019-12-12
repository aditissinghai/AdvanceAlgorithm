package IndianJob;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    /*
     * Complete the indianJob function below.
     */
    static String indianJob(int g, int[] arr) {
        /*
         * Write your code here.
         */

        int sum = 0, max = 0;

        for(int i = 0; i < arr.length; i++) {
            sum += arr[i];
            max = Math.max(max, arr[i]);
        }

        System.out.println("sum: "+sum);
        System.out.println("max: "+max);

        if(sum/2 > g || max > g)
            return "NO";

        if(sum <= g || max > sum/2) {
            return "YES";
        }

        int dp[][] = new int[arr.length+1][g+1];

        // Initial state of robbers // Setting rows to zero
        for(int i = 0; i < arr.length + 1; i++) {
            dp[i][g] = 0;
        }

        // Setting columns to zero
        for(int j = 0; j < g + 1; j++) {
            dp[0][j] = 0;
        }

        for( int i = 1; i < arr.length + 1; i++ ) {
            for ( int j = 0; j < g + 1; j++ ) {
                if(arr[i-1] <= j) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-arr[i-1]]+arr[i-1]);
                }
            }
        }

        if(dp[arr.length][g] <= g && (sum - dp[arr.length][g]) <= g ) {
            return "YES";
        }

        return "NO";
    }

    private static final Scanner scanner = new Scanner(System.in);
    static List<String> res = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        int t = Integer.parseInt(scanner.nextLine().trim());

        for (int tItr = 0; tItr < t; tItr++) {
            String[] ng = scanner.nextLine().split(" ");

            int n = Integer.parseInt(ng[0].trim());

            int g = Integer.parseInt(ng[1].trim());

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");

            for (int arrItr = 0; arrItr < n; arrItr++) {
                int arrItem = Integer.parseInt(arrItems[arrItr].trim());
                arr[arrItr] = arrItem;
            }

            String result = indianJob(g, arr);
            res.add(result);
        }

        for(String ans : res) {
            System.out.println(ans);
        }

    }
}
