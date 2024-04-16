package string;

import util.Utility;

public class DistinctSubSequence {

    static int ans = 0;
    public static int numDistinct(String s, String t) {
        numDistinctRec(s, t, 0, new StringBuilder("") );
        return ans;
    }
    private static void numDistinctRec(String s, String t, int i, StringBuilder temp) {

        if(i >= s.length()) {
            if(temp.toString().equals(t)) {
                ans++;
            }
            return;
        }

        temp.append(s.charAt(i));
        numDistinctRec(s, t, i+1, temp);

        temp.deleteCharAt(temp.length() - 1);
        numDistinctRec(s, t, i+1, temp);
    }

    public static void main(String[] args) throws Exception {

        Utility.assertTrue(numDistinct("babgbag", "bag"), 5);
        Utility.assertTrue(numDistinct("rabbbit", "rabbit"), 3);
    }

}

