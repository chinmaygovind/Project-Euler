package problems;

import util.Numbers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

// Generalized Hamming Numbers.
public class Problem204 {
    private static int N = 1_000_000_000;
    public static void main(String[] args) {
        ArrayList<Integer> primes = Numbers.generatePrimes(N);
        System.out.println("Generated primes...");
        boolean[] good = new boolean[N + 1];
        for (int i = 1; i <= N; i++) { // too much water
            good[i] = true;
        }
        int idx = 0;
        for (int p : primes) {
            if (p < 100) continue;
            if (idx % 1000 == 0) System.out.println(p);
            idx++;
            // System.out.println(p);
            int np = p;
            while (np < N) {
                good[np] = false;
                np += p;
            }
        }
        int total = 0;
        for (boolean n : good) {
            if (n) total++;
        }
        System.out.println("The number of generalized Hamming numbers of type 100 below 10^9 is: " + total);
    }


}
