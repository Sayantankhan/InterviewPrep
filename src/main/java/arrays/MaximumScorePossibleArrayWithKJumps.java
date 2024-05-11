package arrays;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

// https://www.geeksforgeeks.org/maximum-score-possible-from-an-array-with-jumps-of-at-most-length-k/
public class MaximumScorePossibleArrayWithKJumps {

    static int MAX = Integer.MIN_VALUE;

    public static void main(String[] args) {
        int[] arr = {100, -30, -50, -15, -20, -30};
        System.out.println(findMaxScoreDPQ(arr, 3));
        //findMaxScore(arr, 0, 3, 0);
        //System.out.println(MAX);

        arr = new int[]{-44, -17, -54, 79};
        System.out.println(findMaxScoreDP(arr, 2));
//        MAX = Integer.MIN_VALUE;
//        findMaxScore(arr, 0, 2, 0);
//        System.out.println(MAX);
    }

    private static void findMaxScore(int[] arr, int i, int k, int score) {

        if(i == arr.length-1) {
            MAX = Math.max(MAX, score + arr[i]);
            return;
        }

        for(int j = 1; j <= k && i+j < arr.length; j++) {
            findMaxScore(arr, i+j, k, score+arr[i]);
        }
    }

    private static int findMaxScoreDP(int[] arr, int k) {
        int[] dp = new int[arr.length];
        Arrays.fill(dp, Integer.MIN_VALUE);

        dp[arr.length-1] = arr[arr.length-1];

        for(int j = arr.length-2; j >= 0; j--) {
            for(int i = 1; i <= k && i+j < arr.length; i++) {
                dp[j] = Math.max(dp[j], arr[j] + dp[j+i]);
            }
        }

        return dp[0];
    }

    private static int findMaxScoreDPQ(int[] arr, int k) {
        int N = arr.length;
        int K = k;

        Deque<Integer> maxQueue = new LinkedList<>(); // Create a deque to store indices - K elements
        maxQueue.offer(arr.length-1); // Initialize the deque with the first index

        int[] dp = new int[arr.length];

        dp[arr.length-1] = arr[arr.length-1];
        for(int j = arr.length-2; j >= 0; j--) {
            while(!maxQueue.isEmpty() && maxQueue.peekFirst() > j+k) { // we are checking the first element in the queue
                // and the new element index is in bound or not
                maxQueue.pollFirst();
            }
            dp[j] = arr[j] + dp[maxQueue.peekFirst()]; // adding dp value
            while(!maxQueue.isEmpty() && dp[j] >= maxQueue.peekLast()) {
                maxQueue.poll(); // checking the max of the k results
            }
            maxQueue.offer(j);
        }

        return dp[0];
    }
}
