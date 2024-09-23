package company.google;

//https://leetcode.com/problems/jump-game-ii/description/
// Min steps to jump
public class JumpGameII {

    // just checking min and farthest we can jump
    public int jump(int[] nums) {
        int n = nums.length;
        int l = 0, r = 0, jump = 0;

        // r >= n or n-1 is the ans
        while(r < n-1) {
            int farthest = 0;
            for(int i = l; i <= r; i++) {
                farthest = Math.max(farthest, i + nums[i]);
            }
            jump++;
            l = r+1;
            r = farthest;
        }

        return jump;

    }
}
