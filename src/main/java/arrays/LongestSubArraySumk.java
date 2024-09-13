package arrays;

import java.util.HashMap;
import java.util.Map;

public class LongestSubArraySumk {

    public static int lenOfLongSubarr(int A[], int N, int K) {
        // Complete the function

        // 11, 10, 5, -1, -3 || 11
        // 11, 21, 26, 25, 28

        Map<Integer, Integer> map = new HashMap();
        int sum = 0;
        int result = 0;

        for(int i = 0; i < N; i++) {

            sum += A[i];
            if(sum == K)  result = Math.max(result, i + 1);

            Integer index = map.get(sum-K);
            if(index != null) {
                result = Math.max(result, i - index + 1);
            }
            if(map.get(sum) == null) {
                map.put(sum, i);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(lenOfLongSubarr(new int[]{1,4,3,3,5,5}, 6, 16));
    }
}
