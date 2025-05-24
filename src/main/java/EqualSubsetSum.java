import java.util.Arrays;

public class EqualSubsetSum {

    public boolean canPartition(int[] nums) {
        // divide into 2 equal parts
        int sum = Arrays.stream(nums).sum();

        if(sum % 2 != 0) return false;
        else {
            int f_sum = sum / 2;
            return canPartitionRec(nums, f_sum, 0, 0);
        }
    }

    boolean canPartitionRec(int[] nums, int f_sum, int sum, int index) {
        if(f_sum == sum) return true;

        if(index == nums.length) return false;

        if(sum + nums[index] <= f_sum) {
            return canPartitionRec(nums, f_sum, sum, index+1) || canPartitionRec(nums, f_sum, sum+nums[index], index+1);
        }
        return canPartitionRec(nums, f_sum, sum, index+1);
    }

    boolean canPartitionDP(int[] nums, int f_sum) {
        boolean[][] dp = new boolean[nums.length+1][f_sum+1];

        for(int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }

        for(int i = 1; i < nums.length+1; i++) {
            for(int j = 1;j < f_sum+1; j++) {
                if(nums[i-1] <= j) {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[nums.length][f_sum];
    }

    public static void main(String[] args) {
        new EqualSubsetSum().canPartition(new int[] {1,5,11,5});
    }

}
