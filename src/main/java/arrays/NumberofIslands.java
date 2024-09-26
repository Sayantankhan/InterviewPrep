package arrays;

import util.Utility;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/number-of-islands/description/
public class NumberofIslands {

    static class Tuple {
        int x;
        int y;

        Tuple(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int numIslands(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int count = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(!visited[i][j] && grid[i][j] == '1') {
                    count++;
                    bfs(grid, visited, i, j);
                }
            }
        }

        return count;
    }

    private static void bfs(char[][] grid, boolean[][] visited, int x, int y) {
        Queue<Tuple> queue = new LinkedList<>();

        queue.add(new Tuple(x, y));

        int y_len = visited[0].length;
        int x_len = visited.length;

        while(!queue.isEmpty()) {
            Tuple tup = queue.poll();
            visited[tup.x][tup.y] = true;
            // East (x, y+1)  || West (x, y-1) || North (x+1, y) || South (x-1, y)

            // East
            if(tup.y+1 < y_len && !visited[tup.x][tup.y+1] && grid[tup.x][tup.y+1] == '1') queue.add(new Tuple(tup.x, tup.y+1));
            // West
            if(tup.y-1 >= 0 && !visited[tup.x][tup.y-1] && grid[tup.x][tup.y-1] == '1') queue.add(new Tuple(tup.x, tup.y-1));
            // North
            if(tup.x+1 < x_len && !visited[tup.x+1][tup.y] && grid[tup.x+1][tup.y] == '1') queue.add(new Tuple(tup.x+1, tup.y));
            // South
            if(tup.x-1 >= 0 && !visited[tup.x-1][tup.y] && grid[tup.x-1][tup.y] == '1')queue.add(new Tuple(tup.x-1, tup.y));
        }
    }

    public static int numIslands2(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int count = 0;
        boolean[][] visited = new boolean[n][m];
        Queue<Tuple> queue = new LinkedList<Tuple>();

        int x[] = {1, -1, 0, 0};
        int y[] = {0, 0, 1, -1};

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++) {
                if(!visited[i][j] && grid[i][j] == '1') {
                    queue.offer(new Tuple(i, j));
                    count++;
                    while(!queue.isEmpty()) {

                        Tuple t = queue.poll();
                        visited[t.x][t.y] = true;

                        for(int a = 0; a < x.length; a++) {

                            if((t.x + x[a] >= 0 && t.x + x[a] < n)
                                    && (t.y + y[a] >= 0 && t.y + y[a] < m)
                                    && !visited[t.x+x[a]][t.y+y[a]]
                                    && grid[t.x+x[a]][t.y+y[a]] == '1') {

                                queue.offer(new Tuple(t.x+x[a],  t.y + y[a]));
                            }
                        }

                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) throws Exception {
        char[][] grid = new char[][]{
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };
        Utility.assertTrue(numIslands(grid), 1);

        grid = new char[][]{
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        Utility.assertTrue(numIslands2(grid), 3);
    }
}
