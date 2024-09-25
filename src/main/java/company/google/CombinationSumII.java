package company.google;
import java.util.*;

// Combination sum get all the combination list
public class CombinationSumII {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        List<List<Integer>> list = new ArrayList<List<Integer>>();
        int length = candidates.length;

        Arrays.sort(candidates);
        combinationSR(candidates, new LinkedList<Integer>(), list, target, 0);
        return list;
    }

    void combinationSR(int[] candidates, LinkedList<Integer> temp, List<List<Integer>> list, int remain, int curr) {

        if( remain == 0) {
            list.add(new ArrayList<Integer>(temp));
            return;
        }

        for(int i = curr; i < candidates.length; i++) {
            if( i > curr && candidates[i] == candidates[i - 1])
                continue;

            Integer e = candidates[i];
            if( remain - e < 0)
                break;
            temp.addLast(e);
            combinationSR(candidates,temp,list, remain-e, i+1);
            temp.removeLast();

        }

    }

    public static void main(String[] args) {
        System.out.println(new CombinationSumII().combinationSum2(new int[]{10,1,2,7,6,1,5}, 8));
    }
}
