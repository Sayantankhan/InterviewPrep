package arrays;

import java.util.Arrays;

public class BeautyGarden {

    public int maximumBeauty(int[] flowers, int newFlowers, int target, int full, int part) {
        int len = flowers.length;
        Arrays.sort(flowers);

        // Edge case 1: what happens min of flowers it self >= target || that means every garden is full
        if(flowers[0] >= target)  return full * len;
        // Edge case 2: what happens if there are a lot of new flowers which can make all the garden full
        int sum = Arrays.stream(flowers).sum();
        if(newFlowers >= target * len - sum) {
            // this means i've a choice to
            //  - full all garden
            //  - full all garden except one + make one garden partial with target-1 [the max number that a garden can hold before full]
            return Math.max(full * len, full * (len-1) + part * (target-1));
        }

        int[] cost = new int[len];
        for(int i = 1; i < len; i++) {
            cost[i] = cost[i-1] + ((flowers[i] - flowers[i-1]) * i);
        }

        // find all the garden which are already full
        int j = len-1;
        while (flowers[j] >= target) {
            j--;
        }

        int ans = 0;
        while(newFlowers >= 0) {
            // Find the maximum value that can be achieved for patial gardens
            int index = Arrays.binarySearch(cost, newFlowers);
            if (index < 0) index = Math.abs(index)-1;
            int idx = Math.min(j, index-1);

            // current minimum flower in the partial garden
            int val = flowers[idx] + ((newFlowers - cost[idx]) / (idx+1));

            ans = Math.max(ans, val * part + full * (len - j - 1));

            newFlowers -= (target - flowers[j]);
            j -= 1;
        }

        return ans;
    }

    public static void main(String[] args) {
        BeautyGarden beautyGarden = new BeautyGarden();
        System.out.println(beautyGarden.maximumBeauty(
                new int[] {1, 3, 1, 1},
                7,
                6,
                12,
                1
        ));


        System.out.println(beautyGarden.maximumBeauty(
                new int[] {20,1,15,17,10,2,4,16,15,11},
                2,
                20,
                10,
                2
        ));

    }
}
