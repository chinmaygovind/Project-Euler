package problems;


import java.util.ArrayList;

import util.Numbers;


//Not Coprime.
public class Problem838 {

    ArrayList<Integer> primes = Numbers.generatePrimes(1000000);
    public static void main(String[] args) {
        System.out.printf("The natural log of the smallest number not coprime with any n < 1,000,000 where n ends in a 3 is: %.6f", lnF(2800));
    }

    public static double lnF(int n) {
        ArrayList<Integer> factors = new ArrayList<>();
        for (int k = 3; k < n; k+= 10) {
            boolean hasSomething = false;
            for (int f : Numbers.getPrimeFactors(k)) {
                if (factors.contains(f)) {
                    hasSomething = true;
                    break;
                }
            }
            if (!hasSomething) factors.add(Numbers.getPrimeFactors(k).get(0));
        }

        double ln = 0;
        for (int f : factors) {
            ln += Math.log(f);
        }
        return ln;
    }
}

