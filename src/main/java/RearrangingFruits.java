import java.util.*;

// O(Nlogn)
public class RearrangingFruits {
    // Rearranging Fruits
    // Min minimum cost to make both the baskets equal or -1 if impossible.

    // count the freq and indirect swap
    public long minCost(int[] basket1, int[] basket2) {
        if(basket1.length != basket2.length) return -1;

        Map<Integer, Integer> map = new HashMap<>();
        int min = Integer.MAX_VALUE;

        for(int i = 0; i < basket1.length; i++) {
            map.put(basket1[i], map.getOrDefault(basket1[i], 0) + 1);
            map.put(basket2[i],map.getOrDefault(basket2[i], 0) - 1);
            min = Math.min(min, Math.min(basket1[i], basket2[i] ));
        }

        List<Integer> list = new ArrayList<Integer>();
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() % 2 != 0) return -1;
            int val = Math.abs(entry.getValue())/2;
            while(val-- > 0) {
                list.add(entry.getKey());
            }
        }

        Collections.sort(list);
        long count = 0;

        for(int i = 0; i < list.size()/2; i++) {
            // indirect exchage
            // rather than exchaning smallest with biggest
            // we can exchange with another smallest a -> b and b -> c || b < {a, c} || cost - 2*b
            count += Math.min(2*min, list.get(i));
        }

        return count;

    }
}
