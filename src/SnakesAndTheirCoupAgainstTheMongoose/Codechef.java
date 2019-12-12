package SnakesAndTheirCoupAgainstTheMongoose;

import java.util.Scanner;

public class Codechef {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        String s1;
        String s2;

        while(t-- > 0) {
            int n = s.nextInt();
            s1 = s.next();
            s2 = s.next();

            // counting snakes in each string
            int[] snakeCount = new int[2];
            int fences = 0;

            for (int i = 0; i < n; i++) {
                if(s1.charAt(i) == '*')
                    snakeCount[0]++;
                if(s2.charAt(i)=='*')
                    snakeCount[1]++;
            }

            // Count number of fences
            if(snakeCount[0] > 0 && snakeCount[1] > 0) {
                fences = 1;
                snakeCount[0] = 0;
                snakeCount[1] = 1;

                for (int i = 0; i < n; i++) {
                    if(s1.charAt(i) == '*')
                        snakeCount[0]++;
                    if(s2.charAt(i)=='*')
                        snakeCount[1]++;

                    if(snakeCount[0] > 1 && snakeCount[1] > 1) {
                        fences++;
                        snakeCount[0] = 0;
                        snakeCount[1] = 1;
                        i--;
                    }
                }
            } else if(snakeCount[0] == 0 && snakeCount[1] > 1 || snakeCount[0] > 1 && snakeCount[1] == 0) {
                fences = Math.max(snakeCount[0], snakeCount[1])-1;
            } else {
                fences = 0;
            }

            System.out.println(fences);
        }
    }
}