package problems;


import util.Numbers;

import java.util.ArrayList;
import java.util.HashMap;

//Prime Digit Replacements.
public class Problem051 {
    private static ArrayList<Integer> primes = Numbers.generatePrimes(1000000);
    private static ArrayList<Integer> fails = new ArrayList<>();
    public static void main(String[] args){
        int i = 0;
        while (!checkNum(primes.get(i))){
            i++;
        }
        System.out.println("The smallest prime part of an 8-prime digit replacement family is: " + primes.get(i));
    }


    private static boolean checkNum(int num) {
        //try different numbers in the number to replace (like 00 in 56003)
        for (int replaceDigit = 0; replaceDigit < 10; replaceDigit++){
            int numPrimes = 0;
            StringBuilder masked = new StringBuilder(String.valueOf(num));
            //loop through different numbers to replace with (like 56113, 56223...)
            for (int i = 0; i < 10; i++){
                int charIndex = 0;
                for (char digit : String.valueOf(num).toCharArray()){
                    //if digit is the replace digit
                    if (Integer.parseInt(String.valueOf(digit)) == replaceDigit){
                        if (charIndex == 0 && i == 0){
                           numPrimes--;
                           break;
                        }
                        masked.setCharAt(charIndex, Integer.toString(i).toCharArray()[0]);
                    }
                    charIndex++;
                }
                if (primes.contains(Integer.parseInt(masked.toString()))){
                    numPrimes++;
                }
            }
            if (numPrimes == 8){
                return true;
            }
        }
        return false;
    }
}
