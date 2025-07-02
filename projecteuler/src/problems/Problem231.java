package problems;

import util.Numbers;
import util.Sets;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// Prime Factorisation of Binomial Coefficients.
public class Problem231 {
    private static int n = 20_000_000;
    private static int k = 15_000_000; // assume k >= n/2
    public static void main(String[] args) {
        // WTF sum of prime factors of 2e7choose1.5e7.
        // this is just sum of prime factors of 1.5e7 * 1.5e7 + 1 * ... * 2e7
        long sum = 0;
        ArrayList<Integer> primes = Numbers.generatePrimes(n);
        for (int p : primes) {
            int pow = 1;
            long totalMult = 0;
            while (Math.pow(p, pow) < n) {
                int pPower = (int) Math.pow(p, pow); // im like damn he got lucky
                int low = (int) Math.ceil((double) (k + 1) / pPower);
                int high = (int) Math.floor((double) n / pPower);
                long mult = high - low + 1;
                pow++;
                totalMult += mult;
            }

//            System.out.println(p + ", " + totalMult);
            sum += totalMult * p;
            // 4, 5, 6, 7, 8, 9, 10
        }
//        System.out.println("---------");
        for (int p : primes) {
            int pow = 1;
            long totalMult = 0;
            while (Math.pow(p, pow) < n) {
                int pPower = (int) Math.pow(p, pow); // im like damn he got lucky
                int low = 1;
                int high = (int) Math.floor(((double) (n - k)) / pPower);
                long mult = high - low + 1;
                pow++;
                totalMult += mult;
            }
            // 1, 2, 3, 4, 5, 6, 7

//            System.out.println(p + ", " + totalMult);
            sum -= totalMult * p;
        }


        System.out.println("The sum of the terms in the prime factorization of 20000000choose15000000 is: " + sum);

    }
}
