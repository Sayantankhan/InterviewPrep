package tree;

import java.util.ArrayList;
import java.util.*;
import java.util.function.Predicate;

//https://leetcode.com/problems/unique-binary-search-trees/
//https://leetcode.com/problems/unique-binary-search-trees-ii/
public class UniqueBinarySearchTree {

    public static int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        // f(3) = f(0) * f(2) [root -right -right] + f(1) * f(1) [left - root - right] + f(2) * f(0) [left - left - root]
        for(int i = 2; i <= n; i++) {
            int total = 0;
            for(int j = 0; j < i; j++) {
                total += dp[j] * dp[i-j-1];
            }
            dp[i] = total;
        }

        return dp[n];
    }

    public static int numTreesRec(int n) {
        if(n == 0) return 1;
        if(n == 1) return 1;
        if(n == 2) return 2;

        // f(5) -> f(0) * f(4) = 14
        //        + f(2) * f(2) = 4
        //        + f(1) * f(3) = 5
        //        + f(4) * f(0) = 14
        //        + f(3) * f(1) = 5

        int total = 0;
        for(int i = 0; i < n; i++) {
            total += numTreesRec(i) * numTreesRec(n-i-1);
        }

        return total;
    }

    Predicate<boolean[]> allNodeVisited = (visited) -> {
        boolean allVisited = true;
        for(boolean v : visited) allVisited = allVisited && v;
        return allVisited;
    };


    class CustomHashSet<E> extends HashSet<E> {

        @Override
        public boolean add(E e) {
            String s = inorder((TreeNode)e);
            for (E existing : this) {
                String temp = inorder((TreeNode) existing);
                if (s.equals(temp)) {
                    return true; // Object already exists in the set
                }
            }
            return super.add(e);
        }

        private String inorder(TreeNode n) {
            if(n == null) return "";
            return inorder(n.left) + n.val + inorder(n.left);
        }
    }

    public List<TreeNode> generateTrees(int n) {
        boolean[] visited = new boolean[n];
        Set<TreeNode> list = new CustomHashSet<>();

        for(int i = 1; i <= n; i++) {
            visited[i-1] = true;
            generateTreesRec(n, list, visited, new TreeNode(i));
            visited[i-1] = false;
        }
        return new ArrayList<>(list);
    }

    void generateTreesRec(int n, Set<TreeNode> list, boolean[] visited, TreeNode root) {

        if(allNodeVisited.test(visited)) {
            list.add(new TreeNode(root.val, root.left, root.right));
            return;
        }

        for(int i = 1; i <= n; i++) {
            if(!visited[i-1]) {
                addToTree(root, i);
                visited[i-1] = true;
                generateTreesRec(n, list, visited, root);
                visited[i-1] = false;
                root.left = null;
                root.right = null;
            }
        }
    }

    TreeNode addToTree(TreeNode root, int i) {
        if(root == null) {
            return new TreeNode(i);
        }

        if(root.val > i) {
            root.left = addToTree(root.left, i);
        }
        else if(root.val < i) {
            root.right = addToTree(root.right, i);
        }

        return root;
    }

    public static void main(String[] args) {
        System.out.println(new UniqueBinarySearchTree().generateTrees(3));
    }
}
