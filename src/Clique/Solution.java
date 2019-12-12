package Clique;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        for(int i = 0; i < t; i++) {

            int n = sc.nextInt();
            int m = sc.nextInt();

            System.out.println(solve(n, m));
        }
    }
    static int helper(int n, int k) {
        int g1 = n % k;
        int g2 = k - g1;
        int sz1 = n / k + 1;
        int sz2 = n / k;
        int ret = g1 * sz1 * g2 * sz2 + g1 * (g1 - 1) * sz1 * sz1 / 2 + g2
                * (g2 - 1) * sz2 * sz2 / 2;
        return ret;
    }

    static int solve(int n, int e) {
        int k, low = 1, high = n + 1;
        while (low + 1 < high) {
            int mid = low + (high - low) / 2; // to avoid overflow
            k = helper(n, mid);
            if (k < e)
                low = mid;
            else
                high = mid;
        }
        return high;
    }
}
