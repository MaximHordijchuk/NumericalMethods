package Lab3;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by max on 18.11.14.
 */
public class InterpolationTest {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new InputStreamReader(
                    new FileInputStream("src/Lab3/resources/InterpolationInput1.txt"),"UTF-8")).useLocale(Locale.US);
            int size = scanner.nextInt();
            Point[] points = new Point[size];

            PrintWriter pts = new PrintWriter(new File("src/Lab3/resources/res/points.pts"));
            for (int i = 0; i < size; i++) {
                points[i] = new Point(scanner.nextDouble(), scanner.nextDouble());
                pts.println(points[i].x + " " + points[i].y);
            }
            pts.close();
            double step = 0.1;
            double minx = points[0].x, maxx = points[size - 1].x;

            PrintWriter reverse = new PrintWriter(new File("src/Lab3/resources/res/reverse.int"));
            for (double i = minx; i < maxx; i+=step) {
                reverse.println(i + " " + Interpolation.getValueNewtonReversePolynomial(i, points));
            }
            reverse.close();

            PrintWriter direct = new PrintWriter(new File("src/Lab3/resources/res/direct.int"));
            for (double i = minx; i < maxx; i+=step) {
                direct.println(i + " " + Interpolation.getValueNewtonDirectPolynomial(i, points));
            }
            direct.close();

            PrintWriter lagrange = new PrintWriter(new File("src/Lab3/resources/res/lagr.int"));
            for (double i = minx; i < maxx; i+=step) {
                lagrange.println(i + " " + Interpolation.getValueNewtonDirectPolynomial(i, points));
            }
            lagrange.close();

            PrintWriter spline = new PrintWriter(new File("src/Lab3/resources/res/spline.int"));
            Spline spl = new Spline();
            spl.buildSpline(points);
            for (double i = minx; i < maxx; i+=step) {
                spline.println(i + " " + spl.interpolate(i));
            }
            spline.close();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
