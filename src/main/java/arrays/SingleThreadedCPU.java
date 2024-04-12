package arrays;

import util.Utility;

import java.util.*;

// https://leetcode.com/problems/single-threaded-cpu/description/
public class SingleThreadedCPU {

    /*
     * If the CPU is idle and there are no available tasks to process, the CPU remains idle.
     * If the CPU is idle and there are available tasks, the CPU will choose the one with the shortest processing time.
     * If multiple tasks have the same shortest processing time, it will choose the task with the smallest index.
     * Once a task is started, the CPU will process the entire task without stopping.
     * The CPU can finish a task then start a new one instantly.
     */
    public static int[] getOrder(int[][] tasks) {
        PriorityQueue<Task> taskPool = new PriorityQueue<>();
        List<Task> tempList = new ArrayList<>();
        List<Integer> taskLists = new ArrayList<>();

        for(int i = 0; i < tasks.length; i++) {
            Task task = new Task(i, tasks[i][0], tasks[i][1]);
            tempList.add(task);
        }

        // sorting by enqueue time & processing time
        Collections.sort(tempList, new Comparator<Task>() {
            // sorting : preference on enqueue_time and if enqueue_time is equal then pref is processing time
            @Override
            public int compare(Task o1, Task o2) {
                 if(o1.enqueueTime > o2.enqueueTime)  return 1;
                 else if (o1.enqueueTime == o2.enqueueTime) {
                     if(o1.processingTime > o2.processingTime) return 1;
                     else return -1;
                 } else {
                     return -1;
                 }
            }
        });

        int timeUnit = 0; // keeping a time unit for measuring time
        for(int i = 0; i < tasks.length; i++) {
            Task task = tempList.get(i);

            if(taskPool.isEmpty()) {
                // for the first task with t0 ,
                // cpu does not have much option, it will pick the first
                taskLists.add(task.index);
                timeUnit = Math.max(timeUnit, task.enqueueTime) + task.processingTime; // setting up the time unit, if we pick up any task
            }
            else {
                int t = task.enqueueTime;
                // if task is available to the queue
                while(!taskPool.isEmpty()) {
                    if(timeUnit < t) { // checking is there any time to pick task from task queue before new task enter the task pool
                        Task temp = taskPool.poll();
                        taskLists.add(temp.index);
                        timeUnit = timeUnit + temp.processingTime; // setting up the time unit, if we pick up any task
                    } else {
                        break;
                    }
                }
                taskPool.offer(task); // adding new task to task pool
            }

            // adding all the task which is in the limit of timeunit
            while(i+1 < tasks.length && tempList.get(i+1).enqueueTime <= timeUnit) {
                taskPool.offer(tempList.get(i+1));
                i++;
            }

        }

        // if any task is still left
        while(!taskPool.isEmpty()) {
            taskLists.add(taskPool.poll().index);
        }

        return taskLists.stream().mapToInt(i -> i).toArray();
    }

    static class Task implements Comparable {
        int index;
        int enqueueTime;
        int processingTime;

        Task(int index, int enqueueTime, int processingTime) {
            this.index = index;
            this.enqueueTime = enqueueTime;
            this.processingTime = processingTime;
        }

        @Override
        public int compareTo(Object o) {
            Task t = ((Task)o);
            // If the CPU is idle and there are available tasks, the CPU will choose the one with the shortest processing time.
            if (this.processingTime > t.processingTime) return 1;
            // If multiple tasks have the same shortest processing time
            else if(this.processingTime == t.processingTime) {
                // it will choose the task with the smallest index.
                return (this.index > t.index) ? 1 : -1;
            }
            else return -1;
        }
    }

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(getOrder(new int[][]{{1,2}, {2,4}, {3,2}, {4,1}}), new int[]{0, 2, 3, 1});
        Utility.assertTrue(getOrder(new int[][]{{7,10}, {7,12}, {7,5}, {7,4}, {7,2}}), new int[]{4, 3, 2, 0, 1});

        Utility.assertTrue(getOrder(new int[][]{{19,13},{16,9},{21,10},{32,25},{37,4},{49,24},{2,15},{38,41},{37,34},{33,6},{45,4},{18,18},{46,39},{12,24}}),
                new int[]{6,1,2,9,4,10,0,11,5,13,3,8,12,7});
    }
}
