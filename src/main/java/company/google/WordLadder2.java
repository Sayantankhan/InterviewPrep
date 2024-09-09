package company.google;

import java.util.function.BiPredicate;
import java.util.*;

public class WordLadder2 {

    static BiPredicate<String, String> isAdjacentContOne = (s1, s2) -> {
        int count = 0;

        int len = s1.length() < s2.length() ? s1.length() : s2.length();

        for(int i = 0; i < len; i++) {
            if(s1.charAt(i) != s2.charAt(i)) {
                count++;
                if (count > 1) return false;
            }
        }
        if(len < s1.length()) count += (s1.length() - len);
        if(len < s2.length()) count += (s2.length() - len);

        if(count > 1) return false;
        else return true;
    };

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<String> lists = new ArrayList<String>();
        List<List<String>> result = new ArrayList<List<String>>();

        boolean visited[] = new boolean[wordList.size()];
        List<String> res = new ArrayList<String>() {{ add(beginWord); }};
        findLaddersRec(beginWord, endWord, visited, wordList, result, res);

        return result;
    }

    public List<List<String>> findLaddersImproved(String beginWord, String endWord, List<String> wordList) {

        Set wordSet = new HashSet(wordList);
        int level = 0;
        Queue<List<String>> queue = new LinkedList<>();
        queue.add(new ArrayList<>() {{ add(beginWord); }});

        List<String> lists = new ArrayList<String>();
        List<List<String>> result = new ArrayList<List<String>>();

        List<String> usedOnLevel = new ArrayList<>();

        while(!queue.isEmpty()) {
            List<String> levelNodes = queue.poll();
            if(levelNodes.size() > level) {
                level++;
                if(!usedOnLevel.isEmpty()) {
                    for(String s : usedOnLevel) {
                        wordSet.remove(s);
                    }
                    usedOnLevel.clear();
                }
            }

            // check for the last node == endWord
            String s = levelNodes.get(levelNodes.size()-1);
            if(s.equals(endWord)) {
                if(result.size() == 0 || result.get(0).size() == levelNodes.size()) {
                    result.add(levelNodes);
                }
                break;
            }

            // checking all the possibilites for a word
            for(int i = 0; i < s.length(); i++) {
                for(char c = 'a'; c <= 'z'; c++) {
                    char[] ch = s.toCharArray();
                    ch[i] = c;
                    String newString = String.valueOf(ch);
                    if(wordSet.contains(newString)) {
                        levelNodes.add(newString);
                        usedOnLevel.add(newString);
                        queue.add(new ArrayList<>(levelNodes));
                        levelNodes.remove(levelNodes.size()-1);
                    }
                }
            }
        }


        return result;
    }

    // ["hot","dot","dog","lot","log","cog"]
    void findLaddersRec(String beginWord, String endWord, boolean[] visited, List<String> wordList, List<List<String>> result, List<String> res) {

        if(beginWord.equals(endWord)) {
            if(result.isEmpty() || result.get(0).size() == res.size()) result.add(new ArrayList<>(res));
            else {
                if(result.get(0).size() > res.size()) {
                    result.clear();
                    result.add(new ArrayList<>(res));
                }
            }
            return;
        }

        for(int i = 0; i < visited.length; i++) {
            if(isAdjacentContOne.test(wordList.get(i), beginWord) && !visited[i]) {
                res.add(wordList.get(i));
                String temp = beginWord;
                beginWord = wordList.get(i);
                visited[i] = true;
                findLaddersRec(beginWord, endWord, visited, wordList, result, res);
                visited[i] = false;
                res.remove(res.size()-1);
                beginWord = temp;
            }

        }

    }

    public static void main(String[] args) {
        System.out.println(isAdjacentContOne.test("dot","log"));
        // System.out.println(new WordLadder2().findLadders("hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog")));
        System.out.println(new WordLadder2().findLaddersImproved("a", "c", List.of("a", "b", "c")));
        System.out.println(new WordLadder2().findLaddersImproved("qa", "sq", List.of("si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye")));
    }
}
