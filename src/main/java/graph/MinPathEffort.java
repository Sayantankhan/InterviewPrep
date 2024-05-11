package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/path-with-minimum-effort/description/
public class MinPathEffort {

    // BFS or DFS ; SC -> O(V+E) || BFS takes more space than DFS
    public static int minimumEffortPath(int[][] heights) {
        Tuple source = new Tuple(0, 0, 0);
        Tuple destination = new Tuple(heights.length-1, heights[0].length-1, Integer.MAX_VALUE);

        int[][] dp = new int[heights.length][heights[0].length];
        Arrays.stream(dp).forEach(arr -> Arrays.fill(arr, Integer.MAX_VALUE));
        dp[0][0] = 0;

         minimumEffortPathUsingDFS(heights, source, destination, dp);
         return destination.maxEffortTillNow;

//        int[][] dp = new int[heights.length][heights[0].length];
//        minimumEffortPathUsingBFS(heights, source, destination, dp);
//        return dp[destination.x][destination.y];
    }

    private static void minimumEffortPathUsingBFS(int[][] heights, Tuple source, Tuple destination, int[][] dp) {
        Queue<Tuple> q = new LinkedList<>();

        // Initialize
        Arrays.stream(dp).forEach(arr -> Arrays.fill(arr, Integer.MAX_VALUE));

        q.add(source);
        dp[0][0] = 0;
        int[] xmoves = {-1, 1, 0, 0};
        int[] ymoves = {0, 0, -1, 1};

        while(!q.isEmpty()) {
            Tuple t = q.poll();

            for(int i = 0; i < xmoves.length; i++) {
                if((t.x + xmoves[i] >= 0 && t.x + xmoves[i] < heights.length) &&
                        (t.y + ymoves[i] >= 0 && t.y + ymoves[i] < heights[0].length)) {


                    int dist = Math.max(t.maxEffortTillNow, Math.abs(heights[t.x + xmoves[i]][t.y + ymoves[i]] - heights[t.x][t.y]));

                    if(dist < dp[t.x + xmoves[i]][t.y + ymoves[i]]) {
                        dp[t.x + xmoves[i]][t.y + ymoves[i]] = dist;
                        q.add(new Tuple(t.x + xmoves[i], t.y + ymoves[i], dist));
                    }
                }
            }
        }
    }

    static class Tuple {
        int x;
        int y;
        int maxEffortTillNow;

        Tuple(int x, int y, int maxEffortTillNow) {
            this.x = x;
            this.y = y;
            this.maxEffortTillNow = maxEffortTillNow;
        }

        public boolean equals(Tuple o) {
            return this.x == o.x && this.y == o.y;
        }
    }
    private static void minimumEffortPathUsingDFS(int[][] heights, Tuple source, Tuple destination, int[][] dp) {
        if(source.equals(destination)) {
            destination.maxEffortTillNow = Math.min(destination.maxEffortTillNow, source.maxEffortTillNow);
            return;
        }

        // up
        if(source.x-1 >= 0 ) {
            int dist = Math.max(source.maxEffortTillNow,
                    Math.abs(heights[source.x-1][source.y] - heights[source.x][source.y]));
            if(dist < dp[source.x-1][source.y]) {
                dp[source.x-1][source.y] = dist;
                minimumEffortPathUsingDFS(heights, new Tuple(source.x-1, source.y, dist), destination, dp);
            }
        }

        // down
        if(source.x+1 < heights.length){
            int dist = Math.max(source.maxEffortTillNow,
                    Math.abs(heights[source.x+1][source.y] - heights[source.x][source.y]));

            if(dist < dp[source.x+1][source.y]) {
                dp[source.x+1][source.y] = dist;
                minimumEffortPathUsingDFS(heights, new Tuple(source.x+1, source.y, dist), destination, dp);
            }
        }

        // left
        if(source.y-1 >= 0) {
            int dist = Math.max(source.maxEffortTillNow,
                    Math.abs(heights[source.x][source.y-1] - heights[source.x][source.y]));
            if(dist < dp[source.x][source.y-1]) {
                dp[source.x][source.y-1] = dist;
                minimumEffortPathUsingDFS(heights, new Tuple(source.x, source.y-1, dist), destination, dp);
            }
        }

        // right
        if(source.y+1 < heights[0].length) {
            int dist = Math.max(source.maxEffortTillNow,
                    Math.abs(heights[source.x][source.y+1] - heights[source.x][source.y]));
            if(dist < dp[source.x][source.y+1]) {
                dp[source.x][source.y+1] = dist;
                minimumEffortPathUsingDFS(heights, new Tuple(source.x, source.y+1, dist), destination, dp);
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(minimumEffortPath(new int[][]{{1,2,2}, {3,8,2}, {5,3,5}}));
        System.out.println(minimumEffortPath(new int[][]{{1,2,1,1,1}, {1,2,1,2,1}, {1,2,1,2,1}, {1,2,1,2,1}, {1,1,1,2,1}}));
        System.out.println(minimumEffortPath(new int[][]{
                {8,3,2,5,2,10,7,1,8,9},
                {1,4,9,1,10,2,4,10,3,5},
                {4,10,10,3,6,1,3,9,8,8},
                {4,4,6,10,10,10,2,10,8,8},
                {9,10,2,4,1,2,2,6,5,7},
                {2,9,2,6,1,4,7,6,10,9},
                {8,8,2,10,8,2,3,9,5,3},
                {2,10,9,3,5,1,7,4,5,6},
                {2,3,9,2,5,10,2,7,1,8},
                {9,10,4,10,7,4,9,3,1,6}
        }));
    }
}
