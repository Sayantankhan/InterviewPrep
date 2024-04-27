package arrays;

import util.Utility;

import java.util.*;

//https://leetcode.com/problems/merge-intervals/description/
public class MergeIntervals {

    static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        Stack<Point> stack = new Stack<>();
        stack.add(new Point(intervals[0][0], intervals[0][1]));
        for(int i = 1; i < intervals.length; i++) {
            Point p = stack.peek();
            if(!stack.isEmpty() && intervals[i][0] >= p.x && intervals[i][0] <= p.y) {
                stack.pop();
                stack.add(new Point(p.x, Math.max(p.y, intervals[i][1])));
            } else {
                stack.add(new Point(intervals[i][0], intervals[i][1]));
            }
        }

        int size = stack.size();
        int[][] ans = new int[size][2];
        while(!stack.isEmpty()) {
            Point p = stack.pop();
            ans[size-1] = new int[]{p.x, p.y};
            size--;
        }

        return ans;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(merge(new int[][]{{1,3},{2,6},{8,10},{15,18}})); //  new int[][]{{1,6},{8,10},{15,18}});
    }
}
