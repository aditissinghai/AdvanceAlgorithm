package CoinChangeProblem;

import java.util.HashMap;
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

//        Arrays.sort(coins);

        System.out.println(getWays(coins, n));
    }

    private static long getWays(int[] coins, int n) {

        if(n < 0) {
            return 0;
        }

        return numOfWays(n, coins, 0, new HashMap<String , Long>());
    }

    private static long numOfWays(int n, int[] coins, int i, HashMap<String, Long> map) {
        String k = n + "," + coins[i];

        if(map.containsKey(k))
            return map.get(k);

        if(i == coins.length - 1) {
            if(n % coins[i] == 0) {
                map.put(k, 1l);
                return 1;
            }else {
                map.put(k, 0l);
                return 0;
            }
        }

        long numberWays = 0;

        for(int id = 0; id <= n; id = id+coins[i] ) {
            numberWays += numOfWays(n-id, coins, i+1, map);
        }

        map.put(k, numberWays);
        return numberWays;
    }
}
