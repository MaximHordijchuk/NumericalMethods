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
            Scanner scanner = new Scanner(new InputStreamReader(new FileInputStream("src/Lab2/GaussInput1.txt"), "UTF-8"))
                    .useLocale(Locale.US);
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
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Unsupported Encoding");
        }
    }

}
