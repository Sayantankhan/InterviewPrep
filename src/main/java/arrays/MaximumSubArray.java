package arrays;

import util.Utility;

// https://leetcode.com/problems/maximum-subarray/description/
public class MaximumSubArray {

    public static int maxSubArray(int[] nums) {
        int sum = Integer.MIN_VALUE;
        int sum_till_now = 0;

        for(int i = 0; i < nums.length; i++) {
            sum_till_now += nums[i];
            sum = Math.max(sum, sum_till_now);
            if(sum_till_now < 0) {
                sum_till_now = 0;
            }
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}), 6); // The subarray [4,-1,2,1] has the largest sum 6.
        Utility.assertTrue(maxSubArray(new int[]{1}), 1); // The subarray [1] has the largest sum 1.
        Utility.assertTrue(maxSubArray(new int[]{5,4,-1,7,8}), 23); // The subarray [5,4,-1,7,8] has the largest sum 23.
        Utility.assertTrue(maxSubArray(new int[]{-2, 1}), 1);
        Utility.assertTrue(maxSubArray(new int[]{-1}), -1);

    }
}
