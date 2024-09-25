package arrays.twopointers;

import util.Utility;

// https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/
public class TwoSumOnSortedArray {

    public static int[] twoSum(int[] numbers, int target) {
        // As the array is sorted , we dont need to sort

        int start = 0, end = numbers.length - 1;
        while (start < end) {
            if(numbers[start] + numbers[end] == target)  {
                return new int[]{start+1, end+1};
            } else if(numbers[start] + numbers[end] > target) {
                end --;
            } else {
                start++;
            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(twoSum(new int[]{2,7,11,15}, 9), new int[]{1, 2});
        Utility.assertTrue(twoSum(new int[]{2,3,4}, 6), new int[]{1, 3});
        Utility.assertTrue(twoSum(new int[]{-1,0}, -1), new int[]{1, 2});
    }
}
