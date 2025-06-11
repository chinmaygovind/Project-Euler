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
        System.out.println(reduceProblem(123456789, 987654321));
    }

    /**
     * solves f(n + 1) / f(n) = n/d
     * @param num int
     * @param denom int
     * @return n
     */
    public static int reduceProblem(int num, int denom) {
        // f(x) = nr ways, full stop
        // e(x) = nr ways if x even = f(x/2) + f((x-2)/2)
        // o(x) = nr ways if x odd = f((x-1)/2)
        // assume n is even.
        // f(n + 1) / f(n) = n/d
        // d * f(n + 1) = n * f(n)
        // f(n + 1) * d = n * f(n)
        // (f(n/2)) * d = n * f(n/2) + n * f(n/2 - 1)
        // n' = n/2
        // (f(n')) * d = n * f(n') + n * f(n' - 1)
        // f(n') * (d - n) = f(n' - 1) * n
        // f(n') / f(n' - 1) = n / (d - n)
        // m = n' - 1
        // f(m + 1) / f(m) = n / d - n
        // m = n/2 - 1, n = 2(m + 1)
        System.out.printf("Solving f(n + 1)/f(n) = %d/%d\n", num, denom);
        if (denom < 100) {
            solveProblem(num, denom);
        }
        return 2 * (reduceProblem(num, denom - num) + 1);
    }

    /**
     * solves f(n + 1)/f(n) = num / denom
     * @param num int
     * @param denom int
     * @return n
     */
    public static int solveProblem(int num, int denom) {
        int n = 1;
        Fraction target = new Fraction(num, denom);
        long fn = f(n);
        long fn1 = f(n + 1);
        while (!target.equals(new Fraction(fn1, fn))) {
            n++;
            fn = fn1;
            fn1 = f(n + 1);
        }
        return n;
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
