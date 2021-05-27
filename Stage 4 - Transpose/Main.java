package processor;

public class Main {

    public static void main(String[] args) {

        MatrixOperationFactory operation = null;

        while (true) {

            printMenu();

            java.util.Scanner sc = new java.util.Scanner(System.in);

            int selection = sc.nextInt();

            switch (selection) {

                case 1 :
                    operation = MatrixOperationFactory.executeMatrixAddition();
                    break;

                case 2 :
                    operation = MatrixOperationFactory.executeMatrixScalarMultiplication();
                    break;

                case 3 :
                    operation = MatrixOperationFactory.executeMatrixMultiplication();
                    break;

                case 4:
                    operation = MatrixOperationFactory.executeMatrixTranspose();
                    break;

                case 0 :
                    return;

                default:
                    System.out.println("Invalid choice! Please choose again!");
                    break;
            }
        }
    }

    static void printMenu() {

        System.out.print("1. Add matrices\n" +
                "2. Multiply matrix by a constant\n" +
                "3. Multiply matrices\n" +
                "0. Exit\n" +
                "Your choice: ");
    }
}

abstract class MatrixOperationFactory {

    static java.util.Scanner scanner = new java.util.Scanner(System.in);
    boolean isIntegerMatrix = false;

    static MatrixAddition executeMatrixAddition() {
        return new MatrixAddition();
    }

    static MatrixScalarMultiplication executeMatrixScalarMultiplication() {
        return new MatrixScalarMultiplication();
    }

    static MatrixMultiplication executeMatrixMultiplication() {
        return new MatrixMultiplication();
    }

    static MatrixTranspose executeMatrixTranspose() {return new MatrixTranspose();}

