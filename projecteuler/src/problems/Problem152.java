package problems;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

//Sums of Square Reciprocals.
public class Problem152 {
    //i am a generating function demon
    private static BigInteger FACTORIAL_80_SQUARED = BigInteger.ONE;
    private static BigInteger HALF_FACTORIAL_80_SQUARED = BigInteger.ONE;
    //in the form, power + coefficient
    private static HashMap<BigInteger, Integer> generatingFunction = new HashMap<>();
    private static int FIRST_HALF = 39;
    private static int LIMIT = 80;
    private static int solutions = 0;
    public static void main(String[] args) {
        List<Integer> forbidden = List.of(11, 22, 33, 44, 17, 34, 51, 19, 38, 23, 46, 29, 31, 
        37, 41, 43, 47);
        //if a term gets one of these, it'll not be evenly divisible by the square
        for (int i = 2; i <= 80; i++) {
            FACTORIAL_80_SQUARED = FACTORIAL_80_SQUARED.multiply(BigInteger.valueOf(i*i));
        }
        HALF_FACTORIAL_80_SQUARED = FACTORIAL_80_SQUARED.divide(BigInteger.TWO);

        //initialize generating function with the necessary 2 term
        generatingFunction.put(upscale(2), 1);
        for (int i = 3; i <= FIRST_HALF; i++) {
            if (forbidden.contains(i)) continue;
            //if we add something like a 1/19^2, that term is ruining everything forever so we can skip those
            //convolution??!?!?
            HashMap<BigInteger, Integer> newGeneratingFunction = new HashMap<>(generatingFunction);
            //already is multiplied by 1, add the x^(1/i^2) term

            //max_remaining: if you used all the remaining fractions that you can add, how high can you get?
            BigInteger max_remaining = BigInteger.ZERO;
            for (int j = i + 1; j <= LIMIT; j++)  {
                if (forbidden.contains(j)) continue;
                max_remaining = max_remaining.add(upscale(j));
            }

            for (BigInteger key : generatingFunction.keySet()) {
                BigInteger newKey = key.add(upscale(i));
                //remove terms bigger than 1/2 or more than max_remaining away from 1/2
                BigInteger newKeyPlusMaxRemaining = newKey.add(max_remaining);
                if (newKey.compareTo(HALF_FACTORIAL_80_SQUARED) > 0
                || newKeyPlusMaxRemaining.compareTo(HALF_FACTORIAL_80_SQUARED) < 0) {
                    if (newKeyPlusMaxRemaining.compareTo(HALF_FACTORIAL_80_SQUARED) < 0) {
                        //newGeneratingFunction.remove(key);
                    }
                    continue;
                }
                if (!newGeneratingFunction.containsKey(newKey)) {
                    newGeneratingFunction.put(newKey, generatingFunction.get(key));
                } else {
                    newGeneratingFunction.put(newKey, newGeneratingFunction.get(newKey) + generatingFunction.get(key));
                }
            }
            generatingFunction = newGeneratingFunction;
            //System.out.println("i = " + i + ", terms: " + generatingFunction.size());
           
        }
        HashMap<Double, Integer> prettyMap = new HashMap<>();
        for (BigInteger key : generatingFunction.keySet()) {
            prettyMap.put(key.doubleValue() / FACTORIAL_80_SQUARED.doubleValue(), generatingFunction.get(key));
        }
        
        int[] backHalf = new int[]{40, 42, 45, 48, 49, 50, 51, 52, 54, 56, 60, 63, 64, 65, 70, 72, 75, 78, 80};
        BigInteger[] additives = new BigInteger[1 << backHalf.length];
        for (int i = 0; i < 1 << backHalf.length; i++) {
            BigInteger running_total = BigInteger.ZERO;
            for (int b = 0; b < backHalf.length; b++) {
                if ((i & (1 << b)) > 0) {
                    running_total = running_total.add(upscale(backHalf[b]));
                }
            }
            additives[i] = running_total;
        }
        //System.out.println(additives.length);
        for (BigInteger inv : additives) {
            BigInteger val = HALF_FACTORIAL_80_SQUARED.subtract(inv);
            int coeff = generatingFunction.getOrDefault(val, 0);
            solutions += coeff;
        }
       // System.out.println(solutions);
        //System.out.println(generatingFunction);
        //System.out.println(prettyMap);
        //System.out.println(generatingFunction.get(HALF_FACTORIAL_80_SQUARED));
        System.out.printf("The number of ways to write 1/2 as the sum of reciprocals of square using distinct integers from 2 to 80 is: %d", solutions);
    }

    public static BigInteger upscale(int i) {
        return FACTORIAL_80_SQUARED.divide(BigInteger.valueOf(i*i));
    }
 }



