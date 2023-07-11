package problems;


import java.util.ArrayList;

import util.Numbers;

//Prime Summations.
public class Problem077 {
    private static ArrayList<Integer> primes = Numbers.generatePrimes(100000);
    private static int answer = 0;
    public static void main(String[] args) {
        while (primePartitions(answer) <= 5000){
            answer++;
        }
        System.out.println("The smallest number that can be written as the sum of primes in over 5000 different ways is: " + answer);
        System.out.println("The number of ways to write it as a sum of primes is: " + primePartitions(answer));
    }

    public static int primePartitions(int num){
        if (num == 0) return 1;
        if (num < 2) return 0;
        int totalPartitions = 0;
        for (int prime : primes){
            if (prime > num) break;
            totalPartitions += primePartitions(num - prime, prime);
        }
        return totalPartitions;
    }

    public static int primePartitions(int num, int a){
        if (num == 0) return 1;
        if (num < 2) return 0;
        int totalPartitions = 0;
        for (int prime : primes){
            if (prime > num || prime > a) break;
            totalPartitions += primePartitions(num - prime, prime);
        }
        return totalPartitions;
    }


}
