import tree.TreeNode;

public class BinaryTreeUpsideDown {
    // Binary Tree Upside Down
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if(root == null || root.left == null) return root;
        return upsideDownBinaryTreeRec(root);
    }

    TreeNode upsideDownBinaryTreeRec(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }

        TreeNode node = upsideDownBinaryTreeRec(root.left);

        //if(parent == null) return node;
        root.left.left = root.right; // new left child
        root.left.right = root;

        root.right = null;
        root.left = null;
        return node;
    }
}
