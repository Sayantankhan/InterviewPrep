package arrays;

// https://leetcode.com/problems/minimum-size-subarray-sum/description/
public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
        // 2,3,1,2,4,3 --> 2,5,6,8,12,15
        // 2,3,4,3,1,2 --> 2,5,9,12,13,15

        // 3,1,7,4,3  --> 3,4,11,15,18
        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];

        // SC - O(N) || not to have any extra space we can use two pointer rather than prefix sum
        for(int i = 1; i < nums.length; i++) {
            prefixSum[i] = prefixSum[i-1] + nums[i];
        }

        int window_left = 0;
        int prev_window_left = -1;
        int result = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length ; i++) {
            if(prefixSum[i] >= target) {
                while(prefixSum[i] - prefixSum[window_left] >= target) {
                    prev_window_left = window_left;
                    window_left++;
                }

                result = Math.min(result, i - prev_window_left);
            }
        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumSizeSubarraySum().minSubArrayLen(11, new int[] {1,2,3,4,5}));
        System.out.println(new MinimumSizeSubarraySum().minSubArrayLen(7, new int[] {2,3,1,2,4,3}));
        System.out.println(new MinimumSizeSubarraySum().minSubArrayLen(15, new int[] {1,2,3,4,5}));
    }
}
