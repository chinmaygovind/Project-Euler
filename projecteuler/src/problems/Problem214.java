package problems;

import util.Numbers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;

// Totient Chains.
public class Problem214 {
    private static int N = 40_000_000;
    private static int len = 25;
    private static HashSet<Integer> primes = new HashSet<>(Numbers.generatePrimes(N));

    private static int[] totientChain = new int[N + 1];
    public static void main(String[] args) {
        totientChain[1] = 1;
        Numbers.generateCachedPrimes(N);
        System.out.println("Generated primes.");
        long sum = 0;
        for (int i = 2; i <= N; i++) {
            if (i % 100_000 == 0) System.out.println(i);
            if (primes.contains(i)) totient(i);
            if (totientChain[i] == len && primes.contains(i)) {
                sum += i;
            }
        }
        System.out.printf("The sum of all primes less than %d which generate a totient chain of length %d is: %d", N, len, sum);
    }

    private static void totient(int num){
        if (num <= 1 || totientChain[num] > 0) {
            return;
        }
        if (primes.contains(num)) {
            totient(num - 1);
            totientChain[num] = totientChain[num - 1] + 1;
            return;
        }
        double totient = num;
        for (double primeFactor : new HashSet<>(Numbers.getPrimeFactors(num, true))){
            totient *= (1 - 1/primeFactor);
        }
        int res =  (int) Math.round(totient);
        if (totientChain[res] < 0) {
            totientChain[num] = -1;
            return;
        };
        totient(res);
        totientChain[num] = totientChain[res] + 1;
        if (totientChain[num] > len) totientChain[num] = -1; // ts chopped
    }


}
