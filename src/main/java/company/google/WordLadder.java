package company.google;

import java.util.*;

public class WordLadder {

    int wordDiff(String s1, String s2) {
        if(s1.length() > s2.length()) return wordDiff(s2, s1);
        int count = (s2.length() > s1.length()) ? (s2.length() - s1.length()) : 0;

        for (int i = 0 ; i < s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i)) count++;
        }

        return count;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        boolean[] visited = new boolean[wordList.size()];
        int ans = ladderLengthRec(beginWord, endWord, wordList, visited);
        return (ans == Integer.MAX_VALUE) ? 0 : ans;
    }

    // TC - O(n!⋅n⋅m)
    int ladderLengthRec(String word, String endword, List<String> wordList, boolean[] visited) {

        if (word.equals(endword)) {
            return 1;
        }

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < wordList.size(); i++) {
            if(!visited[i]) {
                if(wordDiff(word, wordList.get(i)) == 1) {
                    visited[i] = true;
                    int hop = ladderLengthRec(wordList.get(i), endword, wordList, visited);
                    if (hop != Integer.MAX_VALUE) {
                        min = Math.min(min, 1 + hop);
                    }
                    visited[i] = false;
                }
            }
        }

        return min;

    }

    // The approach here is bfs , as we can see
    // hit -> hot -> dot -> dog -> cog
    //            -> lot -> log -> cog
    // this is a graph based solution, now exploring every possible combination which is n! , we can generate
    // all the neighbours and check that exists or not - if m is the length and then complexity will be (m^2 . n)
    // BFS is effective as we have to find shortest route
    int ladderLengthImporved(String beginWord, String endWord, List<String> wordList) {

        Set<String> set = new HashSet<>(wordList);
        Queue<String> q = new LinkedList<>();

        q.add(beginWord);
        set.remove(beginWord);
        int level = 0;

        while(!q.isEmpty()) {
            int size = q.size();
            level++;

            for(int i = 0; i < size; i++) {
                String s = q.poll();
                if(s.equals(endWord)) return level;
                List<String> neighbour =  neightbours(s);
                for(String n : neighbour) {
                    if(set.contains(n)) {
                        q.add(n);
                        set.remove(n);
                    }
                }
            }

        }

        return 0;

    }

    // Generating all the possible conbinations
    List<String> neightbours(String s) {
        List<String> neighbour = new ArrayList<>();
        char[] charArr = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            char temp = charArr[i];
            for(char j = 'a'; j <= 'z'; j++) {
                charArr[i] = j;
                neighbour.add(new String(charArr));

            }
            charArr[i] = temp;
        }
        return neighbour;
    }


    // More Improve Bi-Directional BFS
    int ladderLengthImporvedBiDirectional(String beginWord, String endWord, List<String> wordList) {

        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) return 0;

        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();

        beginSet.add(beginWord);
        endSet.add(endWord);

        int level = 1;

        while(!beginSet.isEmpty() && !endSet.isEmpty()) {
            if(beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }

            Set<String> newBeginSet = new HashSet<>();
            for(String word: beginSet) {
                List<String> neighbour = neightbours(word);
                for(String w: neighbour) {
                    if(endSet.contains(w)) return level+1;
                    if(set.contains(w)) {
                        newBeginSet.add(w);
                        set.remove(w);
                    }
                }
            }

            beginSet = newBeginSet;
            level++;
        }

        return 0;

    }

    public static void main(String[] args) {
        WordLadder wl = new WordLadder();
        System.out.println(wl.ladderLengthImporvedBiDirectional("hit", "cog", List.of("hot","dot","dog", "lot", "log", "cog")));
    }
}
