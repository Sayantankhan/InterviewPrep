package arrays;

public class MaxSumWithK {
    public static long maxSumWithK(long a[], long n, long k)
    {
        // [-2. 1. 2. 3]

        int i = 0, j = 0;
        long sum = 0;
        long max = Integer.MIN_VALUE;
        long[] max_prefix_sum = new long[a.length];
        for(int x = 0; x < n; x++) {
            sum += a[x];
            max_prefix_sum[x] = Math.max(sum, a[x]);
            if(sum < 0) sum = 0;
        }
        sum = 0;
        while(j < n) {
            sum += a[j];
            if(j-i+1 < k) j++;
            else if(j-i+1 == k) {
                max = Math.max(max, sum);
                sum -= a[i];
                max = Math.max(max, sum + max_prefix_sum[i]);
                i++;
                j++;
            }
        }

        return max;
    }

}
