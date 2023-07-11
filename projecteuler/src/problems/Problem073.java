package problems;


import java.util.ArrayList;

import util.Numbers;

//Counting Fractions in a Range.
public class Problem073 {
    private static ArrayList<Integer> primes = Numbers.generatePrimes(350);
    private static long totalFractions = 0;
    public static void main(String[] args) {
        for (int i = 2; i <= 5999; i++){
            ArrayList<Integer> factors = getPrimeFactors(i);
            for (int j = 2 * i + 1; j <= Math.min(3 * i, 12001) - 1; j++){
                boolean relativelyPrime = true;
                for (int factor : factors){
                    if (j%factor == 0){
                        relativelyPrime = false;
                        break;
                    }
                }
                if (relativelyPrime) totalFractions++;
            }
        }
        System.out.println("The number of proper fractions between 1/2 and 1/3 with the denominator up to 12,000 is: " + totalFractions);
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
