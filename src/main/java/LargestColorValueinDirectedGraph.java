import java.util.*;

public class LargestColorValueinDirectedGraph {
    // DAG color Largest color
    // Largest Color Value in a Directed Graph
    public int largestPathValue(String colors, int[][] edges) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        int[] indegree = new int[colors.length()];

        for(int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            adj.computeIfAbsent(from, k -> new ArrayList<Integer>()).add(to);
            indegree[to]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < colors.length(); i++) {
            if(indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int[][] count = new int[colors.length()][26]; // this has been added as we are counting the frequency
        int nodeSeen = 0;
        int answer = 1;

        while(!queue.isEmpty()) {
            int node = queue.poll();
            count[node][colors.charAt(node) - 'a'] += 1;
            answer = Math.max(answer, count[node][colors.charAt(node) - 'a']);
            nodeSeen ++;

            if (!adj.containsKey(node)) {
                continue;
            }

            for(int neighbour : adj.get(node)) {
                for(int i = 0; i < 26; i++) {
                    count[neighbour][i] = Math.max(count[neighbour][i], count[node][i]);
                }
                indegree[neighbour]--;
                if(indegree[neighbour] == 0) queue.offer(neighbour);
            }
        }

        return nodeSeen < colors.length() ? -1 : answer;
    }
}
