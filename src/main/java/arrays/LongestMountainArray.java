package arrays;

import util.Utility;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

// https:leetcode.com/problems/longest-mountain-in-array/
public class LongestMountainArray {

    // To find a mountain peak we need to find a range (left to right)
    // 1. traverse from left to right and only accept increasing seq  else -1
    // 2. traverse from right to left and only accept increasing seq else -1
    // if right == -1 or left == -1 dont consider
    // else right - left + 1
    public static int longestMountain(int[] arr) {
        int[] left = findLeftRange(arr);
        Arrays.stream(left).forEach(x -> System.out.print(x + " "));
        System.out.println();
        int[] right = findRightRange(arr);
        Arrays.stream(right).forEach(x -> System.out.print(x + " "));
        System.out.println();
        int max = 0;

        for(int i = 0; i < left.length; i++) {
            if(right[i] == left[i] || right[i] == -1 || left[i] == -1) continue;
            else max = Math.max(max, right[i] - left[i] + 1);
        }

        return max;
    }

    static int[] findLeftRange(int[] arr) {
        Deque<Integer> dq = new LinkedList<>();
        int[] left = new int[arr.length];

        for(int i = 0; i < arr.length; i++) {
            if(!dq.isEmpty()) {
               if(arr[dq.peekLast()] >= arr[i]) {
                   while(!dq.isEmpty()) {
                       dq.pollLast();
                   }
               }
            }
            if(dq.isEmpty()) left[i] = -1;
            else {
                left[i] = dq.peekFirst();
            }
            dq.addLast(i);
        }

        return left;
    }

    static int[] findRightRange(int[] arr) {
        Deque<Integer> dq = new LinkedList<>();
        int[] right = new int[arr.length];

        for(int i = arr.length-1; i >= 0; i--) {
            if(!dq.isEmpty()) {
                if(arr[dq.peekLast()] >= arr[i]){
                    while(!dq.isEmpty()) {
                        dq.poll();
                    }
                }
            }
            if(dq.isEmpty()) right[i] = -1;
            else {
                right[i] = dq.peekFirst();
            }
            dq.addLast(i);
        }

        return right;
    }

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(longestMountain(new int[]{2, 1, 4, 7, 3, 2, 5}), 5);
        Utility.assertTrue(longestMountain(new int[]{2, 2, 2}), 0);
       Utility.assertTrue(longestMountain(new int[]{2, 3}), 0);
        Utility.assertTrue(longestMountain(new int[]{0, 2, 2}), 0);
        Utility.assertTrue(longestMountain(new int[]{0,2,0,2,1,2,3,4,4,1}), 3);
       Utility.assertTrue(longestMountain(new int[]{1, 1, 0, 0, 1, 0}), 3);
        Utility.assertTrue(longestMountain(new int[]{0, 2, 2, 2, 3}), 0);
    }
}
