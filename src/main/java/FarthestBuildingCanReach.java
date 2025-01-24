import java.util.*;

public class FarthestBuildingCanReach {

    public int furthestBuilding(int[] heights, int bricks, int ladders) {

        int[] diff = new int[heights.length];
        int[] dp = new int[heights.length];

        Arrays.fill(dp, -1);

        diff[0] = 0;
        for(int i = 1; i < heights.length; i++) {
            if(heights[i-1] < heights[i]) diff[i] = (heights[i] - heights[i-1]);
            else diff[i] = 0;
        }

        Arrays.stream(diff).forEach(System.out::print);
        return furthestBuildingRec(diff, bricks, ladders, 0, dp);
    }

    int furthestBuildingRec(int[] diff, int bricks, int laddders, int index, int[] dp) {

        if(index == diff.length) {
            return index-1;
        }

        if(diff[index] > bricks && laddders == 0) {
            return index-1;
        }

        //if(dp[index] != -1) return dp[index];

        if(diff[index] != 0) {
            int index_with_bricks = -1;
            int index_with_ladders = -1;
            if(bricks >= diff[index]) {
                index_with_bricks = furthestBuildingRec(diff, bricks-diff[index], laddders, index+1, dp);
            }
            if(laddders > 0) {
                index_with_ladders = furthestBuildingRec(diff, bricks, laddders-1, index+1, dp);
            }

            return Math.max(index_with_ladders, index_with_bricks);
            // return dp[index];
        } else {
            return furthestBuildingRec(diff, bricks, laddders, index+1, dp);
        }
    }
}
