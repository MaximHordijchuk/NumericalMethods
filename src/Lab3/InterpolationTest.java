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
            Scanner pointsScanner = new Scanner(new InputStreamReader(
                    new FileInputStream("src/Lab3/resources/InterpolationInput2.txt"),"UTF-8")).useLocale(Locale.US);
            int pointsCount = pointsScanner.nextInt();
            Point[] points = new Point[pointsCount];

            PrintWriter pointsWriter = new PrintWriter(new File("src/Lab3/resources/res/points.pts"));
            for (int i = 0; i < pointsCount; i++) {
                points[i] = new Point(pointsScanner.nextDouble(), pointsScanner.nextDouble());
                pointsWriter.println(points[i].x + " " + points[i].y);
            }
            pointsWriter.close();

            //Writing points for building graphic
            double minX = points[0].x, maxX = points[pointsCount - 1].x;

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
                lagrangeWriter.println(i + " " + Interpolation.getValueLagrangePolynomial(i, points));
            }
            lagrangeWriter.close();

            PrintWriter splineWriter = new PrintWriter(new File("src/Lab3/resources/res/spline.int"));
            Spline spline = new Spline();
            spline.buildSpline(points);
            for (double i = minX; i < maxX; i += STEP) {
                splineWriter.println(i + " " + spline.interpolate(i));
            }
            splineWriter.close();

            //Writing checking points
            Scanner checkPointsScanner = new Scanner(new InputStreamReader(
                    new FileInputStream("src/Lab3/resources/CheckPoints.txt"),"UTF-8")).useLocale(Locale.US);
            int checkPointsCount = checkPointsScanner.nextInt();
            double[] checkPoints = new double[checkPointsCount];

            PrintWriter checkPointsWriter = new PrintWriter(new File("src/Lab3/resources/res/checkPoints.pts"));
            for (int i = 0; i < checkPointsCount; i++) {
                checkPoints[i] = checkPointsScanner.nextDouble();
                checkPointsWriter.println(checkPoints[i]);
            }
            checkPointsWriter.close();

            PrintWriter checkDirectWriter = new PrintWriter(new File("src/Lab3/resources/res/directNewtonCheck.pts"));
            for (int i = 0; i < checkPointsCount; i++) {
                checkDirectWriter.println(checkPoints[i] + " " +
                        Interpolation.getValueNewtonDirectPolynomial(checkPoints[i], points));
            }
            checkDirectWriter.close();

            PrintWriter checkReverseWriter = new PrintWriter(new File("src/Lab3/resources/res/reverseNewtonCheck.pts"));
            for (int i = 0; i < checkPointsCount; i++) {
                checkReverseWriter.println(checkPoints[i] + " " +
                        Interpolation.getValueNewtonReversePolynomial(checkPoints[i], points));
            }
            checkReverseWriter.close();

            PrintWriter checkLagrangeWriter = new PrintWriter(new File("src/Lab3/resources/res/lagrangeCheck.pts"));
            for (int i = 0; i < checkPointsCount; i++) {
                checkLagrangeWriter.println(checkPoints[i] + " " +
                        Interpolation.getValueLagrangePolynomial(checkPoints[i], points));
            }
            checkLagrangeWriter.close();

            PrintWriter checkSplineWriter = new PrintWriter(new File("src/Lab3/resources/res/splineCheck.pts"));
            for (int i = 0; i < checkPointsCount; i++) {
                checkSplineWriter.println(checkPoints[i] + " " +
                        spline.interpolate(checkPoints[i]));
            }
            checkSplineWriter.close();
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
