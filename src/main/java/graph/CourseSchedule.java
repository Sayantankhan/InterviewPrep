package graph;

import java.util.*;

//https://leetcode.com/problems/course-schedule/description/
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites.length == 0) return true;

        ArrayList[] graph = new ArrayList[numCourses];

        for(int i = 0; i < prerequisites.length; i++) {
            int from = prerequisites[i][1];
            int to = prerequisites[i][0];

            if(graph[from] == null) graph[from] = new ArrayList(){{add(to);}};
            else graph[from].add(to);

            if(graph[to] == null) graph[to] = new ArrayList();
        }

        // recstack cycle detection
//        boolean[] visited = new boolean[graph.length];
//        boolean[] recstack = new boolean[graph.length];
//
//        System.out.println(recstack.length);
//
//        for(int i = 0; i < graph.length; i++) {
//            if(detectCycle(graph, visited, recstack, i)) {
//                return false;
//            }
//        }

        // topological sort
        int[] indegree = new int[numCourses];

        for(int i = 0; i < prerequisites.length; i++) {
            indegree[prerequisites[i][0]]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for(int i=0; i<indegree.length; i++){
            if(indegree[i]==0) q.add(i);
        }

        int visited = 0;
        while (!q.isEmpty()) {
            int t = q.poll();
            visited++;

            ArrayList children = graph[t];
            if(children != null) {
                for(Object child : children) {
                    indegree[(int)child]--;

                    if (indegree[(int)child] == 0) {
                        q.add((int)child);
                    }
                }
            }
        }

        return !(visited != numCourses);
    }

    boolean detectCycle(ArrayList[] graph, boolean[] visited, boolean[] recStack, int index) {
        if(recStack[index]) return true;
        if(visited[index]) return false;

        visited[index] = true;
        recStack[index] = true;

        ArrayList children = graph[index];
        if(children != null) {
            for(Object child : children) {
                if(detectCycle(graph, visited, recStack, (int)child)){
                    return true;
                }
            }
        }


        recStack[index] = false;
        return false;
    }

    public static void main(String[] args) {
//        System.out.println(
//                new CourseSchedule().canFinish(20,
//                        new int[][]{{0,10},{3,18},{5,5},{6,11},{11,14},{13,1},{15,1},{17,4}}));

        System.out.println(
                new CourseSchedule().canFinish(2,
                        new int[][]{{1,0}}));
    }
}
