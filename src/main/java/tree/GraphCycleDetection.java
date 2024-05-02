package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphCycleDetection {

    public static void main(String[] args) {
        ArrayList[] a = new ArrayList[6];

        a[0] = new ArrayList() {{ add(1); add(2);}};
        a[1] = new ArrayList() {{ add(2);}};
        a[2] = new ArrayList() {{ add(3);}};
        a[3] = new ArrayList() {{ add(4);}};
        a[4] = new ArrayList() {{ add(3); add(5);}};
        a[5] = new ArrayList();

        System.out.println(detectCycleTopologicalSort(a));
    }

    private static boolean detectCycleDFS(ArrayList[] a) {
        boolean[] visited = new boolean[a.length];
        boolean[] recStack = new boolean[a.length];

        for(int i = 0; i < a.length; i++) {
            if(detectCycleRec(a, i, visited, recStack)) {
                return true;
            }
        }

        return false;
    }

    private static boolean detectCycleRec(ArrayList[] a, int i, boolean[] visited, boolean[] recStack) {
        if(recStack[i]) return true;
        if(visited[i]) return false;

        List<Integer> children = a[i];
        visited[i] = true;
        recStack[i] = true;

        if(children != null) {
            for(Integer child : children) {
                if(detectCycleRec(a, child, visited, recStack)) {
                    return true;
                }
            }
        }
        recStack[i] = false;
        return false;
    }

    private static boolean detectCycleTopologicalSort(ArrayList[] a) {

        int[] indegree = new int[a.length];

        for(List children : a) {
            for(Object child : children) {
                int index = (int) child;
                indegree[index]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        // add where indegree is 0
        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0) {
                q.add(i);
            }
        }
        int cnt = 0;

        while(!q.isEmpty()) {
            Integer i = q.poll();
            cnt++;
            for(Object child: a[i]) {
                int index = (int) child;
                indegree[index]--;
                if (indegree[index] == 0) {
                    q.add(index);
                }
            }
        }

        if(cnt == a.length) return false;
        return true;
    }
}
