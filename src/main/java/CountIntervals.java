import java.util.*;

public class CountIntervals {

    final TreeMap<Integer, Integer> map;
    int count = 0;
    public CountIntervals() {
        map = new TreeMap<>();
    }

    public void add(int left, int right) {

        Map.Entry<Integer, Integer> range = map.floorEntry(left); // looks for prev nearest
        if(range == null || range.getValue() < left) range = map.higherEntry(left); // looks for next nearest

        while(range != null && range.getKey() <= right) {
            left = Math.min(range.getKey(), left);
            right = Math.max(range.getValue(), right);

            count -= (range.getValue()-range.getKey()+1);
            map.remove(range.getKey());

            range =  map.higherEntry(left);
        }

        map.put(left, right);
        count += right - left + 1;
    }

    public int count() {
        return count;
    }
}
