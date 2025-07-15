import java.util.*;

public class MazeShortestDistance {
    // Maze Shortest Distance
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;
        int[][] distance = new int[m][n];
        for(int[] row: distance)
            Arrays.fill(row, Integer.MAX_VALUE);

        int[] x = {0, 1, 0, -1};
        int[] y = {-1, 0, 1, 0};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        distance[start[0]][start[1]] = 0;

        while(!queue.isEmpty()) {

            int[] s = queue.remove();
            for(int i = 0; i<4; i++) {

                int r = s[0] + x[i];
                int c = s[1] + y[i];
                int count = 0;
                while(r >= 0 && r < m && c >= 0 && c < n && maze[r][c] == 0) {
                    r += x[i];
                    c += y[i];
                    count++;
                }

                if(distance[s[0]][s[1]] + count < distance[r - x[i]][c - y[i]]) {
                    distance[r - x[i]][c - y[i]] = distance[s[0]][s[1]] + count;
                    queue.offer(new int[]{r - x[i], c - y[i]});
                }

            }
        }

        return distance[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : distance[destination[0]][destination[1]];
    }

    public static void main(String[] args) {
        System.out.println(
                new MazeShortestDistance().shortestDistance(new int[][] {{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}}, new int[]{0,4}, new int[]{4,4}));
    }
}
