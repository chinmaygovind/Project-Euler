package problems;


import util.Numbers;

import java.math.BigInteger;

//Powerful Digit Sum.
public class Problem056 {
    private static int maxDigitalSum = 0;
    public static void main(String[] args) {
        for (int a = 1; a < 100; a++){
            for (int b = 1; b < 100; b++){
                int productSum = getDigitalSum(a, b);
                if (productSum > maxDigitalSum){
                    maxDigitalSum = productSum;
                }
            }
        }
        System.out.println("The highest digital sum of a number of the form a^b with a and b under 100 is: " + maxDigitalSum);
    }
    public static int getDigitalSum(int a, int b){
        BigInteger powerProduct = BigInteger.ONE;
        BigInteger base = new BigInteger(String.valueOf(a));
        for (int i = 0; i < b; i++){
            powerProduct = powerProduct.multiply(base);
        }
        int sum = 0;
        for (char digit : powerProduct.toString().toCharArray()){
            sum += Integer.parseInt(String.valueOf(digit));
        }
        return sum;
    }
}
