package string;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import util.Utility;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 1)
@Warmup(iterations = 5, timeUnit = TimeUnit.MILLISECONDS, time = 5000)
@Measurement(iterations = 5, timeUnit = TimeUnit.MILLISECONDS, time = 5000)
public class DistinctSubSequence {

    static int ans = 0;

    public static int numDistinct(String s, String t) {
//        numDistinctRec(s, t, 0, new StringBuilder("") );
//        return ans;
        // return numDistinctRec2(s, t, 0, 0);
        return numDistinctDP(s, t);
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

    private static int numDistinctRec2(String s, String t, int i, int j) {

        if(j >= t.length()) return 1;
        if(i >= s.length()) return 0;

        if(s.charAt(i) == t.charAt(j)) {
            return numDistinctRec2(s, t, i+1, j+1) +
                    numDistinctRec2(s, t, i+1, j);
        }
        return numDistinctRec2(s, t, i+1, j);
    }

    private static int numDistinctDP(String s, String t) {

        int[][] dp = new int[s.length()+1][t.length()+1];

        for(int i = 0; i < s.length()+1; i++) {
            dp[i][t.length()] = 1;
        }

        for(int i = s.length()-1; i >= 0; i--) {
            for(int j = t.length()-1; j >= 0; j--) {
                if(s.charAt(i) == t.charAt(j)) {
                    dp[i][j] = dp[i+1][j+1] + dp[i+1][j];
                } else {
                    dp[i][j] = dp[i+1][j];
                }
            }
        }

        return dp[0][0];
    }

    @Benchmark
    public void bechmarkTest() {
       // numDistinctDP("babgbag", "bag"); // 250 ns/ops
        // numDistinctRec2("babgbag", "bag", 0, 0); // 50 ns/ops

        //numDistinctRec2("rabbbbbbbbbbbbbbbbbbbbbit", "rabbit", 0, 0); // 3627 ns/ops
        numDistinctDP("rabbbbbbbbbbbbbbbbbbbbbit", "rabbit"); // 760 ns/ops

        // Recursion is good for small String size, where dp perfoms better for large String size
    }


    Predicate<Integer> isEven = (number) -> (number % 2 == 0);
    Predicate<Integer> isNotZero = (number) -> (number == 0);

    @Benchmark
    public void benchmarkWithMultipleFilters() {
        List list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        list.stream()
                .filter(x -> isEven.test((Integer) x))
                .filter(x -> isNotZero.test((Integer) x))
                .count();
    }

    @Benchmark
    public void benchmarkWithOneFilters() {
        List list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        list.stream()
                .filter(x -> isEven.test((Integer) x) && isNotZero.test((Integer) x))
                .count();
    }

    public static void main(String[] args) throws Exception {

        Utility.assertTrue(numDistinct("babgbag", "bag"), 5);
        Utility.assertTrue(numDistinct("rabbbit", "rabbit"), 3);
    }

}

