package arrays;

import java.util.*;

public class NextPermutation {

    public static ArrayList<Integer> nextPermutation(ArrayList<Integer> permutation)
    {
        // Write your code here.
        // Think the points as mountain curve, easy to get the intution

        int len = permutation.size();
        int dipIndex = -1;

        // find the dip
        for (int i = len-2; i >= 0; i--) {
            if(permutation.get(i) < permutation.get(i+1)) {
                dipIndex = i;
                break;
            }
        }

        // if no dip , just reverse it, as we have to circle back to the first combination
        if(dipIndex == -1) {
            Collections.reverse(permutation);
            return permutation;
        }

        // swap the dip with next immediate bigger element
        for(int i = len-1; i >= dipIndex+1; i--) {
            if(permutation.get(i) > permutation.get(dipIndex)) {
                Collections.swap(permutation, i, dipIndex);
                break;
            }
        }

        // swap the remaining
        swap(permutation, dipIndex+1, len-1);

        return permutation;

    }

    static void swap(List<Integer> list, int start, int end) {

        while(start < end) {
            Collections.swap(list, start, end);
            start++;
            end--;
        }
    }
}
