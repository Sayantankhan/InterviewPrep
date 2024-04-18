package arrays;

import java.util.PriorityQueue;

public class MinRectangleToCoverPoints {

    static class Point implements Comparable {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Object o) {
            Point p = (Point) o;

            if(this.x > p.x) return 1;
            else return -1;
        }
    }

    public static int minRectanglesToCoverPoints(int[][] points, int w) {
        PriorityQueue<Point> pq = new PriorityQueue();

        for(int[] point : points) {
            pq.add(new Point(point[0], point[1]));
        }

        int count = 0;
        Point p = null;
        while(!pq.isEmpty()) {
            p = pq.poll();
            count++;
            while(!pq.isEmpty() && pq.peek().x - p.x <= w) {
                pq.poll();
            }
        }

        return count ;
    }

    public static void main(String[] args) {
        System.out.println(minRectanglesToCoverPoints(new int[][]{{2,1}, {1,0}, {1,4}, {1,8}, {3,5}, {4,6}}, 1));
        System.out.println(minRectanglesToCoverPoints(new int[][]{{0,0}, {1,1}, {2,2}, {3,3}, {4,4}, {5,5}, {6, 6}}, 2));
        System.out.println(minRectanglesToCoverPoints(new int[][]{{1,1}}, 1));
    }
}
