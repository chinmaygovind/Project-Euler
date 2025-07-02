package problems;

import util.Fraction;
import util.Numbers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Consecutive Positive Divisors.
public class Problem179 {
    private static int N = 10_000_000;
    public static void main(String[] args) {
        int total = 0;
        int[] numFactors = new int[N + 1];
        for (int f = 1; f <= N; f++) {
            for (int i = 0; i <= N; i+= f) {
                numFactors[i]++;
            }
        }
        for (int n = 2; n < N; n++) {
            if (numFactors[n] == numFactors[n + 1]) total++;
        }
        System.out.println("The number of integers 1 < n < 10^7 for which n and n + 1 have the same number of factors is: " + total);
    }


}
