package SuffixRotation;

import java.util.*;

public class Solution {

    private static List<Integer> result = new ArrayList<>();
    private static HashMap<String, Integer> map;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int g = sc.nextInt();

        for(int i = 0; i < g; i++) {
            String s = sc.next();
            map = new HashMap<>();
            char[] c = s.toCharArray();
            result.add(function(c,0));
        }

        for(Integer ans : result) {
            System.out.println(ans);
        }
    }

    private static int function(char[] s, int pointer) {

        int count = Integer.MAX_VALUE;

        if(pointer == s.length) {
            return 0;
        }

        String postfix = String.valueOf(s, pointer, s.length-pointer);


        if(map.containsKey(postfix)){
            return map.get(postfix);
        }

        List<Integer> occurrences = getNextLowestCharacter(s, pointer);

        for(int lowestIdx : occurrences) {
            if(lowestIdx == pointer) {
                count = Math.min(function(s, pointer+1), count);
            }else {
                count = Math.min(function(rotate(s, pointer, lowestIdx), pointer+1) + 1, count);
            }

        }
        map.put(postfix, count);
        return count;
    }

    private static char[] rotate(char[] s, int pointer, int lowestIdx) {
        char[] copy = Arrays.copyOf(s, s.length);
        int current = pointer;

        for(int i = lowestIdx; i < s.length; i++) {
            copy[current] = s[i];
            current++;
        }

        for(int i = pointer; i < lowestIdx; i++) {
            copy[current] = s[i];
            current++;
        }

        return copy;
    }

    private static List<Integer> getNextLowestCharacter(char[] s, int pointer) {
        int nextMin = pointer;

        // Get the next minimum character's pointer
        for(int i = pointer+1; i < s.length; i++) {
            if(s[nextMin] > s[i]) {
                nextMin = i;
            }
        }

        // Get all the indexes matching to the character at location nextMin
        List<Integer> sameChar = new ArrayList();
        for(int i = pointer; i < s.length; i++) {
            if(s[nextMin] == s[i]) {
                sameChar.add(i);
            }
        }

        return sameChar;
    }
}
