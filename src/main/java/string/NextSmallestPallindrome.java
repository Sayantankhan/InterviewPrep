package string;

//https://www.interviewbit.com/problems/next-smallest-palindrome/
public class NextSmallestPallindrome {

    public static String solve(String A) {

        String ans =  generateNextString(A, false);

        if(ans.compareTo(A) <= 0) { // if the ans comes same or less than the number , so add +1 with  str(0..len/2) + 1 + str(len/2+1 ... len)
            int len = A.length();
            int mid = -1;
            if(len % 2 == 1) {
                mid = len / 2;
            } else {
                mid = (len / 2) - 1;
            }

            String temp = A.substring(0, mid+1);
            temp = nextNumberString(temp, 1);

            temp = temp + A.substring(mid+1, A.length());
            return generateNextString(temp, true);
        }
        return ans;
    }

    static String generateNextString(String A, boolean overlap) {
        boolean res = isPallindrom(A);

        if(res && overlap) return A;

        int len = A.length();
        int mid = -1;

        if(len % 2 == 1) {
            mid = len / 2;
        } else {
            mid = (len / 2) - 1;
        }

        if(res) {
            A = nextNumberString(A, 1);
        }

        String ans =  generatePallidromString(A, mid);
        return ans;
    }


    static String nextNumberString(String A, int num) {

        Integer i = Integer.valueOf(A.charAt(A.length()-1) - '0');
        int carry = (i+1) / 10;
        String temp = ""+ (i+1) % 10;

        for(int j = A.length()-2; j >=0 ; j--) {
            Integer x = Integer.valueOf(A.charAt(j) - '0');
            if( carry > 0) {
                x = x + carry;
                carry = x / 10;
                x = x % 10;
            }
            temp = x + temp;
        }

        if(carry > 0)  {
            temp = carry + temp;
        }

        return temp;
    }

    static String generatePallidromString(String A, int mid) {
        boolean isOdd = A.length() % 2 != 0;
        String substr = A.substring(0, mid+1);

        StringBuilder s = new StringBuilder(substr);
        int startIndx = (isOdd) ? substr.length()-2 : substr.length()-1;
        for(int i = startIndx; i >= 0; i--) {
            s.append(A.charAt(i));
        }

        return s.toString();
    }

    static boolean isPallindrom(String A) {
        StringBuilder sb = new StringBuilder(A);

        if(A.equals(sb.reverse().toString())) {
            return true;
        }
        else return false;
    }

    public static void main(String[] args) {
        // System.out.println(solve("184963788291359953192887369481"));

        System.out.println(solve("888"));

        // 1849637882913-5995-3192887369481

        // 1849637882913-6006-3192887369481
        // 1849637882913-5995-3192887369481
    }

    // 991234  -- 991199 || 992299 ||
}
