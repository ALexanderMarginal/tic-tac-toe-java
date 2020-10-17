import java.util.Scanner;

class Main {

    //Rotate Matrix to 90 degree toward Left(counter clockwise)
    private static int[][] rotateMatrixBy90DegreeCounterClockwise(int[][] matrix) {

        int totalRowsOfRotatedMatrix = matrix[0].length; //Total columns of Original Matrix
        int totalColsOfRotatedMatrix = matrix.length; //Total rows of Original Matrix

        int[][] rotatedMatrix = new int[totalRowsOfRotatedMatrix][totalColsOfRotatedMatrix];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                rotatedMatrix[(totalRowsOfRotatedMatrix - 1) - j][i] = matrix[i][j];
            }
        }
        return rotatedMatrix;
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] matrix = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        int[][] rotatedMatrix = rotateMatrixBy90DegreeCounterClockwise(matrix);
        rotatedMatrix = rotateMatrixBy90DegreeCounterClockwise(rotatedMatrix);
        rotatedMatrix = rotateMatrixBy90DegreeCounterClockwise(rotatedMatrix);

        printMatrix(rotatedMatrix);
    }
}