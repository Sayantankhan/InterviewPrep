package list;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.time.StopWatch;

public class MergeTwoSortedLists {
    static StopWatch watch = new StopWatch();
    /*
     * You are given the heads of two sorted linked lists list1 and list2.
     *  Merge the two lists into one sorted list. The list should be made by splicing
     *  together the nodes of the first two lists.
     */
    public static void mergerTwoList(List<Integer> l1, List<Integer> l2) {
        // Option1 : Iterate through both the lists and put them into another list
        // and do a merge sort on that list
        // if size(l1) = m, size(l2) = n; Time Complexity = O(m+n log m+n); Space = O(m+n)
        watch.start();
        mergeTwoListBruteForce(l1, l2);
        watch.stop();
        System.out.println("Time taken for mergeTwoListBruteForce "+ watch.getNanoTime());

        watch.reset();

        // Option2 : As the lists are sorted; we can take element and compare and put in the list
        // if size(l1) = m, size(l2) = n; Time Complexity = O(m+n);
        watch.start();
        mergeTwoListWithoutSortingAlgo(l1, l2);
        watch.stop();
        System.out.println("Time taken for mergeTwoListWithoutSortingAlgo "+ watch.getNanoTime());

        watch.reset();

        // Option3 : As the lists are sorted; we can use a min heap and store the elements from
        // each iteration and heap will give us the min
        // if size(l1) = m, size(l2) = n; no of sorted list -> 2;
        // Time Complexity = O(m+n log2) -> O(m+n)
        watch.start();
        mergeTwoListMinHeap(l1, l2);
        watch.stop();
        System.out.println("Time taken for mergeTwoListMinHeap "+ watch.getNanoTime());
    }

    static class Tuple {
        int index;
        int eindex;
        int val;
        Tuple(int index, int eindex, int val) {
            this.index = index;
            this.eindex = eindex;
            this.val = val;
        }
    }
    private static void mergeTwoListMinHeap(List<Integer> l1, List<Integer> l2) {
        PriorityQueue<Tuple> priorityQueue = new PriorityQueue<>(new Comparator<Tuple>() {
            @Override
            public int compare(Tuple o1, Tuple o2) {
                 if(o1.val > o2.val) return 1 ;
                 else return -1;
            }
        });

        List<Integer> list = new ArrayList<>();

        priorityQueue.add(new Tuple(0, 0, l1.get(0)));
        priorityQueue.add(new Tuple(1, 0, l2.get(0)));

        // O(m*n log2)
        while (!priorityQueue.isEmpty()) { // m*n
            Tuple t = priorityQueue.poll(); // poll O(1) -- Heapify O(log2)
            list.add(t.val);
            Tuple temp = null;
            if(t.index == 0 && t.eindex+1 < l1.size()) priorityQueue.add(new Tuple(0, t.eindex+1, l1.get(t.eindex+1)));
            else if(t.index == 1 && t.eindex+1 < l2.size()) priorityQueue.add(new Tuple(1, t.eindex+1, l2.get(t.eindex+1)));
        }
    }

    private static void mergeTwoListWithoutSortingAlgo(List<Integer> l1, List<Integer> l2) {

        List<Integer> list = new ArrayList<>();
        int i = 0, j = 0;
        while(i < l1.size() && j < l2.size()) {
            if(l1.get(i) < l2.get(j)) {
                list.add(l1.get(i));
                i++;
            } else {
                list.add(l2.get(j));
                j++;
            }
        }

        while(i < l1.size()) {
            list.add(l1.get(i));
            i++;
        }
        while(j < l2.size()) {
            list.add(l1.get(j));
            j++;
        }
    }

    private static void mergeTwoListBruteForce(List<Integer> l1, List<Integer> l2) {
        List<Integer> list = new ArrayList<>();
        Iterator i1 = l1.iterator();
        Iterator i2 = l2.iterator();

        // O(m+n)
        while(i1.hasNext() || i2.hasNext()) {
            if(i1.hasNext()) list.add((Integer) i1.next());
            if(i2.hasNext()) list.add((Integer) i2.next());
        }

        // O(m*n log mn)
        Collections.sort(list);
    }

    public static void main(String[] args) {
        List<Integer> even = IntStream.range(1, 100000).filter(x -> x%2 == 0).boxed().collect(Collectors.toList());
        List<Integer> odd = IntStream.range(1, 100000).filter(x -> x%2 != 0).boxed().collect(Collectors.toList());

        mergerTwoList( odd, even);
    }
}
