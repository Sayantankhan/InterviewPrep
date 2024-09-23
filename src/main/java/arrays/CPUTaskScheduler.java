package arrays;

import java.util.Arrays;
import java.util.PriorityQueue;

// CPU task scheduler
public class CPUTaskScheduler {

    public static int cpuTaskScheduler(int n, int[][] arr)
    {
        // sort by start time
        Arrays.sort(arr, (a, b) -> {
            if(a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });

        // Sort by end time
        PriorityQueue<int[]> tasks = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        int cpu_count = 1;
        for(int i = 0; i < arr.length; i++) {
            if(tasks.isEmpty()) {
                // if there is no task running pick the first one
                tasks.offer(arr[i]);
            } else {
                int[] el = tasks.peek();
                if(el[1] <= arr[i][0]) {
                    // if we have tasks that are greater than peek task - start removing them
                    cpu_count = Math.max(cpu_count, tasks.size());
                    while(!tasks.isEmpty() && el[1] <= arr[i][0]){
                        tasks.poll();
                        el = tasks.peek();
                    }
                }
                tasks.offer(arr[i]);
            }
        }

        cpu_count = Math.max(cpu_count, tasks.size());
        return cpu_count;
    }

    public static void main(String[] args) {
        System.out.println(CPUTaskScheduler.cpuTaskScheduler(23, new int[][]{{13,74},
                {30,50},
                {25,93},
                {82,95},
                {70,71},
                {62,64},
                {67,88},
                {97,99},
                {3,24},
                {14,58},
                {42,55},
                {11,30},
                {60,66},
                {3,10},
                {4,15},
                {1,3},
                {24,36},
                {86,91},
                {57,85},
                {66,76},
                {61,83},
                {73,85},
                {73,81}}));
    }
}
