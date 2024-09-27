package arrays.dp;

import java.util.*;
// https://leetcode.com/problems/race-car/description/
// instruction 'A'
//          position += speed
//          speed *= 2
// instruction 'R'
//          position = will be same
//          speed = +/- 1

import java.util.Arrays;

// p = (2^x)-1 [where x stands for no of contigious A]
public class RaceCar {

    public int racecar(int target) {

        int[] dp = new int[target+1];
        Arrays.fill(dp,1,dp.length, -1);
        return raceCar(target, dp);

    }

    public int raceCar(int target, int[] dp){

        if(dp[target] >= 0){
            return dp[target];
        }
        dp[target] = Integer.MAX_VALUE;
        int x = 1, j =1;
        for(; j<target; j=(int) (Math.pow(2, ++x) -1)){

            for(int q=0, p=0; p<j; p=(int) (Math.pow(2, ++q) -1)){
                dp[target] = Math.min(dp[target], x +1 +1 +q +raceCar(target-(j-p), dp));
            }
        }

        dp[target] = Math.min(dp[target], x+(target==j ? 0 : 1 + raceCar(j-target, dp)));

        return dp[target];
    }


    class RCTuple {
        int position;
        int speed;
        int move;

        RCTuple(int position, int speed, int move) {
            this.position = position;
            this.speed = speed;
            this.move = move;
        }
    }

    public int racecarBFS(int target) {
        Queue<RCTuple> queue = new LinkedList<RCTuple>();
        RCTuple r = new RCTuple(0, 1, 0);
        // Set<Integer> visited = new HashSet<>();
        queue.add(r);

        while(!queue.isEmpty()) {
            int len = queue.size();
            for(int i = 0; i < len; i++) {
                RCTuple rc = queue.poll();

                if(rc.position == target) return rc.move;

                queue.add(new RCTuple(rc.position + rc.speed, 2 * rc.speed, rc.move+1));

                int speed = (rc.speed > 0) ? -1 : 1;
                if((rc.position + rc.speed < target && rc.speed < 0) ||
                        (rc.position + rc.speed > target && rc.speed > 0)) {
                    queue.add(new RCTuple(rc.position, speed, rc.move+1));
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int x = 1, j =1;
        while(j<18) {
            System.out.println(j);
            j= (int) (Math.pow(2, ++x) -1);
        }

        System.out.println();
    }
}
