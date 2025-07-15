import java.util.Arrays;

public class FrequencyMostFrequentElement {
    // Frequency of the Most Frequent Element

    public int maxFrequency(int[] nums, int k) {

        Arrays.sort(nums);
        long total = 0;
        int left = 0;
        int maxFreq = 0;

        for (int right = 0; right < nums.length; right++)  {
            long target = nums[right];
            total += target;

            while(target * (right - left + 1) > total + k) {
                total -= nums[left];
                left++;
            }

            maxFreq = Math.max(maxFreq, right - left + 1);
        }
        return maxFreq;
    }
}
