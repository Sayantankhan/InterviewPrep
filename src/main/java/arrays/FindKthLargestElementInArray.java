package arrays;

import util.Utility;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.com/problems/find-the-kth-largest-integer-in-the-array/description/
public class FindKthLargestElementInArray {

    // TimeComplexity -> sort : O(nlogn) || pq : O(n logk) == O(nlogn)
    public static String kthLargestNumber(String[] nums, int k) {
        Arrays.sort(nums, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                BigInteger i1 = new BigInteger(o1);
                BigInteger i2 = new BigInteger(o2);
                return i1.compareTo(i2);
            }
        });

        return nums[nums.length - k];

//        final PriorityQueue<BigInteger> pq = new PriorityQueue<>();
//        for(String num : nums) {
//            if(pq.size() == k) {
//                pq.poll();
//            }
//            pq.add(new BigInteger(num));
//        }
//
//        return pq.poll().toString();
    }

    public static String kthLargestNumber2(String[] nums, int k) {
        Arrays.sort(nums, (a,b) -> {
            if(a.length() > b.length()) return 1;
            else if(a.length() < b.length()) return -1;
            else {
                return a.compareTo(b);
            }
        });

        return nums[nums.length - k];
    }

    public static void main(String[] args) throws Exception {
        Utility.assertTrue(kthLargestNumber2(new String[]{"3","6","7","10"}, 4), "3");
        Utility.assertTrue(kthLargestNumber2(new String[]{"2","21","12","1"}, 3), "2");
        Utility.assertTrue(kthLargestNumber2(new String[]{"0","1"}, 2), "0");
        Utility.assertTrue(kthLargestNumber2(new String[]{"233","97"}, 1), "233");
        Utility.assertTrue(kthLargestNumber2(new String[]{"62","52054932","7685161227","404637","64990","63017","36841","69332","37956878","4918","95191088"},
                4), "37956878");
    }
}
