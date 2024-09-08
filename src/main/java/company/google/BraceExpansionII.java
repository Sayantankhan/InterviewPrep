package company.google;

import java.util.*;

//https://leetcode.com/problems/brace-expansion-ii
public class BraceExpansionII {

    class Tuple {
        char ch;
        int index;

        Tuple(char ch, int index ) {
            this.ch = ch;
            this.index = index;
        }
    }

    public List<String> braceExpansionII(String expression) {
        Stack<String> stack1 = new Stack();
        Stack<List<String>> stack2 = new Stack();

        // {ax{ab,c}}


        System.out.println(stack1);
        return new ArrayList<String>();
    }

    public static void main(String[] args) {
        BraceExpansionII be = new BraceExpansionII();
        System.out.println(be.braceExpansionII("ax{b,c}"));
    }
}
