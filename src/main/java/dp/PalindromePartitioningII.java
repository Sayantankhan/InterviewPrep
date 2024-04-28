package dp;

// https://leetcode.com/problems/palindrome-partitioning-ii/description/
public class PalindromePartitioningII {

    public static int minCut(String s) {
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
        System.out.println(minCut("sbb"));
        System.out.println(minCut("ababababababababababababcbabababababababababababa"));
        System.out.println(isPallindrom("ababababababababababababcbabababababababababababa"));
        System.out.println(minCut("aaabaa"));
    }
}
