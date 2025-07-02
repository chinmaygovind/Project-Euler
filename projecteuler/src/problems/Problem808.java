package problems;



import util.Numbers;

import java.math.BigInteger;
import java.util.ArrayList;

// Reversible Prime Squares.
public class Problem808 {
    public static void main(String[] args) {
        ArrayList<Integer> primes = Numbers.generatePrimes(100000000);
        primes.remove(0); // remove 2
        primes.remove(0); // remove 3
        long sum = 0;
        long found = 0;
        for (int p : primes) {
            long p2 = (long) p * (long) p;
            long p2rev = Long.parseLong(new StringBuilder(String.valueOf(p2)).reverse().toString());
            long p2revSqrt = (long) Math.sqrt(p2rev);
            if (p2revSqrt * p2revSqrt == p2rev && primes.contains((int) p2revSqrt) && p != p2revSqrt) {
                // System.out.printf("%d = %d^2\n", p2, p);
                sum += p2;
                found++;
            }
            if (found == 50) {
                break;
            }
        }
        System.out.printf("The sum of the first 50 reversible prime squres is: %d", sum);

    }
}

