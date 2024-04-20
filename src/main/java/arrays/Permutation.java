package arrays;

import util.Utility;

import java.util.*;

//https://leetcode.com/problems/permutations-ii/description/?envType=featured-list&envId=top-google-questions?envType=featured-list&envId=top-google-questions
public class Permutation {

    public static List<List<Integer>> permuteUnique(int[] nums) {

        Set<List<Integer>> results = new HashSet<>();
        boolean[] visited = new boolean[nums.length]; // keeping track of visited and non visited list
        permuteUniqueRec(nums, 0, new ArrayList<Integer>(), results, visited);
        return new ArrayList<List<Integer>>(results);
    }

    private static void permuteUniqueRec(int[] nums, int i, ArrayList<Integer> integers, Set<List<Integer>> results, boolean[] visited) {
        if(i == nums.length) {
            results.add(new ArrayList(integers));
            return;
        }

        for(int a = 0; a < nums.length; a++) {
            if(!visited[a]) {
                visited[a] = true;
                integers.add(nums[a]);

                permuteUniqueRec(nums, i+1, integers, results, visited);

                visited[a] = false;
                integers.remove(integers.size() - 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(permuteUnique(new int[]{1,1,2}), List.of(List.of(1, 1, 2), List.of(2, 1, 1), List.of(1, 2, 1)));

        Utility.assertTrue(permuteUnique(new int[]{1, 2, 3}), List.of(
                List.of(1,2,3),
                List.of(1,3,2),
                List.of(2,1,3),
                List.of(2,3,1),
                List.of(3,1,2),
                List.of(3,2,1))
        );
    }
}
