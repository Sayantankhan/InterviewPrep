package util;

import org.apache.commons.io.FileUtils;
import tree.TreeNode;

import java.io.*;
import java.nio.charset.StandardCharsets;
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

    public static String readFile(String filePath) throws Exception {
        final File file = FileUtils.getFile("./src/main/resources/leetcode2207_input.txt");
        if (!file.exists()) throw new FileNotFoundException("File Not Found");
        final InputStream inputStream =
                new DataInputStream(new FileInputStream(file));
        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
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
