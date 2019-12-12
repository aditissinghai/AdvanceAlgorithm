package InsertionSortAdvancedAnalysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {
    static List<Long> ans = new ArrayList<>();
    static long count = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();

        for(int i = 0; i < testCases; i++) {
            int[] test = readTestCase(sc);
            mergeSort(test);
            ans.add(count);
            count = 0;
        }

        for(Long res : ans) {
            System.out.println(res);
        }
    }

    private static int[] mergeSort(int[] test) {

        if(test.length == 1) {
            return test;
        } else {
            int[] left = new int[test.length / 2];
            System.arraycopy(test, 0, left, 0, left.length);

            int[] right = new int[test.length - left.length];
            System.arraycopy(test, left.length, right, 0, right.length);

            left = mergeSort(left);
            right = mergeSort(right);

            // Merge the results back together.
            merge(left, right, test);

            return test;
        }

        /*if(start < end) {
            int mid = (end + start)/2;

            mergeSort(test, start, mid);
            mergeSort(test, mid+1, end);

            merge(test, start, mid, end);
        } else {
            return 0l;
        }
*/
//        return count;
    }

    public static void merge(int left[], int[] right, int[] test) {
        int l = 0;
        int r = 0;
        int w = 0;

        while (l < left.length && r < right.length) {
            if (left[l] <= right[r]) {
                test[w] = left[l];
                l++;
            } else {
                test[w] = right[r];
                r++;
                count += left.length - l;
            }
            w++;
        }

        int[] rest;
        int restIndex;
        if (l >= left.length) {
            // The left array has been use up...
            rest = right;
            restIndex = r;
        } else {
            // The right array has been used up...
            rest = left;
            restIndex = l;
        }

        // Copy the rest of whichever array (left or right) was
        // not used up.
        for (int i = restIndex; i < rest.length; i++) {
            test[w] = rest[i];
            w++;
        }

    }

    private static int[] readTestCase(Scanner sc) {
        int n = sc.nextInt();
        int[] arr = new int[n];

        for(int idx = 0; idx < n; idx++ ) {
            arr[idx] = sc.nextInt();
        }
        return arr;
    }
}
