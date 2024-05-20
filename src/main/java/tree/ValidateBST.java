package tree;

import util.Utility;

public class ValidateBST {

    public boolean isValidBST(TreeNode root) {
        return isValidBSTRec(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean isValidBSTRec(TreeNode root, int minVal, int maxVal) {
        if(root == null) return true;

        if(root.val >= maxVal || root.val <= minVal) return false;

        return isValidBSTRec(root.left, minVal, root.val) && isValidBSTRec(root.right, root.val, maxVal);
    }

    public static void main(String[] args) {
        TreeNode root = Utility.generateBinartTreeFromArray(new Integer[]{5,1,8,null,null,3,9});

    }
}
