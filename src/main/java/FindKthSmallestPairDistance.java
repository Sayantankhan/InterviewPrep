import java.util.Arrays;

public class FindKthSmallestPairDistance {

    int helper(int[] nums, int diff) {
        int count = 0;
        int l = 0;

        for(int i = 1; i < nums.length; i++) {
            while(Math.abs(nums[l] - nums[i]) > diff) l++;
            count += Math.abs(i - l);
        }

        return count;
    }

    public int smallestDistancePair(int[] nums, int k) {

        Arrays.sort(nums); // sorting the array so that we can use two pointer
        // binary search to check where to point to

        int l = 0, r = Arrays.stream(nums).max().getAsInt();
        while(l < r) {
            int mid = l + ((r-l)/2);
            int pairs = helper(nums, mid);
            if(pairs >= k) r = mid;
            else l = mid+1;
        }

        return l;
    }
}
