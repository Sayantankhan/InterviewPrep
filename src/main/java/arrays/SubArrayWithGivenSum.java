package arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SubArrayWithGivenSum {

    public static void main(String[] args) {
        int[] arr = {9, 4, 7, 5, 10, 23, 4, 15, 2};
        int targetSum = 21;

        arr = new int[]{5, 6, 7, 8, -3, 1, 2, 34, 4, 5};
        System.out.println(findSubarrayWithGivenSumZero(arr));

        Arrays.stream(findSubarrayWithGivenSumSlidingWindow(arr, 0)).forEach(x -> System.out.println(x));
    }

    // https://www.geeksforgeeks.org/find-subarray-with-given-sum/z
    // if [a + b + c + d > target_sum]  || (target_sum - (a + b + c + d))
    private static int[] findSubarrayWithGivenSum(int[] arr, int targetSum) {

        int currSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i  = 0; i < arr.length; i++) {
            currSum += arr[i];

            if(currSum == targetSum) {
                return new int[] {0, i};
            }

            int diff = currSum - targetSum;
            if (map.containsKey(diff)) { // if currSum > targetSum => (currSum - targetSum) present in precomputerd list
                return new int[]{map.get(diff) + 1, i};
            }

            map.put(currSum, i); // storing the curr sum by pos
        }
        return new int[]{};
    }

    private static int[] findSubarrayWithGivenSumSlidingWindow(int[] arr, int targetSum) {

       int start = 0;
       int currSum = arr[0];

       for(int i = 1; i < arr.length; i++) {
           currSum += arr[i];

           while(currSum > targetSum && start < i) {
               currSum = currSum - arr[start];
               start++;
           }

           if(currSum == targetSum) {
               return new int[]{start, i};
           }
       }

       return new int[]{};
    }

    private static boolean findSubarrayWithGivenSumZero(int[] arr) {

        int currSum = 0;
        HashSet<Integer> set = new HashSet<>();

        for(int i = 0; i < arr.length; i++) {
            currSum += arr[i];

            if(currSum == 0 || arr[i] == 0 || set.contains(currSum)) return true;

            set.add(currSum);
        }

        return false;
    }

}
