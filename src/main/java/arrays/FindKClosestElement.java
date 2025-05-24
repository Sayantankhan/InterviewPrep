package arrays;

import util.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// https://leetcode.com/problems/find-k-closest-elements/submissions/1239165438/
public class FindKClosestElement {
    //Find K Closest Element

    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        int index;

        if(x < arr[0]) { index = 0;}
        else if(x > arr[arr.length-1]) { index = arr.length - 1;}
        else {
            index = Arrays.binarySearch(arr, x);
        }

        List<Integer> list = new ArrayList<>();

        if(index < 0) {
            index = -(index+1);
        }


        int leftPointer = 0;
        int rightPoint = 0;

        if(index-1 >= 0 && Math.abs(x - arr[index]) >= Math.abs(x - arr[index-1])) {
           leftPointer = index-1;
           rightPoint = index-1;
        } else {
            leftPointer = index;
            rightPoint = index;
        }

        int count = 0;

        // Finding the window || +1 because the postiion we are calculating we are adding it
        while( count+1 < k) {

            int leftVal = Integer.MAX_VALUE;
            int rightVal = Integer.MAX_VALUE;

            if(leftPointer-1 >= 0) {
                leftVal = Math.abs(x - arr[leftPointer-1]);
            }

            if(rightPoint + 1 < arr.length) {
                rightVal = Math.abs(x - arr[rightPoint+1]);
            }

            if(leftVal < rightVal) {
                leftPointer--;
            } else if(leftVal > rightVal) {
                rightPoint++;
            } else {
                leftPointer--;
            }
            count++;
        }

        if(leftPointer < 0) leftPointer = 0;
        if(rightPoint >= arr.length) rightPoint = arr.length-1;

        for(int i = leftPointer; i <= rightPoint; i++) {
            list.add(arr[i]);
        }

        return list;
    }


    public static List<Integer> findClosestElements2(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - k - 1;

        while(left <= right) {
            int mid = (left + right) / 2;

            if(x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return Arrays.stream(arr, left, left+k).boxed().collect(Collectors.toList());
    }

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(findClosestElements2(new int[]{1, 2, 3, 4, 5}, 4, 3), List.of(1, 2, 3, 4));
        Utility.assertTrue(findClosestElements2(new int[]{1, 2, 3, 4, 5}, 4, -1), List.of(1, 2, 3, 4));
        Utility.assertTrue(findClosestElements2(new int[]{1, 2, 3, 4, 5}, 4, 9), List.of(2, 3, 4, 5));
        Utility.assertTrue(findClosestElements2(new int[]{1}, 1, 0), List.of(1));
        Utility.assertTrue(findClosestElements2(new int[]{0,1,1,1,2,3,6,7,8,9},
                9, 4), List.of(0,1,1,1,2,3,6,7,8));
        Utility.assertTrue(findClosestElements2(new int[]{1,1,1,10,10,10},
                1, 9), List.of(10));

        Utility.assertTrue(findClosestElements2(new int[]{1,2,3,4,5,100},
                4, 6), List.of(2, 3, 4, 5));

        Utility.assertTrue(findClosestElements2(new int[]{1, 3},
                1, 2), List.of(1));

        Utility.assertTrue(findClosestElements2(new int[]{0,1,1,1,2,3,6,7,8,9},
                5, 4), List.of(1, 1, 2, 3, 6));
    }
}
