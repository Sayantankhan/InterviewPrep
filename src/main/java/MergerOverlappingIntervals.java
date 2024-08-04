import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class MergerOverlappingIntervals {

    static int[][] mergeIntervals(int[][] intervals) {
        // add your logic here
        Arrays.sort(intervals, (interval1, interval2) -> {
            return interval1[0] - interval2[0];
        });

        Stack<int[]> stack = new Stack();

        for(int i = 1; i < intervals.length; i++) {
            if(!stack.empty() && intervals[i][0] <= stack.peek()[1]) {
                int[] ival = stack.pop();
                ival[1] = intervals[i][1] > ival[1] ? intervals[i][1] : ival[1];
                stack.push(ival);
            } else {
                stack.add(intervals[i]);
            }
        }

        int[][] results = new int[stack.size()][2];
        for(int i = stack.size()-1; i >=0; i--) {
            results[i] = stack.pop();
        }


        return results;
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][] {
                {1, 2},
                {2, 3},
                {1, 4},
                {5, 6}
        };

        System.out.println(mergeIntervals(intervals));
    }
}
