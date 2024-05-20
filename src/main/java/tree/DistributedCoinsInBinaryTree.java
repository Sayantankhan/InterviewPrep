package tree;

import util.Utility;

import java.util.function.Predicate;

// https://leetcode.com/problems/distribute-coins-in-binary-tree/description/
public class DistributedCoinsInBinaryTree {

    Predicate<TreeNode> isLeaf = (node) -> { return node.left == null && node.right == null; };
    int minMove = 0;
    public int distributeCoins(TreeNode root) {

        if(root == null) return 0;

        int left = distributeCoins(root.left);
        int right = distributeCoins(root.right);

        int total = left + right + root.val - 1;
        minMove += Math.abs(left) + Math.abs(right);

        return total;
    }

    public static void main(String[] args) {
        TreeNode root = Utility.generateBinartTreeFromArray(new Integer[]{3, 0, 0});
        TreeNode root2 = Utility.generateBinartTreeFromArray(new Integer[]{4, 0, null, 0, 0});

        //System.out.println(new DistributedCoinsInBinaryTree().distributeCoins(root));
        DistributedCoinsInBinaryTree t = new DistributedCoinsInBinaryTree();
        t.distributeCoins(root2);
        System.out.println(t.minMove);
    }
}
