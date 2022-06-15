package problems;


import util.Numbers;
import util.Sets;

import java.math.BigInteger;
import java.util.ArrayList;

//Large Repunit Factors.
public class Problem132 {
    private static final int TARGET = 1000000000;
    private static final ArrayList<Integer> primes = Numbers.generatePrimes(1000000);
    private static final ArrayList<Integer> primeFactors = new ArrayList<>();
    public static void main(String[] args) {
        for (int p : primes) {
            int cycle = cycle(p);
            if (cycle != 0 && TARGET%cycle == 0) {
                primeFactors.add(p);
                if (primeFactors.size() == 40) break;
            }
        }
        System.out.println("The sum of the first forty-prime factors of R(10^9) is: " + Sets.sum(primeFactors));
    }

    public static int cycle(int n) {
        if (n%2 == 0 || n%5 == 0) return 0;
        int residue = 1;
        int k = 1;
        while (residue != 0) {
            k++;
            residue = (10 * residue + 1)%n;
        }
        return k;

    }



}

