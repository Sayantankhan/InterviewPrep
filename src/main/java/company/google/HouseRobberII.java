package company.google;

import java.util.*;

// https://leetcode.com/problems/house-robber-ii/
public class HouseRobberII {

    public int rob(int[] nums) {
        if(nums.length == 1) return nums[0];
//        int[]dp = new int[nums.length];
//        int[]dp2 = new int[nums.length];
//        int max = Math.max(robKnapSack(nums, 0, nums.length-2, dp), robKnapSack(nums, 1, nums.length-1, dp2));
//        return max;

        int max = Math.max(robTabular(nums, 0, nums.length-2), robTabular(nums, 1, nums.length-1));
        return max;
    }

    int robKnapSack(int[] nums, int index, int n, int[] dp) {
        if(index == n) return nums[n];
        if(index > n) return 0;

        if(dp[index] != 0) return dp[index];
        dp[index] = Math.max(nums[index] +  robKnapSack(nums, index+2, n, dp), robKnapSack(nums, index+1, n, dp));
        return dp[index];
    }

    int robTabular(int[] nums, int start, int end) {
        int[] dp = new int[nums.length];
        dp[end] = nums[end];

        for(int i = end-1; i >= start; i--) {
            int take = nums[i] + ((i+2 > end) ? 0 : dp[i+2]);
            int nottake = ((i+1 > end) ? 0 : dp[i+1]);

            dp[i] = Math.max(take, nottake);
        }

        return dp[start];
    }

    public static void main(String[] args) {
        HouseRobberII houseRobber = new HouseRobberII();
        System.out.println(houseRobber.rob(new int[] {200,3,140,20,10}));

        // 6,6,4,8,4,3,3,10 = {6, 8, 3, 10} = 27
        System.out.println(houseRobber.rob(new int[] { 6,6,4,8,4,3,3,10}));
    }
}
