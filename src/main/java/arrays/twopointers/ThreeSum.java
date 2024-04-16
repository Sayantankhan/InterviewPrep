package arrays.twopointers;

import util.Utility;

import java.util.*;

// https://leetcode.com/problems/3sum/
public class ThreeSum {

    // Approach 1 : Sort and use two pointers and one moving pointer to check - O(n^2)
    public static List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> lists = new HashSet<>();
        Arrays.sort(nums);
        for(int i = 0; i <  nums.length-1; i++) {
            int j = i + 1;
            int end = nums.length - 1;

            while(j < end) {
                int sum = nums[i] + nums[j] + nums[end];
                if (sum == 0) {
                    lists.add(List.of(nums[i], nums[j], nums[end]));
                    j++;
                    end--;
                } else if (sum < 0){
                    j++;
                } else {
                    end--;
                }
            }
        }

        return new ArrayList<>(lists);
    }

    // Approach 2 : One Way to Solve this is using recursion , higher chances of getting time limit exceed - O(2^n)
    private static void threeSumRec(int[] nums, Set<List<Integer>> lists, List<Integer> list, int index, int sum) {
        if(list.size() == 3 && sum == 0) {
            List temp = new ArrayList<>(list);
            Collections.sort(temp);
            lists.add(temp);
            return;
        }

        if (nums.length <= index) return;

        list.add(nums[index]);
        threeSumRec(nums, lists, list, index+1, sum + nums[index]);

        list.remove(list.size()-1);
        threeSumRec(nums, lists, list, index+1, sum);
    }

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(threeSum(new int[] {-1,0,1,2,-1,-4}),
                    List.of(List.of(-1, -1, 2), List.of(-1, 0, 1)));

        Utility.assertTrue(threeSum(new int[] {0, 0, 0}),
                List.of(List.of(0, 0, 0)));
    }
}
