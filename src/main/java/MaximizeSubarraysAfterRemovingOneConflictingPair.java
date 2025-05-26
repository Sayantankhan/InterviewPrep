import java.util.*;

public class MaximizeSubarraysAfterRemovingOneConflictingPair {
    // Maximize Subarrays After Removing One Conflicting Pair
    // For an array of size n, total number of subarrays is: n*(n+1)/2
    // subarray means consicutive = {1. 2. 3} total  - 1 + 2 + 3 = 6
    public int maxSubarrays(int n, List<List<Integer>> conflictingPairs) {
        List<List<Integer>> right = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            right.add(new ArrayList<>());
        }

        // Populate the right conflict list
        for (List<Integer> pair : conflictingPairs) {
            int a = pair.get(0);
            int b = pair.get(1);
            right.get(Math.max(a, b)).add(Math.min(a, b));
        }

        int ans = 0;
        int[] left = {0, 0};
        int[] bonus = new int[n + 1];

        for (int r = 1; r <= n; r++) {
            for (int l : right.get(r)) {
                left = maxPair(left, new int[]{l, left[0]}, new int[]{left[0], l});
            }
            ans += r - left[0];
            bonus[left[0]] += left[0] - left[1];
        }

        return ans + Arrays.stream(bonus).max().getAsInt();
    }

    private int[] maxPair(int[]... pairs) {
        int[] max = pairs[0];
        for (int[] p : pairs) {
            if (comparePair(p, max) > 0) {
                max = p;
            }
        }
        return max;
    }

    // Compares lexicographically: first element, then second if tie
    private int comparePair(int[] a, int[] b) {
        if (a[0] != b[0]) {
            return Integer.compare(a[0], b[0]);
        }
        return Integer.compare(a[1], b[1]);
    }

    public static void main(String[] args) {
        int ans =
                new MaximizeSubarraysAfterRemovingOneConflictingPair().maxSubarrays(5, List.of(List.of(1, 2), List.of(2, 5), List.of(3, 5)));
        System.out.println(ans);
    }
}
