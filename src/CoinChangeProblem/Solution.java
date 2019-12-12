package CoinChangeProblem;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int coins[] = new int[m];

        for (int i = 0; i < m; i++) {
            coins[i] = sc.nextInt();
        }

        Arrays.sort(coins);

        System.out.println(getWays(coins, n));
    }

    private static int getWays(int[] coins, int n) {
        int[] res = new int[n+1];
        res[0] = 1;

        for(int i = 0; i < coins.length; i++) {
            for(int j = coins[i]; j <= n; j++) {
                res[j] += res[j - coins[i]];
            }
        }

        return res[n];
    }
}
