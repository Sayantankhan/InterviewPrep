package tree;

import com.sun.source.tree.Tree;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SplitBST {

    static class TreeNode<T> {
        public T data;
        public TreeNode<T> left;
        public TreeNode<T> right;

        TreeNode(T data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(50);

        TreeNode<Integer> left = new TreeNode<>(13);
        TreeNode<Integer> right = new TreeNode<>(72);

        TreeNode<Integer> ll = new TreeNode<>(3);
        TreeNode<Integer> lr = new TreeNode<>(25);

        TreeNode<Integer> rl = new TreeNode<>(66);

        right.left = rl;

        left.left = ll;
        left.right = lr;

        root.left = left;
        root.right = right;

        System.out.println(splitBST(root, 26));
    }

    // 1. In-order
    // 2. Binary search : vaL
    // 3. Build bst from that index -> 0 - bindex-1 || bindex - n
    public static ArrayList<TreeNode<Integer>> splitBST(TreeNode<Integer> root, int val) {
        //    Write your code here
        ArrayList<TreeNode<Integer>> list = new ArrayList<>();
        List<TreeNode<Integer>> inOrder = new ArrayList<>();

        findInOrder(root, inOrder);
        int index = Collections.binarySearch(inOrder, new TreeNode(val), (node1, node2) -> ((TreeNode<Integer>)node1).data - ((TreeNode<Integer>)node2).data);

        if(index < 0) {
            index = Math.abs(index + 1);
        } else {
            index = index + 1;
        }

        list.add(buildSubTree(inOrder, 0 , index-1));
        list.add(buildSubTree(inOrder, index, inOrder.size()-1));

        return list;
    }

    private static TreeNode<Integer> buildSubTree(List<TreeNode<Integer>> inOrder, int start, int end) {
        if (start > end)
            return null;

        int mid = (start + end)/2;
        TreeNode<Integer> root = new TreeNode<Integer>(inOrder.get(mid).data);

        root.left = buildSubTree(inOrder, start, mid-1);
        root.right = buildSubTree(inOrder, mid+1, end);

        return root;
    }

    static void findInOrder(TreeNode<Integer> root, List<TreeNode<Integer>> results) {
        if(root == null) return;

        findInOrder(root.left, results);
        results.add(root);
        findInOrder(root.right, results);
    }

}
