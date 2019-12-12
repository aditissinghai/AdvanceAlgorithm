package IntervalSelection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {
    static List<Integer> res = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int testCases = sc.nextInt();

        for(int i = 0; i < testCases; i++) {
            int n = sc.nextInt();

            Interval[] in = new Interval[n];

            for(int idx = 0; idx < n; idx++) {
                int start = sc.nextInt();
                int end = sc.nextInt();
                in[idx] = new Interval(start, end);
            }

            Arrays.sort(in);

            int result = 1;
            int permitStart = 1;
            Interval interval = in[0];
            for (int index = 1; index < n; index++) {
                if (in[index].start >= permitStart) {
                    result++;
                    if (interval.end >= in[index].start) {
                        permitStart = interval.end + 1;
                    }
                    interval = in[index];
                }
            }

            res.add(result);
        }

        for(Integer r : res) {
            System.out.println(r);
        }

    }

    static class Interval implements Comparable<Interval> {

        public final int start, end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Interval that) {
            int c = Integer.compare(this.end, that.end);

            if (c != 0) {
                return c;
            } else {
                return Integer.compare(this.start, that.start);
            }
        }

        @Override
        public String toString() {
            return "Line{" +
                    "start =" + start +
                    ", end =" + end +
                    '}';
        }
    }

}

