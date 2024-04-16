package arrays;

import util.Utility;

import java.util.*;

// https://leetcode.com/problems/find-all-duplicates-in-an-array/description/
//  O(n) time and uses only constant extra space
public class FindAllDuplicatesinArray {

    // Given an integer array nums of length n where all the integers of nums are in the range [1, n]
    // and each integer appears once or twice, return an array of all the integers that appears twice.
    // arr = {4,3,2,7,8,2,3,1}
    // {4,3,-2,-7,-8,2,-3,-1}
    public static List<Integer> findDuplicates(int[] nums) {
        AbstractList l = null;
        AbstractSequentialList ll = null;
        List<Integer> list = new ArrayList<>();
        for ( int i=0; i < nums.length; i++) {
            int tIndex = Math.abs(nums[i]);

            if(nums[tIndex - 1] < 0) list.add(tIndex);
            else nums[tIndex - 1] = -nums[tIndex - 1];
        }

        return list;
    }

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(findDuplicates(new int[]{4,3,2,7,8,2,3,1}), List.of(2,3));

        Utility.assertTrue(findDuplicates(new int[]{1,1,2}), List.of(1));
        Utility.assertTrue(findDuplicates(new int[]{1}), Collections.EMPTY_LIST);

    }
}
