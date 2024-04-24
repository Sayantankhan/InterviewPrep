package arrays;

import java.util.*;
import java.util.stream.IntStream;

// https://leetcode.ca/all/759.html
public class EmployeeFreeTime {
    static class Interval {
        int start;
        int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {

        List<Interval> intervals = new ArrayList<>();
        for(List<Interval> interval : schedule) {
            for(Interval inv: interval) {
                intervals.add(inv);
            }
        }
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if(o1.start > o2.start) return 1;
                else if((o1.start < o2.start)) return -1;
                else {
                    if(o1.end > o2.end) return 1;
                    else return -1;
                }
            }
        });

        Deque<Interval> q = new LinkedList<>();
        for(int i = 0; i < intervals.size(); i++) {
            if(q.isEmpty()) {
                q.add(intervals.get(i));
            } else {
                Interval a = q.peekLast();
                Interval b = intervals.get(i);

                if(b.start >= a.start && b.start <= a.end) {
                    q.pollLast();
                    q.add(new Interval(a.start, Math.max(a.end,b.end)));
                } else {
                    q.add(b);
                }
            }
        }


        Interval prev = null;
        intervals.clear();

        while(!q.isEmpty()) {
            if(prev == null) {
                prev = q.pollFirst();
                if(prev.start != 1) {
                    intervals.add(new Interval(1, prev.start));
                }
            } else {
                Interval temp = q.pollFirst();
                intervals.add(new Interval(prev.end, temp.start));
                prev = temp;
            }
        }
        return intervals;
    }


    public static void main(String[] args) {
        List<List<Interval>> intervals = new ArrayList<List<Interval>>();


        intervals.add(List.of(new Interval(1, 2), new Interval(5, 6)));
        intervals.add(List.of(new Interval(1, 3)));
        intervals.add(List.of(new Interval(4, 10)));
        employeeFreeTime(intervals).stream().forEach(x -> System.out.print(x.start + "," + x.end + " "));

        System.out.println();

        intervals.clear();

        intervals.add(List.of(new Interval(1, 3), new Interval(6,7)));
        intervals.add(List.of(new Interval(2, 4)));
        intervals.add(List.of(new Interval(2, 5), new Interval(9, 12)));
        employeeFreeTime(intervals).stream().forEach(x -> System.out.print(x.start + "," + x.end + " "));


    }
}
