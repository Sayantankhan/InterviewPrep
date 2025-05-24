import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SparseVector {

    final Map<Integer, Integer> map;

    SparseVector(int[] nums) {
        map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) {
                map.put(i, nums[i]);
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        Set<Integer> set = map.keySet();
        int ans = 0;
        for(Integer s : set ){
            if(vec.map.containsKey(s)) {
                ans += map.get(s) * vec.map.get(s);
            }
        }

        return ans;
    }
}
