package company.google;

public class RangeSumQuery1D {

    // approach 1
    static class PrefixSumApproach {
        int[] arr;
        int[] prefixSum = null;

        PrefixSumApproach(int[] arr) {
            this.arr = arr;
            this.prefixSum = new int[arr.length];
            preprocess(prefixSum, arr);
        }

        private void preprocess(int[] prefixSum, int[] arr) {
            prefixSum[0] = arr[0];
            for(int i = 1; i < arr.length; i++) {
                prefixSum[i] =  prefixSum[i-1] + arr[i];
            }
        }

        // O(1)
        private int rangeSum(int x1, int x2) {
            return prefixSum[x2] - prefixSum[x1] + arr[x1];
        }

        // update : O(n)
        private void update(int index, int value) {
            int diff = - arr[index] + value;
            for(int i = index; i < arr.length; i++) {
                prefixSum[i] += diff;
            }
            arr[index] = value;
        }
    }

    // approach 2
    static class SegmentTreeApproach {
        int[] arr;
        int[] prefixSum = null;
        int[] segmentTree = null;

        SegmentTreeApproach(int[] arr) {
            this.arr = arr;
            this.segmentTree = new int[2 * arr.length];
            preprocess( 0, arr.length-1, 0);
        }

        // left child -> 2*i + 1
        // right child -> 2*i + 2
        private int preprocess(int left, int right, int index) {
            // Base case : leaf nodes
            if(left == right) {
                segmentTree[index] = arr[left];
                return segmentTree[index];
            }

            int mid = (left + right) / 2;
            int leftSum = preprocess(left, mid, 2*index + 1);
            int rightSum = preprocess(mid+1, right, 2*index + 2);

            segmentTree[index] = leftSum + rightSum;
            return segmentTree[index];
        }

        private int rangeSum(int x1, int x2) {
            return rangeSumRec(x1, x2, 0, arr.length-1, 0);
        }

        // O(nLogn) ??
        private int rangeSumRec(int x1, int x2, int start, int end, int index) {
            if(x2 < start || x1 > end) return 0;
            if (x1 <= start && end <= x2) return segmentTree[index];

            int mid = (start + end) / 2;
            int left = rangeSumRec(x1, x2, start, mid, 2*index + 1);
            int right = rangeSumRec(x1, x2, mid+1, end, 2*index + 2);

            return left + right;
        }

        // update : O(log n)
        private void update(int index, int value) {
            updateRec(index, value, 0, arr.length-1, 0);
        }


        // O(logn)
        private int updateRec(int index, int value, int start, int end, int sindex) {

            if(index < start || index > end) return segmentTree[sindex];
            if(start == end) {
                segmentTree[sindex] = value;
                return segmentTree[sindex];
            }

            int mid = (start + end) / 2;
            int val;

            if (index > mid) {
                int left = segmentTree[2*sindex + 1];
                val = updateRec(index, value, mid+1, end, 2*sindex+2) + left;
            } else {
                int right = segmentTree[2*sindex + 2];
                val = updateRec(index, value, start, mid, 2*sindex+1) + right;
            }

            segmentTree[sindex] = val;
            return val;
        }
    }


    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9};

        PrefixSumApproach psa = new PrefixSumApproach(arr);
        System.out.println(psa.rangeSum(1, 4));
        psa.update(2,  4);
        System.out.println(psa.rangeSum(1, 4));

        SegmentTreeApproach sta = new SegmentTreeApproach(arr);
        sta.update(2, 4);
        System.out.println(sta.rangeSum(1, 4));
    }
}

// [25, 9, 16, 4, 5, 7, 9, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
// [24, 8, 16, 4, 4, 7, 9, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
