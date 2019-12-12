package SuffixRotation;

import java.util.HashMap;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int g = sc.nextInt();

        for(int i = 0; i < g; i++) {
            String s = sc.next();
            HashMap<String, Integer> map = new HashMap<>();
            function(s, 'a' ,map);
        }
    }

    private static void function(String s, char a, HashMap<String, Integer> map) {

    }
}
