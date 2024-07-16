package string;

import java.util.*;
import java.util.stream.Collectors;

public class TaskScheduler {

    static class Tuple {
        int freq;
        int time;

        Tuple(int freq, int time) {
            this.freq = freq;
            this.time = time;
        }
    }

    // max heap to store the freq and
    // queue to store when the element should be available
    // we dont need to store the character only freq is enough

    // Ex 1: AAABBB ; cooldown = 2
    // for A >> A__A__A
    // for B >> AB_AB_AB
    // total : 8
    public static int taskScheduler(String s, int n, int k) {
        Map<Character, Integer> map = new HashMap();

        for(int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0)+1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b-a); // increasing order || more freq element
                                                                        // to take first
        map.entrySet().stream().forEach(entry -> pq.offer(entry.getValue()));

        int time = 0;
        Queue<Tuple> q = new LinkedList();

        while(!pq.isEmpty() || !q.isEmpty()) {
            time++;
            if(pq.size() > 0) {
                int value = pq.poll();
                if(value-1 > 0)  q.add(new Tuple(value-1, time+k));
            }

            if(q.size() > 0 && q.peek().time <= time) {
                pq.offer(q.poll().freq);
            }
        }

        return time;
    }

    public static void main(String[] args) {
        System.out.println(taskScheduler("AAABBB", 6, 2));
    }
}
