package company;

// https://www.geeksforgeeks.org/problems/candy/1
public class CandyDistribution {

    static int minCandy(int N, int ratings[]) {
        // code here
        int i = 1, sum = 1;

        while(i < N) {
            if(ratings[i] == ratings[i-1]) {
                sum += 1;
                i++;
            }

            int peak = 0;
            while(i < N && ratings[i] > ratings[i-1]) {
                // increasing slope
                peak += 1;
                sum += peak;
                i++;
            }

            int down = 0;
            while(i < N && ratings[i] < ratings[i-1]) {
                // decreasing slope
                down += 1;
                sum += down;
                i++;
            }

            if(down + 1 > peak) {
                sum += (down + 1 - peak);
            }
        }

        return sum;

    }

    public static void main(String[] args) {
        System.out.println(minCandy(10, new int[] {11942, 4827, 5436, 32391, 14604, 3902, 153, 292, 12382, 17421}));
    }

    //11942, 4827, 5436, 32391, 14604, 3902, 153, 292, 12382, 17421
    //1, 1, 2, 3, 1, 1, 1, 2, 3, 4
    //2, 1, 1, 4, 3, 2, 1, 1, 1, 1

    //2, 1, 2, 4, 3, 2, 1, 2, 3, 4
}
