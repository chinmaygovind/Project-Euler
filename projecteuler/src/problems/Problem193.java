package problems;

import util.Numbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

// Squarefree Numbers.
public class Problem193 {
    // https://en.wikipedia.org/wiki/Square-free_integer#math_1 ???
    // had to rely on magic sadly


    private static long N = 1L << 50;
    private static long sqrtN = (int) Math.sqrt(N);
    private static ArrayList<ArrayList<Integer>> primeFactors = new ArrayList<>();
    private static int[] mobius = new int[(int) sqrtN + 1]; // oh hell it is just the sign in PIE thats genius
    public static void main(String[] args) {
        Numbers.generateCachedPrimes((int) sqrtN + 1);
        for (int i = 0; i <= sqrtN; i++) {
            primeFactors.add(new ArrayList<>());
        }
        for (Integer p : Numbers.getCachedPrimes()) {
            long pPower = p;
            while (pPower <= sqrtN) {
                System.out.println(pPower);
                for (long np = pPower; np <= sqrtN; np+= pPower) {
                    primeFactors.get((int) np).add(p);
                }
                pPower *= p;
            }
        }
        mobius[1] = 1;
        for (int i = 2; i <= sqrtN; i++) {
            if (new HashSet<>(primeFactors.get(i)).size() != primeFactors.get(i).size()) mobius[i] = 0;
            else mobius[i] = primeFactors.get(i).size() % 2 == 0 ? 1 : -1; //(-1)^k
        }

        long squareFree = 0;
        for (long d = 1; d <= sqrtN; d++) {
            if (d % 10000 == 0) System.out.println(d);
//            System.out.println(d + ": mu(d) = " + mobius[(int) d] + " | N / (d * d) = " + (N / (d * d)));
            squareFree += mobius[(int) d] * (N / (d * d));
        }
        System.out.println("The number of squarefree numbers below " + N + " is: " + squareFree);
    }



}
