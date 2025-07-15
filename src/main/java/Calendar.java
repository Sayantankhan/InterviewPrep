import java.util.*;

public class Calendar {
    // Book the slots in calendar

    final TreeMap<Integer,Integer> timing;

    public Calendar() {
        timing = new TreeMap<>();
    }

    public boolean book(int startTime, int endTime) {
        Map.Entry<Integer, Integer> next_entry = timing.higherEntry(startTime);

        if(next_entry != null && checkOverlap(startTime, endTime, next_entry.getValue(), next_entry.getKey())) return false;

        timing.put(endTime, startTime);
        return true;
    }


    boolean checkOverlap(int startTime1, int endTime1, int startTime2, int endTime2) {

        if((startTime1 < startTime2 && endTime1 <= startTime2)
                ||  startTime1 >= endTime2) return false;

        return true;
    }

    public static void main(String[] args) {

        Calendar c1 = new Calendar();
        System.out.println(c1.book(47,50));
        System.out.println(c1.book(33,41));
        System.out.println(c1.book(39,45));
        System.out.println(c1.book(33,42));
        System.out.println(c1.book(25,32));
        System.out.println(c1.book(26,35));
        System.out.println(c1.book(19,25));
        System.out.println(c1.book(3,8));
        System.out.println(c1.book(8,13));
        System.out.println(c1.book(18,27));
    }
}
