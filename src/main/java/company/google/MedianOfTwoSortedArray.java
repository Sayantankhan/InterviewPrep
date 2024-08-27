package company.google;

public class MedianOfTwoSortedArray {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // As it has been mentioned to use O(log(m+n)) -> intution is to use Binary Search
        // Always perform binary search on the lowest element
        if(nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);

        int low = 0;
        int high = nums1.length;

        int left = (nums1.length + nums2.length + 1) / 2;
        int total = (nums1.length + nums2.length);

        while (low <= high) {
            int mid1 = (low + high) >> 1; // low + high / 2
            int mid2 = left - mid1;

            int l1 = Integer.MIN_VALUE, l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE, r2 = Integer.MAX_VALUE;

            if(mid1-1 >= 0) l1 = nums1[mid1-1];
            if(mid2-1 >= 0) l2 = nums2[mid2-1];

            if(mid1 < nums1.length) r1 = nums1[mid1];
            if(mid2 < nums2.length) r2 = nums2[mid2];

            if(l1 <= r2 && l2 <= r1) {
                if(total % 2 == 0) {
                    return (double)(Math.max(l1,l2) + Math.min(r1,r2)) / 2.0;
                } else {
                    return Math.max(l1, l2);
                }
            }
            else if (l1 > r2) high = mid1 - 1;
            else low = mid1 + 1;
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new MedianOfTwoSortedArray().findMedianSortedArrays(new int[]{1,3}, new int[]{2}));
        System.out.println(new MedianOfTwoSortedArray().findMedianSortedArrays(new int[]{1,3}, new int[]{2, 4}));
        System.out.println(new MedianOfTwoSortedArray().findMedianSortedArrays(new int[]{}, new int[]{2, 4}));
        System.out.println(new MedianOfTwoSortedArray().findMedianSortedArrays(new int[]{}, new int[]{1}));
    }
}
