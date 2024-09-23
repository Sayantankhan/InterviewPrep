package arrays;

// find the sum of all subarrays of the array
public class SumofSubarrays {

    public long subArraySum(int[] arr) {
        long total_contibution = 0;
        long mod = 1000000007;
        for(int i = 0; i < arr.length; i++) {

            long subArrayStartingwithIndex = i + 1;
            long subArrayEndingwithIndex = (arr.length - i);

            long result = (subArrayStartingwithIndex * subArrayEndingwithIndex % mod) * (long) arr[i] % mod;
            total_contibution = (total_contibution +  result) % mod;
        }

        return total_contibution;
    }
}
