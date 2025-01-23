package arrays;

import java.util.*;

public class LongestConsecutiveSequence {

    static int lcsRec(int[] arr, int index, List<Integer> list) {

        if(index == arr.length) {
            //max = Math.max(max, list.size());
            return 0;
        }

        if(list.size() == 0) {
            list.add(arr[index]);
            int a = 1 + lcsRec(arr, index+1, list);
            list.remove(list.size()-1);
            int b = lcsRec(arr, index+1, list);
            return Math.max(a, b);

        } else {
            if(arr[index] == list.get(list.size()-1)+1) {
                list.add(arr[index]);
                int a = 1 + lcsRec(arr, index+1, list);
                list.remove(list.size()-1);
                int b = lcsRec(arr, index+1, list);
                return Math.max(a, b);
            }
            else {
                return lcsRec(arr, index+1, list);
            }
        }
    }

    static int longestConsecutiveSequence(int []arr) {
        int max = 1;
        int smallestTillNow = Integer.MIN_VALUE;
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            if( smallestTillNow+1 == arr[i]) {
                smallestTillNow = arr[i];
                count++;
                max = Math.max(max, count);
            } else if(smallestTillNow == arr[i]) continue;
            else {
                smallestTillNow = arr[i];
                count = 1;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 0, 1};
        Arrays.sort(arr);
        // System.out.println(lcsRec(arr, 0, new ArrayList<>()))
        System.out.println(longestConsecutiveSequence(arr));
    }
}
