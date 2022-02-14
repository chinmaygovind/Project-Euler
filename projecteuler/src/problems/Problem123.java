package problems;


import util.Numbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

//Prime Square Remainders.
public class Problem123 {
    private static final ArrayList<Integer> primes = Numbers.generatePrimes(1000000);
    public static void main(String[] args) {
        primes.add(0, 0);
        int n = 1;
        while (squareRemainder(n) <= 10000000000L) n+=2;
        System.out.println("The least value of n for which the remainder of (pₙ - 1)ⁿ + (pₙ + 1)ⁿ / pₙ² is over ten billion is: " + n);
    }

    public static long squareRemainder(int n) {
        return (n % 2 == 0 ? 2 : 2L * n * primes.get(n) % ((long) primes.get(n) * primes.get(n)));
    }

}

