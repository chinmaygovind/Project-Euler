package problems;


import util.Numbers;
import util.Sets;

import java.util.*;


//Pisano Primes.
public class Problem853 {
    private static int LIMIT = 1_000_000_000;
    private static final int TARGET = 120;
    private static ArrayList<Integer> primes = Numbers.generatePrimes(3000);
    private static ArrayList<Integer> goodPrimes = new ArrayList<>();
    private static final HashMap<Long, Long> pisanoCache = new HashMap<>();
    private static HashSet<Long> solutions = new HashSet<>();
    private static long total = 0;

    public static void main(String[] args) {
        //System.out.println("Generated primes.");

        pisanoCache.put(1L, 1L);
        for (int prime : primes) {
            long pN = modifiedP(prime);
            //System.out.println(prime + ", " + pN);
            if (pN > 0) {
                goodPrimes.add(prime);
                long n = prime;
                long newPN = pN;
                while (n <= LIMIT && newPN <= TARGET) {
                    pisanoCache.put(n, newPN);
                    n *= prime;
                    newPN *= prime;
                }   
            }
        }
        //System.out.println("Cached pisanoes.");
        //System.out.println(pisanoCache);
        for (int twos = 0; twos <= 4; twos++) {
            for (int threes = 0; threes <= 2; threes++) {
                for (int fives = 0; fives <= 1; fives++) {
                    for (int elevens = 0; elevens <= 1; elevens++) {
                        for (int thirtyones = 0; thirtyones <= 1; thirtyones++) {
                            for (int fortyones = 0; fortyones <= 1; fortyones++) {
                                for (int sixtyones = 0; sixtyones <= 1; sixtyones++) {
                                    for (int biggies = 0; biggies <= 1; biggies++) {
                                        double n = Math.pow(2, twos) *
                                                Math.pow(3, threes) *
                                                Math.pow(5, fives) *
                                                Math.pow(11, elevens) *
                                                Math.pow(31, thirtyones) *
                                                Math.pow(41, fortyones) *
                                                Math.pow(61, sixtyones) *
                                                Math.pow(2521, biggies);
                                        if (n >= LIMIT) continue;
                                        long[] contributions = new long[]{
                                                pisanoCache.get((long) Math.pow(2, twos)),
                                                pisanoCache.get((long) Math.pow(3, threes)),
                                                pisanoCache.get((long) Math.pow(5, fives)),
                                                pisanoCache.get((long) Math.pow(11, elevens)),
                                                pisanoCache.get((long) Math.pow(31, thirtyones)),
                                                pisanoCache.get((long) Math.pow(41, fortyones)),
                                                pisanoCache.get((long) Math.pow(61, sixtyones)),
                                                pisanoCache.get((long) Math.pow(2521, biggies))};
                                        double pN = 1;
                                        for (long i : contributions) pN *= (double) i / Numbers.GCF(i, pN);
                                        if (pN == 120) solutions.add((long) n);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        ArrayList<Long> sols = new ArrayList<>(solutions);
        Collections.sort(sols);
        //System.out.println(sols);
        //for (long s : solutions) {
        //    if (modifiedP(s) != 120) System.out.printf("OH SHOOT: %d HAS A PERIOD OF %d", s, pisano(s));
        //}
        total = Sets.sumLongs(sols);
        //System.out.println(solutions);
        System.out.printf("The sum of all values of N under %d which have a Pisano Period of %d is: %d", LIMIT, TARGET, total);
    }

    /**
     * Get the Pisano period of n, the period of the fibonacci series taken modulo n.
     * @param n
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

    /**
     * Get the Pisano period of n, the period of the fibonacci series taken modulo n.
     * Gives up if period does not divide TARGET.
     * @param n
     * @return The Pisano period of n, or -1 does not divide TARGET.
     */
    public static int modifiedP(long n) {
        if (n == 1) return 1;
        long a = 0;
        long b = 1;
        int p = 0;
        do {
            b = (b + a);
            a = (b - a);
            b %= n; a %= n;
            p++;
        } while ((a != 0 || b != 1) && p <= TARGET + 10);
        return (p > 0 && TARGET % p == 0 ? p : -1);
    }

    /**
     * Get the Pisano period of n, the period of the fibonacci series taken modulo n.
     * Gives up if period does not divide TARGET.
     * Uses cached pisano values to make the process faster.
     * @param n
     * @return The Pisano period of n, or -1 if period does not divide TARGET.
     */
    public static int fastP(int n) {
        int p = 1;
        int primeID = 0;
        while (n != 1 && p > 0 && p <= TARGET && primeID < primes.size()) {
            int prime = primes.get(primeID);
            //uses the (conjectured) property that pisano(p^k) = p^(k-1) * pisano(p)
            int k = 0;
            while (n % prime == 0) {
                n /= prime;
                k++;
            }
            if (k > 0)
                p *= (int) Math.pow(prime, k - 1) * pisanoCache.get(prime);
            primeID++;
        }
        return (p > 0 && TARGET % p == 0 ? p : -1);
    }
}

