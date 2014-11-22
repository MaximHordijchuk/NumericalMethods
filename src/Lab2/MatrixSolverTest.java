package Lab2;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by max on 10.11.14.
 */
public class MatrixSolverTest {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new InputStreamReader(new FileInputStream("src/Lab2/resources/GaussInput2.txt"),
                    "UTF-8")).useLocale(Locale.US);
            int size = scanner.nextInt();
            Matrix A = new Matrix(size, size);
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    double value = scanner.nextDouble();
                    A.set(i, j, value);
                }
            }
            Matrix b = new Matrix(size, 1);
            for (int i = 0; i < size; i++) {
                double value = scanner.nextDouble();
                b.set(i, 0, value);
            }
            Matrix resultMatrix = MatrixSolver.getSolutionGauss(A, b);
            resultMatrix.printMatrix(new PrintWriter(new OutputStreamWriter(System.out, "UTF-8")));
            System.out.println();
            Matrix L = new Matrix(size, size);
            Matrix U = new Matrix(size, size);
            MatrixSolver.makeLUDecomposition(A, L, U);
            L.printMatrix(new PrintWriter(new OutputStreamWriter(System.out, "UTF-8")));
            System.out.println();
            U.printMatrix(new PrintWriter(new OutputStreamWriter(System.out, "UTF-8")));
            System.out.println();
            System.out.println(MatrixSolver.getDeterminant(A));
            System.out.println();
            Matrix inverseMatrix = MatrixSolver.getInverseMatrix(A);
            inverseMatrix.printMatrix(new PrintWriter(new OutputStreamWriter(System.out, "UTF-8")));
            System.out.println();
            System.out.println(MatrixSolver.getConditionNumber(A));
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Unsupported Encoding");
        }
        /*try {
            Scanner scanner = new Scanner(new InputStreamReader(
                    new FileInputStream("src/Lab2/resources/SquareRootInput1.txt"),"UTF-8")).useLocale(Locale.US);
            int size = scanner.nextInt();
            Matrix A = new Matrix(size, size);
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    double value = scanner.nextDouble();
                    A.set(i, j, value);
                }
            }
            Matrix b = new Matrix(size, 1);
            for (int i = 0; i < size; i++) {
                double value = scanner.nextDouble();
                b.set(i, 0, value);
            }
            Matrix resultMatrix = MatrixSolver.getSolutionSquareRootMethod(A, b);
            resultMatrix.printMatrix(new PrintWriter(new OutputStreamWriter(System.out, "UTF-8")));
            System.out.println();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Scanner scanner = new Scanner(new InputStreamReader(new FileInputStream("src/Lab2/resources/TridiagonalInput1.txt"),
                    "UTF-8")).useLocale(Locale.US);
            int size = scanner.nextInt();
            Matrix A = new Matrix(size, size);
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    double value = scanner.nextDouble();
                    A.set(i, j, value);
                }
            }
            Matrix b = new Matrix(size, 1);
            for (int i = 0; i < size; i++) {
                double value = scanner.nextDouble();
                b.set(i, 0, value);
            }
            Matrix resultMatrix = MatrixSolver.getSolutionTridiagonalMatrixAlgorithm(A, b);
            resultMatrix.printMatrix(new PrintWriter(new OutputStreamWriter(System.out, "UTF-8")));
            System.out.println();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
        try {
            Scanner scanner = new Scanner(new InputStreamReader(
                    new FileInputStream("src/Lab2/resources/JacobiInput2.txt"),"UTF-8")).useLocale(Locale.US);
            int size = scanner.nextInt();
            Matrix A = new Matrix(size, size);
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    double value = scanner.nextDouble();
                    A.set(i, j, value);
                }
            }
            Matrix b = new Matrix(size, 1);
            for (int i = 0; i < size; i++) {
                double value = scanner.nextDouble();
                b.set(i, 0, value);
            }
            Matrix resultMatrix = MatrixSolver.getSolutionJacobiMethod(A, b);
            resultMatrix.printMatrix(new PrintWriter(new OutputStreamWriter(System.out, "UTF-8")));
            System.out.println();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
