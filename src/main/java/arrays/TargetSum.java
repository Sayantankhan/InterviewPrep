package arrays;

import util.Utility;

import java.util.Arrays;

// https://leetcode.com/problems/target-sum/
public class TargetSum {

    static class Count {
        int count = 0;
    }
    public static int findTargetSumWays(int[] nums, int target) {
//        Count count = new Count();
//        findTargetSumWaysRec(nums, target, 0, 0, count);
//        return count.count;

        int total = Arrays.stream(nums).sum(); // calculating sum, cause adding element sum can make neg
                            // to make it positive we have to add the value with total
        int[][] dp = new int[nums.length][ (2 * total) + 1];
        for(int[] row: dp) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }

        return findTargetSumWaysMRec(nums, target, 0, 0, total, dp);
    }

    // Brute Force :: Time complexity: O(2^n); Space complexity: O(n)
    private static void findTargetSumWaysRec(int[] nums, int target, int index, int totalSum, Count count) {
        if(totalSum == target && index == nums.length) {
            count.count ++;
            return;
        } else if(totalSum != target && index == nums.length) {
            return;
        }

        findTargetSumWaysRec(nums, target, index+1, totalSum+nums[index], count);
        findTargetSumWaysRec(nums, target, index+1, totalSum-nums[index], count);
    }

    // Memorization Force :: Time complexity: O(t.n); Space complexity: O(t.n)
    private static int findTargetSumWaysMRec(int[] nums, int target, int index, int totalSum, int total, int[][] dp) {
        if(nums.length == index) {
            if(totalSum == target) {
                return 1;
            } else {
                return 0;
            }
        }
        if(dp[index][total + totalSum] != Integer.MIN_VALUE) {
            return dp[index][total + totalSum];
        }

        int add = findTargetSumWaysMRec(nums, target, index+1, totalSum + nums[index], total, dp);
        int sub = findTargetSumWaysMRec(nums, target, index+1, totalSum - nums[index], total, dp);

        dp[index][total + totalSum] = add + sub;
        return dp[index][total + totalSum];

    }

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(findTargetSumWays(new int[]{1,1,1,1,1}, 3), 5);
        Utility.assertTrue(findTargetSumWays(new int[]{1}, 1), 1);
    }
}
