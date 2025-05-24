import java.util.LinkedList;
import java.util.*;

public class MinimumKnigntMoves {

    //Min Knight moves
    // O((max(∣x∣,∣y∣))^2)
    public int minKnightMoves(int x, int y) {
        // knight moves 8 direction
        // x,y -> x+2, y+1
        //     -> x+2, y-1
        //     -> x-2, y+1
        //     -> x-2, y-1
        //     -> x+1, y+2
        //     -> x-1, y+2
        //     -> x+1, y-2
        //     -> x-1, y-2


        // shortest path to reach x,y -> BFS
        int count = 0;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});

        // Set<int[]> visited = new HashSet<>();
        // visited.add(new int[]{0, 0});
        // changing it to bit map
        boolean[][] visited = new boolean[607][607];

        int[] x_move = {2, 2, -2, -2, 1, -1, 1, -1};
        int[] y_move = {1, -1, 1, -1, 2, 2, -2, -2};

        while(!q.isEmpty()) {
            int size = q.size();

            for(int a = 0; a < size; a++) {
                int[] pos = q.poll();
                if(pos[0] == x && pos[1] == y) return count;

                for(int i = 0; i < x_move.length; i++){
                    int[] temp_pos = {pos[0]+x_move[i], pos[1]+y_move[i]};

                    if (!visited[temp_pos[0] + 302][temp_pos[1] + 302]) {
                        q.offer(temp_pos);
                        //visited.add(temp_pos);
                        visited[temp_pos[0] + 302][temp_pos[1] + 302] = true;
                    }
                }
            }

            count++;
        }

        return -1;
    }

    public int minKnightMovesDFS(int x, int y) {
        return dfs(Math.abs(x), Math.abs(y));
    }

    private Map<String, Integer> memo = new HashMap<>();

    private int dfs(int x, int y) {
        String key = x + "," + y;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (x + y == 0) {
            return 0;
        } else if (x + y == 2) {
            return 2;
        } else {
            Integer ret = Math.min(dfs(Math.abs(x - 1), Math.abs(y - 2)),
                    dfs(Math.abs(x - 2), Math.abs(y - 1))) + 1;
            memo.put(key, ret);
            return ret;
        }
    }


}
