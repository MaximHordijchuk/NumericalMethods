package Lab3;

import Lab2.Matrix;
import Lab2.MatrixSolver;

/**
 * Created by max on 18.11.14.
 */
public class Interpolation {

    public class SplineTuple {
        public double a;
        public double b;
        public double c;
        public double d;
        public double x;
    }

    private static double FI(double x, int i, Point[] points) {
        double product = 1;
        for (int j = 0; j < points.length; j++) {
            if (i != j) {
                product *= (x - points[j].x) / (points[i].x - points[j].x);
            }
        }
        return product;
    }

    public static double getValueLagrangePolinom(double x, Point[] points) {
        double sum = 0;
        for (int i = 0; i < points.length; i++) {
            sum += points[i].y * FI(x, i, points);
        }
        return sum;
    }

    public static double getValueNewtonDirectPolynomial(double x, Point[] points) {
        double[] f = new double[points.length];
        for (int i = 0; i < f.length; i++) {
            f[i] = points[i].y;
        }
        double[] fNext = new double[points.length];
        double product = 1;
        double sum = 0;
        for (int i = 0; i < points.length; i++) {
            sum += f[0] * product;
            product *= (x - points[i].x);
            for (int j = 0; j < points.length - i - 1; j++) {
                fNext[j] = (f[j + 1] - f[j]) / (points[j + i + 1].x - points[j].x);
            }
            double[] fTemp = f;
            f = fNext;
            fNext = fTemp;
        }
        return sum;
    }

    public static double getValueNewtonReversePolynomial(double x, Point[] points) {
        double[] f = new double[points.length];
        for (int i = 0; i < f.length; i++) {
            f[i] = points[i].y;
        }
        double[] fNext = new double[points.length];
        double product = 1;
        double sum = 0;
        for (int i = 0; i < points.length; i++) {
            sum += f[points.length - i - 1] * product;
            product *= (x - points[points.length - i - 1].x);
            for (int j = 0; j < points.length - i - 1; j++) {
                fNext[j] = (f[j + 1] - f[j]) / (points[j + i + 1].x - points[j].x);
            }
            double[] fTemp = f;
            f = fNext;
            fNext = fTemp;
        }
        return sum;
    }

    public static SplineTuple[] buildSpline(Point[] points) {
        int N = points.length;
        SplineTuple[] splines = new SplineTuple[N];
        for (int i = 0; i < N; i++) {
            splines[i].x = points[i].x;
            splines[i].a = points[i].y;
        }
        Matrix A = new Matrix(N, N);
        Matrix b = new Matrix(N, 1);
        A.set(0, 0, 1);
        A.set(N - 1, N - 1, 1);
        for (int i = 0; i < N - 1; i++) {
            double hi  = points[i].x - points[i - 1].x;
            double hi1 = points[i + 1].x - points[i].x;
            A.set(i, i - 1, hi);
            A.set(i, i, 2 * (hi + hi1));
            A.set(i, i + 1, hi1);
            b.set(i, 0, 6 * ((points[i + 1].y - points[i].y) / hi1 - (points[i].y - points[i - 1].y) / hi));
        }
        Matrix c = MatrixSolver.getSolutionTridiagonalMatrixAlgorithm(A, b);
        for (int i = 0; i < N; i++) {
            splines[i].c = c.get(i, 0);
        }
        for (int i = N - 1; i > 0; i--) {
            double hi = points[i].x - points[i - 1].x;
            splines[i].d = (splines[i].c - splines[i - 1].c) / hi;
            splines[i].b = (points[i].y - points[i - 1].y) / hi + hi * (2 * splines[i].c + splines[i - 1].c) / 6;
        }
        return splines;
    }

    public static double getValueSplineInterpolation(double x, Point[] points) {

    }

}
