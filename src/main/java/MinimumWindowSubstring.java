import java.util.*;

public class MinimumWindowSubstring {
    // Minimum Window Substring
    public String minWindow(String s, String t) {
        char[] chArr = t.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for(char ch : chArr) {
            map.put(ch ,map.getOrDefault(ch, 0) + 1);
        }

        int len = Integer.MAX_VALUE;
        int start = 0;
        int[] index = {-1, -1}; // left-index, right-index


        int size = map.size(); // unique element present in t
        int counter = 0; // unique element present in s to match t

        Map<Character, Integer> wc = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            wc.put(ch, wc.getOrDefault(ch, 0)+1);

            if(
                    map.containsKey(ch) &&
                            wc.get(ch).intValue() == map.get(ch).intValue()
            ) {
                counter++;
            }
            System.out.println(counter);
            while(start <= i && counter == size) {
                ch = s.charAt(start);
                if(len > i - start + 1) {
                    len = i - start + 1;
                    index[0] = start;
                    index[1] = i;
                }

                wc.put(ch, wc.get(ch)-1);
                if(
                        map.containsKey(ch) && wc.get(ch).intValue() < map.get(ch).intValue()
                ) {
                    counter--;
                }

                start ++;
            }
        }

        System.out.println(index[0] + " " + index[1]);
        if(index[0] == -1 && index[1] == -1) return "";
        return s.substring(index[0], index[1]+1);
    }
}
