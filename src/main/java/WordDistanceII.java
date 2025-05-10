import java.util.*;

public class WordDistanceII {
    final Map<String,List<Integer>> map ;
    public WordDistanceII(String[] wordsDict) {
        map = new HashMap<>();
        // precomputing indices
        for(int i = 0; i < wordsDict.length; i++) {
            map.computeIfAbsent(wordsDict[i], k -> new ArrayList<Integer>()).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        // two pointer to figure out which combination gives min
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);

        int p1 = 0,p2 = 0;
        int diff = Integer.MAX_VALUE;
        while(p1 < list1.size() && p2 < list2.size()) {
            diff = Math.min(diff, Math.abs(list1.get(p1)-list2.get(p2)));
            if(list1.get(p1) > list2.get(p2)) p2++;
            else p1++;
        }

        return diff;
    }
}
