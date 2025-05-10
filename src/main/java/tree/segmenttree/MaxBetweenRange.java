package tree.segmenttree;

import java.util.Arrays;

public class MaxBetweenRange {

    public static void main(String[] args) {
        int[] a = {73, 12, 89, 5, 46, 27, 94, 61, 38, 7, 84, 19, 53, 68, 31};

        // find the maximum between a range

        // segment tree approach
        int[] segTree = new int[segmentTreeSize(a.length)];
        buildSegmentTree(a, 0, 0, a.length-1, segTree);
        Arrays.stream(segTree).forEach(e -> System.out.print(e + ","));
        System.out.println();
        int l = 2, r = 8;
        System.out.println(queryFromSegTree(segTree, 0, 0, a.length-1, l, r));
    }

    private static int queryFromSegTree(int[] segTree, int index, int low, int high, int l, int r) {
        // Base case: out of bounds
        if (index >= segTree.length || low > r || high < l) {
            return Integer.MIN_VALUE;
        }

        // Segment completely within range
        if (low >= l && high <= r) {
            return segTree[index];
        }

        // Segment partially overlaps
        int mid = low + (high - low) / 2;
        int left = queryFromSegTree(segTree, 2 * index + 1, low, mid, l, r);
        int right = queryFromSegTree(segTree, 2 * index + 2, mid + 1, high, l, r);

        return Math.max(left, right);
    }


    public static int segmentTreeSize(int n) {
        // 2 * 2^ceil(log₂(n)) - 1.
        // For n = 15 → 2 * 16 - 1 = 31
        // For n = 8 → 2 * 8 - 1 = 15
        // log for 2 side division || ceil for nearest whole number.
        int height = (int) Math.ceil(Math.log(n) / Math.log(2));
        int maxSize = 2 * (int) Math.pow(2, height) - 1;
        return maxSize;
    }

    private static void buildSegmentTree(int[] a, int index, int low, int high, int[] segTree) {
        if(low == high) {
            // leaf node
            segTree[index] = a[low];
            return;
        }

        int mid = (low + high) / 2;
        // left = 2 * index + 1
        buildSegmentTree(a, 2*index+1, low, mid, segTree);
        // right = 2 * index + 2
        buildSegmentTree(a, 2*index+2, mid+1, high, segTree);

        segTree[index] = Math.max(segTree[2*index + 1], segTree[2*index + 2]);
    }
}
