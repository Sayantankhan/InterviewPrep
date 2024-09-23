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

    class Car{
        int position;
        int speed;
        public Car(int position, int speed){
            this.position = position;
            this.speed = speed;
        }
    }

    private int bfs(int target, Car c){
        int res = 0;
        Queue<Car> queue = new LinkedList<>();
        queue.offer(c);

        Set<String> visited = new HashSet<>();
        String key = c.position + "," + c.speed;
        visited.add(key);

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i< size;i++){

                Car c1 = queue.poll();
                int currPos = c1.position;
                int currSpeed = c1.speed;

                if(currPos == target)
                    return res;

                // A
                int Apos = currPos + currSpeed;
                int ASpeed = currSpeed * 2;
                String AKey = Apos + "," + ASpeed;

                if(!visited.contains(AKey) && Math.abs(target - Apos) < target){
                    visited.add(AKey);
                    Car A = new Car(Apos, ASpeed);
                    queue.offer(A);
                }

                // R
                int Rpos = currPos;
                int RSpeed = currSpeed > 0 ? -1 : 1;
                String RKey = Rpos + "," + RSpeed;

                if(!visited.contains(RKey) && Math.abs(target - Rpos) < target){
                    visited.add(RKey);
                    Car R = new Car(Rpos, RSpeed);
                    queue.offer(R);
                }
            }
            res++;
        }

        return -1;
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

    public static void main(String[] args) {
        int x = 1, j =1;
        while(j<18) {
            System.out.println(j);
            j= (int) (Math.pow(2, ++x) -1);
        }

        System.out.println();
    }
}
