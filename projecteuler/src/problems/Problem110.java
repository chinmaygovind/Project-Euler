package problems;


import util.Numbers;
import util.Timer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static problems.Problem108.countSolutions;

//Diophantine Reciprocals II.
public class Problem110 {
    //The number of solutions for n is the number of factors in n^2 / 2.
    private static long n = 1;
    private static long solutions = 1;
    private static final int limit = 4000000;
    //61261200 seems to be smallest one for 8000 solutions.
    private static final int[] factors = new int[1000];
    private static final ArrayList<Integer> primes = Numbers.generatePrimes(1000);
    public static void main(String[] args) {
        while (numSolutions() < limit){
            solutions = numSolutions();
            System.out.println("n = " + n + ": " + numSolutions());
            int bestIndexToUpgrade = 0;
            double bestCost = 0;
            //to find out what prime to multiply by that minimizes the number while maximizing the solutions...
            for (Integer i : primes){
                //cost = multiplicative increase in factors / number
                double tempCost = Math.log(((double) 2 * factors[i] + 3) /(2 * factors[i] + 1)) / Math.log(i);
                //System.out.println(i + ": " + tempCost);
                if (tempCost > bestCost && (double) 2 * factors[i] + 3) /(2 * factors[i] + 1)) * solutions <= 2 * limit){
                    bestCost = tempCost;
                    bestIndexToUpgrade = i;
                }
                if (tempCost < bestCost && factors[i] == 0) break;
            }
            System.out.println("Multiplying by " + bestIndexToUpgrade);
            factors[bestIndexToUpgrade]++;
            n = 1;
            for (int i = 0; i < 1000; i++){
                if (factors[i] != 0) n *= Math.pow(i, factors[i]);
            }
        }
        System.out.println("The least value of n that has over 4 million integer solutions for the equation 1/x + 1/y = 1/n is: " + n);
        System.out.println("The number of solutions it has is: " + solutions);
        System.out.println(Arrays.toString(factors));
    }
    public static long numSolutions(){
        int runningTotal = 1;
        for (int i = 0; i < 1000; i++){
            runningTotal *= factors[i] * 2 + 1;
        }
        return runningTotal / 2 + 1;
    }

}
