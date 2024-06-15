package string;

import java.util.Stack;

// https://leetcode.com/problems/remove-k-digits/description/
public class RemoveKDigits {
    static int min = Integer.MAX_VALUE;
    public static String removeKdigits(String num, int k) {
        int noOfDigLeft = num.length() - k;
        findMinFromDigits(num, 0, noOfDigLeft, noOfDigLeft, "");
        if (min == Integer.MAX_VALUE) {
            return "0";
        }
        return ""+min;
    }
    private static void findMinFromDigits(String num, int i, int noOfDigLeft, int len, String s) {

        if(noOfDigLeft == 0) {
            if(s.equals("") || s.length() != len) return;
            min = Math.min(min, Integer.valueOf(s));
            return;
        }
        if(i >= num.length()) return;

        findMinFromDigits(num, i+1, noOfDigLeft-1, len,s + String.valueOf(num.charAt(i)));
        findMinFromDigits(num, i+1, noOfDigLeft, len, s);

    }


    // Monotonic Stack !!!!!!!
    public static String removeKdigits2(String num, int k) {
       Stack<Integer> stack = new Stack<>();

       for(int i = 0; i < num.length(); i++) {
           while(!stack.isEmpty() && stack.peek() > (num.charAt(i) - '0') && k > 0) {
               stack.pop();
               k--;
           }
           stack.add(num.charAt(i) - '0');
       }

       String ans = "";

        while(!stack.isEmpty()) {
            // int el = stack.pop();
            ans = stack.pop() + ans;
        }
        ans = ans.substring(0, ans.length() - k);

        int i = 0;
        while(i < ans.length() && ans.charAt(i) == '0') { // removing leading 0's
            i++;
        }

        ans = ans.substring(i, ans.length());
        if(ans.equals("")) return "0";
        return ans;
    }

    public static String removeKdigits3(String num, int k) {

        if(num.length() <= k) return "0";

        Stack<Integer> stack = new Stack<Integer>();

        for(int i = 0; i < num.length(); i++) {
            while(!stack.isEmpty() &&
                    stack.peek() > num.charAt(i) - '0' && k > 0) {

                stack.pop();
                k--;
            }

            stack.add(num.charAt(i) - '0');
        }

        StringBuilder sb = new StringBuilder("");
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }


        String s = sb.reverse().toString();
        s = s.substring(0, s.length()-k);

        int i = 0;
        while(i < s.length() && s.charAt(i) == '0') {
            i++;
        }
        s = s.substring(i, s.length());
        if(s.equals("")) return "0";
        return s.substring(i, s.length());
    }

    public static void main(String[] args) {

        System.out.println(removeKdigits3("10200", 1));
    }

}
