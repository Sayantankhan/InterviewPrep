package company.google;

// Longest Palindromic Subsequence
public class LongestPalindromicSubsequence {

    public int longestPalindromeSubseq(String s) {
        StringBuilder sb = new StringBuilder(s);
        String temp = sb.reverse().toString();

        // Base Condition: s = ".." and temp = null || return 0 -> vise versa
        // both math s.charAt(i) == temp.char(j) -> i+1, j+1
        // else max(i+1,j or i,j+1)

        int n = s.length()+1;
        int[][] dp = new int[s.length()+1][s.length()+1];

        for(int i = n-2; i>= 0; i--) {
            for(int j = n-2; j >= 0; j--) {
                if(s.charAt(i) == temp.charAt(j)) {
                    dp[i][j] = Math.max(dp[i][j], 1 + dp[i+1][j+1]);
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
            }
        }

        return dp[0][0];
    }
}
