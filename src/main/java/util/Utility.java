package util;

import java.util.Arrays;

public class Utility {

    public static void assertTrue(Object actual, Object expected) throws Exception {
        if(!actual.equals(expected)) throw new Exception("Actual: "+actual+" Expected: "+expected);
    }

    public static void assertTrue(int[] actual, int[] expected) throws Exception {
        if(!Arrays.equals(actual, expected)) throw new Exception("Actual: "+actual+" Expected: "+expected);
    }
}
