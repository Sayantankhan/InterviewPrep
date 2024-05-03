package tree;

import util.Utility;

import java.math.BigInteger;

public class SumToRootLeaf {

    static String sum = "0";
    public static int sumNumbers(TreeNode A) {
        sumNumbersRec(A, "");
        return new BigInteger(sum).mod(new BigInteger("1003")).intValue();
    }

    static void sumNumbersRec(TreeNode A, String v) {

        if(A == null) return;

        if(A.left == null && A.right == null) {
            BigInteger bi = new BigInteger(sum);
            BigInteger bi2 = new BigInteger(v+ A.val);

            sum = bi.add(bi2).toString();
            return;
        }


        sumNumbersRec(A.left, v + A.val);
        sumNumbersRec(A.right, v + A.val);
    }

    public static void main(String[] args) {
//        TreeNode  t = Utility.generateBinartTreeFromArray(new Integer[]{5, 1, null, 2, null, null, null});
//        sumNumbers(t);

        TreeNode t = Utility.generateBinartTreeFromArray(new Integer[]{1, 2, 3, 4, 5, null, 6, 7, 8, null,null,null,null,null,null,null,null});
        System.out.println(sumNumbers(t));
    }
}
