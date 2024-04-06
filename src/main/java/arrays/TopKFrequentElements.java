package arrays;

import util.Utility;

import java.util.*;
import java.util.stream.Collectors;

// https://leetcode.com/problems/top-k-frequent-elements/description/
public class TopKFrequentElements {
    /*
     * Given an integer array nums and an integer k, return the k most frequent elements.
     *  You may return the answer in any order.
     */
    // Approach 1 : Keeping a HashMap to count
    public static int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> map = new TreeMap<Integer, Integer>();

        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // O(n logn)
        List<Map.Entry<Integer, Integer>> list = map.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .collect(Collectors.toList());

        int[] res = new int[k];
        for (int i = 0; i < k && i < list.size(); i++) {
            res[i] = list.get(i).getKey();
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2), new int[]{1, 2});
        Utility.assertTrue(topKFrequent(new int[]{1, 2, 3}, 3), new int[]{1, 2, 3});
    }
}
