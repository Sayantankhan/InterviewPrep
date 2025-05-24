import java.util.function.Predicate;

public class MaximumNumberofnonoverlappingPalindromeSubstrings {
    //Maximum Number of Non-overlapping Palindrome Substrings
    // Max No of non-overlaping Pallindromic

    Predicate<String> isPalindrome = s -> s.equals(new StringBuilder(s).reverse().toString());

    public int maxPalindromes(String s, int k) {
        int len = s.length();
        int[][] dp = new int[len][len];
        // return maxPallindromesRec(s, k, 0, len, dp);
        // return maxPallindromesDP(s, k, len);
        return maxPalindromesgreedy(s, k);
    }

    int maxPallindromesRec(String s, int k, int index, int n, int[][] dp)  {
        if(index == n) return 0;

        if(dp[index][n-1] != 0) return dp[index][n-1];

        int noOfcuts = 0;
        for(int i = index; i < n; i++) {
            String sub = s.substring(index, i+1);
            if(isPalindrome.test(sub) && sub.length() >= k) {
                noOfcuts = Math.max(noOfcuts, 1 + maxPallindromesRec(s, k, i+1, n, dp));
            } else {
                noOfcuts = Math.max(noOfcuts, maxPallindromesRec(s, k, i+1, n, dp));
            }
        }

        dp[index][n-1] = noOfcuts;
        return noOfcuts;
    }

    int maxPallindromesDP(String s, int k, int n)  {

        int noOfcuts = 0;
        int[] dp = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = dp[i + 1]; // Option 1: skip this char

            for (int j = i; j < n; j++) {
                if (j - i + 1 >= k) {
                    String sub = s.substring(i, j + 1);
                    if (isPalindrome.test(sub)) {
                        dp[i] = Math.max(dp[i], 1 + dp[j + 1]); // Option 2: take this palindrome
                    }
                }
            }
        }


        return dp[0];
    }

    public int maxPalindromesgreedy(String s, int k) {
        int count = 0;
        int idx = 0;

        // greedily check for palindromes of length k or k + 1
        while (idx <= s.length() - k) {
            if (isPalindrome.test(s.substring(idx, idx + k))) {
                count++;
                idx += k;
            } else if (idx < s.length() - k && isPalindrome.test(s.substring(idx, idx + k + 1))) {
                count++;
                idx += k + 1;
            } else {
                idx++;
            }
        }
        return count;
    }
}
