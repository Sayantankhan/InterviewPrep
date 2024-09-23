package string.dp;

import java.util.Arrays;

public class PalindromePartitioning {

    static boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder(s);

        if(s.equals(sb.reverse().toString())) return true;
        else return false;
    }

    static boolean isPalindrome(String s, int start, int end) {

        while(start < end) {
            if(s.charAt(start++) != s.charAt(end--)) return false;
        }
        return true;
    }

    static int total = Integer.MAX_VALUE;

    static int palindromicPartition(String str)
    {
        int n = str.length();
//        palindromicPartitionWithCut(str, 0, n-1, "", 0);
//        return total - 1;
        int[][] dp = new int[n][n];
        Arrays.stream(dp).forEach(a -> Arrays.fill(a, -1));

        for(int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }

        for(int i = 1; i < n; i++) {
            for(int j = 0; j < n - i; j++) {
                //System.out.print("(" + j + "," + (i + j) + ") ");
                if(isPalindrome(str, j, i+j)) dp[j][i+j] = 0;
                else {
                    int res = Integer.MAX_VALUE;
                    for(int x = j; x < i+j; x++) {
                        res = Math.min(res, dp[j][x] + dp[x+1][i+j] + 1);
                    }
                    dp[j][i+j] = res;
                }
            }
        }

        return dp[0][n-1];
    }

    static void palindromicPartitionWithCut(String str, int sindex, int eindex, String temp, int count) {

        if(sindex >= str.length()) {
            total = Math.min(total, count);
            return;
        }

        String s = "" ;
        for(int i = sindex; i <= eindex; i++) {
            s += str.charAt(i);

            if(isPalindrome(s)) {
                palindromicPartitionWithCut(str, i + 1, eindex, s, count + 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(palindromicPartition("ababbbabbababa"));
        System.out.println(palindromicPartition("aaabba"));
        // System.out.println(palindromicPartition("abba"));
    }
}
