package Lab2;

import java.util.Random;

import static java.lang.Math.*;

/**
 * Created by max on 10.11.14.
 */
public class MatrixSolver {

    public static final double EPS = 1e-10;

    public static Matrix getSolutionGauss(Matrix A, Matrix b) throws IllegalArgumentException {
        A = new Matrix(A);
        b = new Matrix(b);
        int N = A.getRows();
        for (int i = 0; i < N - 1; i++) {
            int maxRow = i;
            double maxElement = A.get(i, i);
            for (int j = i + 1; j < N; j++) {
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
            for (int j = i; j < N; j++) {
                A.set(i, j, A.get(i, j) / maxElement);
            }
            b.set(i, 0, b.get(i, 0) / maxElement);
            for (int j = i + 1; j < N; j++) {
                b.set(j, 0, b.get(j, 0) - A.get(j, i) * b.get(i, 0));
                for (int k = N - 1; k >= i ; k--) {
                    A.set(j, k, A.get(j, k) - A.get(j, i) * A.get(i, k));
                }
            }
        }
        //TODO: DIAGONAL ELEMENTS
        return getSolutionReverseSubstitution(A, b);
    }

    public static Matrix getSolutionReverseSubstitution(Matrix A, Matrix b) {
        int N = A.getRows();
        Matrix x = new Matrix(N, 1);
        for (int i = N - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = N - 1; j > i; j--) {
                sum += A.get(i, j) * x.get(j, 0);
            }
            x.set(i, 0, (b.get(i, 0) - sum) / A.get(i, i));
        }
        return x;
    }

    public static Matrix getSolutionDirectSubstitution(Matrix A, Matrix b) {
        int N = A.getRows();
        Matrix x = new Matrix(N, 1);
        for (int i = 0; i < N; i++) {
            double sum = 0;
            for (int j = 0; j < i; j++) {
                sum += A.get(i, j) * x.get(j, 0);
            }
            x.set(i, 0, (b.get(i, 0) - sum) / A.get(i, i));
        }
        return x;
    }

