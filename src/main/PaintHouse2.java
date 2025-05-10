public class PaintHouse2 {

    // DP - O(m.k)
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

    // Rec - O(k^n) : At each house (total n levels of recursion), you loop over up to k - 1 colors.
    int minCostRec(int[][] costs, int index, int exclude){

        if(index == costs.length) return 0;

        int smallest = Integer.MAX_VALUE;
        for(int i = 0; i < costs[index].length; i++) {
            if(i != exclude) {
                int cost = costs[index][i] + minCostRec(costs, index+1, i);
                smallest = Math.min(smallest, cost);
            }
        }

        return smallest;
    }
}
