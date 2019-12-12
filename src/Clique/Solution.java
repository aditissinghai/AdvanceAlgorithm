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
    static int helper(int n, int r) {
        int mod = n % r;
        int p = r - mod;
        int q = n / r + 1;
        int o = n / r;
        int t = mod * q * p * o + mod * (mod - 1) * q * q / 2 + p
                * (p - 1) * o * o / 2;
        return t;
    }

    static int solve(int n, int m) {
        int k, low = 1, high = n + 1;
        while (low + 1 < high) {
            int mid = low + (high - low) / 2; // to avoid overflow
            k = helper(n, mid);
            if (k < m)
                low = mid;
            else
                high = mid;
        }
        return high;
    }
}
