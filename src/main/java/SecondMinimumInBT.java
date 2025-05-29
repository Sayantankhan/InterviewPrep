import tree.TreeNode;

public class SecondMinimumInBT {
    // Second Minimum Node In a Binary Tree

    int ans = Integer.MAX_VALUE;
    public int findSecondMinimumValue(TreeNode root) {

        traverse(root, root.val);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    void traverse(TreeNode node, int min) {
        if(node == null) return;

        if(min < node.val && node.val < ans) ans = node.val;

        traverse(node.left, min);
        traverse(node.right, min);
    }
}
