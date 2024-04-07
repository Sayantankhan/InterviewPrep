package string;

import util.Utility;

import java.util.Arrays;

public class LongestCommonSubsequence {

    /*
     * Power Set
     */
    public static int longestCommonSubsequence(String text1, String text2) {
//        int[][] dp = new int[text1.length()][text2.length()];
//        return longestCommonSubsequenceRec(text1, text2, text1.length()-1, text2.length()-1, dp);

        return longestCommonSubsequenceDP(text1, text2);
    }

    /*
     * memorization : Good still getting time limit exception
     */
    private static int  longestCommonSubsequenceRec(String text1, String text2, int index1, int index2, int[][] dp) {

        if(index1 < 0 || index2 < 0) {
            return 0;
        }

        if(text1.charAt(index1) == text2.charAt(index2)) {
            // return 1 + longestCommonSubsequenceRec(text1, text2, index1 - 1, index2 -1, dp);
            if(dp[index1][index2] == 0) {
                dp[index1][index2] = 1 + longestCommonSubsequenceRec(text1, text2, index1 - 1, index2 -1, dp);
            }
            return dp[index1][index2];
        } else {
            // return Math.max(longestCommonSubsequenceRec(text1, text2, index1 - 1, index2, dp),
            //               longestCommonSubsequenceRec(text1, text2, index1, index2 -1, dp))
            if(dp[index1][index2] == 0) {
                dp[index1][index2] = Math.max(
                        longestCommonSubsequenceRec(text1, text2, index1 - 1, index2, dp),
                        longestCommonSubsequenceRec(text1, text2, index1, index2 -1, dp)
                );
            }

            return dp[index1][index2];
        }
    }

    /*
     * DP Approach
     */
    private static int  longestCommonSubsequenceDP(String text1, String text2) {

        int xlength = text1.length();
        int ylength = text2.length();

        // Adding extra space to store the neg value : 1 -> len+1 rather 0 -> len
        int[][] dp = new int[xlength+1][ylength+1];

        // initialize with base condition
        for(int i = 0; i <= xlength; i++) dp[i][0] = 0;
        for(int i = 0; i <= ylength; i++) dp[0][i] = 0;

        for(int i = 1; i < xlength+1; i++) {
            for(int j = 1; j < ylength+1; j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[xlength][ylength];
    }

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(longestCommonSubsequence("abcde", "ace"), 3);
        Utility.assertTrue(longestCommonSubsequence("abc", "abc"), 3);
        Utility.assertTrue(longestCommonSubsequence("abc", "def"), 0);
        Utility.assertTrue(longestCommonSubsequence("mhunuzqrkzsnidwbun", "szulspmhwpazoxijwbq"), 6);
        Utility.assertTrue(longestCommonSubsequence(
                "ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc",
                "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"
                ), 0);
    }
}
