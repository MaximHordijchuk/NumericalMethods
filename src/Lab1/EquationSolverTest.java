package Lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by max on 24.10.14.
 */
public class EquationSolverTest {

    public static void main(String[] args) {

        EquationSolver equationSolver = null;
        try {
            equationSolver = new EquationSolver(new PrintWriter(new File("/home/max/output")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("%10.7f", equationSolver.bisectionMethod()));
        System.out.println(String.format("%10.7f", equationSolver.newtonMethod()));
        System.out.println(String.format("%10.7f", equationSolver.relaxationMethod()));
        System.out.println(String.format("%10.7f", equationSolver.simpleIterationMethod()));
    }

}
