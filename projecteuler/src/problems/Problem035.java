package problems;

import util.Numbers;

import java.util.ArrayList;

//Circular Primes.
public class Problem035 {
    private static ArrayList<Integer> circularPrimes = new ArrayList<>();

    public static void main(String[] args){
        for (int i = 0; i < 1000000; i++){
            checkCircularPrime(i);
        }
        System.out.println("The number of circular primes below one million is: " + circularPrimes.size());
    }

    private static void checkCircularPrime(int num){
        if (circularPrimes.contains(num)){
            return;
        }
        int rotations = 0;
        ArrayList<Integer> primes = new ArrayList<>();
        while (rotations < String.valueOf(num).length()){
            int rotatedPrime = (int) (num%Math.pow(10, rotations) * Math.pow(10, String.valueOf(num).length() - rotations) + Math.floor(num/Math.pow(10, rotations)));
            if (Numbers.isPrime(rotatedPrime)){
                if (!primes.contains(rotatedPrime)) primes.add(rotatedPrime);
            } else {
                return;
            }
            rotations++;
        }
        for (int prime : primes){
            circularPrimes.add(prime);
        }
    }
}
