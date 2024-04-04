package arrays;

import util.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//https://leetcode.com/problems/longest-increasing-subsequence/description/
public class LongestIncreasingSubsequence {

    /*
     * Approach : The idea is  if we find a number that is greater than the current number then we are good to add,
     * if we are getting an integer that is smaller than the last one in the array, then find a suitable position ( binary search ), and put it there
     * cause even if with that new sequence we are not getting the greater sequence, still the array holds the older one, in question they asked
     * about the substring length, not the substring
     *
     * Lets take Example {10,9,2,5,3,7}
     * List = [10]
     * Step 1: i = 1; list.get(list.size()-1) =>( 10 < 9 ? ) => false => find_the_lowest_index where 9 can fit => 0 || List [ 9 ]
     * Step 2: i = 2; list.get(list.size()-1) =>( 9 < 2 ? ) => false => find_the_lowest_index where 2 can fit => 0 || List [ 2 ]
     * Step 3: i = 3; list.get(list.size()-1) =>( 2 < 5 ? ) => true => add|| List [ 2, 5 ]
     * Step 2: i = 4; list.get(list.size()-1) =>( 5 < 3 ? ) => false => find_the_lowest_index where 3 can fit => 1 || List [ 2, 3 ]
     * Step 2: i = 5; list.get(list.size()-1) =>( 3 < 7 ? ) => true => add|| List [ 2, 3, 7 ]
     */
    public static int lengthOfLIS(int[] nums) {
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] > list.get(list.size()-1)) {
                list.add(nums[i]);
            } else {
                int index = Collections.binarySearch(list, nums[i]);
                if (index < 0) {
                    list.set(-(index+1), nums[i]);
                }
            }
        }
        return list.size();
    }

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}), 4);
        Utility.assertTrue(lengthOfLIS(new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15}), 6);
    }
}
