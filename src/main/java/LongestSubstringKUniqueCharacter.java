import java.util.*;

public class LongestSubstringKUniqueCharacter {

    static class Element {
        int index;
        char ch;

        Element(int index, char ch) {
            this.index = index;
            this.ch = ch;
        }
    }

    public static void main(String[] args) {
        String s = "aabacbebebe";
        int k = 3;

        System.out.println(longestSubstringKUniqueCharacter(s, k));
    }

    private static int longestSubstringKUniqueCharacter(String s, int k) {

        int ans = 0;
        int start = 0; int end = 0;
        Map<Character, Integer> map = new HashMap<>();
        int len = 0;

        int n = s.length();
        for(; end < n; end++) {
            map.put(s.charAt(end), map.getOrDefault(s.charAt(end), 0)+1);
            if(map.size() > k) {
                // calculate current
                ans = Math.max(ans, end-start);
                // check what to remove
                while(map.size() != k) {
                    int count = map.get(s.charAt(start));
                    if(count-1 > 0) {
                        map.put(s.charAt(start), count-1);
                    } else {
                        map.remove(s.charAt(start));
                    }
                    start += 1;
                }
            }

        }
        ans = Math.max(ans, end-start);
        return ans;
    }

}
