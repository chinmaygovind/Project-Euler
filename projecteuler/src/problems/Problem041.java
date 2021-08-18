package problems;


import util.Numbers;

import java.util.ArrayList;
import java.util.Collections;

//Pandigital Prime.
public class Problem041 {
    private static ArrayList<String> permutations;
    private static int numDigits = 9;
    private static boolean notFound = true;
    public static void main(String[] args){
        while (notFound) {
            String digits = "";
            for (int i = 1; i <= numDigits; i++){
                digits += i;
            }
            permutations = Numbers.getPermutations(digits);
            Collections.reverse(permutations);
            for (String permutation : permutations) {
                if (Numbers.isPrime(Integer.parseInt(permutation))){
                    System.out.println("The largest n-digit pandigital prime is: " + permutation);
                    notFound = false;
                    break;
                }
            }
            numDigits--;

        }
    }
}
