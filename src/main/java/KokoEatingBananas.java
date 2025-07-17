import java.util.Arrays;

public class KokoEatingBananas {

    // Koko Eating Bananas
    // Return the minimum integer k such that she can eat all the bananas within h hours.
    // Koko Eating Bananas
    public int minEatingSpeed(int[] piles, int h) {

        int start = 1, end = 1;
        for (int pile : piles) {
            end = Math.max(end, pile);
        }

        while (start < end) {
            int mid = (start + end)/2;

            if(!isAllocationPossible(piles, mid, h)) {
                start = mid+1;
            } else {
                end = mid;
            }
        }

        return start;
    }

    boolean isAllocationPossible(int[] piles, int mid, int h) {
        int count = 0;
        for (int i = 0; i < piles.length; i++) {
            count += Math.ceil((double) piles[i] / mid);
        }

        return count <= h;
    }

    public static void main(String[] args) {
        System.out.println(new KokoEatingBananas().minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
        int i = 312884470;
        System.out.println(Math.ceil(5.44));
        System.out.println(Math.floor(5.44));
    }
}
