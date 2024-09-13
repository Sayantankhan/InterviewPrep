package arrays;
import java.util.*;
public class KSizedMaxSubArray {

    class Tuple {
        int index;
        int value;

        Tuple(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
    public int[] maxSlidingWindow(int[] nums, int k) {

        PriorityQueue<Tuple> pq = new PriorityQueue<>((a,b) -> b.value - a.value);
        List<Integer> list = new ArrayList<Integer>();

        for(int i = 0; i < k; i++) {
            pq.add(new Tuple(i, nums[i]));
        }

        list.add(pq.peek().value);

        for(int i = k; i < nums.length; i++) {
            pq.add(new Tuple(i, nums[i]));

            while(!pq.isEmpty() && i - pq.peek().index + 1 > k) {
                pq.poll();
            }
            list.add(pq.peek().value);
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    static ArrayList<Integer> max_of_subarrays(int arr[], int n, int k) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Deque<Integer> dq = new LinkedList();


        int i = 0, j = 0;
        while(j < n) {
            while(!dq.isEmpty() && dq.peekLast() < arr[j]) {
                dq.pollLast();
            }

            dq.addLast(arr[j]);

            if(j-i+1 < k) j++;
            else if (j-i+1 == k) {
                result.add(dq.peekFirst());
                if(arr[i] == dq.peekFirst()) {
                    dq.pollFirst();
                }
                i++;
                j++;
            }
        }

        return result;

    }


    public static void main(String[] args) {
        System.out.println(max_of_subarrays(new int[]{1,2,3,1,4,5,2,3,6}, 9, 3));

        System.out.println(new KSizedMaxSubArray().maxSlidingWindow(new int[] {1,  -1}, 1));
        // System.out.println(max_of_subarrays(new int[]{8,5,10,7,9,4,15,12,90,13}, 10, 3));
        // 6 -3, -5, 4, 1, 8
    }
}
