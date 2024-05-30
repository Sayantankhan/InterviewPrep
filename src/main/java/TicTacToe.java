public class TicTacToe {

    String[][] board;
    TicTacToe(int n) { // boardsize
        board = new String[n][n];
    }

    boolean isWin(String[][] board, int i, int j) {
        int len = board.length;
        String s = board[i][j];

        //i-1,j || i,j || i+1,j
        if(i-1 >=0 && i+1 <len) {
            if(s.equals(board[i - 1][j]) && s.equals(board[i + 1][j])) return true;
        }
        //i+1,j-1, i,j, i-1,j+1
        if(i+1 >=0 && i+1 < len && j-1 >= 0 && j+1 < len) {
            if(s.equals(board[i+1][j-1]) && s.equals(board[i-1][j+1])) return true;
        }
        //i+1,j+1. i,j i-1,j-1
        if(i-1 >=0 && i+1 <len && j+1 < len && j-1 >= 0) {
            if(s.equals(board[i + 1][j + 1]) && s.equals(board[i - 1][j - 1])) return true;
        }
        // i,j-1, i,j , i,j+1
        if(j-1 >=0 && j+1 <len) {
            if(s.equals(board[i][j-1]) && s.equals(board[i][j+1])) return true;
        }

        return false;
    }


    int move(int x, int y, int player) {

        board[x][y] = ""+player;
        boolean res = isWin(board, x, y);
        if(res) return player;
        else return 0;
    }

    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe(3);

        System.out.println(ticTacToe.move(0, 0, 1));
        System.out.println(ticTacToe.move(0, 2, 2));
        System.out.println(ticTacToe.move(2, 2, 1));
        System.out.println(ticTacToe.move(1, 1, 2));
        System.out.println(ticTacToe.move(2, 0, 1));
        System.out.println(ticTacToe.move(1, 0, 2));
        System.out.println(ticTacToe.move(2, 1, 1));
    }
}
