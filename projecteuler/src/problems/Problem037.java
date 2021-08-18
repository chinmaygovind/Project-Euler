package problems;


import util.Numbers;

import java.util.ArrayList;

//Truncatable Primes.
public class Problem037 {
    private static ArrayList<Integer> truncatablePrimes = new ArrayList<>();
    private static int sum = 0;
    public static void main(String[] args){
        int num = 10;
        while (truncatablePrimes.size() < 11){
            if (isTruncatablePrime(num)){
                truncatablePrimes.add(num);
            }
            num++;
        }
        for (int d : truncatablePrimes){
            sum += d;
        }
        System.out.println("The sum of all 11 left-right truncatable primes is: " + sum);

    }

    public static boolean isTruncatablePrime(int num){
        int digits = 0;
        String strNum = String.valueOf(num);
        while (digits < strNum.length()){
            if (!Numbers.isPrime(Integer.parseInt(strNum.substring(0, strNum.length()-digits))) ||
                    !Numbers.isPrime(Integer.parseInt(strNum.substring(digits)))){
                return false;
            }
            digits++;
        }
        return true;
    }
}
