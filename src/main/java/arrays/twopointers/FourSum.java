package arrays.twopointers;

import util.Utility;

import java.util.*;

// https://leetcode.com/problems/4sum/description/
public class FourSum {

    // Simple way to understand of this is keep two pointer static - remaining will be again 2SUM;
    // SAME for 5SUM, we keep 3pointers and remaining will be again 2SUM;
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);

        // this has been added to remove neg cases;
        // in a sorted array -- array[n-1] > avg(array) > array[0]
        int div = target / 4;
        if(nums[0] > div || nums[nums.length-1] < div) return Collections.emptyList();

        // -2 -1 0 0 1 2
        Set<List<Integer>> list = new HashSet<>();
        for(int i = 0; i < nums.length - 2; i++) {
            int start = i + 1;
            while(start < nums.length - 2) {
                List<List<Integer>> anss = twoSum(nums, (long)target - (long)nums[start] - (long)nums[i], start+1, nums.length-1);
                for (List<Integer> ans : anss) {
                    List temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[start]);
                    temp.addAll(ans);
                    list.add(temp);
                }
                start++;
            }
        }
        return new ArrayList<>(list);
    }

    private static List<List<Integer>> twoSum(int[] nums, long target, int start, int end) {

        List<List<Integer>> list = new ArrayList<>();
        while(start < end) {
            if(nums[start] + nums[end] == target) {
                list.add(List.of(nums[start], nums[end]));
                start++;
                end--;
            } else if (nums[start] + nums[end] < target) {
                start++;
            } else {
                end--;
            }
        }
        return list;
    }

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(fourSum(new int[]{1,0,-1,0,-2,2}, 0), List.of(List.of(-2,-1,1,2), List.of(-2,0,0,2), List.of(-1,0,0,1)));
        Utility.assertTrue(fourSum(new int[]{2,2,2,2,2}, 8), List.of(List.of(2,2,2,2)));
        Utility.assertTrue(fourSum(new int[]{-3,-1,0,2,4,5}, 0), List.of(List.of(-3,-1,0,4)));
        Utility.assertTrue(fourSum(new int[]{-2,-1,-1,1,1,2,2}, 0), List.of(List.of(-2,-1,1,2), List.of(-1,-1,1,1)));
        System.out.println(fourSum(new int[]{-3,-2,-1,0,0,1,2,3}, 0)); //-2,-1,0,3
        System.out.println(fourSum(new int[]{-1000000000,-1000000000,-1000000000,-1000000000, 1000000000}, 294967296)); //-2,-1,0,3

    }
}
