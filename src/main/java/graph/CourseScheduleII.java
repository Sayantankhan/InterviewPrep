package graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// https://leetcode.com/problems/course-schedule-ii/description/
// Topological sort
// cycle detection
public class CourseScheduleII {
    static ArrayList<Integer> graph[];
    static int v;
    static int e;

    static void addEdge(int a, int b){
        graph[b].add(a);
    }


    public static int[] findOrder(int num, int[][] pre) {

        v = num;
        e = pre.length;
        graph = new ArrayList[v];

        for(int i=0; i<v; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<pre.length; i++){
            addEdge(pre[i][0],pre[i][1]);
        }

        int indegree[] = new int[v];

        for(int i=0; i<pre.length; i++){
            indegree[pre[i][0]]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for(int i=0; i<v; i++){
            if(indegree[i]==0) q.add(i);
        }

        if(q.size()==0){
            int arr[] = {};
            return arr;
        }
        int ans[] = new int[v];
        int idx = 0;

        while(q.size()!=0){
            int a = q.remove();
            ans[idx++] = a;
            for(Integer x : graph[a]){
                indegree[x]--;
                if(indegree[x]==0) q.add(x);
            }
        }
        if(idx!=v){
            int arr[] = {};
            return arr;
        }else return ans;

    }


    static public int[] findOrderStk(int num, int[][] pre) {
        ArrayList<Integer> graph[] = new ArrayList[num];

        for(int i = 0; i < num; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < pre.length; i++) {
            graph[pre[i][0]].add(pre[i][1]);
        }

        boolean[] visited = new boolean[num];
        Stack<Integer> stack = new Stack<Integer>();

        for(int i = 0; i < num; i++) {
            List<Integer> child = graph[i];
            if(!visited[i]) topologicalSortStack(graph, num, stack, visited, i);
        }

        int[] result = new int[stack.size()];
        int i = stack.size()-1;

        // cycle detection
        Map<Integer, Integer> pos = new HashMap<>();
        int index = 0;

        while(!stack.isEmpty()) {
            result[i--] = stack.peek();
            pos.put(stack.pop(), index);
            index ++;
        }
        boolean isCycle = hasCycle(graph, num, pos);
        return !isCycle ? result : new int[0];
    }

    static boolean hasCycle(ArrayList<Integer>[] graph, int num, Map<Integer, Integer> pos) {
        for(int i = 0; i < num; i++) {

            for(int c : graph[i]) {
                if(pos.get(c) < pos.get(i)) return true;
            }
        }
        return false;
    }

    static void topologicalSortStack(ArrayList<Integer>[] graph, int num, Stack<Integer> stack, boolean[] visited, int node) {
        visited[node] = true;
        for(int c : graph[node]) {
            if(!visited[c]) {
                topologicalSortStack(graph, num, stack, visited, c);
            }
        }
        stack.push(node);
    }

    public static void main(String[] args) {
         System.out.println(findOrderStk(2, new int[][]{{0, 1}, {1, 0}}));
         //System.out.println(findOrderStk(4, new int[][]{{1, 0}, {0, 1}, {2, 3},{3, 4}, {4, 5}}));
//         System.out.println(findOrder(1, new int[][]{}));
    }
}
