package arrays.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class MaxConsecutiveOnes {

    public int longestOnes(int[] nums, int k) {
        int len = nums.length;
        int start = 0;
        int ans = Integer.MIN_VALUE;
        int count_zero = 0;

        for(int end = 0; end < len; end++) {
            if (nums[end] == 0) count_zero++;
            ans = Math.max(ans, end - start);
            while(count_zero > k) {
                if (nums[start] == 0) count_zero--;
                start = start + 1;
            }
        }

        ans = Math.max(ans, len - start);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println((new MaxConsecutiveOnes()).longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}, 2));
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        System.out.println(map.size());

    }
}
