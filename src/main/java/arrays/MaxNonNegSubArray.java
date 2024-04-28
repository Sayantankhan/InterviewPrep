package arrays;

import java.util.*;

public class MaxNonNegSubArray {

    public static ArrayList<Integer> maxset(ArrayList<Integer> A) {
        Map<Long, String> map = new TreeMap(Collections.reverseOrder());
        int index = 0;
        int end = -1;
        long sum = 0;

        for(int i = 0; i < A.size(); i++) {
            if(A.get(i) >= 0) {
                end = i;
                sum += (long) A.get(i);
            } else {

                if(end != -1) {
                    putValueIntoMap(map, sum, end, index);
                }
                index = i+1;
                sum = 0;
            }
        }

        putValueIntoMap(map, sum, end, index);

        String val = (String) ((Map.Entry)map.entrySet().iterator().next()).getValue();

        String[] el = val.split("_");
        int l = Integer.valueOf(el[0]);
        int r = Integer.valueOf(el[1]);

        ArrayList<Integer> list = new ArrayList<>();
        for(int i = l; i <= r && r < A.size(); i++) {
            list.add(A.get(i));
        }
        return list;
    }

    static void putValueIntoMap(Map<Long, String> map, long sum, int end, int  index) {
        if(map.get(sum) != null) {
            String s = map.get(sum);

            int ll = Integer.valueOf(s.split("_")[0]);
            int rr = Integer.valueOf(s.split("_")[1]);

            if(rr - ll < end - index) {
                map.put(sum, index+"_"+end);
            } else if (rr - ll == end - index) {
                if(ll > index) {
                    map.put(sum, index+"_"+end);
                }
            }

        } else {
            map.put(sum, index+"_"+end);
        }

    }

    public static void main(String[] args) {


        long a = 1967513926;
        long b = 1540383426;

        System.out.println(a+b);

        ArrayList<Integer> list = new ArrayList();

        list.add(1);
        list.add(2);
        list.add(-5);
        list.add(3);
        list.add(4);
        list.add(-5);
        list.add(7);


        System.out.println(maxset(list));
    }
}
