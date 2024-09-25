package company.google;
import java.util.*;

public class WordSearch {

    class Tuple {
        int x;
        int y;

        Tuple(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public boolean exist(char[][] board, String word) {
        if(word.length() <= 0) return false;
        if(board.length == 0) return false;

        char c = word.charAt(0);
        List<Tuple> lists = new ArrayList<Tuple>();

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == c) lists.add(new Tuple(i,j));
            }
        }

        Iterator<Tuple> itr = lists.iterator();
        boolean[][] visited = new boolean[board.length][board[0].length];
        while(itr.hasNext()) {
            Tuple t = itr.next();
            visited[t.x][t.y] = true;
            if(startDFS(board, word, t, 1, visited)) return true;
            visited[t.x][t.y] = false;
        }

        return false;
    }

    boolean startDFS(char[][] board, String word, Tuple t, int index, boolean[][] visited) {
        if(index == word.length()) return true;

        int[] x = {1, -1, 0, 0};
        int[] y = {0, 0, 1, -1};

        boolean res = false;
        for(int i = 0; i < x.length; i++) {
            if((t.x + x[i] >= 0 && t.x + x[i] < board.length)
                    && (t.y + y[i] >= 0 && t.y + y[i] < board[0].length)
                    && !visited[t.x + x[i]][t.y + y[i]]) {

                if(board[t.x + x[i]][t.y + y[i]] == word.charAt(index)) {
                    visited[t.x + x[i]][t.y + y[i]] = true;
                    res = res || startDFS(board, word, new Tuple(t.x + x[i], t.y + y[i]), index+1, visited);
                    visited[t.x + x[i]][t.y + y[i]] = false;
                }

            }
        }

        return res;
    }
}
