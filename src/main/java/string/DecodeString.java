package string;

import java.util.Stack;

// https://leetcode.com/problems/decode-string/submissions/1252324802/
public class DecodeString {

    public static String decodeString(String s) {

        Stack<Character> stack = new Stack<>();
        String ans = "";

        for(int i = 0; i < s.length(); i++) {
            String repeat = "";
            String timesOfRepeat = "";
            if(s.charAt(i) == ']') {
                while(!stack.isEmpty() && stack.peek() != '[') {
                    repeat = stack.pop() + repeat;
                }

                stack.pop();
                while(!stack.isEmpty() && (stack.peek() - '0') >= 0 && (stack.peek() - '0') <= 9) {
                    timesOfRepeat = stack.pop() + timesOfRepeat;
                }

                for(int j = 0; j < Integer.valueOf(timesOfRepeat); j++) {
                        for(int k = 0; k < repeat.length(); k++) {
                            stack.push(repeat.charAt(k));
                        }
                }
            }
            else {
                stack.push(s.charAt(i));
            }
        }


        while(!stack.isEmpty()) {
            ans = stack.pop() + ans;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(decodeString("3[a]2[bc]"));
        System.out.println(decodeString("3[a2[c]]"));
        System.out.println(decodeString("2[abc]3[cd]ef"));

    }
}
