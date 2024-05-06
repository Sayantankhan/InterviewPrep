import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Sqrt {

    // i've to find the sqrt
    // 3.222
    // o(Sqrt(num))
    public static double squreRoot(int  num) {
        double val = 0;
        double sqrt = (double) num / 2;

        while(val - sqrt != 0) {
            System.out.println("sqrt Fn");
            val = sqrt;
            sqrt = (val + (num/val))/2;
        }

        return sqrt;
    }

    public static void printPrimeNumber(int num) {
        Set<Integer> digits = new HashSet<>();
        // 0
        digits.add(2);
        digits.add(3);
        digits.add(5);
        digits.add(7);

        for(int i = 0 ; i < num; i++) {
            if(isPrime(i, digits)) {
                System.out.println(i);
            }
        }
    }

    //O(n * size(set)) == 0(n)
    private static boolean isPrime(int i, Set<Integer> digits) {
        // boolean isPrime = false;
        if(i == 1) return true;

        if(i < 10) return digits.contains(i);

        for(Integer digit : digits) {

            //System.out.println(i + " " + i % digit);
            if(i % digit == 0) return false;
        }
        digits.add(i);
        return true;
    }


    private static boolean isPrime(int i) {
        int temp = i/2;

        if(temp == 0 || temp == 1) return false;

        else {
            for(int j = 2; j <= temp; j++) {
                if(i % j != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int num = 1;
//        System.out.println(squreRoot(num));
//        System.out.println(Math.sqrt(num));
//        System.out.println(squreRoot(num) == Math.sqrt(num));
        printPrimeNumber(48);
    }
}
