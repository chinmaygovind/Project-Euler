package problems;


import util.Numbers;

// Arithmetic Derivative.
public class Problem484 {

    public static void main(String[] args) {
        int limit = 100_000_000;
        Numbers.generateCachedPrimes(limit);
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

        for (int i = 1; i <= 300; i++) {
            long d = d(i);
            long gcd = Numbers.gcd(i, d);
            if (true) {
                System.out.printf("d(%d) = %d, GCD = %d\n", i, d, gcd);
            }
        }

        // 1 - 1, 2 - 1, 4 - 4, 8 - 4, 16 - 16, 32 - 16, 64 - 64, 128 - 64, 256 - 256, 512 - 256
        // highest power of 2^2 below 2^n?
        // 1 - 1, 3 - 1, 9 - 3, 27 - 27, 81 - 27, 243 - 81, 729 - 729, 2187 - 729, 6561 - 2187
        // blocks of 3, last one multiplied by 3 and then 9
        //  1 - 1, 4 - 4, 16 - 16, 64 - 64, 256 - 256, 1024 - 1024, 4096 - 4096
        // each one multiplies by 4? so maybe the 2 actually just multiplies by 2 each time and the 4 adds to it
        // 1, 1, 5, 25, 125, 3125, 3125
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

