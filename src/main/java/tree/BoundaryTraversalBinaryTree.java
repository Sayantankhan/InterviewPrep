package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

// https://www.naukri.com/code360/problems/boundary-traversal_790725?topList=top-salesforce-coding-interview-questions&problemListRedirection=true
public class BoundaryTraversalBinaryTree {

    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static Predicate<TreeNode> isLeaf = treeNode -> treeNode.left == null && treeNode.right == null;

    public static List<Integer> traverseBoundary(TreeNode root){
        List<Integer> list = new ArrayList<>();

        TreeNode temp = root;
        while(!isLeaf.test(temp)) {
            list.add(temp.data);
            temp = temp.left;
        }

        return list;
    }
}
