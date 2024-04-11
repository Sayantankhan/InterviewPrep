package tree;

import com.sun.source.tree.Tree;
import util.Utility;

// Given a BST node, return the node which has value just greater than the given node.
// https://www.interviewbit.com/problems/next-greater-number-bst/
public class NextGreaterNumberBST {

    public static TreeNode getSuccessorRec(TreeNode root, int el, TreeNode successor) {
        // if(root == null) return null; // This line to be added if el is not in the tree
        if(root.val == el) {
            if(root.right != null) {
                // find the left most
                return getMostleft(root.right);
            }
            return null;
        }

        TreeNode temp = null;
        if(root.val > el) {
            successor = root;
            temp = getSuccessorRec(root.left, el, successor);
        } else {
            temp = getSuccessorRec(root.right, el, successor);
        }
        return (temp == null) ? successor : temp;
    }

    private static TreeNode getMostleft(TreeNode right) {
        if(right.left != null) {
            return getMostleft(right.left);
        }
        return right;
    }

    private static TreeNode getSuccessor(TreeNode root, int el) {
       return getSuccessorRec(root, el, null);
    }

    public static void main(String[] args) throws Exception {
        TreeNode root = Utility.generateBinartTreeFromArray(new Integer[]{100, 98, 102, 96, 99, null, null, null, 97, null, null });
        Utility.assertTrue(getSuccessor(root, 99).val, 100);
        Utility.assertTrue(getSuccessor(root, 97).val, 98);
        Utility.assertTrue(getSuccessor(root, 102), null);
        // Utility.assertTrue(getSuccessor(root, 103), null);

        TreeNode root1 = Utility.generateBinartTreeFromArray(new Integer[]{20, 9, 25, 5, 12, null, null, null, null, 11, 14});
        Utility.assertTrue(getSuccessor(root1, 12).val, 14);
        Utility.assertTrue(getSuccessor(root1, 14).val, 20);

        TreeNode root2 = Utility.generateBinartTreeFromArray(new Integer[]{20, 9, 25, 5, 12, null, null, null, null, 11, 14, null, null, 13, null});
        Utility.assertTrue(getSuccessor(root2, 12).val, 13);
    }
}
