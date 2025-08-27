package problems;


import util.Numbers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Arithmetic Derivative.
public class Problem484 {

    private static HashMap<Long, BigInteger> cachedS = new HashMap<>();
    private static long LIMIT = 5_000_000_000_000_000L;
    private static BigInteger bigLIMIT = BigInteger.valueOf(LIMIT);
    private static long sqrtLIM = (long) Math.sqrt(LIMIT);

    private static ArrayList<Integer> primes = new ArrayList<>();
    private static ArrayList<BigInteger> bigPrimes = new ArrayList<>();

    public static void main(String[] args) {
        primes = Numbers.generatePrimes((int) sqrtLIM);
        for (int p : primes) {
            bigPrimes.add(BigInteger.valueOf(p));
        }
        long temp = 100;
        Numbers.generateCachedPrimes((int) temp);
        System.out.println(S(temp));
        System.out.println(trueS(temp));
//        for (int b = 2; b <= 20; b++) {
//            if (!Numbers.isPrime(b)) continue;
//            int n = 1;
//            System.out.printf("Powers of %d: ", b);
//            for (int i = 1; n * b < limit; i++) {
//                long d = d(n);
//                System.out.printf("%.0f, ", Math.log(Numbers.gcd(n, d))/Math.log(b));
//                n *= b;
//            }
//            System.out.println();
//        }

//        for (int i = 1; i <= 300; i++) {
//            long d = d(i);
//            long gcd = Numbers.gcd(i, d);
//            if (true) {
//                System.out.printf("d(%d) = %d, GCD = %d\n", i, d, gcd);
//            }
//        }

        // 1 - 1, 2 - 1, 4 - 4, 8 - 4, 16 - 16, 32 - 16, 64 - 64, 128 - 64, 256 - 256, 512 - 256
        // highest power of 2^2 below 2^n?
        // 1 - 1, 3 - 1, 9 - 3, 27 - 27, 81 - 27, 243 - 81, 729 - 729, 2187 - 729, 6561 - 2187
        // blocks of 3, last one multiplied by 3 and then 9
        //  1 - 1, 4 - 4, 16 - 16, 64 - 64, 256 - 256, 1024 - 1024, 4096 - 4096
        // each one multiplies by 4? so maybe the 2 actually just multiplies by 2 each time and the 4 adds to it
        // 1, 1, 5, 25, 125, 3125, 3125
    }

    public static BigInteger S(long n) {
        if (cachedS.containsKey(n)) return cachedS.get(n);
        if (n < 100) {
            long s = 0;
            for (int i = 1; i <= n; i++) {
                long d = d(i);
                long gcd = Numbers.gcd(i, d);
                s += gcd;
            }
            cachedS.put(n, BigInteger.valueOf(s));
            return BigInteger.valueOf(s);
        }
        // hard part.
        BigInteger total = BigInteger.ZERO;
        for (int i = 1; i <= 8; i++) {
            for (long p2 : getSquareCombos(i, BigInteger.valueOf(n))) {
                BigInteger sign = BigInteger.valueOf(i % 2 == 0 ? -1 : 1);
                BigInteger power = p2 == 4 ? BigInteger.valueOf(4) : BigInteger.valueOf(p2).sqrt();
                total = total.add(S(n / p2).multiply(power).multiply(sign));
                System.out.printf("Added %d for multiple %d\n", S(n / p2).multiply(power).multiply(sign), p2);
            }
        }
        return total;
    }

    public static BigInteger trueS(long n) {
        BigInteger total = BigInteger.ZERO;
        for (int i = 1; i <= n; i++) {
            total = total.add(BigInteger.valueOf(Numbers.gcd(i, d(i))));
        }
        return total;
    }

    public static ArrayList<Long> getSquareCombos(int size, BigInteger limit) {
        return getSquareCombos(BigInteger.ONE, 0, size, limit);
    }

    public static ArrayList<Long> getSquareCombos(BigInteger curr, int idx, int remaining, BigInteger limit) {
        if (remaining == 0) return new ArrayList<>(List.of(1L));
        ArrayList<Long> candidates = new ArrayList<>();
        for (int next = idx; next < primes.size(); next++) {
            BigInteger p = bigPrimes.get(next);
            BigInteger newCurr = curr.multiply(p).multiply(p);
            if (newCurr.compareTo(limit) > 0) break;
            for (long c : getSquareCombos(newCurr, next + 1, remaining - 1, limit)) {
                BigInteger newCandidate = BigInteger.valueOf(c).multiply(p).multiply(p);
                if (newCandidate.compareTo(limit) < 0) {
                    candidates.add(newCandidate.longValue());
                } else {
                    break;
                }
            }
        }
        return candidates;
    }

    // arithmetic derivative
    public static long d(long n) {
        if (n == 0 || n == 1) return 1;
        if (Numbers.isPrime(n)) {
            return 1;
        } else {
            long a = Numbers.getPrimeFactors(n, true).get(1);
            long b = n / a;
            return d(a) * b + a * d(b);
        }
    }

}

