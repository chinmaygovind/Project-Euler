package problems;


import java.util.ArrayList;

import util.Numbers;

//Prime Pair Connection.
public class Problem134 {
    private static final ArrayList<Integer> primes = Numbers.generatePrimes(1000100);
    private static long sumS = 0;
    public static void main(String[] args) {
        for (int i = 0; i < primes.size(); i++) {
            int p = primes.get(i);
            if (p < 5) continue;
            if (p > 1000000) break;
            sumS += findS(p, primes.get(i + 1));
        }
        System.out.println("The sum of S for every pair of consecutive primes from five to one million is: " + sumS);
    }

    public static long findS(int p1, int p2) {
        long n = p1;
        int m = (int) Math.log10(p1) + 1;
        while (n%p2 != 0) {
            n += Math.pow(10, m);
        }
        return n;
    }



}

