package arrays;

import java.util.Arrays;

// https://leetcode.com/problems/total-hamming-distance/description/
public class TotalHammingDistance {
    // The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
    public static int totalHammingDistance(int[] nums) {

        int[] zeros = new int[2];
        int ans = 0;
        while(true) {
            int zeroCount = 0;
            Arrays.fill(zeros, 0);
            for(int i = 0; i < nums.length; i++) {
                if(nums[i] == 0) zeroCount++;
                zeros[nums[i]%2] = zeros[nums[i]%2]+1;

                nums[i] = nums[i] >> 1;
            }
            ans += zeros[0] * zeros[1];
            if(zeroCount == nums.length) return ans;
        }
    }


    public static void main(String[] args) {
        totalHammingDistance(new int[]{4, 14, 2});
    }
}
