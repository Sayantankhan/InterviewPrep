package string.dp;

import org.apache.commons.io.FileUtils;
import util.Utility;

// https://leetcode.com/problems/maximize-number-of-subsequences-in-a-string/description/
public class MaximizeNumberSubsequencesString {

    public static long maximumSubsequenceCount(String text, String pattern) {
        // Brute Force Approach will not work for huge string size
//        return
//                Math.max(maximumSubsequenceCountBruteForce(String.valueOf(pattern.charAt(0)) + text, pattern),
//                        maximumSubsequenceCountBruteForce(text + String.valueOf(pattern.charAt(1)) , pattern));

        return Math.max(findPattern(String.valueOf(pattern.charAt(0)) + text, pattern),
                findPattern(text + String.valueOf(pattern.charAt(1)), pattern));

    }

    private static long findPattern(String text, String pattern) {

        long count = 0;
        long ans = 0;

        for(int i = 0; i < text.length(); i++) {
            if(text.charAt(i) == pattern.charAt(0)) count++;
            else if(text.charAt(i) == pattern.charAt(1)) ans += count;
        }


        // if pattern has both same elements
        // if there is n positive number ; sum{n} -> n (n+1)/2; sum{n-1} -> n (n-1)/2
        // Observation if pattern aa ; and text : aaaa
        // | for the first a -> 3 patterns
        // | for the second a -> 2 patterns
        // | for the third a -> 1 pattern
        // total : 3 + 2 + 1 -> 6
        // so it takes two elements to represent a pattern, if there is n element we can say sum(n-1) will be total possible patterns
        if(pattern.charAt(0) == pattern.charAt(1)) {
            ans = (count) * (count-1) / 2;
        }

        return ans;
    }


    private static long maximumSubsequenceCountBruteForce(String text, String pattern) {
        int count = 0;

        for(int i = 0; i < text.length(); i++) {
            if(text.charAt(i) != pattern.charAt(0)) continue;
            for(int j = i+1; j < text.length(); j++) {
                String temp = String.valueOf(text.charAt(i)) + String.valueOf(text.charAt(j));
                if(temp.equals(pattern)) {
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(maximumSubsequenceCount("abdcdbc", "ac"), (long)4);
        Utility.assertTrue(maximumSubsequenceCount("aabb", "ab"), (long)6);
        Utility.assertTrue(maximumSubsequenceCount("iempmp", "mp"), (long)5);
        Utility.assertTrue(maximumSubsequenceCount(Utility.readFile("./src/main/resources/leetcode2207_input.txt"), "ix"), (long)4617755);
        maximumSubsequenceCount(Utility.readFile("./src/main/resources/leetcode2207_input_2.txt"), "zz");

    }
}
