package Lab2;

import java.io.PrintWriter;

/**
 * Created by max on 10.11.14.
 */
public class Matrix {

    private double[][] A; //matrix
    private int rows;
    private int cols;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        A = new double[rows][cols];
    }

    public Matrix(Matrix m) {
        rows = m.getRows();
        cols = m.getCols();
        A = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                A[i][j] = m.get(i, j);
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public double get(int i, int j) {
        return A[i][j];
        //TODO: throw exception
    }

    public void set(int i, int j, double value) {
        A[i][j] = value;
        //TODO: throw exception
    }

    public void swap(int row1, int row2) {
        double[] tempRow = A[row1];
        A[row1] = A[row2];
        A[row2] = tempRow;
    }

    public void printMatrix(PrintWriter writer) {
        for (int i = 0; i < rows; i++) {
            writer.print(A[i][0]);
            for (int j = 1; j < cols; j++) {
                writer.print(" " + A[i][j]);
            }
            writer.println();
        }
        writer.flush();
    }
}
