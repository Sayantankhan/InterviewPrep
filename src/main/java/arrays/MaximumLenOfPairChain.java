package arrays;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode.com/problems/maximum-length-of-pair-chain/description/
public class MaximumLenOfPairChain {

    static int max = Integer.MIN_VALUE;
    public static int findLongestChain(int[][] pairs) {

        boolean[] visited = new boolean[pairs.length];

        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] > o2[0]) return 1;
                else return -1;
            }
        });

        int[] dp = new int[pairs.length+1];
        return findLongestChainDP(pairs, dp);
         // return findLongestChainRec(pairs, Integer.MIN_VALUE, 0);
    }

    private static int findLongestChainDP(int[][] pairs, int[] dp) {
        dp[pairs.length-1] = 1;

        for(int i = pairs.length-2; i >= 0; i--) {
            int[] temp = pairs[i];
            int tempIndex = i+1;
            while(tempIndex!= pairs.length && temp[1] >= pairs[tempIndex][0]) {
                tempIndex++;
            }

            dp[i] = Math.max(dp[i+1], 1 + dp[tempIndex]); // we are carrying maximum of 1+nextelement or immediate next i+1
        }

        return dp[0];
    }

    private static int findLongestChainRec(int[][] pairs, int el, int i) {

        if(i == pairs.length) return 0;

        // dont take the pair
        int pairWithout = findLongestChainRec(pairs, el, i+1);
        int pairWith = Integer.MIN_VALUE;

        // lets take the pair
        if(el < pairs[i][0]) {
            el = pairs[i][1];
            // pairWith = 1 + findLongestChainRec(pairs, visited, el, i+1, dp);
            pairWith = 1 + findLongestChainRec(pairs, el, i+1);
        }

       return Math.max(pairWith, pairWithout);
    }

    public static void main(String[] args) {
        System.out.println(findLongestChain(new int[][]{{1, 2}, {2, 3}, {3, 4}}));
        System.out.println(findLongestChain(new int[][]{{1, 2}, {7, 8}, {4, 5}}));
        System.out.println(findLongestChain(new int[][]{{-10,-8},{8,9},{-5,0},{6,10},{-6,-4},{1,7},{9,10},{-4,7}}));
        System.out.println(findLongestChain(new int[][]{{7,9},{4,5},{7,9},{-7,-1},{0,10},{3,10},{3,6},{2,3}}));
    }
}
