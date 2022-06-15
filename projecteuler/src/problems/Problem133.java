package problems;


import util.Numbers;
import util.Sets;

import java.util.ArrayList;

//Repunit Non-Factors.
public class Problem133 {
    private static final int TARGET = 1000000000;
    private static final ArrayList<Integer> primes = Numbers.generatePrimes(100000);
    private static final ArrayList<Long> nonfactors = new ArrayList<>();

    public static void main(String[] args) {
        for (int p : primes) {
            int cycle = cycle(p);
            while (cycle%2 == 0) cycle/=2;
            while (cycle%5 == 0) cycle/=5;
            if (cycle != 1) nonfactors.add((long) p);
        }
        System.out.println("The sum of all primes under one-hundred thousand that will never be a factors of R(10^n) is: " + Sets.sumLongs(nonfactors));
    }

    public static int cycle(int n) {
        if (n%2 == 0 || n%5 == 0) return 3;
        int residue = 1;
        int k = 1;
        while (residue != 0) {
            k++;
            residue = (10 * residue + 1)%n;
        }
        return k;

    }



}

