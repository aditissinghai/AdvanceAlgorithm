package FradulentActivity;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int MAX_EXPENDITURE = 200;
    // Complete the activityNotifications function below.
    static int activityNotifications(int n, int d) {
        // The history array stores expenditure of d day spendings
        // Number of times e has been spent over d days
        int[] history = new int[MAX_EXPENDITURE + 1];
        int count = 0;

        // Queue to calculate the median for d trailing days only
        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0; i < d; i++) {
            int newTrans = scanner.nextInt();
            history[newTrans] = history[newTrans] +  1;
            queue.offer(newTrans);
        }

        for(int i = 0; i < n-d; i++) {

            int newTrans = scanner.nextInt();

            //Get median and increase count if necessary
            float median = fetchMedian(history, d);

            if(newTrans >= 2*median) {
                count++;
            }

            //Removing outgoing element from queue and history array
            int e = queue.poll();
            history[e] = history[e] - 1;

            //Add incoming element to queue and history array
            queue.add(newTrans);
            history[newTrans] = history[newTrans] + 1;
        }

        return count;
    }

    private static float fetchMedian(int[] history, int d) {
        int index = 0;

        if(d%2 == 0) {

            int counter = (d/2);

            while(counter > 0) {
                counter -= history[index];
                index++;
            }
            index--;
            if(counter <= -1) {
                return index;
            }
            else {
                int first = index;
                int second = index + 1;
                while (history[second] == 0) {
                    second++;
                }
                return (float)(first + second) / 2;
            }
        }else {
            int counter = d/2;
            while(counter >= 0) {
                counter -= history[index];
                index++;
            }
            return (float)index - 1;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int result = activityNotifications(n, d);

        System.out.println(result);

        scanner.close();
    }
}
