package problems;


import util.Numbers;

import java.util.ArrayList;

//Goldbach's Other Conjecture
public class Problem46 {
    private static ArrayList<Integer> primes = Numbers.generatePrimes(100000);
    private static int num = 9;
    public static void main(String[] args){
        while (checkNumber(num)){
            num+=2;
        }
        System.out.println("The smallest odd composite that can't be written as the sum of a prime and twice a square is: " + num);
    }

    private static boolean checkNumber(int num){
        if (Numbers.isPrime(num)) return true;
        int i = 0;
        while (primes.get(i) < num){
            if (Math.sqrt((num - primes.get(i))/2.0)%1 == 0){
                return true;
            }
            i++;
        }
        return false;
    }
}
