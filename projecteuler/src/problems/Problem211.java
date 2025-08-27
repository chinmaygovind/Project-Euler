package problems;

import util.Numbers;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

// Divisor Square Sum.
public class Problem211 {

    public static void main(String[] args) {
        long total = 1;
        for (long i = 4; i < 64_000_000; i++) {
//            if (i % 10000 == 0) System.out.println(i);
            long s2 = 0;
            for (long f : Numbers.getFactors(i)) {
                s2 += f * f;
            }
            long sqrt = (long) Math.sqrt(s2);
            if (sqrt * sqrt == s2) {
                total += i;
                System.out.println(i + ": " + Numbers.pollardRho(i));
            }
        }
        System.out.println(total);
    }


}
