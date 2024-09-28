package tree;

import util.Utility;

// Maximum path sum
// Maximum sum path
// https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
public class BinaryTreeMaximumPathSum {

    static int max = Integer.MIN_VALUE;
    public static int maxPathSumRec(TreeNode root) {
        if (root == null) return 0;

        int left = maxPathSumRec(root.left);
        int right = maxPathSumRec(root.right);

        int local = root.val + left + right; // U
        // If one child is neg : keep max child
        int temp = root.val + Math.max(left, right);
        // If both the children are neg : only root
        int negTemp = root.val;

        // This to calculate global maxima
        max = Math.max(max, Math.max(local, Math.max(temp, negTemp)));

        // returning local maxima
        return Math.max(temp, negTemp);
    }

    public static int maxPathSum(TreeNode root) {
        maxPathSumRec(root);
        return max;
    }

    public static void main(String[] args) throws Exception {
        TreeNode root = Utility.generateBinartTreeFromArray(new Integer[]{-10,9,20,null,null,15,7});
        Utility.assertTrue(maxPathSum(root), 42);
    }

}
