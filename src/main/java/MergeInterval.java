import java.util.*;

public class MergeInterval {

    class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        Stack<Point> stack = new Stack<>();
        stack.add(new Point(intervals[0][0], intervals[0][1]));
        for(int i = 1; i < intervals.length; i++) {
            Point p = stack.peek();
            if(!stack.isEmpty() && intervals[i][0] >= p.x && intervals[i][0] <= p.y) {
                stack.pop();
                stack.add(new Point(p.x, Math.max(p.y, intervals[i][1])));
            } else {
                stack.add(new Point(intervals[i][0], intervals[i][1]));
            }
        }

        int size = stack.size();
        int[][] ans = new int[size][2];
        while(!stack.isEmpty()) {
            Point p = stack.pop();
            ans[size-1] = new int[]{p.x, p.y};
            size--;
        }

        return ans;
    }

    // use stack better in the case
    public int[][] mergeTreeMap(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for(int i = 0; i < intervals.length; i++) {

            int left = intervals[i][0];
            int right = intervals[i][1];

            int value = map.getOrDefault(left, -1);
            Map.Entry<Integer, Integer> entry;
            if (value != -1) {
                entry = Map.entry(left, value);
            } else {
                entry = map.higherEntry(left);
            }


            while (entry != null && (entry.getKey() >= left && entry.getValue() <= left )) {
                left = Math.min(intervals[i][0], entry.getValue());
                right = Math.max(intervals[i][1], entry.getKey());

                map.remove(entry.getKey());
                entry = map.higherEntry(left);
            }

            map.put(right, left);
        }

        int size = map.size();
        int count = 0;
        int[][] ans = new int[size][2];
        for(Map.Entry<Integer, Integer> e : map.entrySet()) {
            ans[count++] = new int[]{e.getValue(), e.getKey()};
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new MergeInterval().mergeTreeMap(new int[][]{{1,4}, {2, 3}})));
    }
}
