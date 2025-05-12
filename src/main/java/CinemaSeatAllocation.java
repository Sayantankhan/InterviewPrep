import java.util.*;

public class CinemaSeatAllocation {
    // Movie Theater seat allocation - 4 group and 10 seats per row xxx | xxxx | xxx
    //Return the maximum number of four-person groups you can assign on the cinema seats
    // four-person group occupies four adjacent seats in one single row.
    // allowed allocaiton : xx - xx or xxxx
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int i = 0; i < reservedSeats.length; i++) {
            map.computeIfAbsent(reservedSeats[i][0], k -> new HashSet<>()).add(reservedSeats[i][1]);
        }

        int count = (n - map.size()) * 2; // if there is no alllocation max number of 4 grouping is 2 : 2-5 and 6-9
        for(int k: map.keySet()) {
            Set<Integer> set = map.get(k);
            // allocaiton flag
            boolean left = true, right = true, middle = true;

            if(set.contains(4) || set.contains(5)) left = middle = false;
            if(set.contains(6) || set.contains(7)) middle = right = false;

            if(set.contains(2) || set.contains(3)) left = false;
            if(set.contains(8) || set.contains(9)) right = false;

            if(left && right) count += 2;
            else if(left || right || middle) count += 1;

        }

        return count;
    }
}
