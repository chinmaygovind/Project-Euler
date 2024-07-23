package problems;

import util.Fraction;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

//Fractions and Sum of Power of Two.
public class Problem175 {

    public static HashMap<BigInteger, Long> cache = new HashMap<>(
            Map.of(
                    BigInteger.ZERO, 1L,
                    BigInteger.ONE, 1L
            )
    );
    public static void main(String[] args) {
        System.out.println(f(BigInteger.TEN.pow(25)));
    }


    
    public static long f(long n) { return f(BigInteger.valueOf(n)); }

    public static long f(BigInteger n) {
        if (cache.containsKey(n)) return cache.get(n);
        long res;
        if (n.testBit(0)) res = f(n.subtract(BigInteger.ONE).divide(BigInteger.TWO));
        else res = f(n.divide(BigInteger.TWO)) + f(n.divide(BigInteger.TWO).subtract(BigInteger.ONE));
        cache.put(n, res);
        return res;
        //int depth = (int) (Math.log(n.doubleValue())/Math.log(2));
        //return fHelper(n, depth);
    }


}
