package arrays;

import java.util.Arrays;
import java.util.stream.IntStream;

// https://leetcode.com/problems/minimum-moves-to-equal-array-elements/
public class MinimumMovesEqualArrayElements {
    /*
     * Observation the minum number will always be minum until it reachs the final number,
     * because every move, other numbers (besides the max) will be increamented too;
     *
     * sum + m * (n - 1) = x * n || m -> no of moves ; x = new value
     *
     * based on observation : x = minNum + m
     *
     * replacing all of these , we get  sum - minNum * n = m
     */
    public int minMoves1(int[] nums) {
        int sum = IntStream.of(nums).min().getAsInt();
        Arrays.sort(nums);
        return sum - nums[0] * nums.length;
    }

    public int minMoves(int[] nums) {
        Arrays.sort(nums);
        int k = nums.length - 2;
        int count = 0;

        if(nums.length > 1) {
            count = nums[nums.length-1] - nums[k];
            // update Array with count
            for(int i = 0; i <= k; i++) nums[i] += count;
        }

        while(!isEqual(nums)) {
            int high = nums.length - 1;

            for(int i = 0; i <= k; i++) {
                int val = nums[i] + 1;
                if(val > nums[high]) {
                    // swap nums, i , high
                    int temp = val;
                    nums[i] = nums[high];
                    nums[high] = temp;
                } else {
                    nums[i] = val;
                }
            }

            count++;
        }

        return count;
    }

    boolean isEqual(int[] nums) {
        return nums[0] == nums[nums.length-1];
    }

    public static void main(String[] args) {
        // new MinimumMovesEqualArrayElements().minMoves(new int[]{1, 2, 3});
        System.out.println(new MinimumMovesEqualArrayElements().minMoves(new int[]{1, 1, 1000000000}));
        System.out.println(new MinimumMovesEqualArrayElements().minMoves(new int[]{1, 1000, 1000000000}));
        System.out.println(new MinimumMovesEqualArrayElements().minMoves(new int[]{-1000000000,-1}));
    }
}
