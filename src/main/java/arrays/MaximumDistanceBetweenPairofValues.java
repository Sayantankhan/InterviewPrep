package arrays;

import util.Utility;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// https://leetcode.com/problems/maximum-distance-between-a-pair-of-values/description/
public class MaximumDistanceBetweenPairofValues {

    //  i <= j && nums1[i] <= nums2[j] => (i, j) --> distance : j - i

    // TC - O(n^2)
    public static int maxDistanceBF(int[] nums1, int[] nums2) {
        // int len = Math.min(nums2.length, nums1.length);
        int diff = 0;
        for(int i = 0; i < nums1.length; i++) {
            for(int j = i; j < nums2.length; j++) {
                if(nums1[i] <= nums2[j]) {
                    diff = Math.max(diff, j - i);
                }
            }
        }

        return diff;
    }

    // 55,30,5,4,2   5,10,10,20,100
    private static int maxDistanceBN(int[] nums1, int[] nums2) {
        // Max(j) - Min(i) ==> ANS where i <= j and nums[i] <= nums[j]
        int[] num = new int[nums2.length];
        for(int i = 0 ; i < nums2.length; i++) {
            num[i] = nums2[nums2.length-1-i];
        }

        nums2 = num;
        int max = 0;
        for(int i = 0; i < nums1.length; i++) {
            int index = Arrays.binarySearch(nums2, nums1[i]);
            if(index < 0) {
                index = -(index + 1);
            }
            max = Math.max(max, (nums2.length - 1 - index - i));
        }
        return max;
    }

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(maxDistance(new int[]{55,30,5,4,2}, new int[]{100,20,10,10,5}), 2);
        Utility.assertTrue(maxDistance(new int[]{2,2,2}, new int[]{10,10,1}), 1);
        Utility.assertTrue(maxDistance(new int[]{30,29,19,5}, new int[]{25,25,25,25,25}), 2);
    }

    //  i <= j && nums1[i] <= nums2[j] => (i, j) --> distance : j - i
    private static int maxDistance(int[] num1, int[] num2) {
        int i = 0;
        int j = 0;
        int max = 0;

        while(i < num1.length && j < num2.length) {
            if(num1[i] > num2[j]) {
                i++;
            } else {
                max = Math.max(max, j - i);
                j++;
            }
        }
        return max;

    }
}
