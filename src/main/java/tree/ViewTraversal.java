package tree;

import com.sun.source.tree.Tree;
import util.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;

// https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/description/
// https://leetcode.com/problems/find-bottom-left-tree-value/description/
public class ViewTraversal {

    public static void main(String[] args) {
        TreeNode root = Utility.generateBinartTreeFromArray(new Integer[]{20, 8, 22, 5, 3, null, 25, null, null, 10, 14});
        TreeNode root2 = Utility.generateBinartTreeFromArray(new Integer[]{20, 8, 22, 5, 3, 4, 25, null, null, 10, null, null, 14});

        // bottomViewBT(root);
        bottomViewBT(root2);

        int[] nodes = {8,5,1,7,10,12};
        TreeNode rt = bstFromPreorder(nodes);
        System.out.println(rt.val);

        TreeNode node =  Utility.generateBinartTreeFromArray(new Integer[]{1,2,3,4,null,5,6,null,null,7});
        findBottomLeftValue(node);

        System.out.println(Math.random());

    }

    // https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/description/
    public static TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = new TreeNode(preorder[0]);
        for(int i = 1; i < preorder.length; i++) {
            addToTree(preorder[i], root);
        }
        return root;
    }

    static void addToTree(int node, TreeNode root) {

        if(root == null) return;

        if(root.val < node) {
            addToTree(node, root.right);
            if(root.right == null) root.right = new TreeNode(node);
        }
        if(root.val > node) {
            addToTree(node, root.left);
            if(root.left == null) root.left = new TreeNode(node);
        }
    }

    // https://leetcode.com/problems/find-bottom-left-tree-value/description/
    static Predicate<TreeNode> isLeaf = (node) -> node.left == null && node.right == null;
    public static int findBottomLeftValue(TreeNode root) {
        Tuple max = new Tuple(null, Integer.MIN_VALUE);
        findBottomLeftValueRec(root, 0, max);
        return 0;
    }

    private static void findBottomLeftValueRec(TreeNode root, int height, Tuple max) {
        if(root == null) return;

        if(isLeaf.test(root)) {
            if(max.height < height) {
                max.height = height;
                max.node = root;
            }
        }

        findBottomLeftValueRec(root.left, height+1, max);
        findBottomLeftValueRec(root.right, height+1, max);
    }


    // Bottom View
    static class Tuple {
        TreeNode node;
        int height;

        public Tuple(TreeNode node, int height) {
            this.node = node;
            this.height = height;
        }
    }
    private static void bottomViewBT(TreeNode root) {
        Map<Integer, Tuple> map = new TreeMap();
        // reason of adding a height to check if there is another node of same i, which one to choose, as it is bottom view
        // we will choose the one with higher heights
        bottomViewBTColumnWise(root, map, 0, 0);
        map.forEach((key, tuple) -> {
            System.out.print(tuple.node.val + " ");
        });
        System.out.println();
    }

    private static void bottomViewBTColumnWise(TreeNode root, Map<Integer, Tuple> map, int i, int height) {

        if(root == null) return;

        Tuple t = new Tuple(root, height);
        Tuple l = map.getOrDefault(i, null);

        if(l == null) {
            map.put(i, t);
        } else {
            if(t.height > l.height) map.put(i, t);
        }


        bottomViewBTColumnWise(root.left, map, i-1, height+1);
        bottomViewBTColumnWise(root.right, map, i+1, height+1);
    }

}
