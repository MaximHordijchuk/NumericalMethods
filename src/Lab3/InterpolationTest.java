package Lab3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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
            for (int i = 0; i < size; i++) {
                points[i] = new Point();
            }
            for (int i = 0; i < size; i++) {
                points[i].x = scanner.nextDouble();
            }
            for (int i = 0; i < size; i++) {
                points[i].y = scanner.nextDouble();
            }
            System.out.println(Interpolation.getValueNewtonReversePolynomial(1.5, points));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
