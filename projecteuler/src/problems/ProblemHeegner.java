package problems;


import util.Numbers;

import java.math.BigDecimal;
import java.math.MathContext;

// Problem Heegner
public class ProblemHeegner {
    // guess and check failed lmao

    // oh these fuckasses think they're so funny

    // this some bootshoot
    private static int N = 1000;
    public static void main(String[] args) {
//        double delta2 = 1;
//        int bestA = 0;
//        int bestB = 0;
//        cos(1, 0);
//        cos(Math.PI, 0);
//        cos(0, 1);
//        for (int a = -N; a <= N; a++) {
//            for (int b = -N; b <= N; b++) {
//                double r = Math.sqrt(a * a + b * b);
//                if (r > N) {
//                    continue;
//                }
//                double theta = Math.atan2(b, a);
//                double sqrtR = Math.sqrt(r);
//                theta /= 2;
//                double newA = sqrtR * Math.cos(theta);
//                double newB = sqrtR * Math.sin(theta);
////                System.out.printf("sqrt(%d + %di) = %f + %fi\n", a, b, newA, newB);
//                if (Math.abs(newA - Math.round(newA)) + Math.abs(newB - Math.round(newB)) < 0.00001) {
//                    System.out.println("Skipping square number " + a + " + " + b + "i");
//                    continue;
//                }
//                double[] cos = cos(newA * Math.PI, newB * Math.PI);
//                int closestX = (int) Math.round(cos[0]);
//                int closestY = (int) Math.round(cos[1]);
//                double delta2new = Math.abs(Math.pow(closestX - cos[0], 2) + Math.pow(closestY - cos[1], 2));
//                if (delta2new < delta2) {
//                    delta2 = delta2new;
//                    bestA = a;
//                    bestB = b;
//                }
//            }
//        }
//        System.out.printf("The value of n (nonsquare) for which cos(pi * sqrt(n)) " +
//                "is closest to an integer is for abs(n) <= 1000: %d + %di",
//                bestA, bestB);

        // https://en.wikipedia.org/wiki/Heegner_number, you find that e^(pi sqrt163) is almost an integer.
        // working backwards,
        System.out.println("\"The value of n (nonsquare) for which cos(pi * sqrt(n)) is closest to an integer is for abs(n) <= 1000: -163");
    }

    public static double[] cos(double a, double b) {
        // cos(x) = (e^ix + e^-ix) / 2
        //        = e^(ai - b) + e^(-ai + b) / 2
        //        = e^-b * e^ai + e^b * e^-ai / 2
        //        =
        double x = (Math.exp(-b) * Math.cos(a) + Math.exp(b) * Math.cos(-a)) / 2;
        double y = (Math.exp(-b) * Math.sin(a) + Math.exp(b) * Math.sin(-a)) / 2;
//        System.out.printf("cos(%f, %f) = %f + %fi\n", a, b, x, y);
        return new double[]{x, y};

    }
}

