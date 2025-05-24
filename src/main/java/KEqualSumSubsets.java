import java.util.Arrays;
import java.util.Map;

public class KEqualSumSubsets {
    // K Equal Subset Sum
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();

        if(sum % k != 0) return false;
        else {
            int f_sum = sum / k;
            boolean[] visited = new boolean[nums.length];
            boolean[][] dp = new boolean[k+1][f_sum+1];
            // return canPartitionKSubsetsRec(nums, f_sum, 0, 0, visited, k, dp);
            return canPartitionKSubsetsDP(nums, f_sum, k);
        }
    }

    // O(n * n!)
    private boolean canPartitionKSubsetsRec(int[] nums, int f_sum, int sum, int index, boolean[] visited, int k, boolean[][] dp) {
        if(k==0) return true;

        if(sum == f_sum)
            return canPartitionKSubsetsRec(nums, f_sum, 0, 0, visited, k-1, dp);

        if(index == nums.length) return false;

        if(dp[k][sum]) return false;

        if(!visited[index] && nums[index]+sum <= f_sum) {
            visited[index] = true;
            if(canPartitionKSubsetsRec(nums, f_sum, sum+nums[index], index+1, visited, k, dp)) {
                dp[k][sum] = true;
                return true;
            }
            visited[index] = false;
        }

        return canPartitionKSubsetsRec(nums, f_sum, sum, index+1, visited, k, dp);
    }

    // o(n * 2^n) - Tabulization + BitMask
    private boolean canPartitionKSubsetsDP(int[] nums, int f_sum, int k) {
        int n = nums.length;

        // 0 to 2^n
        int[] subsetSum = new int[(1 << n)];
        Arrays.fill(subsetSum, -1);

        // Initially only one state is valid, i.e don't pick anything.
        subsetSum[0] = 0;

        for(int mask = 0; mask < (1 << n); mask++) {
            // If the current state has not been reached earlier.
            if (subsetSum[mask] == -1) {
                continue;
            }

            for (int i = 0; i < n; i++) {
                // If the number arr[i] was not picked earlier, and arr[i] + subsetSum[mask]
                // is not greater than the targetSum then add arr[i] to the subset
                // sum at subsetSum[mask] and store the result at subsetSum[mask | (1 << i)].
                if ((mask & (1 << i)) == 0 && subsetSum[mask] + nums[i] <= f_sum) {
                    subsetSum[mask | (1 << i)] = (subsetSum[mask] + nums[i]) % f_sum;
                }
            }

            if (subsetSum[(1 << n) - 1] == 0) {
                return true;
            }
        }

        return subsetSum[(1 << n) - 1] == 0;
    }

    public static void main(String[] args) {
        new KEqualSumSubsets().canPartitionKSubsets(new int[] {4, 3, 2, 3, 5, 2, 1}, 4);
    }
}
