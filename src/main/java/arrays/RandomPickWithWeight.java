package arrays;
import java.util.*;

// https://leetcode.com/problems/random-pick-with-weight/
public class RandomPickWithWeight {
    static int[] sumTillNow;
    int sum;
    Random rand = new Random();

    RandomPickWithWeight(int[] w) {
        sumTillNow = new int[w.length];
        sumTillNow[0] = w[0];
        for(int i = 1; i < w.length; i++) { sumTillNow[i] = sumTillNow[i-1] + w[i]; }
    }

    public int pickIndex() {
        int random = rand.nextInt(sumTillNow[sumTillNow.length - 1]);

        int left = 0;
        int right = sumTillNow.length - 1;
        int resultIndex = -1;
        while(left <= right) {
            int mid = (left + right)/2;

            if(random < sumTillNow[mid]) {
                right = mid-1;
                resultIndex = mid;
            } else {
                left = mid + 1;
            }
        }

        return resultIndex;
    }

    public static void main(String[] args) {
        RandomPickWithWeight rpw = new RandomPickWithWeight(new int[]{1,3});
        System.out.println(rpw.pickIndex());
        System.out.println(rpw.pickIndex());
        System.out.println(rpw.pickIndex());
        System.out.println(rpw.pickIndex());
        System.out.println(rpw.pickIndex());
    }
}
