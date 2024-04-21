package arrays;

import util.Utility;

// https://leetcode.com/problems/search-in-rotated-sorted-array/description/
public class SearchRotatedSortedArray {

    //You must write an algorithm with O(log n) runtime complexity.
    public static int search(int[] nums, int target) {

        int high = nums.length-1;
        int low = 0;

        while(high >= low) {
            int mid = (high + low)/2;

            if(nums[mid] == target) return mid;
            if (high == low) break;

            // 4 5 6 7 0 1 2   1
            // 4 5 6 7 8 1 2   8
            // 6 7 1 2 3 4 5   7

            // we are checking which side we have to pick
            // if nums{low} < nums{mid} and target < nums{low} and nums{mid} > target -- nums{low} < target < nums{mid} - high = mid - 1;
            // if nums{low} < nums{mid} and nums{mid} < target --- nums{mid} < target < high == low = mid + 1

            // similarly
            // if nums{high} > nums{mid} and target > nums{mid} and target < nums[high] --- nums{mid} < target < high  == low = mid + 1
            // if nums{high} > nums{mid} and target > nums[high] == high = mid - 1

            if(nums[low] <= nums[mid]) {
                if(nums[low] <= target && nums[mid] > target) {
                    high = mid-1;
                } else {
                    low = mid + 1;
                }
            }
            else {
                if(nums[high] >= target && nums[mid] < target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
//        Utility.assertTrue(search(new int[]{4,5,6,7,0,1,2}, 3), -1);
//        Utility.assertTrue(search(new int[]{4,5,6,7,0,1,2}, 2), 6);
//        Utility.assertTrue(search(new int[]{4,5,6,7,0,1,2}, 0), 4);
//        Utility.assertTrue(search(new int[]{5, 1, 3}, 5), 0);
//        Utility.assertTrue(search(new int[]{4,5,6,7,8,1,2,3}, 8), 4);
        Utility.assertTrue(search(new int[]{6,7,1,2,3,4,5}, 6), 0);

    }
}
