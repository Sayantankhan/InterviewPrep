package company.google;

import java.util.*;


// Graph Valid Tree
// all nodes are connected
// no cycle
public class GraphValidTree {
    public static Boolean checkGraph(int[][] edges, int n, int m) {
        // Write your code here
        // idea is if all nodes are connected and edges = vertix + 1 means connected
        // if more/less means cycle or not connected.

        boolean[] visited = new boolean[n];

        ArrayList<Integer> graph[] = new ArrayList[n];

        for (int i = 0; i < edges.length; i++) {
            if (graph[edges[i][0]] == null) graph[edges[i][0]] = new ArrayList<>();
            if (graph[edges[i][1]] == null) graph[edges[i][1]] = new ArrayList<>();
            graph[edges[i][0]].add(edges[i][1]);
            graph[edges[i][1]].add(edges[i][0]);

        }

        Queue<Integer> q = new LinkedList<>();
        int cnt = 1;


        q.add(0);

        while (!q.isEmpty()) {
            Integer node = q.poll();
            visited[node] = true;
            ArrayList<Integer> children = graph[node];

            for (Integer child : children) {
                if (!visited[child]) {
                    cnt++;
                    q.add(child);
                }
            }
        }


        return cnt == n ? true : false;
    }

    public static void main(String[] args) {
        //System.out.println(new GraphValidTree().checkGraph(new int[][] {{0,1}, {0,2}, {0,3}, {1,4}}, 6, 4));

        // System.out.println(new GraphValidTree().checkGraph(new int[][] {{0,1}, {0,4}, {1,2}, {1,3}, {2,3}, {3, 4}}, 5, 6));

        // System.out.println(new GraphValidTree().checkGraph(new int[][] {{0,1}, {1,2}, {2,5}, {1,3}, {1, 4}}, 6, 5));

        System.out.println(new GraphValidTree().checkGraph(new int[][]{{1, 0},
                {2, 3},
                {4, 0},
                {4, 2},
                {4, 5},
                {6, 7},
                {7, 5}}, 8, 7));
    }
}
