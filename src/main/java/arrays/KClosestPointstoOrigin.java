package arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KClosestPointstoOrigin {

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
            Double distance1 = getDistance(new Point(0, 0));
            Double distance2 = p.getDistance(new Point(0, 0));
            return distance2.compareTo(distance1);
        }

        public Double getDistance(Point o) {
            return Math.sqrt(Math.pow((this.x - o.x), 2) + Math.pow((this.y - o.y), 2));
        }
    }

    public static int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Point> pq = new PriorityQueue<>();

        for(int[] point: points) {
            Point po = new Point(point[0],point[1]);
            if(pq.size() < k) pq.add(po);
            else {
                Point temp = pq.peek();
                if(temp.compareTo(po) != 1) {
                    pq.poll();
                    pq.add(po);
                }
            }
        }

        List<Point> list = new ArrayList<>();

        while(!pq.isEmpty()) {
            list.add(pq.poll());
        }

        int[][] ans = new int[list.size()][2];
        int index = 0;
        for(Point p : list) {
            ans[index++] = new int[]{p.x, p.y};
        }

        return ans;
    }

    public static void main(String[] args) {
        //[[3,3],[5,-1],[-2,4]], k = 2
        System.out.println(kClosest(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2));
    }
}
