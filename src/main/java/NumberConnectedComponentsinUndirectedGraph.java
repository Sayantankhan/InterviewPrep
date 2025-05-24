import java.util.*;

public class NumberConnectedComponentsinUndirectedGraph {
    // Number of Connected Components in an Undirected Graph

    // O(V+E)
    public int countComponents(int n, int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int[] edge : edges) {
            map.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            map.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }
        boolean[] visited = new boolean[n];

        int count = 0;
        Queue<Integer> q = new LinkedList<>();

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                count++;
                q.offer(i);
                visited[i] = true;
            }

            while(!q.isEmpty()) {
                Integer el = q.poll();
                List<Integer> lists = map.get(el);
                if(lists != null && lists.size() > 0) {
                    for(Integer node : lists) {
                        if(!visited[node]) {
                            visited[node] = true;
                            q.offer(node);
                        }
                    }
                }

            }
        }

        return count;

    }
}
