package dp;

import java.util.stream.IntStream;

// https://leetcode.com/problems/palindrome-partitioning-ii/description/
public class PalindromePartitioningII {

    public static int minCutDP(String s) {
        // Front partition
      // return minCutRec(s, 0) - 1;

        int[] dp = new int[s.length()+1];
        dp[s.length()] = 0;

        for(int i = s.length()-1; i >= 0; i--) {
            int min = Integer.MAX_VALUE;
            for(int j = i; j < s.length(); j++) {
                String temp = s.substring(i, j+1);
                if(isPallindrom(temp)) {
                    min = Math.min(min, 1 + dp[j+1]);
                }
            }
            dp[i] = min;
        }

        return dp[0] - 1;
    }

    public static int minCut(String s) {
        // validate input
        if (s == null || s.length() <= 1) {
            return 0;
        }
        // dp
        int N = s.length();
        int[] dp = IntStream.range(0, N).toArray(); // initial value: dp[i] = i

        for (int mid = 1; mid <  N; mid++) { // iterate through all chars as mid point of palindrome
            // CASE 1. odd len: center is at index mid, expand on both sides
            for (int start = mid, end = mid; start >= 0 && end < N && s.charAt(start) == s.charAt(end); start--, end++) {
                int newCutAtEnd = (start == 0) ? 0 : dp[start - 1] + 1;
                dp[end] = Math.min(dp[end], newCutAtEnd);
            }
            // CASE 2: even len: center is between [mid-1,mid], expand on both sides
            for (int start = mid - 1, end = mid; start >= 0 && end < N && s.charAt(start) == s.charAt(end); start--, end++) {
                int newCutAtEnd = (start == 0) ? 0 : dp[start - 1] + 1;
                dp[end] = Math.min(dp[end], newCutAtEnd);
            }
        }
        return dp[N - 1];
    }


    // TC o(N * (k^n))
    public static int minCutRec(String s, int index) {
        if(index == s.length()) return 0;

        int min = Integer.MAX_VALUE;

        for(int i = index; i < s.length(); i++) {
            String temp = s.substring(index, i+1);
            if(isPallindrom(temp)) {
                min = Math.min(min, 1 + minCutRec(s, i+1));
            }
        }

        return min;
    }

    static boolean isPallindrom(String s) {
        StringBuilder sb = new StringBuilder(s);
        return s.equals(sb.reverse().toString());
    }

    public static void main(String[] args) {

        System.out.println(minCut("abb"));
        System.out.println(minCut("bbbbb"));
        System.out.println(minCut("ababababababababababababcbabababababababababababa"));
    }
}
