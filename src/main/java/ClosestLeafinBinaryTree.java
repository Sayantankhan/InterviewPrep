import tree.TreeNode;

import java.util.*;

public class ClosestLeafinBinaryTree {
    // closest leaf in BT

    // TC SC : O(N) -> N = number of nodes
    public int findClosestLeaf(TreeNode root, int k) {
        // one solution change the tree to graph and use BFS for the shortest
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
        dfs(graph, root, null);

        Queue<TreeNode> q = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();

        // find the k value node and start from there
        for(TreeNode node : graph.keySet()) {
            if(node != null && node.val == k) {
                q.offer(node);
                visited.add(node);
            }
        }

        while(!q.isEmpty()) {
            TreeNode node = q.poll();
            if(node != null) {
                if(graph.get(node).size() == 1) return node.val;
                for(TreeNode n : graph.get(node)) {
                    if(!visited.contains(n)) {
                        visited.add(n);
                        q.offer(n);
                    }
                }
            }

        }

        throw null;
    }

    // changing tree to graph
    void dfs(Map<TreeNode, List<TreeNode>> graph, TreeNode root, TreeNode parent) {
        if(root != null) {
            graph.computeIfAbsent(root, k -> new ArrayList<TreeNode>()).add(parent);
            graph.computeIfAbsent(parent, k -> new ArrayList<TreeNode>()).add(root);

            dfs(graph, root.left, root);
            dfs(graph, root.right, root);
        }
    }
}
