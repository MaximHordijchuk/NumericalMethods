package Lab1;

import java.io.*;
import static java.lang.Math.*;

/**
 * Created by max on 24.10.14.
 */

public class EquationSolver {

    public static final double EPS = 1e-6;
    public static final double a = 0;
    public static final double b = 0.5;
    public static final double m1 = abs(f_(b));
    public static final double M1 = abs(f_(a));
    public PrintWriter resultFile;
    private int iteration = 0;

    public EquationSolver() throws UnsupportedEncodingException {
        this(new PrintWriter(new OutputStreamWriter(System.out, "UTF-8")));
    }

    public EquationSolver(PrintWriter resultFile) {
        this.resultFile = resultFile;
    }

    public static double f(double x) {
        return x*x*x + sin(x) - 12*x + 1;
    }

    public static double f_(double x) {
        return 3*x*x + cos(x) - 12;
    }

    public static double relaxFi(double x) {
        double tau = 2 / (m1 + M1);
        return x + tau*f(x);
    }

    public static double fi(double x) {
        return x + 0.05*f(x);
    }

    private void printNextIteration(double x) {
        iteration++;
        resultFile.println(iteration + " " + String.format("%10.7" + "f", x));
    }

    private void setIteratorToZero() {
        iteration = 0;
    }

    public double bisectionMethod() {
        resultFile.println("Bisection method");
        double a = this.a;
        double b = this.b;
        setIteratorToZero();
        double x = Double.NaN;
        while (b - a > EPS) {
            x = (a + b) / 2;
            printNextIteration(x);
            if (signum(f(a)) * signum(f(x)) < 0) {
                b = x;
            } else {
                a = x;
            }
        }
        resultFile.flush();
        return x;
    }

    public double simpleIterationMethod() {
        resultFile.println("Simple iteration method");
        double x0 = a;
        setIteratorToZero();
        double x = fi(x0);
        printNextIteration(x);
        while (abs(x - x0) > EPS) {
            x0 = x;
            x = fi(x0);
            printNextIteration(x);
        }
        resultFile.flush();
        return x;
    }

    public double newtonMethod() {
        resultFile.println("Newton method");
        double x0 = a;
        setIteratorToZero();
        double x = x0 - f(x0) / f_(x0);
        printNextIteration(x);
        while (abs(x - x0) > EPS) {
            x0 = x;
            x = x0 - f(x0) / f_(x0);
            printNextIteration(x);
        }
        resultFile.flush();
        return x;
    }

    public double relaxationMethod() {
        resultFile.println("Relaxation method");
        double x0 = a;
        setIteratorToZero();
        double x = relaxFi(x0);
        printNextIteration(x);
        while (abs(x - x0) > EPS) {
            x0 = x;
            x = relaxFi(x0);
            printNextIteration(x);
        }
        resultFile.flush();
        return x;
    }

}
