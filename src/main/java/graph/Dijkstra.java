package graph;

import java.util.*;
import java.util.stream.Collectors;

public class Dijkstra {

    public static void main(String[] args) {
        // 0 - 1 - 3 - 4
        // |           | - 5
        // 2 - ---------t
        // Tuple -> (source, destination, weight)
        // int[][] routes = new int[][]{{0, 1, 1}, {0, 2, 1}, {1, 3, 1}, {2, 4, 1}, {3, 4, 1}, {4, 5, 1}};
        // int numberOfStation = 6;

        int[][] routes = new int[][]{{0, 1, 4},{0, 7, 8},{1, 2, 8},{1, 7, 11},{2, 3, 7},{2, 8, 2},{2, 5, 4},{3, 4, 9},{3, 5, 14},{4, 5, 10},{5, 6, 2},{6, 7, 1},{6, 8, 6},{7, 8, 7}};
        int numberOfStation = 9;

        ArrayList<Tuple> graph[] = new ArrayList[numberOfStation];
        ArrayList<Integer> path[] = new ArrayList[numberOfStation];

        for(int i = 0; i < numberOfStation; i++) {
            graph[i] = new ArrayList<Tuple>();
            path[i] = new ArrayList<>();
        }

        for(int i = 0; i < routes.length; i++) {
            int[] route = routes[i];
            graph[route[0]].add(new Tuple(route[1], route[2]));
        }

        List op = findMinPathBFS(graph, path, numberOfStation, 0, 5);

        System.out.println((Integer)op.get(op.size()-1));
        System.out.println(path[path.length-1]);
    }

    static class Tuple {
        int station;
        int weight;

        Tuple(int station, int weight) {
            this.station = station;
            this.weight = weight;
        }
    }

    static List<Integer> findMinPathBFS(ArrayList<Tuple>[] graph, ArrayList<Integer>[] path, int n, int source, int destination) {
        // PriorityQueue<Tuple> q = new PriorityQueue<>(Comparator.comparing(node -> node.station));
        Queue<Tuple> q = new LinkedList<>();

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        q.add(new Tuple(source, 0));
        dist[source] = 0;
        path[source].add(0);

        while(!q.isEmpty()) {
            Tuple t = q.poll();
            ArrayList<Tuple> children = graph[t.station];

            for (Tuple child: children) {
                if(dist[child.station] > dist[t.station] +  child.weight) {
                    dist[child.station] = dist[t.station] +  child.weight;

                    // this is logic for path
                    ArrayList p = path[t.station];
                    path[child.station].removeAll(path[child.station]);
                    path[child.station].addAll(p);
                    path[child.station].add(child.station);

                    q.add(new Tuple(child.station, dist[child.station]));
                }
            }
        }

        return Arrays.stream(dist).boxed().collect(Collectors.toList());
    }
}
