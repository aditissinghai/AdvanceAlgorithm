package ImplementStrStr;

public class Solution {

    public static void main(String[] args) {
        String txt = "hello";
        String pat = "ll";
        Solution s = new Solution();
        System.out.println("Pattern found at index: "+s.strStr(pat, txt));

    }

    // d is the number of characters in the input alphabet
    public final static int d = 256;


    public int strStr(String haystack, String needle) {
        int N = haystack.length();
        int M = needle.length();

        if(M == 0) {
            return 0;
        } else if (N == 0) {
            return -1;
        } else if (M > N) {
            return -1;
        }

        int i, j;

        int q = 991; // any prime number used for modular multiplication
        int n = 0;   // hashvalue for needle
        int hs = 0;   // hashvalue for haystack
        int h = 1;

        // The value of h would be "pow(d, M-1)%q"
        for (i = 0; i < M-1; i++)
            h = (h*d)%q;

        // Calculate the hash value of needle and first
        // window of haystack
        for (i = 0; i < M; i++)
        {
            n = (d*n + needle.charAt(i))%q;
            hs = (d*hs + haystack.charAt(i))%q;
        }

        // Slide the needle over haystack one by one
        for (i = 0; i <= N - M; i++)
        {
            // Check the hash values of current window of haystack
            // and needle. If the hash values match then only
            // check for characters on by one
            if ( n == hs )
            {
                /* Check for characters one by one */
                for (j = 0; j < M; j++)
                {
                    if (haystack.charAt(i+j) != needle.charAt(j))
                        break;
                }

                // if n == hs and needle[0...M-1] = haystack[i, i+1, ...i+M-1]
                if (j == M)
                    return i;
            }

            // Calculate hash value for next window of haystack: Remove
            // leading digit, add trailing digit
            if ( i < N-M )
            {
                hs = (d*(hs - haystack.charAt(i)*h) + haystack.charAt(i+M))%q;

                // We might get negative value of t, converting it
                // to positive
                if (hs < 0)
                    hs = (hs + q);
            }
        }

        return -1;
    }

}
