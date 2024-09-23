package arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// count of occurance Annagrams
// https://www.geeksforgeeks.org/problems/count-occurences-of-anagrams5839/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card
public class CountOccurencesofAnagrams {

    boolean isAnagram(int[] ch, HashMap<Character, Integer> pat) {

        for(Map.Entry<Character, Integer> entry : pat.entrySet()) {
            if(ch[entry.getKey() - 'a'] != entry.getValue()) return false;
        }
        return true;
    }

    int search(String pat, String txt) {
        // code here
        int window = pat.length();
        int[] chArr = new int[26];
        int count = 0;


        HashMap<Character, Integer> patmap =  new HashMap();
        for(int i = 0; i < pat.length(); i++) {
            patmap.put(pat.charAt(i), patmap.getOrDefault(pat.charAt(i), 0) + 1);
        }

        int i = 0, j = 0;
        for(; j < window; j++) {
            chArr[txt.charAt(j) - 'a'] += 1;
        }
        j--;
        while(j < txt.length()) {
            if(isAnagram(chArr, patmap)) count++;

            chArr[txt.charAt(i) - 'a'] -= 1;
            i++;
            j++;
            if (j < txt.length()) chArr[txt.charAt(j) - 'a'] += 1;
        }
        return count;
    }

    public static void main(String[] args) {
        // System.out.println(new CountOccurencesofAnagrams().search("for", "forxxorfxdofr"));
        System.out.println(new CountOccurencesofAnagrams().search("aaba", "aabaabaa"));
    }
}
