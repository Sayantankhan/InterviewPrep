package string;

import java.math.BigInteger;

public class MultiplyString {

    public static String multiply(String A, String B) {
        int sum = 0;
        String tempSum = "";
        for(int i = B.length()-1; i >= 0; i--) {
            String levelMul = multiplyLevel(A, B.charAt(i));
            //System.out.println(levelMul);
            levelMul = multiplyLevelByZero(levelMul, (B.length()-1-i));
            tempSum = addition(tempSum, levelMul);
        }

        tempSum = removeLeadZeros(tempSum);
        return tempSum;
    }

    private static String removeLeadZeros(String tempSum) {
        int index = -1;
        for(int i = 0; i < tempSum.length(); i++) {
            if(tempSum.charAt(i) == '0'){
                index++;
            } else {
                break;
            }
        }

        if(index == -1) return tempSum;
        return tempSum.substring(index, tempSum.length());
    }

    private static String multiplyLevelByZero(String levelMul, int idx) {
        for(int i = 0; i < idx; i++){
            levelMul = levelMul + "0";
        }
        return levelMul;
    }

    private static String addition(String tempSum, String levelMul) {

        if(tempSum.isEmpty() || tempSum.equals("")) return levelMul;
        if(levelMul.isEmpty()|| levelMul.equals("")) return tempSum;

        int i = tempSum.length()-1;
        int j = levelMul.length()-1;

        int carry = 0;
        String elstr = "";

        while(i >= 0 && j >= 0) {
            int el1 = tempSum.charAt(i) - '0';
            int el2 = levelMul.charAt(j) - '0';

            int res = el1 + el2 + carry;

            carry = res / 10;
            elstr = (res % 10) + elstr;

            i--;
            j--;
        }

        while(i >= 0 && carry > 0) {
            int el1 = tempSum.charAt(i) - '0';
            int res = el1 + carry;

            carry = res / 10;
            elstr = (res % 10) + elstr;
            i--;
        }

        while(j >= 0 && carry > 0) {
            int el2 = levelMul.charAt(j) - '0';
            int res = el2 + carry;

            carry = res / 10;
            elstr = (res % 10) + elstr;
            j--;
        }

        if(i >= 0 && carry == 0) {
            elstr = tempSum.substring(0, i+1) + elstr;
        }
        if(j >= 0 && carry == 0) {
            elstr = levelMul.substring(0, i+1) + elstr;
        }

        if(carry > 0) {
            elstr = carry + elstr;
        }

        return elstr;
    }

    static String multiplyLevel(String A, char ch) {
        int carry = 0;
        String el = "";

        for(int j = A.length()-1; j >= 0; j--) {
            int jel = A.charAt(j) - '0';
            int iel = ch - '0';

            int mul = jel * iel;

            el = (carry + mul) % 10 + el;
            carry = (carry + mul) / 10;
        }

        if(carry > 0) {
            el = carry + el;
        }

        return el;
    }

    public static void main(String[] args) {
//        BigInteger bi1 = new BigInteger("5131848155574784703269632922904933776792735241197982102373370");
//        BigInteger bi2 = new BigInteger("6");
//
//        String s = "56675688419586288442134264892419611145485574406534291250836";
//        for(int i = s.length()-1; i>=0; i--) {
//            bi2 = new BigInteger(String.valueOf(s.charAt(i)));
//            System.out.println(bi1.multiply(bi2).toString());
//        }
//
//        //System.out.println(bi1.multiply(bi2).toString());
//
//        // 56675688419586288442134264892419611145485574406534291250836
//
//        System.out.println();
        System.out.println(multiply("5131848155574784703269632922904933776792735241197982102373370","56675688419586288442134264892419611145485574406534291250836"));

        //29085102-70-81985078955918627261-7-51688832742312387314888842460711865148550965912482730570750031304678344564428861596637320
        //29085102-69-81985078955918627261-6-51688832742312387314888842460711865148550965912482730570750031304678344564428861596637320

    }
}
