package graph;

import java.util.*;
import java.util.function.Predicate;

// https://leetcode.com/problems/network-delay-time/description/
public class NetworkDelayTime {

    class Node {
        int node;
        int weight;

        Node(int node, int weight) { this.node = node; this.weight = weight; }
    }


    public int networkDelayTime(int[][] times, int n, int k) {
        ArrayList<Node> graph[] = new ArrayList[n];

        // Arrays.fill(graph, new ArrayList<Node>());
        for(int i = 0; i < graph.length; i++) graph[i] = new ArrayList<>();

        for(int i = 0; i < times.length; i++) {
            int source = times[i][0] - 1;
            int destination = times[i][1] - 1;
            int weight = times[i][2];

            graph[source].add(new Node(destination, weight));
        }

        Queue<Node> q = new LinkedList<>();
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);

        q.add(new Node(k-1, 0));
        distance[k-1] = 0;

        while(!q.isEmpty()) {
            int l = q.size();
            for(int i = 0; i < l; i++) {
                Node node = q.poll();
                List<Node> children = graph[node.node];

                children.stream().forEach(child -> {
                    if(distance[child.node] > distance[node.node] + child.weight) {
                        distance[child.node] = Math.min(distance[child.node], distance[node.node] + child.weight);
                        q.add(child);
                    }
                });
            }
        }

        Arrays.sort(distance);
        return distance[distance.length-1] == Integer.MAX_VALUE ? -1 : distance[distance.length-1];
    }

    public static void main(String[] args) {
        //System.out.println(new NetworkDelayTime().networkDelayTime(new int[][]{{2,1,1}, {2,3,1}, {3,4,1}}, 4, 2));

        //System.out.println(new NetworkDelayTime().networkDelayTime(new int[][]{{1,2,1}}, 2, 1));

        //System.out.println(new NetworkDelayTime().networkDelayTime(new int[][]{{1,2,1}, {2,1,3}}, 2, 2));

        System.out.println(new NetworkDelayTime().networkDelayTime(new int[][]{{1,2,1}, {2,3,2}, {1,3,4}}, 3, 1));
    }
}
