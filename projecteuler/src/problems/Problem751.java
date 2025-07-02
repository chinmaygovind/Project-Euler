package problems;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

// Concatentation Coincidence.
public class Problem751 {


    public static void main(String[] args) {
        BigDecimal tau = new BigDecimal("2.00000000000000000000000000000000000000000");
        int precision = 1;
        while (precision <= 24) {
            String tauString = tau.toString().substring(0, 2 + precision);
            String resString = concat(tau).toString().substring(0, 2 + precision);
            if (tauString.equals(resString)) {
                // System.out.println(tauString);
                precision++;
            } else {
                tau = tau.add(BigDecimal.TEN.pow(-precision, new MathContext(30)));
            }
        }
        System.out.printf("The only value for Theta such that a1 = 2 and Tau = Theta is: %.24f\n", tau);
    }

    public static String concat(BigDecimal t) {
        List<BigInteger> seq = getSequence(t, t.toString().length());
        StringBuilder res = new StringBuilder(seq.get(0).toString());
        res.append(".");
        int i = 1;
        while (res.length() < t.toString().length()) {
            res.append(seq.get(i));
            i++;
        }
        return res.toString();
    }
    public static List<BigInteger> getSequence(BigDecimal t, int terms) {
        BigDecimal[] b = new BigDecimal[terms];
        BigInteger[] a = new BigInteger[terms];
        b[0] = t;
        a[0] = t.setScale(0, RoundingMode.FLOOR).toBigInteger();
        for (int i = 1; i < b.length; i++) {
            BigDecimal floored = b[i-1].setScale(0, RoundingMode.FLOOR);
            b[i] = floored.multiply(b[i-1].subtract(floored).add(BigDecimal.ONE));
            a[i] = b[i].setScale(0, RoundingMode.FLOOR).toBigInteger();
        }
        return Arrays.asList(a);
    }

}
