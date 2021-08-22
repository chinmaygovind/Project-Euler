package problems;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

//Square Root Digital Expansion.
public class Problem080 {
    private static int totalDigitalSum = 0;
    private static MathContext precision = new MathContext(200);
    public static void main(String[] args) {
        for (int i = 1; i < 100; i++){
            if (Math.sqrt(i)%1 == 0) continue;
            for (char digit : new BigDecimal(BigInteger.valueOf(i)).sqrt(precision).toString().substring(0, 101 + (int) Math.floor(Math.log10(Math.sqrt(i)))).replace('.', '0').toCharArray()){
                totalDigitalSum += Character.getNumericValue(digit);
            }
        }
        System.out.println("The sum of the first hundred digits of the irrational square roots of the first hundred natural numbers is: " + totalDigitalSum);
    }

}
