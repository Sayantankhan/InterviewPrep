package company;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/*
   You are given an array of integers. Your task is to create pairs of them, such that every created pair has the same sum.
   This sum is NOT specified, but the number of created pairs should be the maximum possible.
   Each array element may belong to one pair only. Write a function: public int solution(int[] A)
   that given an array A of N integers, returns the maximum possible number of pairs with the same sum. Examples:

    A = [1, 9, 8, 100, 2], should return 2: the pairs are [1, 9] and [8, 2] for a sum of 10
    A = [2, 2, 2, 3], should return 1: [2, 2] (sum 4) OR [2, 3] (sum 5). Notice we can only form sum 4 once since there is overlap between the elements
    A = [2, 2, 2, 2, 2], should return 2: [2, 2] and [2, 2] for a sum for 4. The fifth 2 is not used, while the first four 2's are used.
 */
public class Google_MaximumUniquePairsFormSum {

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 8, 9, 10};

        // countMaxPairSameSumBF(a);

        // Not So good Approach !!
        Arrays.sort(a);
        int minSum = a[0] + a[1];
        int maxSum = a[a.length-1] + a[a.length-2];
        int ans = IntStream.range(minSum, maxSum).map(x -> twoSum(a, x)).max().getAsInt();

        System.out.println(ans);
    }

    private static int twoSum(int[] a, int x) {

        int low = 0;
        int high = a.length-1;
        int count = 0;

        while(low < high) {
            if(a[low] + a[high] == x) {
                count++;
                low++;
                high--;
            }
            else if (a[low] + a[high] > x) {
                high--;
            } else {
                low++;
            }
        }
        return count;
    }

    private static int countMaxPairSameSumBF(int[] a) {
        HashMap<Integer, Integer> freq = new HashMap<>();

        for(int i = 0; i < a.length; i++) {
            for(int j = i+1; j < a.length; j++) {
                freq.put(a[i] + a[j], freq.getOrDefault(a[i] + a[j], 0)+1);
            }
        }

        int maxSumCount = 0;
        for(Map.Entry<Integer,Integer> entry : freq.entrySet()) {
            maxSumCount = Math.max(maxSumCount, entry.getValue());
        }

        return maxSumCount;
    }
}
