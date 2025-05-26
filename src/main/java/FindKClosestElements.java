import java.util.*;
import java.util.stream.Collectors;

public class FindKClosestElements {
    // Find K Closest Elements

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
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
}
