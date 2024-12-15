package problems;

import util.Fraction;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        System.out.println(f(List.of(1)));
        System.out.println(f(2500));
        System.out.println(f(List.of(3,4,1,1,3)));
        System.out.println(f(2501));
        System.out.println(f(List.of(1,2,4,1,1,3)));
        //f(n) = f(X, x2, xs) = (X-1) * f(x2 + 1, xs) + f(x2, xs)
        //f(n + 1) = f(1, X-1, x2, xs) = f(X-1, x2, xs) = (X-2) * f(x2 + 1, xs) + f(x2, xs)
        //f(x2, xs) = 123456789
        //
        System.out.println(f(BigInteger.TEN.pow(25)));
        System.out.println(f(List.of(26,2,3,5,3,2,10,2,5,1,2,4,2,2,3,1,2,4,5)));
    }


    public static long f(List<Integer> sbe) {
        if (sbe.isEmpty()) return 1;
        if (sbe.size() == 1) return sbe.getFirst();
        List<Integer> l1 = new ArrayList<>(sbe.subList(1, sbe.size()));
        List<Integer> l2 = sbe.subList(1, sbe.size());
        l1.addFirst(l1.getFirst() + 1);
        l1.remove(1);
        return (sbe.getFirst() - 1) * f(l1) + f(l2);
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
