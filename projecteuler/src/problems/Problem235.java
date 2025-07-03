package problems;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

// An Arithmetic Geometric Sequence.
public class Problem235 {

    private static MathContext mc = new MathContext(100);
    private static BigDecimal target = BigDecimal.valueOf(-600_000_000_000L);
    private static BigDecimal tolerance = BigDecimal.valueOf(0.00001);

    private static DecimalFormat df = new DecimalFormat("#.############E0");
    public static void main(String[] args) {
        // u(k) = (900 - 3k) r^(k - 1)
        // u(1) = 897 * 1
        // u(2) = 894 * r
        // a = 897, d = -3
        // b = 1, r = r
        // Sn = (ab - (a + nd)br^n) / (1 - r) + (dbr(1 - r^n)) / (1 - r)^2
        int a = 897;
        int d = -3;
        int b = 1;
        int n = 5000;
        BigDecimal rLow = BigDecimal.ONE;
        BigDecimal rHigh = BigDecimal.valueOf(1.1);
        BigDecimal r = rLow.add(rHigh).divide(BigDecimal.TWO, mc);
        BigDecimal Sn = Sn(a, d, b, n, r);
        while (Sn.subtract(target).abs().compareTo(tolerance) > 0) {
//            System.out.println(r + ": " + df.format(Sn) + " | " + df.format(manualSn(a, d, b, n, r)));
//            System.out.println(r + ": " + df.format(Sn));
            if (Sn.compareTo(target) < 0) { // r too big
                rHigh = r;
            } else {
                rLow = r;
            }
            r = rLow.add(rHigh).divide(BigDecimal.TWO, mc);
            Sn = Sn(a, d, b, n, r);
        }
        System.out.printf("The value of r for which s(5000) = -6e11 is: %.12f", r);

    }

    public static BigDecimal Sn(long a, long d, long b, int n, BigDecimal r) {
        BigDecimal invR = BigDecimal.ONE.subtract(r);
        BigDecimal leftTerm = BigDecimal.valueOf(a * b).subtract(BigDecimal.valueOf(b * (a + n * d)).multiply(r.pow(n)))
                .divide(invR, mc);
        BigDecimal rightTerm = BigDecimal.valueOf(d * b).multiply(r)
                .multiply(BigDecimal.ONE.subtract(r.pow(n)))
                .divide(invR.pow(2), mc);
//        System.out.println("fuck: " + leftTerm + " ass: " + rightTerm);
        return leftTerm.add(rightTerm);
    }

    public static BigDecimal manualSn(long a, long d, long b, int n, BigDecimal r) {
        BigDecimal sum = BigDecimal.ZERO;
        for (int k = 0; k < n; k++) {
            sum = sum.add(BigDecimal.valueOf(b * (a + d * k)).multiply(r.pow(k)));
//            System.out.println("fuckass : " + BigDecimal.valueOf(b * (a + d * k)).multiply(r.pow(k)));
        }
        return sum;
    }

}
