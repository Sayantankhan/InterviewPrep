import java.util.*;

public class ShortestWordDistance {
    // faster - O(N) O(1)
    public int shortestWordDistance1(String[] wordsDict, String word1, String word2) {
        // diff between Short Word Distance 1 and this is
        // word1 and word2 may be the same. It is guaranteed that they represent two individual words in the list.

        // rather than storing in a list and then iterate over
        // we could have two pointer, one holds the prev index and another one holds the next index
        int ans = Integer.MAX_VALUE;
        int prev = -1; // another one i will calculate on the fly

        for(int i=0; i<wordsDict.length; i++) {
            if(wordsDict[i].equals(word1) ||
                    wordsDict[i].equals(word2) ) {
                if(prev != -1 &&
                        (!wordsDict[prev].equals(wordsDict[i]) || word1.equals(word2))) {
                    ans = Math.min(ans, i - prev);
                }
                prev = i;
            }
        }

        return ans;
    }

    // - O(N) O(N)
    public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
        // diff between Short Word Distance 1 and this is
        // word1 and word2 may be the same. It is guaranteed that they represent two individual words in the list.

        Map<String, List<Integer>> map = new HashMap<>();

        for(int i = 0; i < wordsDict.length; i++) {
            map.computeIfAbsent(wordsDict[i], k -> new ArrayList<>()).add(i);
        }


        if(word1.equals(word2)) {
            List<Integer> l1 = map.get(word1);
            int p = 0;
            int ans = Integer.MAX_VALUE;
            while(p+1 != l1.size()) {
                ans = Math.min(ans, Math.abs(l1.get(p) - l1.get(p+1)));
                p++;
            }
            return ans;

        } else {
            List<Integer> l1 = map.get(word1);
            List<Integer> l2  = map.get(word2);
            int p = 0;
            int q = 0;
            int ans = Integer.MAX_VALUE;
            while(p != l1.size() && q != l2.size()) {
                ans = Math.min(ans, Math.abs(l1.get(p) - l2.get(q)));
                if( l1.get(p) > l2.get(q)) q++;
                else p++;
            }

            return ans;
        }
    }
}
