public class HouseRobber {

    public int rob(int[] nums) {
        int n = nums.length;

        int[] maxRob = new int[n+1];

        maxRob[n] = 0;
        maxRob[n-1] = nums[n-1];

        for(int i = n-2; i >= 0; i--) {
            maxRob[i] = Math.max(maxRob[i+1], maxRob[i+2] + nums[i]);
        }

        return maxRob[0];

    }
}
