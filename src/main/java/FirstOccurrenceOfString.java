public class FirstOccurrenceOfString {

    public int strStr(String haystack, String needle) {
        int m = needle.length();

        for(int i = 0; i < haystack.length(); i++) {
            int j = 0;
            int temp = i;
            while(i < haystack.length() && j < m && haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            }
            if(j == m) {
                return i-j;
            }
            i = temp;

        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new FirstOccurrenceOfString().strStr("mississippi", "issip"));
        System.out.println(new FirstOccurrenceOfString().strStr("leetcode", "leeto"));
        System.out.println(new FirstOccurrenceOfString().strStr("sadosad", "sad"));
    }
}
