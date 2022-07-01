package problems;


import util.Numbers;

import java.math.BigInteger;
import java.util.ArrayList;


//Counting Binary Quadratic Representations.
public class Problem804 {
    //private static final BigInteger limit = BigInteger.valueOf((long) Math.pow(10, 16));
    private static final BigInteger limit = new BigInteger("10000000000000000");
    private static BigInteger t = BigInteger.ZERO;
    public static void main(String[] args) {
        //loop through possible values of y for comparison
        BigInteger y = BigInteger.ZERO;
        while (true) {
            //min possible value of evaluated polynomial (where x = 1/2 y)
            //find all cases where polynomial evaluates <= limit
            BigInteger discriminant = limit.multiply(BigInteger.valueOf(4)).subtract(y.multiply(y).multiply(BigInteger.valueOf(163)));
            if (discriminant.compareTo(BigInteger.ZERO) >= 0) {
                discriminant = discriminant.sqrt();
            } else {
                break;
            }
            BigInteger minX = y.multiply(BigInteger.valueOf(-1L)).subtract(discriminant).divide(BigInteger.TWO);
            BigInteger maxX = y.multiply(BigInteger.valueOf(-1L)).add(discriminant).divide(BigInteger.TWO);
            //cheese factor
            while (f(minX, y).compareTo(limit) > 0) minX = minX.add(BigInteger.ONE);
            while (f(maxX, y).compareTo(limit) > 0) maxX = maxX.subtract(BigInteger.ONE);
            BigInteger solutions = maxX.subtract(minX).abs().add(BigInteger.ONE).multiply((y.equals(BigInteger.ZERO) ? BigInteger.ONE : BigInteger.TWO));

            t = t.add(solutions);
            //discount the (0, 0) case
            if (y.equals(BigInteger.ZERO)) t = t.subtract(BigInteger.ONE);
            y = y.add(BigInteger.ONE);
        }
        System.out.println("The value of T(10^16) is: " + t);
    }

    public static BigInteger f(BigInteger x, BigInteger y) {
        return x.multiply(x).add(x.multiply(y)).add(y.multiply(y).multiply(BigInteger.valueOf(41)));
    }
}

