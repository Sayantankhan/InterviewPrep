import java.util.*;

// maxDepth - (the depth of the integer) + 1
// nested weight sum
interface NestedInteger {
     public boolean isInteger();
     public Integer getInteger();
     public void setInteger(int value);
     public void add(NestedInteger ni);
     public List<NestedInteger> getList();
 }

public class NestedListWeighSumII {
        // reverse depth weighting
        // rather than doing the calculation like compute depth amd then weight, just look at the equation
        // ∑(value × (maxDepth - depth + 1))
        // now understand the trick :: ReverseSum = (maxDepth + 1) × flatSum - WeightedDepthSum(∑value*depth)
        // lets understand this by formula
        // x is a number , depth 1 and max-depth = 5 = (5 - 1 + 1)x
        // y is a number , depth 2 and max-depth = 5 = (5 - 2 + 1)y
        // z is a number , depth 3 and max-depth = 5 = (5 - 3 + 1)z
        // = (5 - 1 + 1)x + (5 - 2 + 1)y + (5 - 3 + 1)z
        // = 5x -x + x + 5y - 2y + y + 5z - 3z +z
        // = 5(x+y+z)- (x+2y+3z) + (x+y+z)
        // = (5+1) ∑(x+y+z) - ∑(x+2y+3z)
        // = (max_depth+1)*flatten_sum - weightedDepthSum


        private int maxDepth = 1;
        private int flatSum = 0;
        private int depthSum = 0;

        public int depthSumInverse(List<NestedInteger> nestedList) {
            dfs(nestedList, 1);
            return (maxDepth+1)*flatSum - depthSum;
        }

        void dfs(List<NestedInteger> list, int depth) {
            if(list == null) return;

            maxDepth = Math.max(maxDepth, depth);

            for(NestedInteger ni : list) {
                if(ni.isInteger()) {
                    flatSum += ni.getInteger();
                    depthSum += depth * ni.getInteger();
                }
                else {
                    dfs(ni.getList(), depth+1);
                }
            }
        }
}
