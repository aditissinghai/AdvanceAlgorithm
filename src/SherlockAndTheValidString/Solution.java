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
            int k = map.getOrDefault(s.charAt(i), 0);
            map.put(s.charAt(i), k+1);
        }

        HashMap<Integer, Integer> mapFreq = new HashMap<>();

        for(Map.Entry<Character, Integer> e: map.entrySet()) {
            int k = mapFreq.getOrDefault(e.getValue(), 0);
            mapFreq.put(e.getValue(), k+1);
        }

        if(mapFreq.size() == 1) {
            return "YES";
        } else if(mapFreq.size()==2) {
            for(Map.Entry<Integer, Integer> e: mapFreq.entrySet()) {
                if(e.getValue() == 1) {
                    return "YES";
                }
            }
        }

        return "NO";

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        String s = scanner.nextLine();

        String result = isValid(s);

        System.out.println(result);

        scanner.close();
    }
}

