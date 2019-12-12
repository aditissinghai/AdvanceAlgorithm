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
    static int activityNotifications(int[] expenditure,int n, int d) {
        // The history array stores expenditure of d day spendings
        // Number of times e has been spent over d days
        int[] history = new int[MAX_EXPENDITURE + 1];
        int count = 0;

        for(int i = 0; i < d; i++) {
            history[expenditure[i]] += 1;
        }

        // Queue to calculate the median for d trailing days only
        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0; i < expenditure.length; i++) {
            //To start (before d items), populate the queue and history table only
            while(i < d) {
                queue.add(expenditure[i]);
                history[expenditure[i]] += 1;
                i++;
            }

            //Get median and increase count if necessary
            float median = fetchMedian(history, d);

            if(expenditure[i] >= 2*median) {
                count++;
            }

            //Removing outgoing element from queue and history array
            int e = queue.remove();
            history[e] -= 1;

            //Add incoming element to queue and history array
            queue.add(expenditure[i]);
            history[expenditure[i]] +=1;
        }

        return count;
    }

    private static float fetchMedian(int[] history, int d) {
        if(d%2 == 1)
        {
            int mid = 0;
            for(int i=0;i<history.length;i++)
            {
                mid = mid + history[i];

                if(mid > d/2)
                {
                    return i;
                }
            }
        }else{
            int count = 0;
            int first = -1;
            int second = -1;
            for(int i=0;i<history.length;i++)
            {
                count = count + history[i];

                if(count == d/2)
                {
                    first = i;
                }else if(count > d/2)
                {
                    if(first < 0 ) first = i;
                    second = i;

                    return ((float)first + (float)second)/2;
                }
            }
        }
        return 0f;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure,n, d);

        System.out.println(result);

        scanner.close();
    }
}
