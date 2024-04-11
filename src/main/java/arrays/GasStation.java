package arrays;

import util.Utility;

import java.util.Arrays;
import java.util.stream.IntStream;

public class GasStation {

    /*
     * Gas -> [1,2,3,4,5]
     * Cost -> [3,4,5,1,2]
     *
     * Wei -> [-2, -2, -2, 3, 3]
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {

        int totalGas = IntStream.of(gas).sum();
        int totalCost = IntStream.of(cost).sum();

        // Not Enough Gas
        if (totalGas < totalCost) return -1;
        int total = 0;
        int index = 0;
        for(int i = 0; i < gas.length; i++) {
            total += (gas[i] - cost[i]);

            if(total < 0) {
                total = 0;
                index = i + 1;
            }
        }
        return index;
    }

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(canCompleteCircuit(new int[] {1,2,3,4,5}, new int[]{3,4,5,1,2}), 3);
        Utility.assertTrue(canCompleteCircuit(new int[] {2,3,4}, new int[]{3,4,3}), -1);
        Utility.assertTrue(canCompleteCircuit(new int[] {5,1,2,3,4}, new int[]{4,4,1,5,1}), 4);
    }
}
