package problems;

import util.Rational;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

//Concealed Square.
public class Problem206 {
    // low low hanging fruit
    public static void main(String[] args) {
        // Find the unique positive integer whose square has the form 1_2_3_4_5_6_7_8_9_0,
        // where each “_” is a single digit.
        // n^2 = 1_2_3_4_5_6_7_8_9_0
        // note that since n^2 ends in 0, n must end in 0, and therefore n^2 ends in 00
        // n^2 = 1_2_3_4_5_6_7_8_900
        // n^2 approx = 1.X2e18 -> n approx = 1.xe9
        // n = 1_______(3/7)0

        for (long n = 1000000000; n < 2000000000; n += 10) {
            if (n % 100 != 30 && n % 100 != 70) continue;
            String n2 = String.valueOf(n * n);
            if (n2.length() == 19 &&
                    n2.charAt(0) == '1' &&
                    n2.charAt(2) == '2' &&
                    n2.charAt(4) == '3' &&
                    n2.charAt(6) == '4' &&
                    n2.charAt(8) == '5' &&
                    n2.charAt(10) == '6' &&
                    n2.charAt(12) == '7' &&
                    n2.charAt(14) == '8' &&
                    n2.charAt(16) == '9' &&
                    n2.charAt(18) == '0') {
                System.out.printf("sqrt(%d) = %d", n * n, n);
                break;
            }
        }

    }


}
