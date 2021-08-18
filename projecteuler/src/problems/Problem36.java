package problems;

import util.Numbers;

import java.util.ArrayList;

//Double-Base Palindromes.
public class Problem36 {
    private static ArrayList<Integer> doubleBasePalindromes = new ArrayList<>();
    private static int sum = 0;

    public static void main(String[] args){
        for (int i = 0; i < 1000000; i++){
            if (Numbers.isPalindrome(String.valueOf(i)) && Numbers.isPalindrome(Integer.toBinaryString(i))){
                doubleBasePalindromes.add(i);
            }
        }
        for (int d : doubleBasePalindromes){
            sum += d;
        }
        System.out.println("The sum of all integers under one million that are palindromes in base 10 and base 2 is: " + sum);

    }
}
