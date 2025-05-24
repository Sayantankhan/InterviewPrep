public class SortedTansformedArray {

    // sort in Transformed array = ax^2+bx+c

    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        // ax2+bx+c
        // return Arrays.stream(nums)
        //     .map(num -> (int)(a * Math.pow(num, 2) + b * num + c))
        //     .sorted()  // ✅ add parentheses!
        //     .toArray();

        // O(n)
        int i = 0, j = nums.length - 1;
        int len = nums.length - 1;
        int l = 0, r = len;
        int[] ans = new int[nums.length];
        while(l <= r) {
            int num1 = (int)(a * Math.pow(nums[l], 2) + b * nums[l] + c);
            int num2 = (int)(a * Math.pow(nums[r], 2) + b * nums[r] + c);

            // If a=0, then f(x)=bx+c will represent a straight line.
            // If a != 0 f(x)=ax^2+bx+c - upside parabola U
            // for the case when a < 0 it willbe downwards parabola
            if(a >= 0) {
                // cases where [pos and neg mixed]
                if(num1 > num2) {
                    ans[j--] = num1;
                    l++;
                }else {
                    ans[j--] = num2;
                    r--;
                }
            } else {
                // only neg cases
                if(num1 > num2) {
                    ans[i++] = num2;
                    r--;
                }else {
                    ans[i++] = num1;
                    l++;
                }
            }
        }

        return ans;

    }
}
