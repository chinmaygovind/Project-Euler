package problems;


import java.math.BigInteger;
import java.util.ArrayList;

//Square Root Convergents.
public class Problem57 {
    private static ArrayList<BigInteger> numerators = new ArrayList<>();
    private static ArrayList<BigInteger> denominators = new ArrayList<>();
    private static int numeratorsWithMoreDigits = 0;
    public static void main(String[] args) {
        numerators.add(new BigInteger("3"));
        numerators.add(new BigInteger("7"));
        denominators.add(new BigInteger("2"));
        denominators.add(new BigInteger("5"));
        for (int i = 2; i < 1000; i++){
            numerators.add(numerators.get(i - 1).multiply(BigInteger.TWO).add(numerators.get(i - 2)));
            denominators.add(denominators.get(i - 1).multiply(BigInteger.TWO).add(denominators.get(i - 2)));
            if (numerators.get(i).toString().length() > denominators.get(i).toString().length()){
                numeratorsWithMoreDigits++;
            }
        };
        System.out.println("In the first 1000 term expansions of root 2's infinite fraction, the number of which the numerator is higher than the denominator is: " + numeratorsWithMoreDigits);
    }
}
