package arrays;

import util.Utility;

// https://leetcode.com/problems/longest-turbulent-subarray/description/
public class LongestTurbulentSubarray {
    // Time Complexity = O(n) and Space = O(1)
    public static int maxTurbulenceSize(int[] arr) {
        int max = 0;
        int start = 0;

        // skip until you are not getting new value
        while( start+1 < arr.length && arr[start] == arr[start+1]) {
            start = start+1;
        }
        if(start + 1 >= arr.length) {
            return 1;
        }

        // We need a pair to identify the flips - 1st and 2nd
        boolean flip = (arr[start] > arr[start+1]);
        int count = 2;

        for(int i = start+2; i < arr.length; i++) {
            if( !flip && arr[i] < arr[i-1]) { // if 1st < 2nd so next will be 2nd > 3rd ...
                // take the value
                count++;
                flip = !flip;
            } else if(flip && arr[i] > arr[i-1]) { // if 1st > 2nd so next will be 2nd < 3rd ...
                // take the value;
                count++;
                flip = !flip;
            } else {
                // reset the param
                max = Math.max(max, count);
                // if values are equal
                if(arr[i-1] == arr[i]) {
                    // skip until you are not getting new value
                    while( i+1 < arr.length && arr[i] == arr[i+1]) {
                        i++;
                    }
                    if(i+1 < arr.length) {
                        flip = (arr[i] > arr[i+1]);
                        i ++;
                    }
                }else {
                    flip = (arr[i-1] > arr[i]);
                }
                count = 2;
            }
        }
        return Math.max(max, count);
    }

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(maxTurbulenceSize(new int[]{9,4,2,10,7,8,8,1,9}), 5);
        Utility.assertTrue(maxTurbulenceSize(new int[]{4,8,12,16}), 2);
        Utility.assertTrue(maxTurbulenceSize(new int[]{100, 100, 100, 100, 100, 100, 90, 99, 80, 94}), 5);
        Utility.assertTrue(maxTurbulenceSize(new int[]{100}), 1);
        Utility.assertTrue(maxTurbulenceSize(new int[]{0,1,1,0,1,0,1,1,0,0}), 5);
    }
}
