import java.util.*;

public class MinimumEdgeReversalEveryNodeReachable {
 // Minimum Edge Reversals So Every Node Is Reachable
    // Edge Reversals
    class Node {
        int node;
        int cost;

        Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    int ersn = 0;
    public int[] minEdgeReversals(int n, int[][] edges) {
        // As the ques is asking for  minimum number of edge reversals required from a node, so that it can
        // reach every node

        // which means if there is an edge [0. 2] 0->2
        // can we say 0->2 cost = 0 ; but 2->0 cost going to be 1
        // now if a create 2d matrix for each connection  from any point i can calculate total cost to reach all the node
        Map<Integer, List<Node>> graph = new HashMap<>();

        for(int[] edge: edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(new Node(edge[1], 0));
            graph.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(new Node(edge[0], 1));
        }

        int[] depth = new int[n];
        boolean[] visited = new boolean[n];
        int[] edgeReverse = new int[n];
        int[] ans = new int[n];

        int startnode = 0;
        dfs(startnode, -1, graph, visited, edgeReverse, 0, depth);
        ans[startnode] = ersn;

        System.out.println("depth: " + Arrays.toString(depth));
        System.out.println("visited: " + Arrays.toString(visited));
        System.out.println("edgeReverse: " + Arrays.toString(edgeReverse));
        System.out.println("ans: " + Arrays.toString(ans));

        for(int i=0; i < n; i++) {
            if(i == startnode) continue;
            int val = depth[i] - edgeReverse[i]; // cost to reverse from this node to this starting node
            int val2 = ersn - edgeReverse[i]; //cost to reverse from starting node to all other nodes -cost to reverse until this node

            ans[i] = val + val2;
        }
        return ans;
    }

    void dfs(int node, int parent,  Map<Integer, List<Node>> graph, boolean[] visited, int[] edgeReverse, int d, int[] depth) {
        visited[node] = true;
        depth[node] = d;

        for(Node n : graph.get(node)) {
            if(visited[n.node] == false) {
                edgeReverse[n.node] = edgeReverse[node] + n.cost;
                ersn += n.cost;
                dfs(n.node, node, graph, visited, edgeReverse, d+1 , depth);
            }
        }
    }
}
