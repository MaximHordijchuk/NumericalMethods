package Lab3;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by max on 18.11.14.
 */
public class InterpolationTest {

    public static final double STEP = 0.1;

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new InputStreamReader(
                    new FileInputStream("src/Lab3/resources/InterpolationInput1.txt"),"UTF-8")).useLocale(Locale.US);
            int size = scanner.nextInt();
            Point[] points = new Point[size];

            PrintWriter pointsWriter = new PrintWriter(new File("src/Lab3/resources/res/points.pts"));
            for (int i = 0; i < size; i++) {
                points[i] = new Point(scanner.nextDouble(), scanner.nextDouble());
                pointsWriter.println(points[i].x + " " + points[i].y);
            }
            pointsWriter.close();

            double minX = points[0].x, maxX = points[size - 1].x;

            PrintWriter directWriter = new PrintWriter(new File("src/Lab3/resources/res/directNewton.int"));
            for (double i = minX; i < maxX; i += STEP) {
                directWriter.println(i + " " + Interpolation.getValueNewtonDirectPolynomial(i, points));
            }
            directWriter.close();

            PrintWriter reverseWriter = new PrintWriter(new File("src/Lab3/resources/res/reverseNewton.int"));
            for (double i = minX; i < maxX; i += STEP) {
                reverseWriter.println(i + " " + Interpolation.getValueNewtonReversePolynomial(i, points));
            }
            reverseWriter.close();

            PrintWriter lagrangeWriter = new PrintWriter(new File("src/Lab3/resources/res/lagrange.int"));
            for (double i = minX; i < maxX; i += STEP) {
                lagrangeWriter.println(i + " " + Interpolation.getValueLagrangePolinom(i, points));
            }
            lagrangeWriter.close();

            PrintWriter splineWriter = new PrintWriter(new File("src/Lab3/resources/res/spline.int"));
            Spline spline = new Spline();
            spline.buildSpline(points);
            for (double i = minX; i < maxX; i += STEP) {
                splineWriter.println(i + " " + spline.interpolate(i));
            }
            splineWriter.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
