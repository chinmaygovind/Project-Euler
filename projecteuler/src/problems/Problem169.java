package problems;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;

//Sums of Powers of Two
public class Problem169 {

    public static HashMap<BigInteger, long[]> cache = new HashMap<>();

    public static void main(String[] args) {
        System.out.printf(
                "The number of ways to express 10^25 as a sum of integer powers of using each power no more than twice is: %d\n", f(BigInteger.TEN.pow(25)));
    }

    public static long f(BigInteger n) {
        int depth = (int) (Math.log(n.doubleValue())/Math.log(2));
        return fHelper(n, depth);
    }

    public static long fHelper(BigInteger n, int depth) {
        if (cache.containsKey(n) && cache.get(n)[depth] > 0) {
            //System.out.printf("f(%s, %d) = %d\n", n, depth, cache.get(n)[depth]);
            return cache.get(n)[depth];
        }
        BigInteger val = BigInteger.TWO.pow(depth);
        BigInteger depthCap = BigInteger.TWO.pow(depth + 2).subtract(BigInteger.TWO);
        long res;
        if (n.compareTo(BigInteger.ZERO) < 0) res = 0;
        else if (n.compareTo(depthCap) > 0) res = 0;
        else if (depth == 0) {
            if (n.compareTo(BigInteger.ZERO) == 0 || n.compareTo(BigInteger.ONE) == 0 || n.compareTo(BigInteger.TWO) == 0)
                res = 1;
            else
                res = 0;
        } else {
            res = fHelper(n, depth-1) +
                    fHelper(n.subtract(val), depth-1) +
                    fHelper(n.subtract(val.multiply(BigInteger.TWO)), depth-1);
        }
        //System.out.printf("f(%s, %d) = %d\n", n, depth, res);
        if (!cache.containsKey(n)) {
            cache.put(n, new long[100]);
            for (int i = 0; i < 100; i++) cache.get(n)[i] = -1;
        }
        cache.get(n)[depth] = res;
        return res;


    }
}
