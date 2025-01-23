package company.google;

import java.util.Stack;

// https://leetcode.com/discuss/interview-question/5878475/AMAZON-OA/
public class lexiographicallyBiggerString {

    public static void main(String[] args) {

        lexiographicallyBiggerString lb = new lexiographicallyBiggerString();

        System.out.println(lb.getSpecialStringStack("abcd"));
        System.out.println(lb.getSpecialStringStack("aacd"));
        System.out.println(lb.getSpecialStringStack("abbscd"));
        System.out.println(lb.getSpecialStringStack("aaaa"));
        System.out.println(lb.getSpecialStringStack("zzabb"));
        System.out.println(lb.getSpecialStringStack("abczz"));
        System.out.println(lb.getSpecialStringStack("abcc"));
        System.out.println(lb.getSpecialStringStack("abccss"));
        System.out.println(lb.getSpecialStringStack("zyx"));

        System.out.println(lb.getSpecialStringStack("abbd"));
        System.out.println(lb.getSpecialStringStack("abccdeaaa"));

        System.out.println(lb.getSpecialStringStack("zyxwvutstuvwxyz"));
        System.out.println(lb.getSpecialStringStack("zyz"));
        System.out.println(lb.getSpecialStringStack("zyxz"));

        System.out.println(lb.getSpecialStringStack("zyzyzyz"));

    }


    // Special String
    // 1. no adjacent character
    // 2. get the next character
    // 3. if there is z go back and modify the prev character
    // 4. if there is no way to get the next one return -1

    String getSpecialString(String s) {

        char[] ch = s.toCharArray();
        // find immediate lexiographically larger : lastchar + 1
        char c = getNextCharacter(s.charAt(s.length()-1));
        ch[s.length()-1] = c;

        int index = -1;
        for(int i = 0; i < s.length()-1; i++) {
            if(ch[i+1] == '$') {
                // This check is for only last character is z
                ch[i] = getNextCharacter(ch[i]);
                if(ch[i] == '$') {
                    if(i-2 < -1) return "-1";
                    i -= 2; continue;
                }
                // else return "-1";
                //ch[i+1] = 'z';
            }
            if(ch[i] == ch[i+1]) {
                index = i;
                break;
            }

        }


        // z is the main problem, as there will no character after z
        // check the consecutive character which got a match is z or not
        if(index != -1) {
            int lindex = -1;
            char temp = ch[index];
            if(temp == 'z') {
                if(index-1 < 0) return "-1";
                else {
                    ch[index-1] = getNextCharacter(ch[index-1]);
                    lindex = index-1;
                }
            } else {
                ch[index+1] = getNextCharacter(ch[index]);
                lindex = index+1;
            }

            // now fill the rest with abababab....
            for(int j = lindex+1; j < s.length(); j++) {
                if(ch[j-1] == 'a') ch[j] = 'b';
                else ch[j] = 'a';
            }

        }

        return new String(ch);
    }

    class Tuple {
        Stack<Character> stack;
        String s;

        Tuple(Stack<Character> stack, String s ) {
            this.stack = stack;
            this.s = s;
        }
    }
    Tuple updateAdjacent(String s) {
        Stack<Character> chstk = new Stack<>();
        int len = s.length();

        for(int i = 0 ; i < len; i++) {
            if((!chstk.isEmpty() && chstk.peek() == s.charAt(i)) || (s.charAt(i) == '$')){
                char ch = getNextCharacter(s.charAt(i));
                while(chstk.size() > 0 && (ch == '$' || ch == chstk.peek())) {
                    if(ch == '$') {
                        ch = getNextCharacter(chstk.pop());
                        continue;
                    }
                    if(ch == chstk.peek()) {
                        ch = getNextCharacter(ch);
                    }
                }
                if(ch == '$') return new Tuple(null, "-1");
                chstk.push(ch);
                break;
            }
            else chstk.push(s.charAt(i));
        }

        if(chstk.size() != len) {
            int left = len - chstk.size();
            String res = generateString(chstk.peek(), left);
            StringBuilder sb = new StringBuilder();
            chstk.stream().forEach(ch -> sb.append(ch));
            return new Tuple(chstk, sb.append(res).toString());
        }

        StringBuilder sb = new StringBuilder();
        chstk.stream().forEach(ch -> sb.append(ch));
        return new Tuple(chstk, sb.toString());
    }

    String getSpecialStringStack(String s) {

       Tuple adj = updateAdjacent(s);
       if(!adj.s.equals(s)) {
           return adj.s;
       } else {
           char ch = getNextCharacter(adj.stack.pop());
           StringBuilder sb = new StringBuilder();
           adj.stack.stream().forEach(c -> sb.append(c));
           return updateAdjacent(sb.append(ch).toString()).s;
       }

    }
    char getNextCharacter(char c) {
        if(c == 'z') return '$';
        if(c == '$') return '$';
        return (char)(c+1);
    }

    String generateString(char c, int len) {
        String s = "";
        boolean isA = false;
        if(c != 'a'){
            s += 'a';
            isA = true;
        } else {
            s += 'b';
            isA = false;
        }

        //fill it up
        for(int i = 0; i < len-1; i++) {
            if(isA) s += 'b';
            else s+= 'a';

            isA = !isA;
        }
        return s;
    }

}
