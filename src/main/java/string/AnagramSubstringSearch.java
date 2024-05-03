package string;

import java.util.Arrays;

public class AnagramSubstringSearch {

    public static void main(String[] args) {
//        int val ='z';
//        System.out.println(val);
        findAnagramSearchRK("BACDGABCDA", "ABCD");
    }

    private static void findAnagramSearch(String a, String b) {
        int tlen = a.length();
        int plen = b.length();

        char[] bc = b.toCharArray();
        Arrays.sort(bc);
        b = new String(bc);

        String subs = a.substring(0, plen-1);
        for(int i = plen-1; i < tlen; i++) {
            subs += String.valueOf(a.charAt(i));

            if(compare(subs, b)) {
                System.out.println("Index "+ (i - (plen - 1)));
            }

            subs = subs.substring(1, subs.length());
        }

    }

    private static boolean compare(String subs, String b) {

        char[] subc = subs.toCharArray();
        char[] bc = b.toCharArray();

        Arrays.sort(subc);

        for(int i = 0; i < b.length(); i++) {
            if(subc[i] != bc[i]) return false;
        }

        return true;
    }

    //Rabin Karp Algorithm.
    private static void findAnagramSearchRK(String text, String pattern) {
        int MAX = 122;

        int m = text.length();
        int n = pattern.length();

        int[] countM = new int[MAX];
        int[] countN = new int[MAX];

        for(int i = 0; i < n; i++) {
            countM[text.charAt(i)]++;
            countN[pattern.charAt(i)]++;
        }

        for(int i = n; i < m; i++) {
            if(scompare(countM, countN, MAX)) {
                System.out.println("Found at Index " +
                        (i - n));
            }

            countM[text.charAt(i)] ++;
            countM[text.charAt(i - n)] --;
        }

        // Check for the last window in text
        if(scompare(countM, countN, MAX))
            System.out.println("Found at Index " +
                    (m - n));
    }

    private static boolean scompare(int[] countM, int[] countN, int max) {
        for(int i = 0; i < max; i++) {
            if(countM[i] != countN[i]){
                return false;
            }
        }
        return true;
    }
}
