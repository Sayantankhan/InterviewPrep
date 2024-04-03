package util;

public class Utility {

    public static void assertTrue(Object actual, Object expected) throws Exception {
        if(!actual.equals(expected)) throw new Exception("Actual: "+actual+" Expected: "+expected);
    }
}
