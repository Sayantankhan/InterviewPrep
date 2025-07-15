import java.util.LinkedList;
import java.util.Queue;

public class MazeUber {
    // 490. MAZE
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {

        int m = maze.length;
        int n = maze[0].length;

        boolean[][] visited = new boolean[m][n];

        int[] x = {0, 1, 0, -1};
        int[] y = {-1, 0, 1, 0};

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{start[0], start[1]});
        visited[start[0]][start[1]] = true;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            if(cur[0] == destination[0] && cur[1] == destination[1]) return true;

            for (int i =0; i<4; i++) {
                int r = cur[0];
                int c = cur[1];
                while(r >= 0 && r < m && c >= 0 && c < n && maze[r][c] != 1) {
                    r += x[i];
                    c += y[i];
                }
                // Revert the last move to get the cell to which the ball rolls
                r -= x[i];
                c -= y[i];

                if(!visited[r][c]) {
                    q.offer(new int[]{r,c});
                    visited[r][c] = true;
                }
            }
        }

        return false;
    }
}
