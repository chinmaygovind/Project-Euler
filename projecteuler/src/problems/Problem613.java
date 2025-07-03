package problems;


import util.Numbers;

import java.util.ArrayList;
import java.util.HashSet;

// Pythagorean Ant.
public class Problem613 {
    public static void main(String[] args) {
        // integrate over triangle (0, 0), (40, 0), (40, 30)
        // for each point (x, y)
        // (0, 0) and (40, 30)
        // can find that angle with vectors
        // v1 = (-x, -y), v2 = (40 - x, 30 - y)
        // v1 * v2 = |v1||v2|cosTheta
        // -x(40 - x) + -y(30 - y) = sqrt(x^2 + y^2)sqrt((40 - x)^2 + (30 - y)^2)cosTheta
        // Chunks: 8.05306368E10
        // The probability that the ant leaves along the longest side is: 0.3916720805 with step = 0.0001220703
        // The probability that the ant leaves along the longest side is: 0.3916723078 with step = 0.0001000000
        // The probability that the ant leaves along the longest side is: 0.3916723837 with step = 0.0000500000

        // so at least its converging
        double step = 0.000025;
        while (step > 0.0000001) {
            System.out.printf("The probability that the ant leaves along the longest side is: %.10f with step = %.10f\n",
                    getIntegral(step), step);
            step /= 2;
        }
    }

    private static double getIntegral(double step) {
        double integral = 0;
        double dA = step * step;
        System.out.println("Chunks: " + (1200 / (step * step)));
        int lastInt = 0;
        for (double x = 0; x < 40; x += step) {
            if (x * 100 > lastInt) {
                System.out.println("x = " + x);
                lastInt = (int) (x * 100) + 1;
            }
            for (double y = 0; y < 3 * x / 4; y += step) {
                // v1 = (-x, -y)
                // v2 = (40 - x, 30 - y)
                // v1 * v2 =
                double x2 = 40 - x;
                double y2 = 30 - y;
                double dot = -x * x2 + -y * y2;
                double v1 = Math.sqrt(x * x + y * y);
                double v2 = Math.sqrt(x2 * x2 + y2 * y2);
//                System.out.println(cosTheta);
                double theta = Math.acos(Math.clamp(dot / (v1 * v2), -1, 1));
                double p = theta / (2 * Math.PI);
                integral += p * dA;
            }
        }
        integral = integral / 600.0;
        return integral;
    }
}

