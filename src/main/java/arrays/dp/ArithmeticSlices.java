package arrays.dp;

import java.util.ArrayList;

//https://leetcode.com/problems/arithmetic-slices/description/
public class ArithmeticSlices {

    public static int numberOfArithmeticSlices(int[] nums) {
        if(nums.length < 3) return 0;

        // return numberOfArithmeticSlicesRec(nums, 0, 0, null, null);
        // O(N)
        // Since we are dealing with subarrays and subsequences we just need one key, value pair.
        int res = 0;
        int key = Integer.MIN_VALUE;
        int val = Integer.MIN_VALUE;

        for(int i=1; i<nums.length; i++) {
            int d = nums[i] - nums[i-1];
            if(key != d) {
                key = d;
                val = 0;
            } else val ++;

            if(val >= 1) res += val;
        }
        return res;
    }

    // TC : O(2^N)
    private static int numberOfArithmeticSlicesRec(int[] nums, int index, int count, Integer prev, Integer diff) {

        // Base Condition Check:
        if(index == nums.length) {
            if(count >= 3) return 1;
            else return 0;
        }
        // if nothing selected, also remaining element is less than 3; so no point to move further
        if(prev == null && nums.length-index < 3) return 0;

        int total = 0;

        // when nothing selected
        if(prev == null) {
            // take two indexes - index and index+1
            int x = numberOfArithmeticSlicesRec(nums, index+2, count+2, index+1, nums[index+1] - nums[index]);
            // dont take any index, goto the next pos and check the same
            int y = numberOfArithmeticSlicesRec(nums, index+1, count, prev, diff);

            total = x + y;
        }
        else {
            // if prev == index-1 is false, that means we cant take it , else it will not be a subsequent
            if(prev == index-1 && diff == (nums[index] - nums[prev] )) {
                // take
                int x = numberOfArithmeticSlicesRec(nums, index+1, count+1, index, diff);
                // dont take
                int y = numberOfArithmeticSlicesRec(nums, index+1, count, prev, diff);

                total = x + y;
            }
        }

        // condition to check : if we already got count 3 , so if we skip the current one and take the next
        // even the diff matches, but we cant take as those are not subsequent
        return (total == 0) ? (count >= 3) ? 1 : 0 : total;
    }




    public static void main(String[] args) {
        System.out.println(numberOfArithmeticSlices(new int[]{1, 2, 3, 4, 5}));
    }
}
