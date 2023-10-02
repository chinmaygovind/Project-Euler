package problems;


import util.Numbers;
import util.Sets;
import util.Timer;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.*;


//PisanoPeriods 2.
public class Problem854 {
    private static int LIMIT = 1_000_000;
    private static BigInteger MODULO = BigInteger.valueOf(1_234_567_891L);
    private static ArrayList<Integer> primes = Numbers.generatePrimes(LIMIT);
    private static BigInteger[] fibonacci = new BigInteger[LIMIT+2];

    private static double totalGCDTime = 0;
    private static int samples = 0;
    public static void main(String[] args) {
//        fibonacci[0] = BigInteger.ZERO;
//        fibonacci[1] = BigInteger.ONE;
//        for (int i = 2; i <= LIMIT+1; i++) {
//            fibonacci[i] = fibonacci[i - 2].add(fibonacci[i-1]);
//        }
//        System.out.println("Generated fibonacci.");
        //System.out.println(Arrays.toString(fibonacci));
//        BigInteger p = BigInteger.ONE;
//        for (int i = 1; i <= LIMIT; i++) {
//            BigInteger M = M(i);
//            //System.out.println(i + ": " + M);
//            if (M.equals(BigInteger.TWO) && i != 3) continue;
//            p = p.multiply(M.mod(MODULO)).mod(MODULO);
//        };
        //System.out.println(Arrays.toString(fibonacci));
//        BigInteger prod = BigInteger.ONE;
//        for (int p = 1; p <= LIMIT; p++) {
//            if (p%(LIMIT/100) == 0) {
//                System.out.printf("p = %d, Average GCD Time: %.2fms\n", p, (totalGCDTime/samples / 1_000_000));
//                totalGCDTime = 0;
//                samples = 0;
//            }
//            BigInteger m = M(p);
//            prod = prod.multiply(m).mod(MODULO);
//            if (m.compareTo(BigInteger.ONE) > 0 && bigPisano(m) != p)
//                System.out.println(p + ": " + m + " | check: " + bigPisano(m));
//        }
//        System.out.println(prod);
        System.out.println(P(LIMIT));
        //System.out.println(debugGCD(BigInteger.valueOf(2584), BigInteger.valueOf(4180)));
    }

    /**
     * Get the Pisano period of n, the period of the fibonacci series taken modulo n.
     * @param n The number to find the Pisano period of.
     * @return The Pisano period of n.
     */
    public static int pisano(long n) {
        if (n == 1) return 1;
        long a = 0;
        long b = 1;
        int p = 0;
        do {
            b = (b + a);
            a = (b - a);
            b %= n; a %= n;
            p++;
        } while (a != 0 || b != 1);
        return p;
    }

    public static int bigPisano(BigInteger n) {
        if (n.equals(BigInteger.ONE)) return 1;
        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;
        int p = 0;
        do {
            b = b.add(a);
            a = b.subtract(a);
            b= b.mod(n); a = a.mod(n);
            p++;
        } while (!a.equals(BigInteger.ZERO) || !b.equals(BigInteger.ONE));
        return p;
    }
    /**
     * Finds largest value of n with Pisano period p.
     * @param p Pisano period for which you want the largest value of n.
     * @return The largest value of n for which its Pisano period is p, as a BigInteger.
     */
    public static BigInteger M(int p) {
        //return null;
        Instant start = Instant.now();
        BigInteger m = fibonacci[p+1].subtract(BigInteger.ONE).gcd(fibonacci[p]);
        totalGCDTime += Duration.between(start, Instant.now()).getNano();
        samples++;
        if (m.equals(BigInteger.TWO) && (p != 3 && p%3 == 0)) return BigInteger.ONE;
        return m;
    }

    public static BigInteger debugGCD(BigInteger a, BigInteger b) {
        while (!a.equals(BigInteger.ONE) && !b.equals(BigInteger.ONE) &&
            !a.equals(BigInteger.ZERO) && !b.equals(BigInteger.ZERO)) {
            System.out.println("GCF of " + a + ", " + b + " =");
            if (a.compareTo(b) > 0) {
                a = a.subtract(b);
            } else {
                b = b.subtract(a);
            }
        }
        return BigInteger.ZERO;
    }

    public static BigInteger fastM(int p) {
        if (p == 1) return BigInteger.ONE;
        BigInteger[] copy = new BigInteger[fibonacci.length];
        for (int i = 0; i < fibonacci.length; i++) copy[i] = fibonacci[i];
        int n = p+1;
        copy[n] = copy[n].subtract(BigInteger.ONE);
        n--;
        int i = 0;
        while (copy[n+1].compareTo(BigInteger.ONE) > 0 && n >= 0) {
            copy[n] = (copy[n].add(fibonacci[i].multiply(BigInteger.valueOf((i%2 == 0 ? 1 : -1)))));
            i++;
            n--;
        }
        n++;
        //System.out.println(Arrays.toString(copy));
        return (copy[n+2].add(copy[n]).equals(BigInteger.ZERO) || copy[n].equals(BigInteger.ZERO))
                ? copy[n+2].mod(MODULO) : BigInteger.ONE;


    }

    public static int P(int n) {
        BigInteger a = BigInteger.valueOf(3);
        BigInteger b = BigInteger.valueOf(5);
        BigInteger prod = BigInteger.TWO;//start at i = 4
        HashMap<Integer, BigInteger> cachedGCDs = new HashMap<>();
        Numbers.generateCachedPrimes(1_000_000);
        long stopwatch = System.currentTimeMillis();
        for (int i = 4; i <= n; i+=2) {
            BigInteger factorOut = BigInteger.ONE;
            for (int d = 2; d <= 10; d++) {
                if (i%d == 0 && cachedGCDs.containsKey(i/d)) {
                    factorOut = cachedGCDs.get(i/d);
                    break;
                }
            }
            BigInteger m = a.divide(factorOut).gcd(b.subtract(BigInteger.ONE).divide(factorOut)).multiply(factorOut);
            if (i < LIMIT/2) cachedGCDs.put(i, m);
            if (i%1000 == 0) {
                System.out.printf("i = %d, took %dms\n", i, System.currentTimeMillis()-stopwatch);
                stopwatch=System.currentTimeMillis();
            }
            //System.out.println(i + ": " + m + " | " + Numbers.getPrimeFactors(m, true));
            if (!(m.equals(BigInteger.TWO) && i%3 == 0 && i > 6))
                prod = prod.multiply(m.mod(MODULO)).mod(MODULO);
            b = b.add(a);
            a = b.subtract(a);
            b = b.add(a);
            a = b.subtract(a);
            //do it twice since we're only checking evens
        }
        return prod.intValue();
    }
}

