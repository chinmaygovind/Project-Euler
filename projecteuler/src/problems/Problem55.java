package problems;


import util.Card;
import util.Numbers;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

//Lychrel Numbers.
public class Problem55 {
    private static int lychrelNumbers = 0;
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++){
            int checks = 1;
            BigInteger temp = new BigInteger(String.valueOf(i));
            temp = reverseAdd(temp);
            while (!Numbers.isPalindrome(String.valueOf(temp))){
                temp = reverseAdd(temp);
                checks++;
                if (checks >= 50){
                    lychrelNumbers++;
                    break;
                }
            }
        }
        System.out.println("The amount of theoretical Lychrel Numbers under 10,000 is: " + lychrelNumbers);
    }
    public static BigInteger reverseAdd(BigInteger num){
        return num.add(new BigInteger(new StringBuilder(String.valueOf(num)).reverse().toString()));
    }
}
