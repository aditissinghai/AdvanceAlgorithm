package SnakesAndTheirCoupAgainstTheMongoose;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Codechef {

    static List<Integer> res = new ArrayList<>();
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        String s1;
        String s2;

        while(t-- > 0) {
            int n = s.nextInt();

            s1 = s.next();
            s2 = s.next();

            boolean firstLine = false;
            boolean secondLine = false;

            boolean up = false;
            boolean down = false;

            int count = 0;

            for(int i=0;i<n;i++){
                if(s1.charAt(i) == '*' && s2.charAt(i) == '*'){
                    firstLine = true;
                    secondLine = true;

                    if(up || down)
                        count++;

                    up = true;
                    down  = true;
                    continue;
                }
                if(s1.charAt(i) == '*' && s2.charAt(i) == '.'){
                    firstLine = true;
                    if(up){
                        count++;
                        down = false;
                    }
                    up = true;
                    continue;
                }

                if(s1.charAt(i) == '.' && s2.charAt(i) == '*'){
                    secondLine = true;
                    if(down){
                        count++;
                        up = false;
                    }
                    down = true;
                    continue;
                }
            }

            if(firstLine && secondLine)
                count++;

            res.add(count);

    }

        for(Integer i:res ) {
            System.out.println(i);
        }
    }
}