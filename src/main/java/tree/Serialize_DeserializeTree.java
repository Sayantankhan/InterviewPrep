package tree;

import java.util.*;

public class Serialize_DeserializeTree {

    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;
        TreeNode() {
            this.data = 0;
            this.left = null;
            this.right = null;
        }
        TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
        TreeNode(int data, TreeNode left, TreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    };

    // Basic binary tree needs Pre-Order to construct tree again
    public static String serializeTree(TreeNode root) {
        Queue<Integer> queue = new LinkedList<>();
        preOrderSerializeHelper(root, queue);

        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()) {
                sb.append(queue.poll());
                sb.append(" ");
            }

            //System.out.println(sb.toString());
            return sb.toString();
        }

    public static void preOrderSerializeHelper(TreeNode root, Queue<Integer> queue) {
        if(root == null) {
            queue.add(-1);
            return;
        }
        queue.add(root.data);
        preOrderSerializeHelper(root.left, queue);
        preOrderSerializeHelper(root.right, queue);
    }

    public static TreeNode deserializeTree(String serialized) {
        Integer[] result = Arrays.stream(serialized.split(" ")).map(Integer::valueOf)
                .toArray(Integer[]::new);
        Queue<Integer> queue = new LinkedList<Integer>(Arrays.asList(result));
        return deserializeTreeHelper(queue);
    }

    public static TreeNode deserializeTreeHelper(Queue<Integer> queue) {
        int value = queue.poll();
        if(value == -1) {
            return null;
        }

        TreeNode node = new TreeNode(value);
        node.left = deserializeTreeHelper(queue);
        node.right = deserializeTreeHelper(queue);

        return node;
    }
}
