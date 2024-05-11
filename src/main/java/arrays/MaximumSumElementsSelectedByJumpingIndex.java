package arrays;

// https://www.naukri.com/code360/library/maximize-the-sum-of-elements-arr-i-selected-by-jumping-index-by-value-i
// maximize the sum of elements ARR[i] selected by jumping index by value i.
public class MaximumSumElementsSelectedByJumpingIndex {

    /*
     * We have been given an array ‘ARR’ of size ‘N.’
     * Our task is to find the maximum sum of elements from the array in such a way that if we pick an ith element,
     * then ARR[i] will be added to the sum, and we will make the ‘ARR[i]’ several jumps from the current index till
     * we cross the array.
     */
    private static void findMaxScore(int[] arr) {
//        int max = Integer.MIN_VALUE;
//        for(int i = 0; i < arr.length; i++) {
//            max = Math.max(max, findMaxScoreRec(arr, i, 0, ""));
//        }
//        System.out.println(max);

        System.out.println(findMaxScoreDP(arr));
    }

    private static int findMaxScoreRec(int[] arr, int index, int score, String res) {
        if(index >= arr.length) {
            System.out.println(res);
            return score;
        }

        return findMaxScoreRec(arr, index + arr[index],score + arr[index], res + "-"+arr[index]);
    }

    // index + arr[index]  ---> new_index
    // score += arr[new_index]
    private static int findMaxScoreDP(int[] arr) {
       int[] dp = new int[arr.length];
       dp[arr.length - 1] = arr[arr.length - 1];
       int maxScore = 0;
       for(int i = arr.length-2 ; i >= 0; i--) {
           dp[i] = arr[i];
           if(arr[i] + i < arr.length) {
               dp[i] = dp[i] + dp[i + arr[i]];
           }
           maxScore = Math.max(maxScore, dp[i]);
       }

       return maxScore;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        findMaxScore(arr);
    }


}
