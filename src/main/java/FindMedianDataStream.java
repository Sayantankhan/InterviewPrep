import java.util.*;

public class FindMedianDataStream {

    List<Integer> list;
    public FindMedianDataStream() {
        list = new ArrayList<>();
    }

    public void addNum(int num) {
        int index = Collections.binarySearch(list, num);
        if(index < 0) {
            index = -index - 1;
        }
        list.add(index, num);
    }

    public double findMedian() {
        int n = list.size();
        //System.out.println(list);
        if(n==1) return list.get(0);
        if(n % 2 == 0) {
            int in = n/2;
            return (list.get(in) + list.get(in-1)) / 2.0;
        } else {
            return list.get(n / 2);
        }
    }
}
