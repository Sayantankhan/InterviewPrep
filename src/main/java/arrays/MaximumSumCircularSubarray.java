package arrays;

import util.Utility;

import java.util.Arrays;

// https://leetcode.com/problems/maximum-sum-circular-subarray/description/
public class MaximumSumCircularSubarray {
    // 806618
    // Appraoch 1: One way to handle circular array is use the same array twice which will form a new big array
    // if x = [a, b, c, d] is circular ; then new x for operation will be [a, b, c, d, a, b, c, d]
    // Time Complexity - O(N^2) Space - O(N)
    public static int maxSubarraySumCircular1(int[] nums) {
        int[] temp = new int[2 * nums.length];
        int len = nums.length;

        for(int i = 0; i < len; i++) {
            temp[len+i] = temp[i] = nums[i];
        }

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < len; i++) {
            int max_till_now = 0;
            for(int j = i; j < len + i; j++) {
                max_till_now += temp[j];
                max = Math.max(max, max_till_now);
                if(max_till_now < 0) {
                    max_till_now = 0;
                }
            }
        }

        return max;
    }

    public static int maxSubarraySumCircular(int[] nums) {
        int total = 0;

        int maxSum = Integer.MIN_VALUE;
        int temp_max_sum = 0;

        int minSum = Integer.MAX_VALUE;
        int temp_min_sum = 0;

        for(int i = 0; i < nums.length; i++) {
            // calculating max sum : kadane
            temp_max_sum += nums[i];
            maxSum = Math.max(maxSum, temp_max_sum);
            temp_max_sum = Math.max(0, temp_max_sum);

            // total
            total += nums[i];

            // calculating min sum : kadane
            temp_min_sum += nums[i];
            minSum = Math.min(minSum, temp_min_sum);
            temp_min_sum = Math.min(0, temp_min_sum);
        }
        // if all the numbers are neg
        if(total == minSum) return maxSum;

        return Math.max(maxSum, total - minSum);
    }

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(maxSubarraySumCircular(new int[]{1, -2, 3, -2}), 3);
        Utility.assertTrue(maxSubarraySumCircular(new int[]{5, -3, 5}), 10); // Subarray [5,5] has maximum sum 5 + 5 = 10.
        Utility.assertTrue(maxSubarraySumCircular(new int[]{-3, -2, -3}), -2);
    }
}
