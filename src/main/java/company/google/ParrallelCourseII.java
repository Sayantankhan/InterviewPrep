package company.google;

import java.util.*;

public class ParrallelCourseII {

    // Topological Sort - ranking
    // Bit Masking - state
    public static int minNumberOfSemesters(int n, int[][] relations, int k) {
        ArrayList<Integer> graph[] = new ArrayList[n+1];
        // [prevCoursei, nextCoursei]
        for(int[] relation : relations) {
            int prev = relation[0];
            int next = relation[1];
            if(graph[prev] == null) graph[prev] = new ArrayList<>();
            if(graph[next] == null) graph[next] = new ArrayList<>();
            graph[prev].add(next);
        }

//        Stack<Integer> stack = new Stack<>();
//        boolean[] visited = new boolean[n+1];
//        for(int i = 1; i <= n; i++) {
//            if(!visited[i]) topologicalSort(graph, stack, visited, i);
//        }

        int[] indegree = new int[n+1];
        for(int i = 1; i <= n; i++) {
            if(graph[i] != null) {
                for(Integer child : graph[i]) {
                    indegree[child] += 1;
                }
            }
        }

        // Lets prepare the mask. Initially set all bits to 1 (Note a bit as 1 means
        // 'not visited' while 0 means visited)

        int mask = 0;
        for (int i = 1; i <= n; i++)
            mask |= 1 << i;

        HashMap<Integer, Integer> map = new HashMap<>();
        return recurse(graph, mask, indegree, map, n, k);
    }

    private static int recurse(ArrayList<Integer>[] graph, int mask, int indegree[], Map<Integer, Integer> map, int n, int k) {
        if (mask == 0)
            return 0;

        if(map.containsKey(mask))
            return map.get(mask);

        List<Integer> nodes = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if ((mask & (1 << i)) != 0 && indegree[i] == 0)
                nodes.add(i);
        }

        int ans = Integer.MAX_VALUE;

        for (List<Integer> kNodes : generateCombinations(nodes, Math.min(k, nodes.size()))) {
            int newMask = mask;
            int newIndegree[] = indegree.clone();

            for (int node : kNodes) {
                newMask ^= (1 << node);
                for (int child : graph[node]) {
                    newIndegree[child]--;
                }
            }

            ans = Math.min(ans, 1 + recurse(graph, newMask, newIndegree, map, n, k));

        }

        map.put(mask, ans);
        return ans;
    }

    private static List<List<Integer>> generateCombinations(List<Integer> nodes, int k) {
        List<List<Integer>> result = new ArrayList<>();
        generateHelper(nodes, k, 0, new ArrayList<>(), result);
        return result;
    }

    private static void generateHelper(List<Integer> nodes, int k, int start, List<Integer> list, List<List<Integer>> result) {
        if (k == 0) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i < nodes.size(); i++) {
            list.add(nodes.get(i));
            generateHelper(nodes, k - 1, i + 1, list, result);
            list.remove(list.size() - 1);
        }
    }

    static void topologicalSort(ArrayList<Integer>[] graph, Stack<Integer> stack, boolean[] visited, int node) {
        visited[node] = true;
        if(graph[node] != null) {
            for(Integer child : graph[node]) {
                if(!visited[child]) {
                    topologicalSort(graph, stack, visited, child);
                }
            }
        }
        stack.push(node);
    }

    int topologicalSort(ArrayList<Integer>[] graph, Stack<Integer> stack, boolean[] visited, int node, int k) {
        if(graph[node] == null) {
            return 1;
        }

        visited[node] = true;

        ArrayList<Integer> children =  graph[node];
        int val = Integer.MIN_VALUE;
        int count = 0;
        for(Integer child : children) {
            if(!visited[child]) {
                count++;
                if(count == k) {
                    val = Math.max(val, 2 + topologicalSort(graph, stack, visited, child, k));
                    count = 0;
                }
                else {
                    val = Math.max(val, 1 + topologicalSort(graph, stack, visited, child, k));
                }
            }
        }

        stack.push(node);
        return val;
    }

    public static void main(String[] args) {
        //System.out.println(new ParrallelCourseII().minNumberOfSemesters(4, new int[][] {{2,1}, {3,1}, {1,4}}, 2));
        System.out.println(new ParrallelCourseII().minNumberOfSemesters(5, new int[][] {{2,1}, {3,1}, {4, 1}, {1, 5}}, 2));
        // System.out.println(new ParrallelCourseII().minNumberOfSemesters(5, new int[][] {{1,5},{1,3},{1,2},{4,2},{4,5},{2,5},{1,4},{4,3},{3,5},{3,2}}, 3));


    }
}
