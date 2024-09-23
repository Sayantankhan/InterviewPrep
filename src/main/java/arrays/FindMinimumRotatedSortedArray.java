package arrays;

import util.Utility;

// Find Minimum in rotated sorted array
// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/
public class FindMinimumRotatedSortedArray {

    // Given the sorted rotated array nums of unique elements, return the minimum element of this array.
    //You must write an algorithm that runs in O(log n) time.
    /*
     * Approach 1: Go through the entire array and have a min element ; Time Complexity -> O(n)
     */
    public static int findMin(int[] nums) {
        return binarySearch(nums, 0, nums.length-1);
    }

    public static int binarySearch(int[] nums, int start, int end) {
        int min = nums[0];
        while(start <= end) {

            int mid = (start + end)/2;
            min = Math.min(min, nums[mid]);

            // case 1 : sub array is [3, 4, 2]; start -> 0 and end -> 2; nums[start] > nums[mid]
            // case 2 : sub array is [7, 0, 1, 2]; start -> 1 and end -> 3; for this case upper condition will
            // Not work!! As it may think this is a rotation array but actually its a sorted one ; to prove that we
            // have to include prev element
            if(nums[start] > nums[mid] ||
                    (start-1 >= 0 && nums[start-1] > nums[start])) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return min;
    }

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(findMin(new int[] {3,4,5,1,2}), 1);
        Utility.assertTrue(findMin(new int[] {4,5,6,7,0,1,2}), 0);
        Utility.assertTrue(findMin(new int[] {11,13,15,17}), 11);
        Utility.assertTrue(findMin(new int[] {11,13,15,10}), 10);
    }
}
