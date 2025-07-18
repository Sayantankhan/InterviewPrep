public class ContainerWithMostWater {
    // Container With Most Water
    // 2 endpoints
    public int maxArea(int[] height) {
        int len = height.length-1;

        int left = 0;
        int right = len;

        int maxArea = Integer.MIN_VALUE;
        while(left < right) {
            int area = (right - left) * Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, area);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;

    }
}
