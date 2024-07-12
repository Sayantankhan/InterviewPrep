package company;

import java.util.*;

public class PacificAtlanticWaterFlow {

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int y_len = heights.length;
        int x_len = heights[0].length;

        List<List<Integer>> result = new ArrayList<>();

        boolean[][] pacificMap = new boolean[y_len][x_len];
        boolean[][] atlanticMap = new boolean[y_len][x_len];


        for(int i = 0; i < x_len; i++) {
            // all the flow can come towards pacific - x axis
            if(!pacificMap[0][i]) {
                dfs(heights, pacificMap, 0, i);
            }
            // all the flow can come towards atlantic - x axis
            if(!atlanticMap[y_len-1][i]) {
                dfs(heights, atlanticMap, y_len-1, i);
            }
        }

        for(int i = 0; i < y_len; i++) {
            // all the flow can come towards pacific - y axis
            if(!pacificMap[i][0]) {
                dfs(heights, pacificMap, i, 0);
            }
            // all the flow can come towards atlantic - y axis
            if(!atlanticMap[i][x_len-1]) {
                dfs(heights, atlanticMap, i, x_len-1);
            }
        }

        for(int i = 0; i < y_len; i++) {
            for (int j = 0; j < x_len; j++) {
                if(pacificMap[i][j] && atlanticMap[i][j]) {
                    result.add(List.of(i, j));
                }
            }
        }

        return result;
    }

    public void dfs(int[][] heights, boolean[][] visited, int i, int j) {
        int y_len = heights.length;
        int x_len = heights[0].length;

        if(i < 0 || j < 0 || i >= y_len || j >= x_len ) return ;

        visited[i][j] = true;

        int[] y = {0, -1, 0, 1};
        int[] x = {-1, 0, 1, 0};

        for(int d = 0; d < x.length; d++) {
            int y_axis = i + y[d];
            int x_axis = j + x[d];

            if(y_axis < 0 || x_axis < 0 || y_axis >= y_len || x_axis >= x_len ) continue;

            if(!visited[y_axis][x_axis] &&
                    heights[i][j] <= heights[y_axis][x_axis]) {
                dfs(heights, visited, y_axis, x_axis);
            }
        }
    }


    public static void main(String[] args) {
        int[][] matrix = new int[][] {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        System.out.println(new PacificAtlanticWaterFlow().pacificAtlantic(matrix));
    }

}
