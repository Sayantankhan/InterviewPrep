package company.google;

import java.util.function.BiPredicate;
import java.util.*;

public class WordLadder2 {

    // TC - O(NK^2) - N is the number of words in wordList, K is the maximum length of a word
    // SC - O(NK).
    // BFS to create DAG and Backtrack for the path
    public List<List<String>> findLaddersII(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> list = new ArrayList<>();
        Queue<String> q = new LinkedList<>();
        Set<String> wl = new HashSet<>(wordList);

        if(wl.contains(beginWord)) wl.remove(beginWord);

        Map<String, Integer> isEnqueued = new HashMap<String, Integer>();
        isEnqueued.put(beginWord, 1);
        q.offer(beginWord);

        // Creating as DAG
        Map<String, List<String>> adjList = new HashMap<String, List<String>>();
        while(!q.isEmpty()) {
            List<String> visited = new ArrayList<>();
            int len = q.size();

            for(int i = len-1; i >= 0; i--) {
                String currWord = q.peek();
                q.remove();

                List<String> neighbors = neightbours(currWord, wl);
                for(String neighbour: neighbors) {
                    visited.add(neighbour);
                    adjList.computeIfAbsent(neighbour, k -> new ArrayList<String>()).add(currWord);
                    if(!isEnqueued.containsKey(neighbour)) {
                        q.add(neighbour);
                        isEnqueued.put(neighbour, 1);
                    }

                }
            }

            // removing words from prev layer
            for (int i = 0; i < visited.size(); i++) {
                if (wl.contains(visited.get(i))) {
                    wl.remove(visited.get(i));
                }
            }
        }

        // Start Backtracking
        List<String> currPath = new ArrayList<String>();
        currPath.add(endWord);
        System.out.println("BFS");
        backtrack(endWord, beginWord, currPath, list, adjList);
        return list;
    }

    void backtrack(String endWord, String beginWord, List<String> currPath, List<List<String>> list, Map<String, List<String>> adjList) {
        if(endWord.equals(beginWord)) {
            List<String> tempPath = new ArrayList<String>(currPath);
            Collections.reverse(tempPath);
            list.add(tempPath);
        }

        if (!adjList.containsKey(endWord)) return;

        for(String path : adjList.get(endWord)) {
            currPath.add(path);
            backtrack(path, beginWord, currPath, list, adjList);
            currPath.remove(currPath.size()-1);
        }
    }

    List<String> neightbours(String s, Set<String> wl) {
        List<String> neighbour = new ArrayList<>();
        char[] charArr = s.toCharArray();

        for (int i = 0; i < s.length(); i++) {
            char temp = charArr[i];
            for(char j = 'a'; j <= 'z'; j++) {
                charArr[i] = j;
                if(charArr[i] == temp || !wl.contains(String.valueOf(charArr))) continue;
                neighbour.add(new String(charArr));
            }
            charArr[i] = temp;
        }
        return neighbour;
    }


    public static void main(String[] args) {
        System.out.println(new WordLadder2().findLaddersII("hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog")));
        //System.out.println(new WordLadder2().findLaddersImproved("a", "c", List.of("a", "b", "c")));
        //System.out.println(new WordLadder2().findLaddersImproved("qa", "sq", List.of("si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye")));
    }
}
