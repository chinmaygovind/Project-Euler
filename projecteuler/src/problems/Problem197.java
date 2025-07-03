package problems;

import java.math.BigDecimal;
import java.util.Arrays;

// A Recursively Defined Sequence.
public class Problem197 {
    public static void main(String[] args) {
        int u0 = -1;
        double uKOdd = u0;
        double uKEven = u0;
        for (int k = 0; k < 1000; k++) {
            uKOdd = f(uKEven);
            uKEven = f(uKOdd);
            System.out.println(uKOdd + ", " + uKEven + " -> " + (uKOdd + uKEven));
        }
        // okay it just converges quickly lmao
        System.out.println("The sum of u_n + u_n+1 for n = 10^12 is: " + (uKOdd + uKEven));
    }

    public static double f(double x) {
        return 0.000000001 * Math.floor(Math.pow(2, 30.403243784 - x * x));
    }


}
