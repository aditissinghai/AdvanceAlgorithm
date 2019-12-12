package ArithmeticExpression;

import java.io.*;
import java.util.*;

public class Solution {

    public static String res = "";

    // Complete the arithmeticExpressions function below.
    static boolean arithmeticExpressions(int[] arr, int start, long total) {
        if(start == arr.length ) {
            return (total % 101 == 0);
        }

        if(total < 100000 && total >= 0 && arithmeticExpressions(arr, start + 1, total * arr[start])) {
            res = "*" + arr[start] + res;
            return true;
        }
        if (total >= 0 && arithmeticExpressions(arr, start + 1, total + arr[start])) {
            res = "+" + arr[start] + res;
            return true;
        }
        if (total >= 0 && arithmeticExpressions(arr, start + 1, total - arr[start])) {
            res = "-" + arr[start] + res;
            return true;
        }
        return false;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        if(arithmeticExpressions(arr, 1, arr[0])) {
            System.out.println(arr[0] + res);
        }

//        bufferedWriter.close();

        scanner.close();
    }
}
