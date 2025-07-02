package problems;

import util.Numbers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// Resilience.
public class Problem243 {
    private static int N = 1000_000;
    private static MathContext mc = new MathContext(50);
    private static final BigDecimal target = new BigDecimal("15499").divide(new BigDecimal("94744"), new MathContext(50));

    // phi(n) / n - 1
    public static void main(String[] args) {
        ArrayList<Integer> primes = Numbers.generatePrimes(N);
        List<Integer> myDenom = List.of(
                2, 2, 3, 5, 7, 11, 13, 17, 19, 23, 2 // the art of guess and check
        );
        BigInteger denom = BigInteger.ONE;
        for (int i : myDenom) {
            denom = denom.multiply(BigInteger.valueOf(i));
        }
        BigDecimal totient = new BigDecimal(denom);
        for (int p : new HashSet<>(myDenom)) {
            BigDecimal reducto = BigDecimal.ONE.subtract(BigDecimal.ONE.divide(new BigDecimal(p), mc));
            totient = totient.multiply(reducto);
        }
        BigDecimal frac = totient.divide(new BigDecimal(denom.subtract(BigInteger.ONE)), mc);
//        System.out.println("my frac: " + frac);
//        System.out.println("my denom: " + denom);
//        System.out.println("target: " + target);
//        System.out.println("all good? " + (frac.compareTo(target) < 0));

        System.out.println("The smallest denominator d with resilience R(d) < 15499/94744 is: " + denom);
        // 30
        // 2, 3, 4, 5, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25, 26, 27, 28, 30
        // 1, 7, 11, 13, 17, 19, 23, 29
    }
}
