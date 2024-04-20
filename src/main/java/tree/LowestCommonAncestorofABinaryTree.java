package tree;

import util.Utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
public class LowestCommonAncestorofABinaryTree {

    // where we allow a node to be a descendant of itself
    // One approach is find the path for both the node and check
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> temp = new ArrayList<>();
        List<List<TreeNode>> path = new ArrayList<List<TreeNode>>();
        findNode(root, p, q, temp, path);

        List<TreeNode> it1 = path.get(0);
        List<TreeNode> it2 = path.get(1);

        int tmp = -1;
        for(int i = 0; i < (it1.size() < it2.size() ? it1.size() : it2.size()); i++) {
            if(!it1.get(i).equals(it2.get(i))) {
                tmp = i-1;
                break;
            }
            tmp = i;
        }

        return (tmp < 0) ? null : it1.get(tmp);
    }

    private static void findNode(TreeNode root, TreeNode p, TreeNode q, List<TreeNode> temp, List<List<TreeNode>> list) {

        if(root == null) return;

        temp.add(root);
        if(root.val == p.val || root.val == q.val) {
            list.add(new ArrayList<>(temp));
        }

        findNode(root.left, p, q, temp, list);
        findNode(root.right, p, q, temp, list);
        temp.remove(temp.size()-1);
    }

    // Another approach is - if we have to find LCA for two nodes who belongs to same side then which is closer to root will be the ans
    // if both lies in diff side then root of both of them will be the ans.
    public static TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return null;
        }
        if(root == p || root == q){
            return root;
        }
        TreeNode left = lowestCommonAncestor1(root.left , p , q);
        TreeNode right = lowestCommonAncestor1(root.right , p , q);

        if(left == null) return right;
        if(right == null) return left;
        if(left != null && right != null){
            return root;
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        TreeNode root = Utility.generateBinartTreeFromArray(new Integer[]{3,5,1,6,2,0,8,null,null,7,4});

        // case 1: p = 5, q = 1
        TreeNode p = Utility.findTreeNode(root, 5);
        TreeNode q = Utility.findTreeNode(root, 1);
        TreeNode ans = Utility.findTreeNode(root, 3);

        Utility.assertTrue(lowestCommonAncestor1(root, p, q), ans);

        // case 2: p = 5, q = 4
        q = Utility.findTreeNode(root, 4);
        TreeNode aa = lowestCommonAncestor(root, p, q);
        Utility.assertTrue(lowestCommonAncestor1(root, p, q), p);
    }
}
