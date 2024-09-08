package company.google;

import java.util.Arrays;

// https://www.geeksforgeeks.org/problems/optimal-strategy-for-a-game-1587115620/1
public class MaxCoinGame {

    int max;
    public long maximumAmount(int arr[], int n) {
        int[][] dp = new int[n][n];
        int sum = Arrays.stream(arr).sum();
        return maximumAmountRec(arr, n,  0, arr.length-1, dp);
    }

    public int maximumAmountRec(int arr[], int k, int start, int end, int[][] dp) {

        if(start > end) {
            return 0;
        }

        if(dp[start][end] != 0) return dp[start][end];

        int res = arr[start] + Math.min(
                maximumAmountRec(arr, k, start+2, end, dp),
                maximumAmountRec(arr, k, start+1, end-1, dp)
        );

        int res2 = arr[end] + Math.min(
                maximumAmountRec(arr, k, start, end-2, dp),
                maximumAmountRec(arr, k, start+1, end-1, dp)
        );

        dp[start][end] = Math.max(res, res2);
        return Math.max(res, res2);
    }

    public int maximumAmountDP(int arr[], int n, int start, int end) {
        int[][] dp = new int[n][n];
        boolean myTurn = true;

        for(int i = 0; i < n; i++) {
            dp[i][i] = arr[i];
        }

        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                if(myTurn) {
                    int res = arr[end] + dp[start][end-1];
                    int res2 = arr[start] + dp[start+1][end];
                    dp[start][end] = Math.max(res, res2);
                }
            }
        }

        return dp[start][end];
    }

    public static void main(String[] args) {
        System.out.println(new MaxCoinGame().maximumAmount(new int[] {5, 3, 7, 10}, 4));

        System.out.println(new MaxCoinGame().maximumAmountDP(new int[] {5, 3, 7, 10}, 4, 0 , 3));


        System.out.println(new MaxCoinGame().maximumAmount(new int[] {8, 15, 3, 7}, 4));
    }
}