    void printMatrix(double[][] matrix, int row, int column) {

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {

                if (isIntegerMatrix) {
                    System.out.print((int) matrix[i][j] + " ");
                } else {
                    System.out.print(matrix[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    double[][] readDoubleMatrix(int row, int column) {

        if (scanner.hasNextInt()) {
            isIntegerMatrix = true;
        }

        double[][] matrix = new double[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
        return matrix;
    }
}

class MatrixAddition extends MatrixOperationFactory {

    MatrixAddition() {

        System.out.print("Enter size of first matrix: ");

        int firstMatrixRow = scanner.nextInt();
        int firstMatrixColumn = scanner.nextInt();

        System.out.println("Enter first matrix: ");
        double[][] firstMatrix = readDoubleMatrix(firstMatrixRow, firstMatrixColumn);

        System.out.print("Enter size of second matrix: ");

        int secondMatrixRow = scanner.nextInt();
        int secondMatrixColumn = scanner.nextInt();

        if (firstMatrixRow != secondMatrixRow || firstMatrixColumn != secondMatrixColumn) {
            System.out.println("Addition cannot be performed on matrices of different size.\n");
            return;
        }

        System.out.println("Enter second matrix: ");
        double[][] secondMatrix = readDoubleMatrix(secondMatrixRow, secondMatrixColumn);

        System.out.println("The result is: ");
        for (int i = 0; i < firstMatrixRow; i++) {
            for (int j = 0; j < firstMatrixColumn; j++) {

                if (isIntegerMatrix) {
                    System.out.print((int) (firstMatrix[i][j] + secondMatrix[i][j]) + " ");
                } else {
                    System.out.print((firstMatrix[i][j] + secondMatrix[i][j]) + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}

class MatrixScalarMultiplication extends MatrixOperationFactory {

    MatrixScalarMultiplication() {

        System.out.print("Enter size of matrix : ");

        int row = scanner.nextInt();
        int column = scanner.nextInt();

        double[][] matrix = readDoubleMatrix(row, column);

        System.out.print("Enter constant : ");
        double constant = scanner.nextFloat();

        System.out.println("The result is: ");

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {

                if (isIntegerMatrix) {
                    System.out.print((int) (matrix[i][j] * constant) + " ");
                } else {
                    System.out.print((matrix[i][j] * constant) + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}

class MatrixMultiplication extends MatrixOperationFactory {

    MatrixMultiplication() {

        System.out.print("Enter size of first matrix: ");

        int firstMatrixRow = scanner.nextInt();
        int firstMatrixColumn = scanner.nextInt();

        System.out.println("Enter first matrix: ");
        double[][] firstMatrix = readDoubleMatrix(firstMatrixRow, firstMatrixColumn);

        System.out.print("Enter size of second matrix: ");

        int secondMatrixRow = scanner.nextInt();
        int secondMatrixColumn = scanner.nextInt();

        if (firstMatrixColumn != secondMatrixRow) {
            System.out.println("Number of columns in first matrix and row of second matrix should be same.\n");
            return;
        }

        System.out.println("Enter second matrix: ");
        double[][] secondMatrix = readDoubleMatrix(secondMatrixRow, secondMatrixColumn);

        System.out.println("The result is: ");
        for (int i = 0; i < firstMatrixRow; i++) {
            for (int j = 0; j < secondMatrixColumn; j++) {

                double temp = 0;
                for (int k = 0; k < secondMatrixRow; k++) {

                    temp += firstMatrix[i][k] * secondMatrix[k][j];
                }

                if (isIntegerMatrix) {
                    System.out.print((int) temp + " ");
                } else {
                    System.out.print(temp + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}

class MatrixTranspose extends MatrixOperationFactory {

    MatrixTranspose(){
        System.out.println();
        System.out.println("1. Main diagonal\n" +
                "2. Side diagonal\n" +
                "3. Vertical line\n" +
                "4. Horizontal line");
        System.out.print("Your choice: ");

        int selection = scanner.nextInt();

        System.out.print("Enter matrix size: ");

        int row = scanner.nextInt();
        int column = scanner.nextInt();

        System.out.println("Enter matrix: ");
        double[][] matrix = readDoubleMatrix(row, column);

        double[][] result = null;

        switch (selection) {

            case 1:
                result = mainDiagonalTranspose(matrix, row, column);

                int temp = row;
                row = column;
                column = row;
                break;

            case 2:
                result = sideDiagonalTranspose(matrix, row, column);

                temp = row;
                row = column;
                column = row;
                break;

            case 3:
                result = verticalTranspose(matrix, row, column);
                break;

            case 4:
                result = horizontalTranspose(matrix, row, column);
                break;
        }

        System.out.println("The result is:");
        printMatrix(result, row, column);
        System.out.println();
    }

    private double[][] mainDiagonalTranspose(double[][] matrix, int row, int column) {

        double[][] result = new double[column][row];

        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                result[i][j] = matrix[j][i];
            }
        }
        return result;
    }

    private double[][] sideDiagonalTranspose(double[][] matrix, int row, int column) {

        double[][] result = new double[column][row];

        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                result[i][j] = matrix[row - j - 1][column - i - 1];
            }
        }
        return result;
    }

    double[][] verticalTranspose(double[][] matrix, int row, int column) {

        double[][] result = new double[row][column];

        int i = 0;
        int j = column - 1;

        while (i < j) {
            for (int k = 0; k < row; k++) {
                result[k][i] = matrix[k][j];
                result[k][j] = matrix[k][i];
            }
            i++;
            j--;
        }
        if (i == j) {
            for (int k = 0; k < row; k++) {
                result[k][i] = matrix[k][i];
            }
        }
        return result;
    }

    double[][] horizontalTranspose(double[][] matrix, int row, int column) {

        double[][] result = new double[row][column];

        int i = 0;
        int j = row - 1;

        while (i < j) {
            for (int k = 0; k < column; k++) {
                result[i][k] = matrix[j][k];
                result[j][k] = matrix[i][k];
            }
            i++;
            j--;
        }
        if (i == j) {
            for (int k = 0; k < column; k++) {
                result[i][k] = matrix[i][k];
            }
        }
        return result;
    }
}