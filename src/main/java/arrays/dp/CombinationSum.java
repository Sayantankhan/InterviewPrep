package arrays.dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://leetcode.com/problems/combination-sum-iv/description/
public class CombinationSum {

    public static int combinationSum4(int[] nums, int target) {
//        Set<List<Integer>> set = new HashSet<>();
//        return combinationSum4Rec(nums, target, 0, 0);

        return combinationSum4DP(nums, target);
    }

    // As this is recursive Approach - TC - O(2^N) - which may throw time limit exception!!
    static int  combinationSum4Rec(int[] nums, int target, int index, int totalSum) {
        // Recursion approach :
        // base case if index > nums.length || totalSum > target -> we return 0;
        if(index >= nums.length || totalSum > target) return 0;
        // base case totalSum == target ; count ++;
        if(totalSum == target) {
            return 1;
        }

        // this loop is for all the combination possible - [1 , 2 ,3] target 4
        // 1 + 1 + 2
        // 2 + 1 + 1
        // 1 + 2 + 2
        int ans = 0;
        for(int j = 0; j < nums.length; j++) {
            // we can either take the same number (total = total + nums[i])
            ans += combinationSum4Rec(nums, target, j, totalSum + nums[j]);
        }
        return ans;
    }


    static int combinationSum4DP(int[] nums, int target) {
        int[] dp = new int[target+1];

        // base case totalSum == target ; count ++;
        dp[target] = 1;

        for(int i = target-1; i >= 0; i--) {
            // we can either take the same number (total = total + nums[i])
            for(int j = 0; j < nums.length; j++) {
                if(i + nums[j] <= target) {
                    dp[i] += dp[i + nums[j]];
                }
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(combinationSum4(nums, 4));;

        nums = new int[]{8, 1};
        System.out.println(combinationSum4(nums, 24));;
    }
}
