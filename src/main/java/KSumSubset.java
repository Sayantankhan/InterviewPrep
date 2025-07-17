import java.util.Arrays;
import java.util.*;

public class KSumSubset {
    // k sum subset
    public boolean hasKSumSubset(int[] nums, int k) {
        Set<Integer> sums = new HashSet<>();
        sums.add(0); // base case

        for (int num : nums) {
            Set<Integer> newSums = new HashSet<>();
            for (int s : sums) {
                int newSum = s + num;
                // if (newSum == k && s != 0) return true; // found non-empty subset
                newSums.add(newSum);
            }
            sums.addAll(newSums);
        }

        return sums.contains(k); // optional: to avoid returning true for empty set
    }

    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) return false;

        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }
        return dp[target];
    }


}
