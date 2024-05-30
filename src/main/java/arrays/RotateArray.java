package arrays;

public class RotateArray {

    // clock wise
    public static void rotateBy90(int[][] matrix) {
        int len = matrix.length;

        // Consider all squares one by one
        for(int x = 0; x < len/2; x++) {
            for (int y = x; y < len - x - 1; y++) {

                int temp = matrix[x][y]; // 0, 0
                // Move values from right to top
                matrix[x][y] = matrix[len - 1 - y][x];
                matrix[len - 1 - y][x] = matrix[len - 1 - x][len - 1 - y];
                matrix[len - 1 - x][len - 1 - y] = matrix[y][len - 1 - x];
                matrix[y][len - 1 - x] = temp;
            }
        }
    }

    // Anti clock wise
    public static void rotateBy90AntiClock(int[][] matrix) {
        int len = matrix.length;

        // Consider all squares one by one
        for(int x = 0; x < len/2; x++) {
            for (int y = x; y < len - x - 1; y++) {

                int temp = matrix[x][y]; // 0, 0
                // Move values from right to top
                matrix[x][y] = matrix[len - 1 - x][y];
                matrix[len - 1 - x][y] = matrix[len - 1 - x][len - 1 - y];
                matrix[len - 1 - x][len - 1 - y] = matrix[y][len - 1 - x];
                matrix[y][len - 1 - x] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotateBy90(matrix);
        System.out.println(matrix);
    }
}
