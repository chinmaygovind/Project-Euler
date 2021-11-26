package problems;


import util.Numbers;
import util.Timer;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import static problems.Problem108.countSolutions;

//Diophantine Reciprocals II.
public class Problem110 {
    //61261200 seems to be smallest one for 8000 solutions.
    private static final int[] factors = new int[5000];
    private static int[] tempFactors;
    private static final ArrayList<Integer> primes = Numbers.generatePrimes(1000);
    private static BigInteger n = BigInteger.ONE;
    public static void main(String[] args) {
        Numbers.generateCachedPrimes(1000000);
        for (int i = 8000001; i < 8100000; i+=2) {
            ArrayList<Long> factors = Numbers.getPrimeFactors(i, true);
            if (factors.get(factors.size()-1) > 300) continue;
            Collections.reverse(factors);
            BigInteger potentialN = BigInteger.ONE;
            for (int f = 0; f < factors.size(); f++) {
                BigInteger power = BigInteger.ONE;
                for (int k = 0; k < (factors.get(f)-1)/2; k++) power =  power.multiply(BigInteger.valueOf(primes.get(f)));
                potentialN = potentialN.multiply(power);
            }
            if (potentialN.compareTo(n) < 0 || n.equals(BigInteger.ONE)) n = potentialN;
        }
        System.out.println("The smallest value of n with over 4 million integer solutions for 1/x + 1/y = 1/n is: " + n);
    }

    public static long cheeseSolutions(){
        long solutions = 1;
        for (int factor : tempFactors) solutions *= (2L * factor) + 1;
        return solutions/2 + 1;
    }
    public static long numSolutions(double n){
        HashMap<Long, Integer> factors = new HashMap<>();
        for (long i : Numbers.getPrimeFactors(n, true)){
            if (!factors.containsKey(i)) factors.put(i, 1);
            else factors.put(i, factors.get(i) + 1);
        }
        long solutions = 1;
        for (Long i : factors.keySet()) solutions *= (2L * factors.get(i)) + 1;
        return solutions/2 + 1;
    }

}
