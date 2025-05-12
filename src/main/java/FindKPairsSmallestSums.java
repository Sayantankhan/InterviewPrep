import org.apache.commons.math3.util.Pair;

import java.util.*;

public class FindKPairsSmallestSums {

    // TC O(min(k⋅logk, m⋅n⋅log(m⋅n)))
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // the idea is first put 0 from num1 and 0 from num2 as they are sorted that will be the smallest
        // then first+1, second and first,second+1 - compare between these two --> MinHeap
        // and who ever small put it in to ans

        int len1 = nums1.length;
        int len2 = nums2.length;

        List<List<Integer>> ans = new ArrayList<>();
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> (a[0] - b[0]));
        Set<Pair<Integer, Integer>> visited = new HashSet<>();

        minHeap.offer(new int[] {nums1[0] + nums2[0], 0, 0});
        visited.add(new Pair<Integer, Integer>(0, 0));

        while(k > 0 && !minHeap.isEmpty()) {
            int[] top = minHeap.poll();
            ans.add(List.of(nums1[top[1]], nums2[top[2]]));
            visited.add(new Pair<Integer, Integer>(top[1], top[2]));

            int first = top[1];
            int second = top[2];

            if(first + 1 < len1 && !visited.contains(new Pair<Integer,Integer>(first+1, second))) {
                minHeap.offer(new int[] {nums1[first+1] + nums2[second], first+1, second});
                visited.add(new Pair<Integer, Integer>(first+1, second));
            }

            if(second+1 < len2 && !visited.contains(new Pair<Integer,Integer>(first, second+1))) {
                minHeap.offer(new int[] {nums1[first] + nums2[second+1], first, second+1});
                visited.add(new Pair<Integer, Integer>(first, second+1));
            }

            k--;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        int k = 3;

        System.out.println(new FindKPairsSmallestSums().kSmallestPairs(nums1, nums2, k));
    }
}
