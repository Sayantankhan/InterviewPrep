package string;

import util.Utility;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
public class LongestSubstringWithoutRepeatingCharacters {

    /*
     * Finding substring from a string based on condition is a problem of sliding window
     * Our approach is take each character and check whether it is there in the substring we formed,
     * if there then remove till the character present in the substring , and add the same character
     * if not present, we just append the character
     *
     * How to check duplicate in a string in O(1) order ? use Hashmap or HashSet !!!
     */
    public static int lengthOfLongestSubstring(String s) {

        Map<Character, Integer> map = new HashMap<>();
        String temp = "";
        int max = 0;

        for(int i = 0; i < s.length(); i++) {
            char el = s.charAt(i);
            if(map.get(el) == null) {
                temp += String.valueOf(el);
                map.put(el, i);
            } else {
                max = Math.max(max, map.size());
                int count = 0;
                while(count < temp.length() && temp.charAt(count) != el) {
                    map.remove(temp.charAt(count));
                    count++;
                }
                map.put(temp.charAt(count), i);
                temp = temp.substring(count+1);
                temp += String.valueOf(el);
            }
        }
        // this compare is required if there is no duplicate character present in actual string
        return Math.max(max, map.size());
    }

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(lengthOfLongestSubstring("abcabcbb"), 3);
        Utility.assertTrue(lengthOfLongestSubstring("bbbbb"), 1);
        Utility.assertTrue(lengthOfLongestSubstring("pwwkew"), 3);
        Utility.assertTrue(lengthOfLongestSubstring(" "), 1);
    }
}
