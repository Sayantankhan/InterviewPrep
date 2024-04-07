package util;

import tree.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Utility {

    public static void assertTrue(Object actual, Object expected) throws Exception {
        if(!actual.equals(expected)) throw new Exception("Actual: "+actual+" Expected: "+expected);
    }

    public static void assertTrue(int[] actual, int[] expected) throws Exception {
        if(!Arrays.equals(actual, expected)) throw new Exception("Actual: "+actual+" Expected: "+expected);
    }

    public static TreeNode generateBinartTreeFromArray(Integer[] integers) {
        Queue<TreeNode> queue = new LinkedList<>();

        int height = 0;
        TreeNode root = new TreeNode(integers[0]);
        queue.add(root);

        int index = 1;
        while(!queue.isEmpty()) {
            if(index >= integers.length) {
                break;
            }

            TreeNode node = queue.poll();

            Integer left = integers[index];
            if(left != null) {
                node.left = new TreeNode(left);
                queue.add(node.left);
            }


            Integer right = integers[index+1];
            if(right != null) {
                node.right = new TreeNode(right);
                queue.add(node.right);
            }

            height++;
            index += 2;
        }

        return root;
    }

}
