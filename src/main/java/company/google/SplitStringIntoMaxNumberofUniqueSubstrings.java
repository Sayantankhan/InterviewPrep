package company.google;

import java.util.*;

// https://leetcode.com/problems/split-a-string-into-the-max-number-of-unique-substrings/description/
public class SplitStringIntoMaxNumberofUniqueSubstrings {

    public int maxUniqueSplit(String s) {
        Set<String> set = new HashSet<>();
        return maxUniqueSplitRec(s, set, 0);
    }

    public int maxUniqueSplitRec(String s, Set<String> set, int index) {
        if(s.length() == index) return 0;

        int max = Integer.MIN_VALUE;

        for(int i = index; i < s.length(); i++) {
            String t = s.substring(index, i+1);
            if(!set.contains(t)) {
                set.add(t);
                max = Math.max(max, 1 + maxUniqueSplitRec(s, set, i+1));
                set.remove(t);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        SplitStringIntoMaxNumberofUniqueSubstrings s = new SplitStringIntoMaxNumberofUniqueSubstrings();
        System.out.println(s.maxUniqueSplit("abcabccc"));
    }
}
