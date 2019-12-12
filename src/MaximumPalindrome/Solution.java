package MaximumPalindrome;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    static int[][] dp;
    static int []fact = new int[100005];
    static int mod = 1000000007;

    /*
     * Complete the 'initialize' function below.
     *
     * The function accepts STRING s as parameter.
     */

    public static void initialize(String s) {
        // This function is called once before all queries.
        dp = new int[26][s.length()];


        // Keeping track of occurrences of every character
        for (int i = 0; i < 26; i++)
        {
            if(s.charAt(0)-'a' == i)
                dp[i][0] = 1;
            for (int j = 1; j < dp[i].length; j++)
            {
                dp[i][j] = dp[i][j-1];
                if(s.charAt(j)-'a' == i)
                    dp[i][j]++;
            }
        }

       /* for(int  i =0 ; i < 26; i++) {
            for(int j = 0; j < dp[i].length; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }*/

       // Pre calculating the factorial values
        fact[0] = 1;
        for (int i = 1; i < fact.length; i++) {
            fact[i] = i*fact[i-1]%mod;      // To avoid overflow of integer range
        }


    }

    /*
     * Complete the 'answerQuery' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER l
     *  2. INTEGER r
     */

    public static int answerQuery(int l, int r) {
        // Return the answer for this query modulo 1000000007.

        l--; // wrt array pointers
        r--; // wrt array pointers

        int f[] = new int[26];

        for(int i = 0; i < 26; i++) {
            f[i] = dp[i][r];
            if( l > 0) {
                f[i] = f[i] - dp[i][l-1]; // why subtract
            }
//            System.out.println(i + " : " +f[i]);
        }

        int even = 0;
        int odd = 0;

        for(int i = 0; i < f.length; i++) {
            even += f[i]/2;
            odd += f[i]%2;
        }

        long res = fact[even];
        if(odd > 0) {
            res = ((res%mod) * (odd%mod));
        }

        for(int i = 0; i < f.length; i++) {
            if(f[i] > 1) {
                res = (res%mod) * (inv(fact[f[i]/2])%mod);
            }
        }

        return (int)res;
    }

    static long inv(long x) {
        long r, y;

        r = 1;
        y = mod - 2;

        while(y!=0) {

            // y & 1 produces value which is either 1 or 0
            // y & 1 == y % 2
            if ((y & 1) == 1)
                r = r * x % mod;

            // Set y to itself by shifting one bit to the right
            // y >> 1 is equivalent to y/2;
            y >>=1;
            x = x*x;
        }

        return r;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String s = bufferedReader.readLine();

        Result.initialize(s);

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        for (int qItr = 0; qItr < q; qItr++) {
            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int l = Integer.parseInt(firstMultipleInput[0]);

            int r = Integer.parseInt(firstMultipleInput[1]);

            int result = Result.answerQuery(l, r);

            System.out.println(String.valueOf(result));
        }
        bufferedReader.close();
    }
}
