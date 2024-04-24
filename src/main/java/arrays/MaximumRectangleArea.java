package arrays;

import java.util.*;

// histogram || https://leetcode.com/problems/maximal-rectangle/
public class MaximumRectangleArea {
    public int maximalRectangle(char[][] matrix) {
        int max = 0;
        for(int i = 0; i < matrix.length; i++) {
            int[] level = calculateByLevel(matrix, i);
            int ans = findMaxArea(level);
            max = Math.max(max, ans);
        }
        return max;
    }

    int[] calculateByLevel(char[][] matrix, int x) {
        int y = matrix[0].length;
        int[] level = new int[y];
        for(int i = 0; i < y; i++) {
            int count = 0;
            for(int j = x; j >= 0; j--) {
                if(matrix[j][i] == '1') count++;
                if(matrix[j][i] == '0') break;
            }
            level[i] = count;
        }
        return level;
    }

    int findMaxArea(int[] arr) {
        int[] startIndex = foundLeftIndex(arr);
        int[] endIndex = foundRightIndex(arr);

        int ans = 0;
        for(int i = 0; i < endIndex.length; i++) {
            int temp = ( - startIndex[i] + endIndex[i] - 1) * arr[i];
            ans = Math.max(ans, temp);
        }
        return ans;
    }

    int[] foundLeftIndex(int[] arr) {
        Stack<Integer> indices = new Stack<Integer>();
        int[] index = new int[arr.length];

        for(int i = 0; i < arr.length; i++) {

            while(!indices.isEmpty() && arr[indices.peek()] >= arr[i]) {
                indices.pop();
            }

            if(indices.isEmpty()) index[i] = -1;
            else {
                index[i] = indices.peek();
            }

            indices.push(i);
        }
        return index;
    }

    int[] foundRightIndex(int[] arr){
        Stack<Integer> indices = new Stack<Integer>();
        int[] index = new int[arr.length];
        int[] revindex = new int[arr.length];
        for(int i = arr.length - 1; i >= 0; i--) {
            while(!indices.isEmpty() && arr[indices.peek()] >= arr[i]) {
                indices.pop();
            }
            if(indices.isEmpty()) index[i] = arr.length;
            else {
                index[i] = indices.peek();
            }
            indices.push(i);
        }

        for(int i = 0 ; i < arr.length; i++) {
            revindex[i] = index[arr.length - 1 - i];
        }
        return index;
    }
}
