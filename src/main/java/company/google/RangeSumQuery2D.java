package company.google;

public class RangeSumQuery2D {

    // Range sum in 1D matrix {M size} using Prefix Sum Algo takes -> O(M) time for preprocessing and O(1) to query
    // For 2D matrix preprocessed O(MN)
    // For query O(M)

    // Approach 2:
    static class AreaRange2D {
        int[][] area;
        int[][] processed;

        AreaRange2D(int[][] area) {
            this.area = area;
            this.processed = new int[area.length+1][area[0].length+1];
            preprocessing();
            int a = processed.length;
        }

        // Preprocessing
        private void preprocessing() {
            int x = area.length;
            int y = area[0].length;
            // Horizontal
            for(int j = 1; j <= y; j++) {
                processed[1][j] = processed[1][j-1] + area[0][j-1];
            }
            // Vertical
            for(int j = 1; j <= x; j++) {
                processed[j][1] = processed[j-1][1] + area[j-1][0];
            }

            for(int i = 2; i <= x; i++) {
                for (int j = 2; j <= y; j++) {
                    processed[i][j] = processed[i-1][j] + processed[i][j-1] - processed[i-1][j-1] + area[i-1][j-1];
                }
            }
        }

        // Area(x1,y1 -> x2,y2) = Area(0,0 -> x2,y2) - Area(0,0 -> x1, y2) - Area(0,0 -> x2, y1) + Area(0,0 -> x1, y1)
        private int sumRegion(int x1, int y1, int x2, int y2) {
            return processed[x2+1][y2+1] - processed[x1][y2+1] - processed[x2+1][y1] + processed[x1][y1];
        }
    }

    // [2, 1] -> [4, 3] = [3, 2] -> [5, 4] = [5,4] - [2,4] - [5,1] + [2,1]

    // Brute Force Approach - O(M,N)
    static int sumRegion(int[][] matrix, int x1, int y1, int x2, int y2) {
        int sum = 0;
        for(int i = x1; i <= x2; i++) {
            for(int j = y1; j <= y2; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };

        System.out.println(sumRegion(matrix, 2, 1, 4, 3));

        AreaRange2D range2D = new AreaRange2D(matrix);
        System.out.println(range2D.sumRegion(2, 1, 4, 3));
    }
}


