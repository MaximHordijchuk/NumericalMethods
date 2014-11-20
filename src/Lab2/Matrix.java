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

    public static Matrix getIdentityVector(int size, int index) {
        Matrix e = new Matrix(size, 1);
        e.set(index, 0, 1);
        return e;
        //TODO: index < size ??
    }

    public Matrix addColumn(Matrix b) {
        Matrix resultMatrix = new Matrix(rows, cols + b.getCols());
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                resultMatrix.set(i, j, A[i][j]);
            }
        }
        for (int i = 0; i < b.getRows(); i++) {
            for (int j = 0; j < b.getCols(); j++) {
                resultMatrix.set(i, cols + j, b.get(i, j));
            }
        }
        return resultMatrix;
        //TODO: size(A) == size(b) ??
    }
    
    public Matrix getTransposed() {
        Matrix transposedMatrix = new Matrix(cols, rows);
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                transposedMatrix.set(i, j, A[j][i]);
            }
        }
        return transposedMatrix;
    }

    public Matrix multiply(Matrix B) {
        Matrix resultMatrix = new Matrix(rows, B.getCols());
        for (int i = 0; i < resultMatrix.getRows(); i++) {
            for (int j = 0; j < resultMatrix.getCols(); j++) {
                double sum = 0;
                for (int k = 0; k < cols; k++) {
                    sum += A[i][k] * B.get(k, j);
                }
                resultMatrix.set(i, j, sum);
            }
        }
        return resultMatrix;
        //TODO: перевірити умови
    }

    public Matrix plus(Matrix B) {
        Matrix sumMatrix = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sumMatrix.set(i, j, A[i][j] + B.get(i, j));
            }
        }
        return sumMatrix;
        //TODO: перевірити умови
    }
}
