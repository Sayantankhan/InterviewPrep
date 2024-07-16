package string.dp;

import java.util.function.Predicate;

public class PallindromeSubString {
    static Predicate<String> isPallindrome = (s) -> !s.isEmpty() && s.equals(new StringBuilder(s).reverse().toString());

    // we should expand from the middle and check
    // as this is only looking for substring - check this way will work
    // start from middle and expand;
    public static int palindromicSubstrings(String str) {
        int count = 0;
        int len = str.length();

        for(int i = 0; i < len; i++) {
            //odd
            int start = i, end = i;

            while(start >= 0 && end < len && str.charAt(start) == str.charAt(end)) {
                count++;
                start--;
                end++;
            }

            //even
            start = i; end = i+1;
            while(start >= 0 && end < len && str.charAt(start) == str.charAt(end)) {
                count++;
                start--;
                end++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(new PallindromeSubString().palindromicSubstrings("xasmlasxm"));
    }
}
