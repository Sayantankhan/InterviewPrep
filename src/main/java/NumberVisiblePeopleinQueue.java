import java.util.Stack;

public class NumberVisiblePeopleinQueue {

    public static int[] canSeePersonsCount(int[] heights) {
        // decreasing Monotonic Stack

        Stack<Integer> stack = new Stack<Integer>();
        int len = heights.length;
        int[] visible = new int[len];

        for(int i = 0; i < len; i++) {
            while(!stack.isEmpty() && heights[stack.peek()] < heights[i]) {
                visible[stack.pop()]++;
            }
            if(!stack.isEmpty()) {
                visible[stack.peek()]++;
            }
            stack.add(i);
        }

        return visible;
    }

    public static void main(String[] args) {
        int[] heights = {10,6,8,5,11,9};
        System.out.println(canSeePersonsCount(heights));
    }
}
