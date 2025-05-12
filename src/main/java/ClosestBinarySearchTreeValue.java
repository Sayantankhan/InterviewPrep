import tree.TreeNode;

import java.util.*;

public class ClosestBinarySearchTreeValue {
    // Closest Binary Search Tree Value II

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        // in order traversal
        List<Integer> arr = new ArrayList<>();

        inOrderTraversal(root, arr);
        // binary search
        int left = 0;
        int right = arr.size() - k;

        while (left < right) {
            int mid = (left + right) / 2;
            if (Math.abs(target - arr.get(mid + k)) < Math.abs(target - arr.get(mid))) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return arr.subList(left, left+k);
    }

    void inOrderTraversal(TreeNode node, List<Integer> arr) {
        if(node == null) return;

        inOrderTraversal(node.left, arr);
        arr.add(node.val);
        inOrderTraversal(node.right, arr);
    }
}
