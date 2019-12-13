package SherlockAndTheValidString;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the isValid function below.
    static String isValid(String s) {

        HashMap<Character, Integer> map = new HashMap<>();

        for(int i = 0 ; i < s.length(); i++) {
            if(map.containsKey(s.charAt(i)))
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            else
                map.put(s.charAt(i), 1);
        }

        HashSet<Integer> freq = new HashSet<>();

        for(Map.Entry<Character, Integer> e: map.entrySet()) {
            freq.add(e.getValue());
        }

        if(freq.size() > 2) {
            return "NO";
        }
        else if(freq.size() == 1) {
            return "YES";
        } else {

            int f1 = 0;
            int f2 = 0;

            int f1Count = 0;
            int f2Count = 0;

            int i = 0;

            for(int n : freq)
            {
                if(i == 0)
                    f1 = n;
                else
                    f2 = n;
                i++;
            }

            for(int freqVal : map.values()) {
                if(freqVal == f1)
                    f1Count++;
                if(freqVal == f2) {
                    f2Count++;
                }
            }

            if((f1 == 1 && f1Count == 1 ) || (f2 == 1 && f2Count == 1 ))
                return "YES";
            else if ((Math.abs(f1 - f2)  == 1) && (f1Count == 1 || f2Count == 1))
                return "YES";
            else
                return "NO";
        }

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        String s = scanner.nextLine();

        String result = isValid(s);

        System.out.println(result);

        scanner.close();
    }
}

