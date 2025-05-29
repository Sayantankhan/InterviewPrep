package arrays.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class MaxConsecutiveOnes {
    // Googles beauty score question
    // Max Consecutive Ones after flipping k elements

    public int longestOnes(int[] nums, int k) {
        int len = nums.length;
        int start = 0;
        int ans = Integer.MIN_VALUE;
        int count_zero = 0;

        for(int end = 0; end < len; end++) {
            if (nums[end] == 0) count_zero++;
            ans = Math.max(ans, end - start); // not adding 1 because i'm taking check for count_zero > k
            while(count_zero > k) {
                if (nums[start] == 0) count_zero--;
                start = start + 1;
            }
        }

        ans = Math.max(ans, len - start);
        return ans;
    }

    public int longestOnesBS(int[] nums, int k) {
        int left = 0, right = nums.length;
        int answer = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (isPossible(nums, k, mid)) {
                answer = mid; // Valid length found, try longer
                left = mid + 1;
            } else {
                right = mid - 1; // Too many zeros, try shorter
            }
        }

        return answer;
    }

    private boolean isPossible(int[] nums, int k, int length) {
        // Is there any window of size length in the array where we can flip at most k 0s to get all 1s?
        int zeroCount = 0;

        // Count zeros in the first 'length' window
        // 0 - (length-1)
        for (int i = 0; i < length; i++) {
            if (nums[i] == 0) zeroCount++;
        }

        if (zeroCount <= k) return true;

        // Slide the window
        for (int i = length; i < nums.length; i++) {
            if (nums[i - length] == 0) zeroCount--;
            if (nums[i] == 0) zeroCount++;

            if (zeroCount <= k) return true;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println((new MaxConsecutiveOnes()).longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}, 2));
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        System.out.println(map.size());

    }
}
