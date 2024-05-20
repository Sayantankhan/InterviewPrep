package string.dp;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

// https://leetcode.com/problems/count-different-palindromic-subsequences/description/
public class CountDifferentPalindromicSubsequences {

    Predicate<StringBuilder> isPallindrome = (s) -> !s.toString().equals("") && s.toString().equals(s.reverse().toString());

    public int countPalindromicSubsequences(String s) {
        Set<String> set = new HashSet<String>();
        countPalindromicSubsequencesRec(s, 0, set, "");
        System.out.println(set);
        return set.size();
    }

    void countPalindromicSubsequencesRec(String s, int index, Set<String> set, String temp) {

        if(isPallindrome.test(new StringBuilder(temp))) {
            set.add(temp);
        }

        if(index >= s.length()) return;

        countPalindromicSubsequencesRec(s, index+1, set, temp + String.valueOf(s.charAt(index)));

        countPalindromicSubsequencesRec(s, index+1, set, temp);
    }

    int countPalindromicSubsequencesDP(String s) {
        // i == j  dp[i][j] = 1
        // bcc
        // dp[i][j] = dp[i][j-1] + dp[i+1][j] - dp[i+1][j-1]
        // dp[0][2] = dp[0][1] + dp[1][2] - dp[1][1]
        //            cnt(bc)  + cnt(cc)  - cnt(c)

        int[][] dp = new int[s.length()][s.length()];

        for(int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }

        for(int i = 0; i < s.length(); i++) {
            for(int j = i+1; j < s.length(); j++) {
                dp[i][j] = dp[i][j-1] + dp[i+1][j] - dp[i+1][j-1];
            }
        }
        //0,1  1,2 , 2,3

        return dp[s.length()-1][s.length()-1];


    }

    public static void main(String[] args) {
        new CountDifferentPalindromicSubsequences().countPalindromicSubsequencesDP("bccb");
    }
}