    public static void makeLUDecomposition(Matrix A, Matrix L, Matrix U) {
        //Initialization
        int N = A.getRows();
        A = new Matrix(A);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    L.set(i, j, 1);
                } else {
                    L.set(i, j, 0);
                }
                U.set(i, j, 0);
            }
        }
        //Decomposition
        for (int k = 0; k < N; k++) {
            U.set(k, k, A.get(k, k));
            for (int i = k + 1; i < N; i++) {
                L.set(i, k, A.get(i, k) / U.get(k, k));
                U.set(k, i, A.get(k, i));
            }
            for (int i = k + 1; i < N; i++) {
                for (int j = k + 1; j < N; j++) {
                    A.set(i, j, A.get(i, j) - L.get(i, k) * U.get(k, j));
                }
            }
        }
    }

    public static double getDeterminant(Matrix A) {
        int N = A.getRows();
        Matrix L = new Matrix(N, N);
        Matrix U = new Matrix(N, N);
        makeLUDecomposition(A, L, U);
        double determinant = 1;
        for (int i = 0; i < N; i++) {
            determinant *= U.get(i, i);
        }
        return determinant;
    }

    public static Matrix getInverseMatrix(Matrix A) {
        int N = A.getRows();
        Matrix L = new Matrix(N, N);
        Matrix U = new Matrix(N, N);
        makeLUDecomposition(A, L, U);
        Matrix inverseMatrix = new Matrix(N, 0);
        for (int i = 0; i < N; i++) {
            Matrix e = Matrix.getIdentityVector(N, i);
            Matrix y = getSolutionDirectSubstitution(L, e);
            Matrix x = getSolutionReverseSubstitution(U, y);
            inverseMatrix = inverseMatrix.addColumn(x);
        }
        return inverseMatrix;
    }

    public static double getConditionNumber(Matrix A) {
        double matrixNorm = getMatrixNorm(A);
        Matrix inverseMatrix = getInverseMatrix(A);
        double inverseMatrixNorm = getMatrixNorm(inverseMatrix);
        return matrixNorm * inverseMatrixNorm;
    }

    public static double getMatrixNorm(Matrix A) {
        double matrixNorm = 0;
        for (int k = 0; k < A.getCols(); k++) {
            double sum = 0;
            for (int j = 0; j < A.getRows(); j++) {
                sum += abs(A.get(j, k));
            }
            if (sum > matrixNorm) {
                matrixNorm = sum;
            }
        }
        return matrixNorm;
    }

    public static Matrix getSolutionTridiagonalMatrixAlgorithm(Matrix A, Matrix b) {
        int N = A.getRows();
        Matrix alpha = new Matrix(N, 1);
        Matrix beta = new Matrix(N, 1);
        alpha.set(1, 0, -A.get(0, 1) / A.get(0, 0));
        beta.set(1, 0, b.get(0, 0) / A.get(0, 0));
        for (int i = 1; i < N - 1; i++) {
            double ai = A.get(i, i - 1);
            double ci = -A.get(i, i);
            double bi = A.get(i, i + 1);
            alpha.set(i + 1, 0, bi / (ci - ai * alpha.get(i, 0)));
            beta.set(i + 1, 0, (-b.get(i, 0) + ai * beta.get(i, 0)) / (ci - ai * alpha.get(i, 0)));
        }
        Matrix y = new Matrix(N, 1);
        y.set(N - 1, 0, (-b.get(N - 1, 0) + A.get(N - 1, N - 2) * beta.get(N - 1, 0)) /
                (-A.get(N - 1, N - 1) - A.get(N - 1, N - 2) * alpha.get(N - 1, 0)));
        for (int i = N - 2; i >= 0; i--) {
            y.set(i, 0, alpha.get(i + 1, 0) * y.get(i + 1, 0) + beta.get(i + 1, 0));
        }
        return y;
        //TODO: size > 1 && tridiagonal
        //TODO: матриця невироджена (перевірити в усіх методах, якщо буде час)
    }

    public static Matrix getSolutionSquareRootMethod(Matrix A, Matrix b) {
        int N = A.getRows();
        Matrix S = new Matrix(N, N);
        Matrix D = new Matrix(N, N);
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                if (i == j) {
                    double sum = 0;
                    for (int k = 0; k < i; k++) {
                        sum += S.get(k, i) * S.get(k, i) * D.get(k, k);
                    }
                    S.set(i, i, sqrt(abs(A.get(i, i) - sum)));
                    D.set(i, i, signum(A.get(i, i) - sum));
                } else {
                    double sum = 0;
                    for (int k = 0; k < i; k++) {
                        sum += S.get(k, i) * D.get(k, k) * S.get(k, j);
                    }
                    S.set(i, j, (A.get(i, j) - sum) / (S.get(i, i) * D.get(i, i)));
                }
            }
        }
        Matrix ST = S.getTransposed();
        Matrix STD = ST.multiply(D);
        Matrix y = getSolutionDirectSubstitution(STD, b);
        Matrix x = getSolutionReverseSubstitution(S, y);
        return x;
        //TODO: перевірити, чи матриця є симетричною
    }

    public static Matrix getSolutionJacobiMethod(Matrix A, Matrix b) {
        int N = A.getRows();
        Matrix sumA1A2 = new Matrix(A);
        Matrix minusInverseD = new Matrix(N, N);
        for (int i = 0; i < N; i++) {
            sumA1A2.set(i, i, 0);
            minusInverseD.set(i, i, -1 / A.get(i, i));
        }
        Matrix B = new Matrix(N, N);
        Matrix C = new Matrix(N, 1);
        for (int i = 0; i < N; i++) {
            C.set(i, 0, minusInverseD.get(i, i) * b.get(i, 0));
            for (int j = 0; j < N; j++) {
                B.set(i, j, minusInverseD.get(i, i) * sumA1A2.get(i, j));
            }
        }
        Matrix x = new Matrix(N, 1);
        Random random = new Random();
        final int MIN_VALUE = 10;
        final int MAX_VALUE = 100;
        for (int i = 0; i < N; i++) {
            x.set(i, 0, random.nextInt(MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE);
        }
        final double EPS = 1e-4;
        Matrix xNew = new Matrix(x);
        do {
            x = new Matrix(xNew);
            for (int i = 0; i < N; i++) {
                double xNext = 0;
                for (int j = 0; j < N; j++) {
                    xNext += B.get(i, j) * x.get(j, 0);
                }
                xNew.set(i, 0, xNext - C.get(i, 0));
            }
        } while (maxDifference(x, xNew) > EPS);
        return xNew;
        //TODO: перевірити умови збіжності
    }

    private static double maxDifference(Matrix A, Matrix B) {
        double maxDifference = 0;
        for (int i = 0; i < A.getRows(); i++) {
            for (int j = 0; j < A.getCols(); j++) {
                double currentDifference = abs(A.get(i, j) - B.get(i, j));
                if (currentDifference > maxDifference) {
                    maxDifference = currentDifference;
                }
            }
        }
        return maxDifference;
        //TODO: size(A) == size(B) ??
    }

}
