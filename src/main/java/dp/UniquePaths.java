package dp;

import util.Utility;

import java.util.LinkedList;
import java.util.Queue;

/*
There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 */

// https://leetcode.com/problems/unique-paths/description/
public class UniquePaths {

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            Point p = (Point) obj;
            return (p.x == this.x) && (p.y == this.y);
        }
    }
    public static int uniquePaths(int m, int n) {

        Point source = new Point(0, 0);
        Point desination = new Point(m-1, n-1);

        // dfs
        // return uniquePathsTraversingDFS(source, desination, m, n);

        // dp
        return uniquePathsTraversingDP(m, n);
    }

    private static int uniquePathsTraversingDFS(Point source, Point desination, int x, int y) {

        if(source.y >= y) return 0;
        if(source.x >= x) return 0;

        if(source.equals(desination)) {
            return 1;
        }

        int right = uniquePathsTraversingDFS(new Point(source.x+1, source.y), desination, x, y);
        int down = uniquePathsTraversingDFS(new Point(source.x, source.y+1), desination, x, y);

        return right + down;
    }

    private static int uniquePathsTraversingDP(int m, int n) {
        int[][] dp = new int[m][n];

        for(int i = n-1; i >= 0; i--) {
            dp[m-1][i] = 1;
        }

        for(int i = m-1; i >= 0; i--) {
            dp[i][n-1] = 1;
        }

        for(int i = m-2; i >= 0; i--) {
            for(int j = n-2; j >= 0; j--) {
                dp[i][j] = dp[i+1][j] + dp[i][j+1];
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(uniquePaths(3, 2), 3);
        Utility.assertTrue(uniquePaths(3, 7), 28);
        Utility.assertTrue(uniquePaths(23, 12), 193536720);
    }
}
