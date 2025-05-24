public class TappingRainWater {

    public int trap(int[] height) {
        // Histogram problem - Max Most Water Contain Hold
        //          [0,1,0,2,1,0,1,3,2,1,2,1]

        // l->R     [0,1,1,2,2,2,2,3,3,3,3,3]
        // R->l     [3,3,3,3,3,3,3,3,2,2,2,1]

        // Min      [0,1,1,2,2,2,2,3,2,2,2,1]
        //          [0,1,0,2,1,0,1,3,2,1,2,1]
        // -----------------------------------
        //          [0,0,1,0,1,2,1,0,0,1,0,0] ->1+1+2+1+1 = 6


        int[] leftToRight = new int[height.length];
        int max = height[0];
        leftToRight[0] = height[0];
        for(int i = 1; i < height.length; i++) {
            leftToRight[i] = Math.max(max, height[i]);
            max = Math.max(max, height[i]);
        }

        int ans = 0;
        max = height[height.length-1];
        for(int i = height.length-2; i >= 0; i--) {
            int value = Math.max(max, height[i]);
            max = Math.max(max, height[i]);
            ans += Math.abs(height[i] - Math.min(value, leftToRight[i]));
        }
        return ans;

    }
}
