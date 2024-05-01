package company;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

// https://www.interviewbit.com/problems/painters-partition-problem/
public class Google_PaintersPartitionProblem {

    public static int paint(int A, int B, int[] C) {

        long sum = 0;
        long max = 0;

        for(int i = 0; i < C.length; i++) {
            sum += (long) C[i] * (long) B;
            max = Math.max(max, (long)C[i] * (long) B);

        }

        if(A > C.length) return (int) (max % 10000003);
        if(A == 1) return (int) (sum % 10000003);

        long high = sum;
        long low = max;

        long result = 0;
        while(low <= high) {
            long mid = (low + high) / 2;
            if(isPossibleAllocation(A, C, B, mid)) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return (int) (result % 10000003);
    }

    static boolean isPossibleAllocation(int a, int[] c, int b, long mid) {
        int painter_count = 1;
        long temp = 0;

        for(int i = 0; i < c.length; i++) {
            temp += c[i] * b;
            if(temp > mid) {
                painter_count++;
                temp = c[i] * b;
            }
        }
        return painter_count <= a;
    }

    public static void main(String[] args) {
        paint(2, 5, new int[]{1, 10});
    }
}
