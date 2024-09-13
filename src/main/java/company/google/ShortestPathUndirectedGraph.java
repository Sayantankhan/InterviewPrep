package company.google;

import java.util.*;
import java.util.function.Predicate;

//https://leetcode.com/problems/shortest-path-visiting-all-nodes/description/
public class ShortestPathUndirectedGraph {

    class Tuple {
        int node;
        String mask;

        Tuple(int node, String mask) {
            this.node = node;
            this.mask = mask;
        }

        public boolean equals(Object obj) {
            return (this == obj);
        }

        public int hashCode(){
            return mask != null ? mask.hashCode() * 37 + node : node;
        }


    }

    // BFS will not an option, as bfs never visit a node twice
    public int shortestPathLength(int[][] graph) {
//        int start = 0;
//        Queue<Tuple> queue = new LinkedList();
//        boolean[] visited = new boolean[graph.length];
//
//        int shortestpath = Integer.MAX_VALUE;
//        String allVisited = Integer.toBinaryString((1 << graph.length) - 1); // 1111
//
//        for(int i = 0; i < graph.length; i++) {
//            queue.add(new Tuple(i, Integer.toBinaryString(1 << i)));
//            Set<Tuple> set = new HashSet();
//            int level = 0;
//            while(!queue.isEmpty()) {
//                Tuple t = queue.poll();
//                if(t.mask.equals(allVisited)) {
//                    shortestpath = Math.min(shortestpath, level);
//                }
//                else {
//                    for(int j = 0; j < graph[t.node].length; j++) {
////                        Tuple t = new Tuple(graph[t.node][j], t.mask | (1 << graph[t.node][j]))
////                        if()
//                    }
//                }
//            }
//        }
//
//
//
//
//        boolean[][] vis = new boolean[graph.length][];
//        for(int i = 0; i < graph.length; i++) {
//            vis[i] = new boolean[graph[i].length];
//        }
//
//        int count = 0;
//
//        while(!queue.isEmpty()) {
//
//            for(int i = 0; i < graph[node].length; i++) {
//                if(!vis[node][i]) {
//                    queue.add(graph[node][i]);
//                    vis[node][i] = true;
//                    break;
//                }
//            }
//
//            if(isAllNodeVisited.test(vis)) {
//                break;
//            }
//        }

        return 0;
    }

    public static void main(String[] args) {
        ShortestPathUndirectedGraph spug = new ShortestPathUndirectedGraph();
        System.out.println(spug.shortestPathLength(new int[][] {{1,2,3}, {0}, {0}, {0}}));
    }
}
