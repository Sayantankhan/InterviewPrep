package arrays.dp;

import java.util.Arrays;

//https://leetcode.com/problems/coin-change/description/
public class CoinChange {

    static int coinChangeRec(int[] coins, int amount, int count) {

        if(amount < 0) return Integer.MAX_VALUE;

        if(amount == 0) return count;

        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < coins.length; i++) {
            ans = Math.min(ans, coinChangeRec(coins, amount - coins[i], count+1));
        }

        return ans;
    }

    static int coinChangeMem(int[] coins, int amount, int[] dp) {

        if(amount < 0) return Integer.MAX_VALUE;

        if(amount == 0) return 0;

        if(dp[amount] != 0) return dp[amount] ;

        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < coins.length; i++) {
            int val = coinChangeMem(coins, amount - coins[i], dp);

            if(val == Integer.MAX_VALUE) ans = Math.min(ans, val);
            else ans = Math.min(ans, 1 + val);
        }

        dp[amount] = ans;

        return dp[amount];
    }

    static int coinChangeTab(int[] coins, int amount) {

        int[] dp = new int[amount+1];
        Arrays.fill(dp , Integer.MAX_VALUE);

        dp[0] = 0;

        for(int j = 1; j <= amount; j++) {
            for(int i = 0; i < coins.length; i++) {
                if(j - coins[i] >= 0) {
                    int val = dp[j - coins[i]];

                    if(val == Integer.MAX_VALUE) dp[j] = Math.min(dp[j], val);
                    else dp[j] = Math.min(dp[j], 1 + val);
                }
            }
        }


        if(dp[amount] == Integer.MAX_VALUE) return -1;
        return dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;

        //System.out.println(coinChangeMem(coins, amount, new int[amount+1]));
        System.out.println(coinChangeTab(coins, amount));

    }
}
