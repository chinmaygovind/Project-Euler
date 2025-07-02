package problems;

import util.Numbers;

import java.util.ArrayList;
import java.util.HashSet;

// Semiprimes.
public class Problem187 {
    private static int N = 100_000_000;
    public static void main(String[] args) {
        ArrayList<Integer> primes = Numbers.generatePrimes(N);
        HashSet<Integer> semiprimes = new HashSet<>();
        for (int p : primes) {
            for (int q : primes) {
                if (p * q < N) {
                    semiprimes.add(p * q);
                } else {
                    break;
                }
            }
        }
        System.out.printf("The number of composite integers below %d with exactly 2 prime factors is: %d", N, semiprimes.size());
    }


}
