package problems;


import util.Numbers;

import java.math.BigInteger;
import java.util.ArrayList;

//Spiral Primes.
public class Problem58 {
    private static int diagonals = 1;
    public static int primes = 0;
    private static int sideLength = 1;
    public static void main(String[] args) {
        int currentNum = 1;
        while ((double) primes/diagonals > 0.1 || sideLength == 1){
            for (int i = 0; i < 4; i++){
                diagonals++;
                currentNum+= sideLength + 1;
                if (Numbers.isPrime(currentNum)) primes++;
            }
            sideLength+=2;
        }

        System.out.println("The side length of the smallest square spiral where less than 10% of the numbers on the diagonals are prime is: " + sideLength);
    }
}
