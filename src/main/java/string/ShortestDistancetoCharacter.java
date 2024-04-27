package string;

import util.Utility;

import java.util.Arrays;

// https://leetcode.com/problems/shortest-distance-to-a-character/description/
public class ShortestDistancetoCharacter {

    // All this problem check by left to right and right to left approach
    public static int[] shortestToChar(String s, char c) {
        //loveleetcode -- left to right -- 0 0 0 0 1 0 0 1 2 3 4 0
        //loveleetcode -- right to left -- 3 2 1 0 1 0 0 4 3 2 1 0

        // left to right
        int[] left = new int[s.length()];
        int index = -1;
        for(int i = 0; i < left.length; i++) {
            if(s.charAt(i) == c) {
                index = 0;
                left[i] = 0;
            }
            else {
                if(index == -1) {
                    left[i] = Integer.MAX_VALUE;
                } else {
                    left[i] = ++index;
                }
            }
        }

        int[] right = new int[s.length()];
        int[] ans = new int[left.length];
        index = -1;
        for(int i = right.length-1; i >= 0; i--) {
            if(s.charAt(i) == c) {
                index = 0;
                right[i] = 0;
            } else {
                if(index == -1) {
                    right[i] = Integer.MAX_VALUE;
                } else {
                    right[i] = ++index;
                }
            }

            ans[i] = Math.min(left[i], right[i]);
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(shortestToChar("loveleetcode", 'e'), new int[]{3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0});
        Utility.assertTrue(shortestToChar("aaab", 'b'), new int[]{3, 2, 1, 0});
    }
}
