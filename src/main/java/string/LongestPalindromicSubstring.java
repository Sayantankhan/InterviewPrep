package string;

// longest pallidromic substring
public class LongestPalindromicSubstring {
    //LPSt
    static String longestPalindrome(String S){
        // code here
        // idea is expand and match

        int len = S.length();
        String result = "";
        for(int i = 0; i < len; i++) {
            String s = String.valueOf(S.charAt(i));

            // for odd
            int start = i-1;
            int end = i+1;

            String temp = String.valueOf(S.charAt(i));
            while(start >= 0 && end < len && S.charAt(start) == S.charAt(end)) {
                temp = String.valueOf(S.charAt(start)) + temp + String.valueOf(S.charAt(end));
                start--;
                end++;
            }


            // for even
            start = i;
            end = i+1;

            String temp2 = "";
            while(start >= 0 && end < len && S.charAt(start) == S.charAt(end)) {
                temp2 = String.valueOf(S.charAt(start)) + temp2 + String.valueOf(S.charAt(end));
                start--;
                end++;
            }

            if (temp.length() > temp2.length()) {
                result = (temp.length() > result.length()) ? temp : result;
            } else {
                result = (temp2.length() > result.length()) ? temp2 : result;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("aaaabbaa"));
    }
}
