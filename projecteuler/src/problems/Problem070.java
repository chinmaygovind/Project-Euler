package problems;


import util.Timer;
import util.Numbers;

import java.util.*;
import java.util.stream.Collectors;

//Totient Permutations.
public class Problem070 {
    private static ArrayList<Integer> primes = Numbers.generatePrimes(10000);
    private static int minRatioN = 0;
    private static double minTotientRatio = 100;
    public static void main(String[] args) {
        for (int i = 2; i < 10000000; i++){
            int totient = totient(i);
            int[] iDigits = new int[10];
            int[] totientDigits = new int[10];
            for (char digit : String.valueOf(i).toCharArray()){
                iDigits[Character.getNumericValue(digit)]++;
            }
            for (char digit : String.valueOf(totient).toCharArray()){
                totientDigits[Character.getNumericValue(digit)]++;
            }
            if (Arrays.equals(iDigits, totientDigits)){
                if (i/ (double) totient < minTotientRatio){
                    minTotientRatio = i/ (double) totient;
                    minRatioN = i;
                }
            }
        }
        System.out.println("The value of n under ten million that is a permutation of φ(n) and produces a minimum value of n/φ(n) is: " + minRatioN);
        System.out.println("The totient of this n is: " + totient(minRatioN));
        System.out.println("The value of n/φ(n) is: " + minTotientRatio);

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
