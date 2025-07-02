package problems;


import util.Files;
import util.Numbers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;


//Shifted Multiples.
public class Problem357 {
    private static int N = 100_000_000;
    public static void main(String[] args) {
        Numbers.generateCachedPrimes(N + 100);
        ArrayList<Integer> primes = Numbers.generatePrimes(N + 100);
        HashSet<Integer> primesSet = new HashSet<>(primes);
        System.out.println("Generated primes...");
        long total = 0;
        int i = 0;
        for (int p : primes) {
            i++;
            if (i % 100000 == 0) System.out.println(p);
            int n = p - 1;
            if (n > N) break;
            if (!primesSet.contains(n/2 + 2)) continue;
            ArrayList<Integer> divisors = Numbers.getFactors(n, true);
            boolean valid = true;
            for (int d : divisors) {
                if (!primesSet.contains(d + n / d)) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                // System.out.println(n + ": " + divisors);
                total += n;
            }
        }
        System.out.printf("The sum of all prime generating integers from 1 to %d is: %d\n", N, total);
    }
}

