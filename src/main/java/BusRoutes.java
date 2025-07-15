import java.util.*;

public class BusRoutes {
    // Bus Routes
    // least no of bus required from source a to dest b
    public int numBusesToDestination(int[][] routes, int source, int target) {

        if(source == target) return 0;
        // store it like <stop, busindex>
        Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
        for(int i = 0; i < routes.length; i++) {
            int len = routes[i].length;
            for(int j = 0; j < len; j++) {
                graph.computeIfAbsent(routes[i][j], k -> new ArrayList<>()).add(i);
            }
        }

        if(graph.getOrDefault(source, null) == null) return -1;

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();

        for(int route : graph.get(source)) {
            q.offer(route);
            visited.add(route);
        }

        int count = 1;

        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++) {
                int r = q.remove();
                for(int stop : routes[r]) {
                    if(stop == target) return count;

                    List<Integer> cstops = graph.get(stop);
                    for(Integer cstop : cstops) {
                        if(!visited.contains(cstop)) {
                            q.offer(cstop);
                            visited.add(cstop);
                        }
                    }
                }
            }
            count++;
        }

        return -1;
    }
}
