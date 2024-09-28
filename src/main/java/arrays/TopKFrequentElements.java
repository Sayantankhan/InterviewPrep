package arrays;

import util.Utility;

import java.util.*;
import java.util.stream.Collectors;

// https://leetcode.com/problems/top-k-frequent-elements/description/
// Top K Frequent Elements
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


    // Approach 2: Priority Queue
    class Tuple {
        int number;
        int count;

        Tuple(int number, int count) {
            this.number = number;
            this.count = count;
        }
    }

    public int[] topKFrequentpq(int[] nums, int k) {
        Arrays.sort(nums);
        int[] res = new int[k];

        PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>((t1, t2) -> {
            return t2.count - t1.count;
        });

        for(int i = 0; i < nums.length; i++) {
            int count = 0;
            int n = nums[i];
            while(i+1 < nums.length && nums[i] == nums[i+1]) {
                count++;
                i++;
            }
            pq.offer(new Tuple(n, count+1));
        }
        int index = 0;
        while(k-1 >= 0) {
            res[index++] = pq.poll().number;
            k--;
        }

        return res;

    }

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2), new int[]{1, 2});
        Utility.assertTrue(topKFrequent(new int[]{1, 2, 3}, 3), new int[]{1, 2, 3});
    }
}
