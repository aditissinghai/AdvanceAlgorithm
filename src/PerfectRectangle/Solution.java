package PerfectRectangle;

import java.util.Arrays;
import java.util.TreeSet;

class Rectangle implements Comparable<Rectangle> {
    public int x1, x2, y1, y2;

    boolean isEnd;

    Rectangle(int[] rectangle, boolean end) {
        y2 = rectangle[3];
        y1 = rectangle[1];
        x1 = rectangle[0];
        x2 = rectangle[2];
        isEnd = end;
    }
    public int compareTo(Rectangle b) {
        if (this.y2 <= b.y1) {
            return -1;
        } else if (this.y1 >= b.y2) {
            return 1;
        } else return 0;
    }
}

class Solution {

    public static void main(String[] args) {

        int[][] rectangles = {
                {1, 1, 3, 3},
                {3, 1, 4, 2},
                {3, 2, 4, 4},
                {1, 3, 2, 4},
                {2, 3, 3, 4}};

        Solution s = new Solution();
        System.out.println(s.isRectangleCover(rectangles));

    }

    public boolean isRectangleCover(int[][] rectangles) {
        int top = Integer.MIN_VALUE;
        int bottom = Integer.MAX_VALUE;

        Rectangle[] intervals = new Rectangle[rectangles.length * 2];

        for (int i = 0; i < rectangles.length * 2; i += 2) {
            intervals[i] = new Rectangle(rectangles[i / 2], false);
            intervals[i + 1] = new Rectangle(rectangles[i / 2], true);
            top = Math.max(top, intervals[i].y2);
            bottom = Math.min(bottom, intervals[i + 1].y1);
        }

        Arrays.sort(intervals, (Rectangle a, Rectangle b) -> {
            int valA = a.isEnd ? a.x2 : a.x1;
            int valB = b.isEnd ? b.x2 : b.x1;
            if (valA == valB) {
                return a.x1 - b.x1;
            }
            return valA - valB;
        });

        TreeSet<Rectangle> treeSet = new TreeSet<>();

        int position = intervals[0].x1;
        int height = 0;

        for (Rectangle rect : intervals) {
            if (rect.isEnd) {
                if (rect.x2 != position && height != (top - bottom)) {
                    return false;
                }
                treeSet.remove(rect);
                position = rect.x2;
                height = height - (rect.y2 - rect.y1);
            } else {
                if (rect.x1 != position || !treeSet.add(rect)) return false;
                position = rect.x1;
                height += rect.y2 - rect.y1;
            }
        }
        return true;
    }
}
