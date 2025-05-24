package company.google;

import tree.TreeNode;

import java.util.*;

//  Find Leaves of Binary Tree
public class FindLeavesBinaryTree {

    // make leaf as height 1 and add 1+ parent until finding root
    // hashmap to store the val
    public List<List<Integer>> findLeavesII(TreeNode root) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<List<Integer>> ans = new ArrayList<>();

        findLeavesRec(root, map);

        for(Map.Entry<Integer, List<Integer>> entry : map.entrySet()){
            ans.add(entry.getValue());
        }
        return ans;
    }

    int findLeavesRec(TreeNode root, Map<Integer, List<Integer>> map) {
        if(root == null) return 0;
        int height = 1 + Math.max(findLeavesRec(root.left, map), findLeavesRec(root.right, map));
        map.computeIfAbsent(height, k -> new ArrayList<Integer>()).add(root.val);
        return height;
    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        findLeavesDFS(root, ans);
        return ans;
    }

    // returing height - same concept just not storing in hash map
    private int findLeavesDFS(TreeNode root, List<List<Integer>> ans) {
        if(root == null) return 0;

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
