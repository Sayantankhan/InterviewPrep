package company;

import util.Utility;

import java.util.*;

// https://leetcode.com/problems/minimum-knight-moves/
// https://leetcode.com/problems/escape-a-large-maze
public class Google_ChessKnightProblem {

    static class Tuple {
        int x;
        int y;
        int level;

        Tuple(int x, int y, int level) {
            this.x = x;
            this.y = y;
            this.level = level;
        }

        public boolean equals(Object o) {
            Tuple t = (Tuple) o;
            return ((t.x == this.x) && (t.y == this.y));
        }
    }

    public static int minStepToReachTarget(int knightpos[], int targetpos[], int N) {
        // A knight goes by 2 moves in one direction and another in 1 direction
        // From a position knight can go 8 diff  direction
        // (x, y)  -> {(x+2, y+1), (x+2, y-1), (x-2, y+1), (x-2, y-1), (x+1, y+2), (x-1, y+2), (x+1, y-2), (x-1, y-2)}

        int[] x = {2, 2, -2, -2, 1, 1, -1, -1};
        int[] y = {1, -1, 1, -1, 2, -2, 2, -2};

        boolean[][] visited = new boolean[N][N];

        Queue<Tuple> q = new LinkedList<>();
        q.add(new Tuple(knightpos[0], knightpos[1], 0));
        visited[knightpos[0]][knightpos[1]] = true;

        while(!q.isEmpty()) {
            Tuple t = q.poll();

            if(t.x == targetpos[0] && t.y == targetpos[1]) {
                return t.level;
            }

            for(int i = 0; i < x.length; i++) {
                int tx = t.x + x[i];
                int ty = t.y + y[i];

                if(tx < N && tx >= 0 && ty < N && ty >= 0
                        && !visited[tx][ty]) {

                    Tuple tup = new Tuple(tx, ty, t.level + 1);
                    visited[tx][ty] = true;
                    q.add(tup);
                }
            }

        }
        return Integer.MAX_VALUE;
    }
    
    public static int minStepToReachTarget_bidirectionalBFS(int knightpos[], int targetpos[], int N) {
        int[] x = {2, 2, -2, -2, 1, 1, -1, -1};
        int[] y = {1, -1, 1, -1, 2, -2, 2, -2};

        Queue<Tuple> front = new LinkedList<>();
        Queue<Tuple> back = new LinkedList<>();

        Set<Tuple> frontSet = new HashSet<>();
        Set<Tuple> backSet = new HashSet<>();

        front.add(new Tuple(knightpos[0], knightpos[1], 0));
        frontSet.add(new Tuple(knightpos[0], knightpos[1], 0));

        back.add(new Tuple(targetpos[0], targetpos[1], 7));
        backSet.add(new Tuple(targetpos[0], targetpos[1], 7));

        while(!front.isEmpty() || !back.isEmpty()) {
            Tuple el = front.poll();

            for(int i = 0; i < x.length; i++) {
                int tx = el.x + x[i];
                int ty = el.y + y[i];

                if(tx < N && tx >= 0 && ty < N && ty >= 0
                        && !frontSet.contains(new Tuple(tx, ty, el.level + 1))) {

                    Tuple tup = new Tuple(tx, ty, el.level + 1);
                    int v = findIndexInSet(backSet, tup);
                    if(v >= 0) {
                        System.out.println("Back Path Found " + tup.level + " - " + v + " =" + (tup.level + v));
                        return tup.level + v - 1;
                    }
                    front.add(tup);
                    frontSet.add(tup);
                }
            }

            Tuple t = back.poll();
            for(int i = 0; i < x.length; i++) {
                int tx = t.x + x[i];
                int ty = t.y + y[i];

                if(tx < N && tx >= 0 && ty < N && ty >= 0
                        && !backSet.contains(new Tuple(tx, ty, t.level - 1))) {

                    Tuple tup = new Tuple(tx, ty, t.level - 1);
                    int v = findIndexInSet(frontSet, tup);
                    if(v >= 0) {
                        System.out.println("Front Path Found " + tup.level + " - " + v + " = " + (tup.level + v));
                        return tup.level + v - 1;
                    }

                    back.add(tup);
                    backSet.add(tup);
                }
            }
        }

        return 0;
    }

    private static int findIndexInSet(Set<Tuple> set, Tuple tup) {
        for(Tuple t : set) {
            if(t.equals(tup)) {
                return t.level;
            }
        }
        return -1;
    }


    public static void main(String[] args) throws Exception {
        Utility.assertTrue(minStepToReachTarget(new int[]{0, 0}, new int[]{7,7}, 8), 6);
    }
}
