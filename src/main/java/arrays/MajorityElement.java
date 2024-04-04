package arrays;

import util.Utility;

// https://leetcode.com/problems/majority-element/description/
public class MajorityElement {

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(majorityElement(new int[] {2,2,1,1,1,2,2}), 2);
        Utility.assertTrue(majorityElement(new int[] {6, 5, 5}), 5);
    }

    /* Approach 1: using hash map; which will take .. Time-Complexity : O(N) and Space Complexity : O(N)

     * Approach 2: Moore's Voting Algorithm - [THIS ONLY APPLICABLE FOR N/2]
     * The Boyer-Moore voting algorithm is one of the popular optimal algorithms which is used to find the majority element
     * among the given elements that have more than N/ 2 occurrences. This works perfectly fine for finding the majority element
     * which takes 2 traversals over the given elements, which works in O(N) time complexity and O(1) space complexity.

     * When the elements are the same as the candidate element, votes are incremented
     * whereas when some other element is found (not equal to the candidate element), we decreased the count.
     * This actually means that we are decreasing the priority of winning ability of the selected candidate,
     * since we know that if the candidate is in majority it occurs more than N/2 times and the remaining elements are less than N/2.
     * We keep decreasing the votes since we found some different element(s) than the candidate element.
     */
    private static int majorityElement(int[] ints) {

        int el = ints[0];
        int count = 1;

        // this algo says ,IFF there is any element exist that more than N/2 that will be el
        for (int i = 1; i < ints.length; i++) {
            if (el == ints[i]) {
                count ++;
            } else {
                count --;
            }

            if (count == 0) {
                i++;
                el = ints[i];
                count = 1;
            }
        }

        // to verify the result
        count = 0;
        for (int i = 0; i < ints.length; i++) {
            if(ints[i] == el) {
                count ++ ;
            }
        }

        if ( count > ints.length/2) {
            return el;
        }
        return -1;
    }
}
