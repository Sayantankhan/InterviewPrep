package company.google;

import design.patterns.Build;

import java.util.*;

public class SkyLineProblem {

    // mark start and end point of each building {split based on start and end} - and + to select the range
    // mark consecutive building = {sort tuple from first step}
    // we need to get max y value of each x axis value - {Heap}
    // everytime height flips we have an answer - maintain prev to compare

    class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        List<Point> points = new ArrayList<>();

        // mark start and end point of each building {split based on start and end} - and + to select the range
        for(int[] building: buildings) {
            points.add(new Point(building[0], -building[2]));
            points.add(new Point(building[1], building[2]));
        }

        // mark consecutive building = {sort tuple from first step}
        Collections.sort(points, (p1, p2) -> {
            if(p1.x != p2.x) return p1.x-p2.x;
            else return p1.y - p2.y;
        });

        // we need to get max y value of each x axis value - {Heap}
        PriorityQueue<Integer> pqHeight = new PriorityQueue<Integer>((p1, p2) -> p2 - p1);
        pqHeight.offer(0);
        int prev = 0;
        for(Point p : points) {
            if(p.y < 0) pqHeight.offer(-p.y);
            else pqHeight.remove(p.y);

            int currHeight = pqHeight.peek();
            // everytime height flips we have an answer - maintain prev to compare
            if(prev != currHeight) {
                result.add(List.of(p.x, currHeight));
                prev = currHeight;
            }

        }
        return result;
    }

    public static void main(String[] args) {
        int[][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
//        System.out.println(new SkyLineProblem().getSkyline(buildings));
        System.out.println("+++++++ =========== ++++++++");
        buildings = new int[][]{{0,2,3},{2,5,3}};
        System.out.println(new SkyLineProblem().getSkyline(buildings));
    }

}
