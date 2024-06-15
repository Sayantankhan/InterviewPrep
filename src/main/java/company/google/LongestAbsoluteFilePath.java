package company.google;

import java.util.*;
import java.util.function.Predicate;

// https://leetcode.com/problems/longest-absolute-file-path/
public class LongestAbsoluteFilePath {

    static Predicate<String> isFile = (name) -> {
        return name.contains(".");
    };
    public static int lengthLongestPath(String input) {

        Stack<String> stack = new Stack<>();
        int len = 0;

        for(int i = 0; i < input.length(); i++) {
            StringBuilder sb = new StringBuilder();
            int depth = 0;

            while(i < input.length() && input.charAt(i) == '\t') {
                depth ++;
                i++;
            }

            while(i < input.length() && input.charAt(i) != '\n') {
                sb.append(input.charAt(i));
                i++;
            }

            if(depth < stack.size()) {
                int l = stack.size();
                while(l > depth) {
                    stack.pop();
                    l = stack.size();
                }
            }

            if(stack.size() == 0) stack.add(sb.toString());
            else stack.add(stack.peek() + "/" + sb.toString());

            if(isFile.test(stack.peek())) {
                len = Math.max(len, stack.peek().length());
            }

        }

        return len;
    }

    public static void main(String[] args) {
        String input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1";

        String input1 = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
        String input2 = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        String input3 = "a";

//        System.out.println(lengthLongestPath(input));
        System.out.println(lengthLongestPath(input1));
        System.out.println(lengthLongestPath(input2));
        System.out.println(lengthLongestPath(input3));
    }
}
