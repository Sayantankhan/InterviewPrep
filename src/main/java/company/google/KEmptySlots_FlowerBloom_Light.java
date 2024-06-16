package company.google;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.ca/all/683.html
public class KEmptySlots_FlowerBloom_Light {

    private static int getKEmptySlots(int[] bulbs, int k) {
        // Approach : sliding window
        // Window size : 1 + k + 1
        // Window pos  : index + k + 1

        // (index_of_bulb_lighting, day)
        Map<Integer, Integer> dayMap = new HashMap<>();

        for(int i = 0; i < bulbs.length; i++) {
            dayMap.put(bulbs[i], i+1);
        }

        int index = 0;
        int window = index + k + 1;
        int left = 1;

        int result = Integer.MAX_VALUE;

        while(window <= bulbs.length ) {
            index++;
            if(dayMap.get(index) > dayMap.get(left) && dayMap.get(index) > dayMap.get(window)) {
                continue;
            }

            if(index == window) {
                result = Math.min(result, Math.max(dayMap.get(left), dayMap.get(window)));
            }

            left = index;
            window = index + k + 1;
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    public static void main(String[] args) {
        int []bulbs = new int[] {1, 3, 2};
        System.out.println(getKEmptySlots(bulbs, 1));

        bulbs = new int[] {1, 2, 3};
        System.out.println(getKEmptySlots(bulbs, 1));
    }
}
