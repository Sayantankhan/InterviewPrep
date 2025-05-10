import java.util.*;

public class MinimizeDiffBetweenTargetandChosenElement {
    // Minimize the Difference Between Target and Chosen Elements in array
    // find the max sum from an array that close to target - 2d
    int min = Integer.MAX_VALUE;
    public int minimizeTheDifference(int[][] mat, int target) {
        int n = mat.length;

        // minimizeTheDifferenceRec(mat, n, target, 0);
        // return min;
        return minimizeDiffDP(mat, target);
    }

    void minimizeTheDifferenceRec(int[][] mat, int n, int target, int index) {
        // if (index == n) return;

        int[] arr = mat[index];

        for(int i = 0; i < arr.length; i++) {
            if(index == n-1) {
                min = Math.min(min, Math.abs(target-mat[index][i]));
            } else{
                minimizeTheDifferenceRec(mat, n, target-mat[index][i], index+1);
            }
        }
    }

    int minimizeDiffDP(int[][] mat, int target) {

        int possibleMin = 0;
        for(int[] rows : mat){
            int min = Integer.MAX_VALUE;
            for (int val: rows) {
                min = Math.min(min, val);
            }
            possibleMin += min;
        }

        if(possibleMin >= target) {
            return Math.abs(target-possibleMin);
        }

        BitSet set = new BitSet();
        set.set(0);

        for(int[] rows: mat) {
            BitSet next = new BitSet();
            for(int val: rows) {
                for (int i = set.nextSetBit(0); i >= 0; i = set.nextSetBit(i + 1)) {
                    int newSum = i + val;
                    if(newSum <= 2*target - possibleMin) next.set(newSum);
                }
            }
            set = next;
        }

        int min = Integer.MAX_VALUE;
        for (int i = set.nextSetBit(0); i >= 0; i = set.nextSetBit(i + 1)) {
            min = Math.min(min, Math.abs(target-i));
        }

        // Set<Integer> set = new HashSet<>();
        // set.add(0);
        // for(int[] rows: mat) {
        //     Set<Integer> next = new HashSet<>();
        //     for(int val: rows) {
        //         for(int el: set) {
        //             int newSum = el + val;
        //             if(newSum <= 2*target - possibleMin) next.add(val+el); //DP pruning
        //         }
        //     }
        //     set = next;
        // }

        // int min = Integer.MAX_VALUE;
        // for(int el: set) {
        //     min = Math.min(min, Math.abs(target-el));
        // }

        return min;
    }
}
