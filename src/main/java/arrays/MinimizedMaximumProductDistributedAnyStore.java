package arrays;

import util.Utility;

import java.math.BigInteger;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

// https://leetcode.com/problems/minimized-maximum-of-products-distributed-to-any-store/description/
public class MinimizedMaximumProductDistributedAnyStore {

    public static int minimizedMaximum(int n, int[] quantities) {
        // if there is n+1 store and n quantities; that means at-least one store does not have any product
        // if there is n store and n quantities; that means each store will get 1 product; maximum of them will be - min
        // if there is 1 store and n quantities; that means one store will get all the products; total of them will be - max
        long totalQuantities = IntStream.of(quantities).sum();
        // range is 1 .. totalQuantities

        long start = 1;
        long end = totalQuantities;
        long result = 0;
        while (start <= end) {
            long mid = (start + end)/2;
            if(isSplitPossible(n, quantities, mid, totalQuantities)) {
                result = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }


        return (int)result;
    }

    private static boolean isSplitPossible(int n, int[] quantities, long mid, long totalQuantity) {
        long count = 0;
        long store_count = 0;
        for(int i = 0; i < quantities.length; i++) {
            count = quantities[i];
            if (count > mid) {
                store_count += count / mid;;
                count = count % mid;
            }
            if (count != 0) store_count ++;
        }

        return store_count <= n;
    }

    public static void main(String[] args) throws Exception {
        BigInteger a = new BigInteger("2975062139");
        //System.out.print(Integer.parseInt("1487531070"));
        System.out.print((long)1 + Long.parseLong("2975062139")/ (long)2);
        Utility.assertTrue(minimizedMaximum(6, new int[]{11, 6}), 3);
        Utility.assertTrue(minimizedMaximum(7, new int[]{15,10,10}), 5);
        Utility.assertTrue(minimizedMaximum(1, new int[]{100000}), 100000);
        Utility.assertTrue(minimizedMaximum(2, new int[]{5, 7}), 7);
        Utility.assertTrue(minimizedMaximum(2, new int[]{4, 8}), 8);
        Utility.assertTrue(minimizedMaximum(3, new int[]{2, 10, 6}), 10);

    }
}
