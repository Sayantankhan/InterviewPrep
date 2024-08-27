package company.google;

import java.util.*;

public class MinimizeMaxDistanceToGasStation {

    public static double findSmallestMaxDist(int stations[], int k) {
        // code here
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(
                (e1, e2) -> (int) (((double)e2[0]/e2[1] - (double)e1[0]/e1[1])*1e8) );
        // The idea of storing the pair -> {distance_between_gas_station, no of stations can be placed}
        // to make it equally spread out we need distance_between_gas_station/ (no of stations can be placed)
        // if we just go ahead and divided by 2 , it will not be optimum
        // Ex -> [1 7] k = 2 if we take divided by 2 approach
        //              one will be placed , max distance will be 7-1/2 = 6/2 = 3
        // what about the second one if we do 3/2 = 1.5 but max dis will be 3,
        // best option is divide into same numner of slot -> 7-1/3 = 2 -> equally distributed and max = 2 now !!!

        for(int i = 1; i < stations.length; i++) {
            pq.offer(new int[]{stations[i]-stations[i-1], 1});
        }

        while(k > 0) {
            int[] gap = pq.poll(); // O(log n) - heapify
            gap[1]++;
            pq.add(gap);
            k--;
        }

        int[] gap = pq.poll();
        return (double)Math.round((double)gap[0]/gap[1]*100) / 100;
    }

    // Minimimum maximum - binary serach
    // find high and low
    public static double findSmallestMaxDistBinarySearch(int stations[], int k) {
        double low = 0;
        // max diff
        double high = 0;
        for(int i = 1; i < stations.length; i++) {
            high = Math.max(high, stations[i] - stations[i-1]);
        }

        double diff = 1e-6 ;
        while(high-low > diff) {
            double mid = (high + low)/(2.0);
            int cnt = checkCnt(stations, mid); // to check whether we can able to fit or not

            if(cnt > k) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return Math.round(high * 100.0)/100.0;
    }

    private static int checkCnt(int[] stations, double mid) {
        int cnt = 0;
        for(int i = 1; i < stations.length; i++) {
            int diff = stations[i] - stations[i-1];
            if(diff%mid == 0) {
                cnt += (diff/mid) - 1;
            } else {
                cnt += (diff/mid);
            }
        }

         return cnt;
    }

    public static void main(String[] args) {
        System.out.println(findSmallestMaxDist(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 9));
        System.out.println(findSmallestMaxDistBinarySearch(new int[]{3,6,12,19,33,44,67,72,89,95}, 2));

        System.out.println(findSmallestMaxDist(new int[]{74,284,316,461,485,587,589,731,755,843,881,943,1065,1183,1185,1305,1347,1362,1467,1515,1637,1836,2106,2296,2382,3096,3301,3394,3397,3557,3563,3592,3643,4057,4084,4101,4176,4204,4218,4226,4321,4511,4595,4770,4829,4831,4856,4984,5019,5026,5032,5039,5052,5167,5215,5266,5283,5289,5362,5475,5538,5655,5661,5895,6214,6297,6309,6363,6513,6670,6688,6696,6814,6838,6920,
                6956,7061,7272,7400,7428,7517,7549,7622,7878,7996,8141,8164,8328,8340,8381,8390,8412,8454,8476,8553,8606,8763,8783,8825,8881,8939,8998,9022,9120,9127,9206,9240,9309,9326,9339,9514,9561,9732,9772,10142,10208,10319,10354,10452,10475,10506,10515,10527,10613,10640,10647,10795,10802,10864,10924,10951,
                10952,11027,11054,11078,11130,11140,11176,11286,11392,11394,11469,11739,11817,11823,11863,11878,11892,11904,11934,12248,12323,12454,12630,12765,12780,13163,13169,13209,13310,13426,13490,13499,13535,13539,13648,13790,13838,13968,14019,14118,14180,14197,14300,14341,14474,14561,14592,14839,14906,15025,15300,15304,15360,15456,15501,15525,15530,15559,15749,15801,15883,15956,16122,16136,16183,16307,16326,
                16371,16476,16592,16755,16858,16862,16887,16899,16915,16917,16930,16965,16974,17031,17036,17097,17239,17261,17332,17344,17435,17512,17523,17617,17726,17734,17763,18103,18114,18121,18211,18215,18352,18506,18603,18628,18965,19216,19515,19534,19672,19701,19788,19864,20004,20103,20105,20134,20318,20496,20618,20621,20740,20858,21018,21024,21029,21102,21230,21415,21531,21565,21575,21698,21800,21890,21935,
                21938,21972,21986,22057,22077,22620,22741,22771,22920,23051,23267,23438,23514,23540,23608,23743,23907,23919,24000,24046,24131,24159,24201,24452,24458,24466,24633,24734,24779,24875,24946,25001,25020,25023,25113,25150,25330,25375,25405,25422,25462,25469,25521,25525,25837,25908,25947,26043,26065,26068,26098,26113,26308,26350,26459,26490,26571,26809,26873,26941,26973,27096,27134,27156,27342,27402,27534,
                27546,27594,27610,27692,27773,27988,28136,28260,28268,28290,28391,28428,28462,28909,28924,29083,29178,29226,29242,29250,29387,29425,29693,29740,29745,29775,29786,30097,30132,30294,30377,30497,30726,30829,30841,30843,30886,30980,30994,31038,31082,31088,31243,31379,31524,31544,31549,31597,31655,31784,31870,31947,31961,32048,32049,32146,32206,32327,32453,32612,32664,32701,32712, 26214},395));

        System.out.println(findSmallestMaxDist(new int[]{0, 10, 20, 30, 50}, 2));

    }



}
