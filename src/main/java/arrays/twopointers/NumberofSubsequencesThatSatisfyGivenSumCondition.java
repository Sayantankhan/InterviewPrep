package arrays.twopointers;

import util.Utility;

import java.util.Arrays;
import java.util.Map;

//https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/description/
public class NumberofSubsequencesThatSatisfyGivenSumCondition {

    public static int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        int start = 0;
        int end = nums.length - 1;

        int ans = 0;
        int mod = 1000000007;

        while (start <= end) {
            if (nums[start] + nums[end] > target) {
                end --;
            } else {
                ans += (int) (Math.pow(2, end - start)) % mod;
                start++;
            }
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
//       Utility.assertTrue(numSubseq(new int[]{3,5,6,7}, 9), 4);
//       Utility.assertTrue(numSubseq(new int[]{3,3,6,8}, 10), 6);
         Utility.assertTrue(numSubseq(new int[]{2,3,3,4,6,7}, 12), 61);
    }
}
