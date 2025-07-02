package problems;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

// Nth Digit of Reciprocals.
public class Problem820 {

    private static int N = 100000;
    public static void main(String[] args) {
        int sum = 0;
        for (int k = 1; k <= N; k++) {
            if (k % 1000 == 0) System.out.println(k);
            // if its only factors are 2 and 5 then get that shi outta here
            int kCopy = k;
            while (kCopy % 2 == 0) kCopy /= 2;
            while (kCopy % 5 == 0) kCopy /= 5;
            if (kCopy == 1) continue;
            List<Integer> residues = new ArrayList<>();
            int div = 1;
            int quotient = 0;
            int lim = 1;
            while (residues.size() < k + lim) {
                while (div < k) {
                    div *= 10;
                    residues.add(0);
                    lim++;
                }
                quotient = div / k;
                residues.add(quotient);
                div = (div % k) * 10;
            }
            int known = residues.size();
            int remaining = N - known;
        }
        System.out.printf("The sum of the nth digit of 1/n from n from 1 through %d, S(%d) = %d", N, N, sum);
    }

}
