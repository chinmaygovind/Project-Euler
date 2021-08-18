package problems;

import java.math.BigInteger;

public class Problem020 {

    private static int digitSum = 0;

    public static void main(String[] args){
        for (char digit : factorial(100).toString().toCharArray()){
            digitSum += Integer.parseInt(String.valueOf(digit));
        }
        System.out.println("The sum of the digits of 100 factorial is: " + digitSum);
    }

    public static BigInteger factorial(int num){
        BigInteger total = BigInteger.ONE;
        int temp = num;
        while (temp != 1){
            total = total.multiply(new BigInteger(String.valueOf(temp)));
            temp--;
        }
        return total;
    }
}
