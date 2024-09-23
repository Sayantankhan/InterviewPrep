package arrays;

import java.util.*;

// Sum of subarray mini
// https://leetcode.com/problems/sum-of-subarray-minimums/
public class SumofSubarrayMinimums {

    public int sumSubarrayMins(int[] arr) {
        // same as historgram
        int[] nse = findNextSmallerElement(arr);
        int[] psee = findPrevSmallerorEqualElement(arr);
        int mod = (int) (10e9+7);

        int total = 0;
        for(int i = 0; i < arr.length; i++) {
            int right = nse[i] - i;
            int left = i - psee[i];

            total = (total + ((right * left * arr[i]) % mod)) % mod;
        }
        return total;
    }

    int[] findNextSmallerElement(int[] arr) {
        int n = arr.length;
        int[] nse = new int[n];
        Stack<Integer> stack = new Stack();

        for(int i = n-1; i >= 0; i--) {
            while(!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }

            nse[i] = (stack.isEmpty()) ? n : stack.peek();
            stack.push(i);
        }

        return nse;
    }

    int[] findPrevSmallerorEqualElement(int[] arr) {
        int n = arr.length;
        int[] psee = new int[n];
        Stack<Integer> stack = new Stack();

        for(int i = 0; i < n; i++) {
            while(!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }

            psee[i] = (stack.isEmpty()) ? -1 : stack.peek();
            stack.push(i);
        }
        return psee;
    }

    public static void main(String[] args) {
        int mod = (int) (10e9+7);
        System.out.println(mod);
        System.out.println(new SumofSubarrayMinimums().sumSubarrayMins(new int[] {11, 81, 94, 43, 3}));
    }
}
