package problems;


import util.Numbers;
import util.Sets;

import java.util.ArrayList;
import java.util.HashSet;

// Not Coprime.
public class Problem838 {
    private static int N = 1_000_000;
    public static void main(String[] args) {
        Numbers.generateCachedPrimes(N);
        System.out.println("Generated cached primes.");
        HashSet<Integer> allPrimes = new HashSet<>(Numbers.getCachedPrimes());
        HashSet<Integer> primes = new HashSet<>();
        HashSet<Integer> composites = new HashSet<>();
        for (int i = 3; i < N; i += 10) {
            if (allPrimes.contains(i)) {
                primes.add(i);
            } else {
                composites.add(i);
            }
        }
        for (Integer c : new HashSet<>(composites)) {
            for (Integer p : primes) {
                if (c % p == 0) {
                    composites.remove(c);
                    break;
                }
            }
        }
        HashSet<Integer> compositePrimeFactors = new HashSet<>();
        for (Integer c : composites) {
            compositePrimeFactors.addAll(Numbers.getPrimeFactors(c));
        }
        System.out.println(compositePrimeFactors);
        HashSet<Integer> removable = getRemovable(compositePrimeFactors, composites);
        while (!removable.isEmpty()) {
            System.out.println("Removable: " + removable);
            int toRemove = new ArrayList<>(removable).getFirst();
            compositePrimeFactors.remove(toRemove);
            removable.remove(toRemove);
            removable = getRemovable(removable, composites);
        }
        System.out.println(primes);
        System.out.println(composites);
        System.out.println(compositePrimeFactors);
        primes.addAll(compositePrimeFactors);
        double sum = 0;
        for (Integer p : primes) {
            sum += Math.log(p);
        }
        System.out.printf("The value of ln(f(%d)) is: %.6f", N, sum);
    }

    private static HashSet<Integer> getRemovable(HashSet<Integer> compositePrimeFactors, HashSet<Integer> composites) {
        HashSet<Integer> removable = new HashSet<>();
        for (Integer cpf : compositePrimeFactors) {
            HashSet<Integer> without = new HashSet<>(compositePrimeFactors);
            without.remove(cpf);
            boolean valid = true;
            for (Integer c : composites) {
                boolean hasFactor = false;
                for (Integer w : without) {
                    if (c % w == 0) {
                        hasFactor = true;
                        break;
                    }
                }
                if (!hasFactor) {
                    valid = false;
                }
            }
            if (valid) {
                removable.add(cpf);
            }
        }
        return removable;
    }
}

