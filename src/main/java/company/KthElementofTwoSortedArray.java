package company;

public class KthElementofTwoSortedArray {

    public static long kthElement(int k, int arr1[], int arr2[]) {
        // code here

        int len1 = arr1.length;
        int len2 = arr2.length;

        int low1 = 0, high1 = len1 ;
        int low2 = 0, high2 = len2 ;

        while(low1 <= high1 && low2 <= high2) {

            int mid1 = (low1 + high1)/2;
            int mid2 = (low2 + high2)/2;

            if (low1 == high1) {
                return arr2[low2 + k - 1];
            }
            if (low2 == high2) {
                return arr1[low1 + k - 1];
            }

            if(mid1 - low1 + mid2 - low2 < k-1) {
                if(arr1[mid1] > arr2[mid2]) {
                    k = k - (mid2 - low2) -1;
                    low2 = mid2 + 1;
                } else {
                    k = k - (mid1 - low1) -1;
                    low1 = mid1 + 1;
                }
            } else {
                if(arr1[mid1] > arr2[mid2]) {
                    high1 = mid1;
                } else {
                    high2 = mid2;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(kthElement(5, new int[] {2, 3, 6, 7, 9}, new int[]{1, 4,8, 10}));
    }
}
