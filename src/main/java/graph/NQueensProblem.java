package graph;

import util.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/n-queens/description/
public class NQueensProblem {

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static List<List<String>> solveNQueens(int n) {

        boolean[][] visited = new boolean[n][n];
        List<List<Point>> list = new ArrayList<List<Point>>();


            for(int j = 0; j < n; j++) {
                dfs(visited, 0, j, list, n, new ArrayList<Point>());
                // clean up the array
                Arrays.stream(visited).forEach(row -> Arrays.fill(row, false));
            }

        List<List<String>> strlist = new ArrayList<>();

        for(List points : list) {
            List<String> lst = new ArrayList<>();
            for(Object point : points) {
                Point p = (Point) point;
                StringBuilder sb = new StringBuilder("");
                for(int i = 0; i < n; i++) {
                    if(p.x == i) sb.append("Q");
                    else sb.append(".");
                }
                lst.add(sb.toString());
            }
            strlist.add(lst);
        }
        return strlist;
    }

    private static void dfs(boolean[][] visited, int i, int j, List<List<Point>> list, int noOfQueen, List<Point> l) {

        if(visited[i][j]) return;
        l.add(new Point(i, j));

        if(l.size() == noOfQueen) {
            list.add(new ArrayList<>(l));
            return;
        }

        // horizontal move;
        for(int a = 0; a < noOfQueen; a++)  visited[a][j] = true;

        // vertical move
        for(int a = 0; a < noOfQueen; a++) visited[i][a] = true;

        // cross move X
        // x,y -> (x+1, y+1) || (x-1, y-1) || (x-1, y+1) || (x+1, y-1)
        int tx = i;
        int ty = j;
        while(tx+1 < noOfQueen && ty+1 < noOfQueen) {
            visited[tx + 1][ty + 1] = true;
            tx++;
            ty++;
        }

        tx = i;
        ty = j;
        while(tx-1 >= 0 && ty-1 >= 0) {
            visited[tx - 1][ty - 1] = true;
            tx--;
            ty--;
        }

        tx = i;
        ty = j;
        while(tx-1 >= 0 && ty+1 < noOfQueen) {
            visited[tx - 1][ty + 1] = true;
            tx--;
            ty++;
        }

        tx = i;
        ty = j;
        while(ty-1 >= 0 && tx+1 < noOfQueen) {
            visited[tx + 1][ty - 1] = true;
            tx++;
            ty--;
        }

        boolean[][] tempvisited = new boolean[visited.length][];
        for(int a = 0; a < noOfQueen; a++) {
            tempvisited[a] = visited[a].clone();
        }

        for(int a = i+1; a < noOfQueen; a++) {
            for(int b = 0; b < noOfQueen; b++) {
                if(!visited[a][b]) {
                    dfs(visited, a, b, list, noOfQueen, l);
                    l.remove(l.size()-1);
                    visited = tempvisited;
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(solveNQueens(4), List.of(List.of(".Q..","...Q","Q...","..Q."), List.of("..Q.","Q...","...Q",".Q..")));
    }
}
