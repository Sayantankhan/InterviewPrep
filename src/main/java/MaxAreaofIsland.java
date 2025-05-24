import java.util.*;

public class MaxAreaofIsland {

    public int maxAreaOfIsland(int[][] grid) {
        int count = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        int[] x = {1, -1, 0, 0};
        int[] y = {0, 0, 1, -1};

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {

                if(!visited[i][j] && grid[i][j] == 1) {
                    int ans = 0;
                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[]{i, j});
                    visited[i][j] = true;

                    while(!q.isEmpty()) {
                        int[] pos = q.poll();
                        ans++;
                        for(int a = 0; a < 4; a++) {
                            int[] npos = {pos[0]+x[a] , pos[1]+y[a]};
                            if(
                                    ( npos[0] >= 0 && npos[0] < grid.length) &&
                                            ( npos[1] >= 0 && npos[1] < grid[0].length) &&
                                            !visited[npos[0]][npos[1]] && grid[npos[0]][npos[1]] != 0
                            ) {
                                q.add(npos);
                                visited[npos[0]][npos[1]] = true;
                            }
                        }
                    }

                    count = Math.max(count, ans);
                }
            }
        }

        return count;
    }

    int area(int x, int y, boolean[][] visited, int[][] grid) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length ||
                visited[x][y] || grid[x][y] == 0) {
            return 0;
        }

        visited[x][y] = true;
        return 1 + area(x+1, y, visited, grid) + area(x, y+1, visited, grid) + area(x-1, y, visited, grid) + area(x, y-1, visited, grid);

    }
}
