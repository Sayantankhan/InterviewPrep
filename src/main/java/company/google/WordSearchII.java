package company.google;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.*;

// Word Search II
// find all the words in dict in the word - boggle array
public class WordSearchII {

    class Node {
        char ch;
        boolean end;
        Map<Character, Node> children;
        String word;

        Node(char ch, String word) {
            this.ch = ch;
            children = new HashMap<>();
            word = this.word;
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        Set<String> list = new HashSet<>();
        Set<Character> set = Arrays.stream(words).map(word -> word.charAt(0)).collect(Collectors.toSet());
        Map<Character, Set> map = new HashMap<>();

        for(Character ch : set) {
            map.put(ch, new HashSet<String>());
        }

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(set.contains(board[i][j])) {
                    Set<String> s = map.get(board[i][j]);
                    String temp = "" + i + "," + j;
                    s.add(temp);
                }
            }
        }

        for(int i = 0; i < words.length; i++) {
            Set<String> s = map.get(words[i].charAt(0));
            for(String point : s) {
                int x = Integer.valueOf(point.split(",")[0]);
                int y = Integer.valueOf(point.split(",")[1]);

                char ch = board[x][y];
                board[x][y] = '$';
                findWordsDFS(x, y, board, words[i], list, 1);
                board[x][y] = ch;
            }
        }

        return new ArrayList<>(list);
    }

    private void findWordsDFS(int i, int j, char[][] board, String word, Set<String> list , int index) {

        if(index >= word.length()) {
            list.add(word);
            return;
        }

        int x[] = {1, -1, 0, 0};
        int y[] = {0, 0, 1, -1};

        for(int pos = 0; pos < x.length; pos++) {

            if((i + x[pos] >= 0 && i + x[pos] < board.length)
                && (j + y[pos] >= 0 && j + y[pos] < board[0].length)
                && board[i + x[pos]][j + y[pos]] != '$'
                && word.charAt(index) == board[i + x[pos]][j + y[pos]]) {

                    char ch = board[i + x[pos]][j + y[pos]];
                    board[i + x[pos]][j + y[pos]] = '$';
                    findWordsDFS(i + x[pos], j + y[pos], board, word, list, index+1);
                    board[i + x[pos]][j + y[pos]] = ch;
            }

        }

        return;

    }


    public static void main(String[] args) {
        System.out.println(new WordSearchII().findWords(
                new char[][] {
                        {'o','a','a','n'},
                        {'e','t','a','e'},
                        {'i','h','k','r'},
                        {'i','f','l','v'}
                },
                new String[]{"oath","pea","eat","rain"}
        ));
    }
}
