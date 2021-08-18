package problems;

import java.math.BigInteger;

//1000-digit Fibonacci Number.
public class Problem25 {
    private static BigInteger[] lastTwo = new BigInteger[] {BigInteger.ONE, BigInteger.ONE};
    private static int currentIndex = 2;
    public static void main(String[] args){
        while (lastTwo[1].toString().length() < 1000){
            BigInteger temp = lastTwo[0].add(lastTwo[1]);
            lastTwo[0] = lastTwo[1];
            lastTwo[1] = temp;
            currentIndex++;
        }
        System.out.println("The index of the first Fibonacci number with 1000 digits is: " + currentIndex);
    }

}
