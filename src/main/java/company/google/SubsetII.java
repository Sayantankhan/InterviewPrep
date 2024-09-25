package company.google;
import java.util.*;

public class SubsetII {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Set<List<Integer>> lists = new HashSet<List<Integer>> ();
        List<Integer> result = new ArrayList<Integer>();
        Arrays.sort(nums);
        subsetsWithDupRec(nums, 0, lists, result);
        return new ArrayList<>(lists);
    }

    void subsetsWithDupRec(int[] nums, int index, Set<List<Integer>> list, List<Integer> result) {
        if(index >= nums.length) {
            List<Integer> r = new ArrayList<Integer>(result);
            // Collections.sort(r);
            list.add(r);
            return;
        }

        // should take it
        result.add(nums[index]);
        subsetsWithDupRec(nums, index+1, list, result);
        // or not
        result.remove(result.size() -1);
        subsetsWithDupRec(nums, index+1, list, result);
    }
}
