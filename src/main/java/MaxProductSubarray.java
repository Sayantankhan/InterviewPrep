import org.apache.commons.math3.util.Pair;

public class MaxProductSubarray {

    // just look at the array and observe
    // if there is 2 -ve the val will come pos
    // if there is odd -ve then the max will be first half or second half
    // if there is no neg then it will be mul to all
    public int maxProduct(int[] nums) {
        int prefix = 1;
        int suffix = 1;
        int len = nums.length - 1;
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < nums.length; i++) {

            prefix = prefix * nums[i];
            suffix = suffix * nums[len - i];

            max = Math.max(max, prefix);
            max = Math.max(max, suffix);

            if(prefix == 0) prefix = 1;
            if(suffix == 0) suffix = 1;
        }

        return max;
    }
}
