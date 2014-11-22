package Lab3;

/**
 * Created by max on 18.11.14.
 */
public class Interpolation {

    private static double F(double x, int i, Point[] points) {
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
            sum += points[i].y * F(x, i, points);
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

}
