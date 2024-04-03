package arrays;

import util.Utility;

public class BestTimeBuySellStockII {

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(bookMaxProfit(new int[]{7,1,5,3,6,4}), 7);
        Utility.assertTrue(bookMaxProfit(new int[]{1,2,3,4,5}), 4);
        Utility.assertTrue(bookMaxProfit(new int[]{7,6,4,3,1}), 0);
    }

    /*
     * On each day, you may decide to buy and/or sell the stock.
     * You can only hold at most one share of the stock at any time.
     *  However, you can buy it then immediately sell it on the same day.
     */
    private static Object bookMaxProfit(int[] prices) {
        // we can buy multiple times and sell mul times
        // we will buy at each dip and sell at each high : Local optimization
        int profit = 0;
        int lastPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (lastPrice < prices[i]) {
                profit = profit + (prices[i] - lastPrice);
            }
            lastPrice = prices[i];
        }
        return profit;
    }
}
