package arrays;

import java.util.Arrays;

// Shuffle array with O(1) space
public class ShuffleArray {

    // 2 * i = x1
    // (2 * i)+1 = y1

    // if curr_position < n : 2 * curr_position
    // else 2 * (curr_position-n) + 1

    int[] shuffle(int[] arr, int n) {
        for(int i=0; i< arr.length; i++) {
            int currNumber = arr[i];
            int currPos = i;

            while(currNumber > 0) {
                int newPos;
                if(currPos < n) {
                    newPos = currPos * 2;
                } else {
                    newPos = 2 * (currPos - n) + 1;
                }
                int newNumber = arr[newPos];
                arr[newPos] = -currNumber;
                currNumber = newNumber;
                currPos = newPos;
            }
        }

        // to change - to +
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] < 0) arr[i] = -arr[i];
        }

        return arr;
    }

    public static void main(String[] args) {
        Arrays.stream(new ShuffleArray().shuffle(new int[] {1, 2, 3, 4, 5, 6}, 3)).forEach(System.out::print);
    }
}
