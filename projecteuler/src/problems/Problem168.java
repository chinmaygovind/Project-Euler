package problems;

import util.Fraction;

import java.math.BigInteger;
import java.util.*;

//Number Rotations.
public class Problem168 {

    //we need
    //a + b | (b * 10^(d - 1) + a/10))
    public static HashMap<Integer, Integer> lastFiveValues = new HashMap<>();
    public static HashSet<BigInteger> sols = new HashSet<>();
    public static void main(String[] args) {
        //observations:
        //all repeated numbers AAAAA satisfy the condition (trivial cases)
        //first nontrivial cases are in 6-digit numbers
        for (int d = 2; d <= 100; d++) {
            //fractions to look at:
            // 1/9ths, 1/19ths, 1/29ths... 1/99ths
            //long nines = (long) Math.pow(10, d) - 1;
            BigInteger nines = BigInteger.TEN.pow(d).subtract(BigInteger.ONE);
            //long low = (long) Math.pow(10, d - 1);
            BigInteger low = BigInteger.TEN.pow(d - 1);
            for (int denom = 9; denom < 200; denom += 10) {
                if (!nines.mod(BigInteger.valueOf(denom)).equals(BigInteger.ZERO)
                        || nines.equals(BigInteger.valueOf(denom))) continue;
                for (int num = 1; num <= denom; num++) {
                    //long n = nines * num / denom;
                    BigInteger n = nines.multiply(BigInteger.valueOf(num)).divide(BigInteger.valueOf(denom));
                    if (n.compareTo(low) < 0) continue;
                    //int lastFive = n % 100_000;
                    int lastFive = n.mod(BigInteger.valueOf(100_000)).intValue();
                    //long rotated = (n % 10) * (long) Math.pow(10, d - 1) + n / 10;
                    BigInteger rotated = (n.mod(BigInteger.TEN))
                            .multiply(BigInteger.TEN.pow(d - 1))
                            .add(n.divide(BigInteger.TEN));
                    if (rotated.mod(n).equals(BigInteger.ZERO)) {
                        //System.out.printf("%s | %s with multiplier %s, and ratio %d/%d, and expected multiplier %d\n", n, rotated, rotated.divide(n), num, denom, (int) (new Fraction(num, denom).denominator+1)/10);
                        sols.add(n);
                        //lastFiveValues.put(lastFive, lastFiveValues.getOrDefault(lastFive, 0) + 1);
                    } else {
                        //System.out.printf("fail: %s | %s with multiplier %s, and ratio %d/%d\n", n, rotated, rotated.divide(n), num, denom);

                    }
                }
            }
        }

        int total = 0;
        //System.out.println(sols.size());
        for (BigInteger sol : sols) {
            total += sol.mod(BigInteger.valueOf(100_000)).intValue();
            total %= 100_000;
        }
        System.out.println("The last 5 digits of the sum of all right-rotatable divisors n, 10 < n < 10^100, is: " + total);
    }
}
