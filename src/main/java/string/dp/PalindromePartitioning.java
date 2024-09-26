package string.dp;

import java.util.*;

//Pallindrome Partitioning
//PalindromePartitioning
public class PalindromePartitioning {

    boolean isPallindrome(String s, int start, int end) {

        while(start < end) {
            if(s.charAt(start++) != s.charAt(end--)) return false;
        }
        return true;
    }

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        List<String> list = new ArrayList<String>();

        partitionPallindromRec(s, 0, s.length(), list, res);
        return res;
    }

    void partitionPallindromRec(String s, int index, int eindex, List<String> list, List<List<String>> res) {

        if(index >= eindex) {
            res.add(new ArrayList<String>(list));
            return;
        }

        for(int i = index; i < eindex; i++) {

            if(isPallindrome(s, index, i)) {
                list.add(s.substring(index, i+1));
                partitionPallindromRec(s, i+1, eindex, list, res);
                list.remove(list.size()-1);
            }
        }
    }
}
