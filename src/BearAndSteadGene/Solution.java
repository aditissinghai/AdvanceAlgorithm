package BearAndSteadGene;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the steadyGene function below.
    static int steadyGene(String gene, int n) {

        int len = gene.length();

        HashMap<Character, Integer> map = new HashMap();
        map.put('A',0);
        map.put('G',0);
        map.put('C',0);
        map.put('T',0);

        for(int i = 0; i < len; i++) {
            map.put(gene.charAt(i), map.get(gene.charAt(i))+1);
        }

        boolean foundZero = true;
        for(Map.Entry<Character, Integer> e : map.entrySet()) {
            if(e.getValue()!=0) {
                foundZero = false;
            }
        }

        if(foundZero) {
            return 0;
        }

        int left = 0, right = 0, min = Integer.MAX_VALUE;

        while(right < n-1) {
            char rightChar = gene.charAt(right++);
            map.put(rightChar, map.get(rightChar) - 1);
            while(isSteady(map, n)){
                min = Math.min(min, right - left);
                char leftChar = gene.charAt(left++);
                map.put(leftChar, map.get(leftChar) + 1);
            }

        }

        return min;
    }

    private static boolean isSteady(Map<Character, Integer> map, int n) {
        for (Integer i : map.values())
            if (i > n / 4) return false;
        return true;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String gene = scanner.nextLine();

        int result = steadyGene(gene, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
