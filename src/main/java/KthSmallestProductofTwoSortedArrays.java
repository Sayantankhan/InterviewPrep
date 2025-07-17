public class KthSmallestProductofTwoSortedArrays {
    // Kth Smallest Product of Two Sorted Arrays
    // TC O(n1 * log n2 * log(max product))
    //  SC O(1)
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        long left = (long) -1e10, right = (long) 1e10;
        int n1 = nums1.length;

        while(left <= right) {
            long mid = (left + right) / 2;
            long count = 0;
            for(int i = 0; i < n1; i++) {
                count += isPossibleProduct(nums2, nums1[i], mid);
            }

            if (count < k) {
                left = mid+1;
            } else {
                right = mid - 1;
            }
        }

        return left;

    }

    int isPossibleProduct(int[] nums2, int num, long e) {
        int n2 = nums2.length;
        int left = 0, right = n2-1;

        while(left <= right) {
            int mid = (left + right) / 2;
            long product = num * nums2[mid];
            // positive || negetive
            if(num >= 0 && product <= e || (num < 0 && product > e)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (num >= 0) {
            return left;
        } else {
            return n2 - left;
        }
    }

}
