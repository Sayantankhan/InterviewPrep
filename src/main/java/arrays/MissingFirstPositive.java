package arrays;

// https://leetcode.com/problems/first-missing-positive/description/
public class MissingFirstPositive {

    public static void main(String[] args) {
        int arr[] = { 3,4,-1,1 };
        int n = arr.length;
        int ans = firstMissingPositive(arr, n);

        System.out.println(ans);
    }

    /*
     * Approach 1 : Sort and Find - TC O(n logn)
     * Approach 2:  if the number is >= 1 and <= n swap until arr[i] != arr[arr[i] - 1] TC - O(n)
     */
    private static int firstMissingPositive(int[] arr, int n) {
        int end = n-1;
        for(int i = 0; i <= end; i++) {
            while(arr[i] < n && arr[i] >= 1 && arr[i] != arr[arr[i] - 1]) {
                swap(arr, i, arr[i]-1);
            }
        }

        for(int i = 0; i < n; i++) {
            if(arr[i] != i+1)
                return i+1;
        }

        return n + 1;
    }

    private static void swap(int[] arr, int i, int end) {

        int temp = arr[i];
        arr[i] = arr[end];
        arr[end] = temp;
    }
}
