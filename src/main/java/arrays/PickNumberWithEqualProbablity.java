package arrays;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

// Pick Random number with equal probablity
// reservoir sampling - where n is not specified || streams
public class PickNumberWithEqualProbablity {

    int pickRandom(int[] arr) {
        int len = arr.length;
        int x = (int)(Math.random()*100); // Math rand only check for 0.0 - 1.0
        System.out.println(x);
        return arr[x % len+1];
    }

    // what if arr len is not fixed
    // Probablity(ai) getting choosen = P(ai) getting chosen by ith step * P(ai) not getting replace by i+1,. i+2......
                            // (1/i) * { (1-(1/i+1)) * (1-(1/i+2)) ....... (1-(1/n))} = 1/n
    int pickRandom1(int[] arr) {
        int random = -1;
        int i = 0;

        Iterator itr = Arrays.stream(arr).iterator(); // this would be stream, we dont know len
        while(itr.hasNext()) {
            i++;
            int num = (Integer) itr.next();
            int randomIndex = (int)(Math.random()*100) % (i+1);
            if(randomIndex == i-1) {
                random = num;
            }
        }

        return random;
    }

    public static void main(String[] args) {
        System.out.println(new PickNumberWithEqualProbablity().pickRandom1(new int[] {1,2,3,4,5}));
    }
}
