package problems;


import util.Numbers;

import java.util.ArrayList;

//Consecutive Prime Sum.
public class Problem50 {
    private static ArrayList<Integer> primes = Numbers.generatePrimes(500000);
    private static int longestChainLength = 0;
    private static int longestChainPrime = 0;
    public static void main(String[] args){
        for (int i = 0; i < primes.size() - 2; i++){
            for (int j = i + 1; j < primes.size() - 1; j++){
                if (j - i + 1 < longestChainLength){
                    continue;
                } else {
                    int sum = 0;
                    for (int k = i; k <= j; k++) {
                        sum += primes.get(k);
                    }
                    if (sum >= 1000000){
                        break;
                    }
                    if (Numbers.isPrime(sum)) {
                        longestChainLength = j - i + 1;
                        longestChainPrime = sum;
                    }
                }
            }
        }
        System.out.println("The prime below one million that can be written as the sum of the most consecutive primes is: " + longestChainPrime);
        System.out.println("The length of the prime chain is: " + longestChainLength);
    }

}
