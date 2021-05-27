package processor;

public class Main {

    static java.util.Scanner scanner = new java.util.Scanner(System.in);

    public static void main(String[] args) {

        int row = scanner.nextInt();
        int column = scanner.nextInt();

        int[][] matrix = readMatrix(row, column);

        int toBeMultipliedBy = scanner.nextInt();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                matrix[i][j] *= toBeMultipliedBy;
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int[][] readMatrix(int row, int column) {

        int[][] matrix = new int[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

}
