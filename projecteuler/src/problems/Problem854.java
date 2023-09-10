package problems;


import util.Numbers;
import util.Sets;

import java.math.BigInteger;
import java.util.*;


//PisanoPeriods 2.
public class Problem854 {
    private static int LIMIT = 50000;
    private static BigInteger MODULO = BigInteger.valueOf(1_234_567_891L);
    private static ArrayList<Integer> primes = Numbers.generatePrimes(LIMIT);
    private static BigInteger[] fibonacci = new BigInteger[LIMIT+2];
    public static void main(String[] args) {
        fibonacci[0] = BigInteger.ZERO;
        fibonacci[1] = BigInteger.ONE;
        for (int i = 2; i <= LIMIT+1; i++) {
            fibonacci[i] = fibonacci[i - 2].add(fibonacci[i-1]);
        }
        //System.out.println(Arrays.toString(fibonacci));
//        BigInteger p = BigInteger.ONE;
//        for (int i = 1; i <= LIMIT; i++) {
//            BigInteger M = M(i);
//            //System.out.println(i + ": " + M);
//            if (M.equals(BigInteger.TWO) && i != 3) continue;
//            p = p.multiply(M.mod(MODULO)).mod(MODULO);
//        };
        //System.out.println(Arrays.toString(fibonacci));
        BigInteger prod = BigInteger.ONE;
        for (int p = 1; p <= LIMIT; p++) {
            BigInteger m = fastM(p);
            assert p == bigPisano(BigInteger.valueOf(p));
            prod = prod.multiply(m).mod(MODULO);
        }
        System.out.println(prod);
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
     * Get the Pisano period of n, the period of the fibonacci series taken modulo n.
     * Gives up if period does not divide target.
     * @param n The number to find the Pisano period of.
     * @return The Pisano period of n, or -1 does not divide target.
     */
    public static int modifiedP(long n, long target) {
        if (n == 1) return 1;
        long a = 0;
        long b = 1;
        int p = 0;
        do {
            b = (b + a);
            a = (b - a);
            b %= n; a %= n;
            p++;
        } while ((a != 0 || b != 1) && p <= target + 1);
        return (p > 0 && target % p == 0 ? p : -1);
    }

    /**
     * Finds largest value of n with Pisano period p.
     * @param p Pisano period for which you want the largest value of n.
     * @return The largest value of n for which its Pisano period is p, as a BigInteger.
     */
    public static BigInteger M(int p) {
        return null;
        //return fibonacci[p+1].subtract(BigInteger.ONE).gcd(fibonacci[p]);
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
                ? copy[n+2] : BigInteger.ONE;


    }
}

