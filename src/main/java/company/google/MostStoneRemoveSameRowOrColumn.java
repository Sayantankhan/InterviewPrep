package company.google;

import java.util.Arrays;

//https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
public class MostStoneRemoveSameRowOrColumn {

    public int removeStones(int[][] stones) {
        // Arrays.sort(stones, (a, b) -> a[0]!= b[0] ? a[0]-b[0] : a[1]-b[1]);
        int length = 0;
        boolean[] visited = new boolean[stones.length];

        for(int i = 0; i < stones.length; i++) {
            visited[i] = true;
            length = length + findRemoveStone(stones, i, visited);
            // Arrays.fill(visited, false);
        }
        return length;
    }

    int findRemoveStone(int[][] stones, int index, boolean[] visited) {
        int max = 0;
        for(int i = 0; i < visited.length; i++) {
            if(!visited[i]) {
                int[] stone = stones[index];
                int[] temp = stones[i];
                if(stone[0] == temp[0] || stone[1] == temp[1]) {
                    visited[i] = true;
                    max += 1 + findRemoveStone(stones, i, visited);
                    // visited[i] = false;
                }
            }
        }

        return max;
    }

    public static void main(String[] args) {
        MostStoneRemoveSameRowOrColumn msr = new MostStoneRemoveSameRowOrColumn();


//        System.out.println(msr.removeStones(new int[][]{
//                {4,0},
//                {3,2},
//                {3,1},
//                {4,4},
//                {1,1},
//                {0,2}
//        }));

        System.out.println(msr.removeStones(new int[][]{
                {0,0},
                {0,1},
                {1,0},
                {1,1},
                {2,1},
                {2,2},
                {3,2},
                {3,3},
                {3,4},
                {4,3},
                {4,4}
        }));
    }

}
