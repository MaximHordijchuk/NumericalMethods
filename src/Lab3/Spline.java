package Lab3;

import Lab2.Matrix;
import Lab2.MatrixSolver;

/**
 * Created by max on 20.11.14.
 */
public class Spline {

    public class SplineTuple {
        public double a;
        public double b;
        public double c;
        public double d;
        public double x;
    }

    SplineTuple[] splines;

    public void buildSpline(Point[] points) {
        int N = points.length;
        splines = new SplineTuple[N];
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
    }

    public double interpolate(double x) {
        if (splines == null) {
            return Double.NaN;
        }
        int N = splines.length;
        SplineTuple spline;
        if (x <= splines[0].x) {
            spline = splines[0];
        } else if (x >= splines[N - 1].x) {
            spline = splines[N - 1];
        } else {
            int L = 0;
            int R = N - 1;
            while (L + 1 < R) {
                int m = L + (R - L) / 2;
                if (x <= splines[m].x) {
                    R = m;
                } else {
                    L = m;
                }
            }
            spline = splines[R];
        }
        double dx = x - spline.x;
        return spline.a + (spline.b + (spline.c / 2 + spline.d * dx / 6) * dx) * dx;
    }

}
