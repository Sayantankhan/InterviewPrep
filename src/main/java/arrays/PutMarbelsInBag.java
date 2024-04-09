package arrays;

import util.Utility;

import java.util.Arrays;

// https://leetcode.com/problems/put-marbles-in-bags/description/
public class PutMarbelsInBag {

    /*
     * Approach : if there is k bags , so number of partition will be k-1
     * if we have a set of { a, b, c, d, e } and k = 2
     * then we can divide this like below -> Total combination possible -> { 4C1 } -> 4!/(1!)(3!) -> 4 cases
     *  Case 1 > a | b c d e    --> weight -> a + a + b + e
     *  Case 2 > a b | c d e    --> weight -> a + b + c + e
     *  Case 3 > a b c | d e    --> weight -> a + c + d + e
     *  Case 4 > a b c d | e    --> weight -> a + d + e + e
     *
     *  we can see for every case first and last elements are coming when we do a minus, both first and last will be removed
     *  Lets take for Case 1 and Case 2 --> ( a + a + b + e ) - ( a + b + c + e ) == (a+b) - (c+e)
     *  Lets take for Case 2 and Case 3 --> ( a + b + c + e ) - ( a + c + d + e ) == (b+c) - (c+d)
     *  Lets take for Case 3 and Case 4 --> ( a + c + d + e ) - ( a + d + e + e ) == (c+d) - (d+e)
     *
     *  Observation 1 : As we can see it we are only focusing on the sums of the element which are opposite sides of each partition
     *  We can keep a sum array to hold all the consecutive sums of elements = sum[i] = set[i] + set[i+1]
     *
     *  Observation 2 : As it has been told to get min and max weight diff, we could sort the sum to get min and max
     *
     *  Let's take an example : {1,3,5,1};  k = 2
     *  Applying Observation 1 : Sum => [4, 8, 6, 0] || 0 as there is no ops performed on the last element
     *  Applying Observation 2 : sort(Sum, 0..sum.len-1) -> Sum -> [4, 6, 8, 0] || as we have not performed ops for the last element; we dont need that
     *
     *  Now we have the array of min and max ; we can substract and then add
     *  weight = (8 - 4) + (4 - 4) = 4
     */
    public static long putMarbles(int[] weights, int k) {
        int[] sum = new int[weights.length];

        // Applying Observation 1
        for(int i = 0; i < weights.length-1; i++) {
            sum[i] = weights[i] + weights[i+1];
        }

        // Applying Observation 2
        Arrays.sort(sum, 0, sum.length-1);

        // Calculating
        int ans = 0;
        for (int i = 0; i < k-1; i++) { // if there is k bags , so number of partition will be k-1
            ans += sum[sum.length - 2 - i] - sum[i];
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(putMarbles(new int[] {1, 3, 5, 1}, 2),  (long)4);
        Utility.assertTrue(putMarbles(new int[] {1, 3}, 2), (long)0);
    }
}