import tree.TreeNode;

public class HouseRobberIII {

    public int rob(TreeNode root) {
        int[] ans = findMaxforRob(root);
        return Math.max(ans[0], ans[1]);
    }

    int[] findMaxforRob(TreeNode node) {
        if(node == null) {
            // robbed , not robbed
            return new int[]{0, 0};
        }

        int[] left = findMaxforRob(node.left);
        int[] right = findMaxforRob(node.right);

        int rob = node.val + left[1] + right[1];
        int notrobbed = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[] {rob, notrobbed};
    }
}
