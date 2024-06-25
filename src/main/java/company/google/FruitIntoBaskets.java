package company.google;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/fruit-into-baskets/description/
public class FruitIntoBaskets {

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

    public static void main(String[] args) {
        System.out.println(new FruitIntoBaskets().totalFruit(new int[] {1, 2, 1}));
        //System.out.println(new FruitIntoBaskets().totalFruit(new int[] {0, 1, 2, 2}));
        //System.out.println(new FruitIntoBaskets().totalFruit(new int[] {1,2,3,2,2}));
    }
}
