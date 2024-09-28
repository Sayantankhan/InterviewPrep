package company.google;

import tree.TreeNode;

import java.util.*;

//  Find Leaves of Binary Tree
public class FindLeavesBinaryTree {


    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        findLeavesDFS(root, ans);
        return ans;
    }

    // returing height
    private int findLeavesDFS(TreeNode root, List<List<Integer>> ans) {
        if(root == null) return -1;

        int left = findLeavesDFS(root.left, ans);
        int right = findLeavesDFS(root.right, ans);

        int level = Math.max(left, right) + 1;

        if(ans.size()-1 < level) {
            ans.add(new ArrayList<Integer>());
        }

        ans.get(level).add(root.val);
        return level;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode ll = new TreeNode(4);
        TreeNode lr = new TreeNode(5);

        left.left = ll;
        left.right = lr;
        root.left = left;
        root.right = right;

        System.out.println(new FindLeavesBinaryTree().findLeaves(root));
    }
}
