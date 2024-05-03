package string;

import java.util.Arrays;

// https://www.geeksforgeeks.org/kth-non-repeating-character/
public class KthNonRepeatingCharacter {

    // Given a string str of length n (1 <= n <= 106) and a number k, the task is to find the kth non-repeating character in the string.
    public static void main(String[] args) {
        System.out.println(kNonRepeatingCharacter("geeksforgeeks", 2));
    }

    private static char kNonRepeatingCharacter(String txt, int k) {
        char ans = 0;
        char[] c = txt.toCharArray();
        Arrays.sort(c);
        int count = 0;
        for(int i = 0 ; i < txt.length(); i++) {
            while(i+1 < txt.length()-1 && c[i] == c[i+1]) {
                i++;
            }

            if(i-1>=0 && i+1 < txt.length() && c[i-1] == c[i] && c[i] != c[i+1]) {
                continue;
            }

            count++;
            if(count == k) {
                return c[i];
            }
        }
        return ans;
    }
}
