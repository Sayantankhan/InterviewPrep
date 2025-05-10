package arrays.slidingwindow;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/fruit-into-baskets/description/
public class FruitIntoBaskets {
    // Fruit Into Baskets
    public int totalFruit(int[] fruits) {
        // treeindex -> count
        Map<Integer, Integer> map = new HashMap<>();
        map.put(fruits[0], 1);
        int maxProfit = -1;

        int left = 0;
        int right = -1;

        for(int i = 1; i < fruits.length; i++) {
           int val = map.getOrDefault(fruits[i], 0);
           if(val == 0) {
               if(map.size() < 2) {
                   map.put(fruits[i], 1);
               }
               else {
                   maxProfit = Math.max(maxProfit, calculateProfit(map));
                   int window = map.size();
                   while(left < fruits.length && window >= 2) {
                       if(map.get(fruits[left]) > 1) map.put(fruits[left], map.get(fruits[left])-1);
                       else {
                           map.remove(fruits[left]);
                       }
                       window = map.size();
                       left ++;
                   }
                   map.put(fruits[i], 1);
               }
           } else {
               map.put(fruits[i], ++val);
           }
        }

        return Math.max(maxProfit, calculateProfit(map));
    }

    private int calculateProfit(Map<Integer, Integer> map) {
        int val = 0;
        for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
            val += entry.getValue();
        }
        return val;
    }

    // O(n)
    public int totalFruit2(int[] fruits) {
        Map<Integer, Integer> map = new HashMap<>();

        int ans = Integer.MIN_VALUE;

        int start = 0;
        int len = fruits.length;

        int busket1 = -1;
        int busket2 = -1;

        for(int end = 0; end < len; end++) {
            if(map.size() < 2) {
                map.put(fruits[end], end);
                if(busket1 == -1) busket1 = fruits[end];
                else if(busket1 != fruits[end]) busket2 = fruits[end];
            } else {
                int index = map.getOrDefault(fruits[end], -1);
                if(index == -1) {
                    ans = Math.max(ans, end - start);

                    int busket1_index = map.get(busket1);
                    int busket2_index = map.get(busket2);

                    if(busket1_index > busket2_index) {
                        start = busket2_index + 1;
                        map.remove(busket2);
                        busket2 = fruits[end];
                    } else {
                        start = busket1_index + 1;
                        map.remove(busket1);
                        busket1 = fruits[end];
                    }

                }
                map.put(fruits[end], end);
            }
        }

        return Math.max(ans, (len - start));
    }

    public static void main(String[] args) {
        System.out.println(new FruitIntoBaskets().totalFruit2(new int[] {3,3,3,1,2,1,1,2,3,3,4}));
        //System.out.println(new FruitIntoBaskets().totalFruit(new int[] {0, 1, 2, 2}));
        //System.out.println(new FruitIntoBaskets().totalFruit(new int[] {1,2,3,2,2}));
    }
}
