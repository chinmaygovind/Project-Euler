package problems;

import java.math.BigInteger;

//Convergents Of e.
public class Problem065 {
    private static int[] coefficients = new int[100];
    private static BigInteger[] convergent100;
    private static int digitSum = 0;
    public static void main(String[] args) {
        //fill up coefficients
        for (int i = 0; i < 100; i++){
            if (i == 0){
                coefficients[i] = 2;
            } else if (i%3 == 2){
                coefficients[i] = (i + 1) / 3 * 2;
            } else{
                coefficients[i] = 1;
            }
        }
        convergent100 = getEConvergents(100);
        System.out.println("The 100th convergent of the continued fraction of e is: " + convergent100[0] + "/" + convergent100[1]);
        for (char digit : convergent100[0].toString().toCharArray()){
            digitSum += Integer.parseInt(String.valueOf(digit));
        }
        System.out.println("The sum of numerator's digits is: " + digitSum);
    }

    public static BigInteger[] getEConvergents(int term){
        BigInteger[] fraction = new BigInteger[2];//0 = numerator, 1 = denominator
        for (int i = term - 1; i >= 0; i--){
            if (fraction[0] == null){
                fraction[0] = BigInteger.valueOf(coefficients[i]);
                fraction[1] = BigInteger.ONE;
            } else {
                BigInteger temp = fraction[0];
                fraction[0] = fraction[1].add(fraction[0].multiply(BigInteger.valueOf(coefficients[i])));
                fraction[1] = temp;
            }
        }
        return fraction;
    }
}
