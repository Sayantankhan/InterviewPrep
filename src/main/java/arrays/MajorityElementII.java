package arrays;

import util.Utility;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/majority-element-ii/description/
public class MajorityElementII {

    // Approach : Same as Moores voting algo, there we are choosing one candidate, here we will choose two candidates
    public static List<Integer> majorityElement(int[] nums) {

        int count1 = 0;
        int el1 = Integer.MIN_VALUE;
        int count2 = 0;
        int el2 = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == el1) {
                count1++;
            } else if (nums[i] == el2) {
                count2++;
            } else if (count1 == 0) {
                el1 = nums[i];
                count1 = 1;
            } else if (count2 == 0) {
                el2 = nums[i];
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        List list = new ArrayList();

        // verify
        count2 = count1 = 0;

        for(int i = 0; i < nums.length; i++) {
            if (nums[i] == el1) {
                count1++;
            }
            if (nums[i] == el2) {
                count2++;
            }
        }

        if (count1 > nums.length / 3) {
            list.add(el1);
        }
        if (count2 > nums.length / 3) {
            list.add(el2);
        }

        return list;
    }

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(majorityElement(new int[]{3, 2, 3}), List.of(3));
        Utility.assertTrue(majorityElement(new int[]{2, 2}), List.of(2));
        Utility.assertTrue(majorityElement(new int[]{3, 3, 4}), List.of(3));
        Utility.assertTrue(majorityElement(new int[]{2, 2, 1, 3}), List.of(2));
    }
}
