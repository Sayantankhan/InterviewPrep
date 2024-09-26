package company.google;
import java.util.*;
public class RottenTomato {

    class Tuple {
        int x;
        int y;
        int level;

        Tuple(int x, int y, int l) {
            this.x = x;
            this.y = y;
            this.level = l;
        }

    }

    public int orangesRotting(int[][] grid) {

        int healthy_tomato = 0;
        int rotten_tomato = 0;
        int count = 0;

        int x_length = grid.length;
        int y_length = grid[0].length;

        Queue<Tuple> q = new LinkedList();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == 1) {
                    healthy_tomato++;
                }
                if(grid[i][j] == 2) {
                    q.add(new Tuple(i,j,0));
                }
            }
        }

        if(healthy_tomato == 0) {
            return 0;
        }

        while(!q.isEmpty()) {

            Tuple t = q.poll();
            boolean doesContamination = false;
            //x+1, x-1, y+1, y-1

            if(t.x + 1 < x_length && grid[t.x+1][t.y] == 1){
                healthy_tomato--;
                grid[t.x+1][t.y] = 2;
                q.add(new Tuple(t.x+1,t.y, t.level+1));
                doesContamination = true;
            }
            if(t.x - 1 >= 0 && grid[t.x-1][t.y] == 1){
                healthy_tomato--;
                grid[t.x-1][t.y] = 2;
                q.add(new Tuple(t.x-1,t.y, t.level+1));
                doesContamination = true;
            }
            if(t.y + 1 < y_length && grid[t.x][t.y+1] == 1){
                healthy_tomato--;
                grid[t.x][t.y+1] = 2;
                q.add(new Tuple(t.x,t.y+1, t.level+1));
                doesContamination = true;
            }
            if(t.y - 1 >= 0 && grid[t.x][t.y-1] == 1){
                healthy_tomato--;
                grid[t.x][t.y-1] = 2;
                q.add(new Tuple(t.x,t.y-1, t.level+1));
                doesContamination = true;
            }

            count = t.level;
            System.out.println(t.x + " :: " + t.y + " : " + count);
        }

        if(healthy_tomato != 0) {
            return -1;
        }

        return count;


    }
}
