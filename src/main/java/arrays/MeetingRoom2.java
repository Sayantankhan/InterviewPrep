package arrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

public class MeetingRoom2 {

    /* Question
        Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
        For example, Given [[0, 30],[5, 10],[15, 20]], return 2.
     */
    // each call to Heapify costs O(lg(n))
    // Build-Heap makes O(n)  such calls.
    // search = O(1)
    
    //  O(n * lg(n))
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int room = 0;

        for(int i = 0; i < intervals.length; i++) {
            heap.offer(intervals[i][1]);
            if(intervals[i][0] < heap.peek()) { room ++; }
            else {
                heap.poll();
            }
        }

        return room;
    }
}
