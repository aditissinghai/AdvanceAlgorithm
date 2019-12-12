package SupermanCelebratesDiwali;

import java.util.Map;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int H = sc.nextInt();
        int I = sc.nextInt();

        int[][] location = new int[N][H+1];
        for(int building = 0; building < N; building++) {
            int count = sc.nextInt();
            for(int person = 0; person < count; person++) {
                int floor = sc.nextInt();
                location[building][floor]++;
            }

        }

        int dp[] = new int[H+1];
        for(int level = 1; level <= H; level++) {
            for(int building = 0; building < N; building++) {
                int below = location[building][level-1];
                if(level > I) {
                    below = Math.max(below, dp[level-I]);
                }
                location[building][level] += below;
                dp[level] = Math.max(dp[level], location[building][level]);
            }
        }

        System.out.println(dp[H]);
    }
}
