package processor;

public class Main {

    static java.util.Scanner scanner = new java.util.Scanner(System.in);

    public static void main(String[] args) {

        int firstRow = scanner.nextInt();
        int firstColumn = scanner.nextInt();

        int[][] firstMatrix = readMatrix(firstRow, firstColumn);

        int secondRow = scanner.nextInt();
        int secondColumn = scanner.nextInt();

        int[][] secondMatrix = readMatrix(secondRow, secondColumn);

        if (firstRow != secondRow || firstColumn != secondColumn) {
            System.out.println("ERROR");
            return;
        }

        for (int i = 0; i < firstRow; i++) {
            for (int j = 0; j < firstColumn; j++) {
                System.out.print((firstMatrix[i][j] + secondMatrix[i][j]) + " ");
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
