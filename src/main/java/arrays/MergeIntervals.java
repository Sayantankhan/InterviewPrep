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
        System.out.println(mergeIntervals(new ArrayList<>(){{
            add(new Interval(2, 3));
            add(new Interval(2, 2));
            add(new Interval(3, 3));
            add(new Interval(1, 3));
            add(new Interval(5, 7));
            add(new Interval(2, 2));
            add(new Interval(4, 6));
        }}));
    }


    static class Interval {
        int start;
        int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static ArrayList<Interval> mergeIntervals(ArrayList<Interval> intervals) {
        // Write your code here
        Collections.sort(intervals, (i1, i2) -> i1.start - i2.start);
        ArrayList<Interval> result = new ArrayList<>();
        Stack<Interval> stack = new Stack<>();

        for(Interval interval : intervals) {

            Interval temp = stack.isEmpty() ? null :
                    stack.peek();

            // overlap check
            if(temp != null && temp.start <= interval.start &&
                    temp.end >= interval.start) {

                if(temp.end < interval.end) {
                    stack.pop();
                    temp.end = interval.end;
                    stack.push(temp);
                }
            } else {
                stack.push(interval);
            }
        }

        while(!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }
}
