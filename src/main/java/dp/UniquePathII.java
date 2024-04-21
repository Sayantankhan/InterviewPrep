package dp;

import jdk.jshell.execution.Util;
import util.Utility;

public class UniquePathII {

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dpgrid = new int[obstacleGrid.length][obstacleGrid[0].length];

        if(obstacleGrid[m-1][n-1] == 0) dpgrid[m-1][n-1] = 1;

        for(int i = m-2; i >= 0; i--) {
            if(obstacleGrid[i][n-1] != 1) {
                dpgrid[i][n - 1] = dpgrid[i + 1][n - 1];
            }
        }
        for(int i = n-2; i >= 0; i--) {
            if(obstacleGrid[m-1][i] != 1) {
                dpgrid[m-1][i] = dpgrid[m-1][i+1];
            }
        }

        for( int i = m-2; i >= 0; i--) {
            for( int j = n-2; j >= 0; j--) {
                if(obstacleGrid[i][j] == 1)
                    dpgrid[i][j] = 0;
                else {
                    dpgrid[i][j] = dpgrid[i+1][j] + dpgrid[i][j+1];
                }
            }
        }

        return dpgrid[0][0];
    }

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(uniquePathsWithObstacles(new int[][]{{0,0,0}, {0,1,0}, {0,0,0}}), 2);
        Utility.assertTrue(uniquePathsWithObstacles(new int[][]{{0,1}, {0,0}}), 1);
    }
}
