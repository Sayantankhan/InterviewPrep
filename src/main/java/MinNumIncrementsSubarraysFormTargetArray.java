import java.util.*;

public class MinNumIncrementsSubarraysFormTargetArray {

    // Minimum Number of Increments on Subarrays to Form a Target Array
    // Min no of incr

    // TC + SC :: O(n)
    public int minNumberOperations(int[] target) {
        Stack <Integer> stack = new Stack<>();
        stack.push(0);
        int result = 0;

        for (int num: target) {
            if (num > stack.peek()) {
                result += num - stack.peek();
            }

            while (num <= stack.peek()) {
                stack.pop();
            }

            stack.push(num);
        }
        return result;
    }

    public int minNumberOperationsGreedy(int[] target) {
        int previous = 0;
        int result = 0;

        for (int num: target) {
            if (num > previous) {
                result += num - previous;
            }

            previous = num;
        }

        return result;
    }

}
