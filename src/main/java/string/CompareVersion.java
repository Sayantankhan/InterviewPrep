package string;

public class CompareVersion {

    public static int compareVersion(String version1, String version2) {
        String[] s1 = version1.split("\\.");
        String[] s2 = version2.split("\\.");

        int len1 = s1.length;
        int len2 = s2.length;
        int pointer = 0;

        while(pointer < len1 && pointer < len2) {
            int a = Integer.parseInt(s1[pointer]);
            int b = Integer.parseInt(s2[pointer]);

            System.out.println(a + " " + b);

            if(a > b) return 1;
            if(a < b) return -1;

            pointer++;
        }

        while(pointer < len2) {
            int b = Integer.parseInt(s2[pointer]);
            if(b > 0) return -1;
            pointer++;
        }

        while(pointer < len1) {
            int a = Integer.parseInt(s1[pointer]);
            if(a > 0) return 1;
            pointer++;
        }

        return 0;
    }

    public static void main(String[] args) {
        compareVersion("1.2","1.10");
    }
}
