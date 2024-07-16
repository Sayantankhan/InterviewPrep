package company;

import java.util.*;

public class LongestLexicographicallyLargestValidSeq {

    public static ArrayList<Integer> validSequence(int n) {
        // 4___4 -> 4_3_43
        // 3__3 -> 3423241
        int len = 2*n-1;
        int[] arr = new int[len];

        Set<Integer> set = new HashSet<>();
        backTrack(arr, 0, set, n);

        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i = 0; i < len; i++) {
            result.add(arr[i]);
        }
        return result;
    }

    static boolean backTrack(int[] arr, int index, Set<Integer> seen, int n) {

        if(index == arr.length) return true;

        if(arr[index] != 0)
            return backTrack(arr, index+1, seen, n);

        for(int i = n; i >= 1; i--) {
            if(!seen.contains(i)) {
                if(i == 1) {
                    arr[index] = i;
                    seen.add(i);

                    if(backTrack(arr, index+1, seen, n)) {
                        return true;
                    }

                    arr[index] = 0;
                    seen.remove(i);

                } else {
                    if(index + i < arr.length && arr[index + i] == 0) {
                        arr[index] = i;
                        arr[index + i] = i;
                        seen.add(i);

                        if(backTrack(arr, index+1, seen, n)) {
                            return true;
                        }

                        arr[index] = 0;
                        arr[index + i] = 0;
                        seen.remove(i);
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(LongestLexicographicallyLargestValidSeq.validSequence(4));
    }
}
