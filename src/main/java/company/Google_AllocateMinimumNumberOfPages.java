package company;

import java.util.stream.IntStream;

//https://www.interviewbit.com/problems/allocate-books/
public class Google_AllocateMinimumNumberOfPages {

    static boolean isPossibleAllocation(int[] arr, int mid, int b) {
        int current_student = 1;
        int temp = 0;

        for(int i = 0; i < arr.length; i++) {
            temp += arr[i];
            if(temp > mid) {
                current_student++;
                temp = arr[i];
            }
        }

        return current_student <= b;
    }

    public static int books(int[] A, int B) {

        // if there is 1 student ; min no of book received = sum(A)
        // if there is len(A) student; min no of book received = max(A)

        int sum = IntStream.of(A).sum();
        int max = IntStream.of(A).max().getAsInt();

        int low = max;
        int high = sum;
        int result = -1;
        while(low <= high) {
            int mid = (high + low)/2;
            if(isPossibleAllocation(A, mid, B)) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;

    }

    public static void main(String[] args) {
        books(new int[]{12, 34, 67, 90}, 2);
    }
}
