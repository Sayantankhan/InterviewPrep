import java.util.Arrays;

public class PaintHouse {

    //paint houses with k colours
    public int minCostII(int[][] costs) {
        // return minCostRec(costs, 0, -1);
        int k = costs[0].length;
        int[] dp = new int[k];

        for(int i = 0; i < costs.length; i++) {
            int[] currDp = new int[k];
            for (int j = 0; j < k; j++) {
                currDp[j] = costs[i][j] + findMin(dp, j);
            }
            dp = currDp;
        }
        return Arrays.stream(dp).min().getAsInt();
    }

    int findMin(int[] dp, int exclude) {
        int smallest = Integer.MAX_VALUE;
        for (int i = 0; i < dp.length; i++) {
            if(i != exclude) {
                smallest = Math.min(smallest, dp[i]);
            }
        }
        return smallest;
    }


    // min cost paint house - colour1, colour2, colour3
    // paint houses with 3 colours

    public int minCost(int[][] costs) {
        // int[][] dp = new int[costs.length+1][costs[0].length+1];
        // return minCostRec(costs, 0, -1);

        // As there are 3 colours only
        int[] dp = {0, 0, 0};
        for(int i = 0; i < costs.length; i++) {
            int dp0 = costs[i][0] + Math.min(dp[1], dp[2]); // choosing the first colour and then checking min of other
            int dp1 = costs[i][1] + Math.min(dp[0], dp[2]);
            int dp2 = costs[i][2] + Math.min(dp[0], dp[1]);

            dp[0] = dp0;
            dp[1] = dp1;
            dp[2] = dp2;
        }

        return Arrays.stream(dp).min().getAsInt();
    }

    int minCostRec(int[][] costs, int index, int exclude) {
        if(index == costs.length) {
            return 0;
        }

        int smallest = Integer.MAX_VALUE;
        for(int i = 0; i < costs[index].length; i++){
            if (exclude != i) {
                int c = minCostRec(costs, index+1, i);
                int cost = costs[index][i] + c;
                smallest = Math.min(smallest, cost);
            }
        }

        return smallest;

    }
}
