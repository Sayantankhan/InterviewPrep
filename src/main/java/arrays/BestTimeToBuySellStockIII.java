package arrays;

import util.Utility;

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/
public class BestTimeToBuySellStockIII {

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(bookMaxProfitRec(new int[]{3,3,5,0,0,3,1,4}), 6);
        Utility.assertTrue(bookMaxProfitRec(new int[]{1,2,3,4,5}), 4);
        Utility.assertTrue(bookMaxProfitRec(new int[]{7,6,4,3,1}), 0);
    }

    /*
     * the maximum profit you can achieve. You may complete at most two transactions.
     */
    private static Object bookMaxProfit(int[] prices) {
        // This is the simpler approach, this approach use global minima and calculate max profit
        // use the max profit to calculate the second transaction
        int minPrice1 = Integer. MAX_VALUE, minPrice2 = Integer. MAX_VALUE;
        int profit1 = 0, profit2 = 0;

        for (int currPrice : prices) {
            minPrice1 = Math.min(currPrice, minPrice1);
            profit1 = Math.max(profit1, currPrice - minPrice1);

            minPrice2 = Math.min(minPrice2, currPrice - profit1);
            profit2 = Math.max(profit2, currPrice - minPrice2);

        }

        return profit2;
    }

    private static Object bookMaxProfitRec(int[] prices) {
        return bookMaxProfitRec(prices, 0, 2, true);
    }

    private static int bookMaxProfitRec(int[] prices, int index, int transaction, boolean allowedToBuy) {

        if(transaction == 0) return 0;
        if(index >= prices.length) return 0;

        if(allowedToBuy) {
            return Math.max(
                // either we can take the stock to buy
                bookMaxProfitRec(prices, index+1, transaction, !allowedToBuy) - prices[index],

                // or not consider the stock
                bookMaxProfitRec(prices, index+1, transaction, allowedToBuy)
            );
        } else {
            return Math.max(
                    // either we can take the stock to sell
                    bookMaxProfitRec(prices, index+1, transaction-1, !allowedToBuy) + prices[index],

                    // or not consider the stock
                    bookMaxProfitRec(prices, index+1, transaction, allowedToBuy)
            );
        }
    }
}
