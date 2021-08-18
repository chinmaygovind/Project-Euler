package problems;


import java.math.BigInteger;

public class Problem16 {
    private static BigInteger twoToThousand = BigInteger.ONE;
    private static int sum;
    public static void main(String args[]){
        for (int i = 0; i < 1000; i++){
            twoToThousand = twoToThousand.multiply(BigInteger.TWO);
        }
        for (char digit:twoToThousand.toString().toCharArray()){
            sum += Integer.parseInt(String.valueOf(digit));
        }
        System.out.println("The sum of the digits of 2^1000 is: " + sum);
    }


}
