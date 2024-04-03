package arrays;

import util.Utility;

import java.util.Objects;

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
public class BestTimeToBuySellStock {

    /*
     * maximize your profit by choosing a single day to buy one stock
     * and choosing a different day in the future to sell that stock.
     */
    public static int bookMaxProfit(int[] prices) {
        // Option 1: we can start buying at the low and check which gives more profit

        int min = Integer.MAX_VALUE;
        int profit = 0;
        for(int price : prices) {
            min = Math.min(price, min);
            profit = Math.max(profit, price - min);
        }
        return profit;
    }

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(bookMaxProfit(new int[]{7,1,5,3,6,4}), 5);
        Utility.assertTrue(bookMaxProfit(new int[]{7,6,4,3,1}), 0);
    }
}
