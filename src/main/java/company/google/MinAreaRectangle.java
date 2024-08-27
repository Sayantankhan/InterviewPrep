package company.google;

import org.apache.commons.math3.analysis.function.Min;

import java.util.*;

public class MinAreaRectangle {

    class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            Point p1 = (Point)o;
            return p1.x == this.x && p1.y == this.y;
        }

        public int hashCode(){
            return x ^ y;
        }
    }

    public int minAreaRect(int[][] points) {
        int min = Integer.MAX_VALUE;

        HashSet<Point> set = new HashSet<>();

        for(int[] point : points) {
            if(set.size() < 3) {
                set.add(new Point(point[0], point[1]));
            } else {
                for(Point p : set) {
                    if(set.contains(new Point(point[0], p.y)) && set.contains(new Point(p.x, point[1]))) {
                        int area = Math.abs(point[0] - p.x) * Math.abs(point[1] - p.y);
                        min = Math.min(min, area);
                    }
                }
                set.add(new Point(point[0], point[1]));
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public static void main(String[] args) {
        System.out.println(new MinAreaRectangle().minAreaRect(new int[][]{
                {1,1},
                {1,3},
                {3,1},
                {3,3},
                {2,2}
        }));
    }
}
