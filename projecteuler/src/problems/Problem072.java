package problems;


import util.Numbers;

import java.util.ArrayList;
import java.util.HashSet;

//Ordered Fractions.
public class Problem072 {
    private static ArrayList<Integer> primes = Numbers.generatePrimes(1000);
    private static long totalFractions = 0;
    public static void main(String[] args) {
        for (int i = 2; i <= 1000000; i++){
            totalFractions += totient(i);
        }
        System.out.println("The number of proper fractions with the denominator up to one million is: " + totalFractions);
    }


    private static int totient(int num){
        HashSet<Integer> factors = new HashSet<>(getPrimeFactors(num));
        for (int factor : factors){
            num *= (1 - (1.0 / factor));
        }
        return Math.round(num);
    }
    private static ArrayList<Integer> getPrimeFactors(int num){
        ArrayList<Integer> factors = new ArrayList<>();
        for (Integer prime : primes) {
            if (num == 1) break;
            while (num % prime == 0) {
                factors.add(prime);
                num /= prime;
            }
        }
        if (num != 1) {
            factors.add(num);
        }
        return factors;
    }
}
