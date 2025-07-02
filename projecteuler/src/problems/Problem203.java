package problems;

import util.Numbers;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;

// Squarefree Binomial Coefficients.
public class Problem203 {
    public static void main(String[] args) {
        HashSet<BigInteger> squareFreeNums = new HashSet<>();
        for (long n = 1; n <= 50; n++) {
//            System.out.println(n + " ------------------");
            for (long k = 0; k <= n; k++) {
                BigInteger c = Numbers.factorial(n).divide(Numbers.factorial(n - k).multiply(Numbers.factorial(k)));
                boolean squareFree = true;
                for (int s = 2; s <= 51; s++) {
                    if (c.mod(BigInteger.valueOf(s * s)).equals(BigInteger.ZERO)) {
                        squareFree = false;
                    }
                }
//                System.out.println(c + ": squarefree = " + squareFree);
                if (squareFree) {
//                    System.out.println(c);
                    squareFreeNums.add(c);
                }
            }
        }
        BigInteger total = BigInteger.ZERO;
        for (BigInteger b : squareFreeNums) {
            total = total.add(b);
        }
        System.out.printf("The sum of distinct squarefree numbers in the first 51 rows of Pascal's Triangle is: %s\n", total);
    }


}
