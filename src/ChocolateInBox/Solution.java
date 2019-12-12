package ChocolateInBox;

import java.io.IOException;
import java.util.Scanner;

public class Solution {

    /*
     * Complete the chocolateInBox function below.
     */
    static int chocolateInBox(int[] arr) {
        /*
         * Write your code here.
         */

        int val = 0;
        int ans = 0;

        for(int i = 0; i < arr.length; i++) {
            val ^= arr[i];
        }

        for(int i = 0; i < arr.length; i++) {
            if((val^arr[i]) < arr[i])
                ans++;
        }

        return ans;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int arrCount = Integer.parseInt(scanner.nextLine().trim());

        int[] arr = new int[arrCount];

        String[] arrItems = scanner.nextLine().split(" ");

        for (int arrItr = 0; arrItr < arrCount; arrItr++) {
            int arrItem = Integer.parseInt(arrItems[arrItr].trim());
            arr[arrItr] = arrItem;
        }

        int result = chocolateInBox(arr);
        System.out.println(result);
    }
}
