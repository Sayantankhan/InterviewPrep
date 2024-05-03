package company;

public class Google_NumOfPairGreaterorEqualTarget {

    // Given a sorted list of numbers and a target Z, return the number of pairs according to following definition: (X,Y) where X+Y >= Z
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 7, 9, 10, 11};
        int target = 7;

        System.out.println(countPairs(a, target));
    }

    /*
     * As this is a sorted array , that means if a[low] + a[index] >= target;
     * all the other indexes in between will also satisfy the condition
     */
    public static int countPairs(int[] arr, int target) {
        int count = 0;
        for (int lo = 0, hi = arr.length - 1; lo < hi; ) {
            if (arr[lo] + arr[hi] >= target) {
                count += hi - lo;
                hi--;
            } else  {
                lo++;
            }
        }
        return count;
    }

    private static int noOfPairBF(int[] a, int target) {
        int count = 0;

        for(int i = 0; i < a.length; i++) {
            for(int j = i+1; j < a.length; j++) {
                if(a[i] + a[j] >= target)
                    count++;
            }
        }

        return count;
    }
}
