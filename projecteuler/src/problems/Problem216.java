package problems;

import util.Numbers;

import java.math.BigInteger;

// The Primality of 2n^2 - 1.
public class Problem216 {
    public static void main(String[] args) {
        int primes = 1; // for n = 2
        Numbers.generateCachedPrimes(100000);
        for (long n = 3; n <= 50_000_000; n++) {
            if (n % 100000 == 0) System.out.println(n);
            if (n % 7 == 2 || n % 7 == 5) continue;
            BigInteger tN = BigInteger.valueOf(2 * n * n - 1);
            if (tN.isProbablePrime(5)) {
                primes++;
//                System.out.println(n + ", " + tN + ", " + Numbers.getPrimeFactors((int) n, true));
            } else {
//                System.out.println("poopy: " + n + ", " + Numbers.getFactors((int) n));
            }
        }
        System.out.println("The number of prime t(n) for n <= 50,000,000 is: " + primes);
    }


}
