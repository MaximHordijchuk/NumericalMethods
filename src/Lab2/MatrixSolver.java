package Lab2;

import static java.lang.Math.*;

/**
 * Created by max on 10.11.14.
 */
public class MatrixSolver {

    public static final double EPS = 1e-10;

    public static Matrix getSolutionGauss(Matrix A, Matrix b) throws IllegalArgumentException {
        A = new Matrix(A);
        b = new Matrix(b);
        for (int i = 0; i < A.getRows(); i++) {
            int maxRow = i;
            double maxElement = A.get(i, i);
            for (int j = i + 1; j < A.getRows(); j++) {
                if (abs(A.get(j, i)) > abs(maxElement)) {
                    maxElement = A.get(j, i);
                    maxRow = j;
                }
            }
            if (abs(maxElement) < EPS) {
                throw new IllegalArgumentException("Can't find not 0 element, bad matrix");
            }
            A.swap(i, maxRow);
            b.swap(i, maxRow);
            for (int j = i; j < A.getRows(); j++) {
                A.set(i, j, A.get(i, j) / maxElement);
            }
            b.set(i, 0, b.get(i, 0) / maxElement);

            for (int j = i + 1; j < A.getRows(); j++) {
                for (int k = A.getCols() - 1; k >= i ; k--) {
                    A.set(j, k, A.get(j, k) - A.get(j, i) * A.get(j - 1, k));
                }
                b.set(j, 0, b.get(j, 0) - A.get(j, i) * b.get(j - 1, 0));
            }
        }
        return b;
    }

}
