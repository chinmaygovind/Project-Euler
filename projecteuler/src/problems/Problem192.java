package problems;

import util.Numbers;

import java.util.ArrayList;
import java.util.Arrays;

// Best Approximations.
public class Problem192 {
    private static int N = 30;
    public static void main(String[] args) {
        for (int n = 1; n <= 100_000; n++) {
            int sqrtNInt = (int) Math.sqrt(n);
            if (sqrtNInt * sqrtNInt == n) continue;
            double sqrtN = Math.sqrt(n);
            ArrayList<Integer> conv = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                conv.add((int) sqrtN);
                sqrtN = sqrtN - (int) sqrtN;
                sqrtN = 1 / sqrtN;
            }
            double bestError = 1;
            int bestDenom = 1;
            for (int lim = 1; lim < 50; lim++) {
                double approx = 1;
                int denom = 1;
                for (int i = lim; i >= 0; i--) {
                    approx = 1 / approx;
                    approx += conv.get(i);
                }
                if (Math.abs(sqrtN - approx) < bestError) {
//                    bestDenom = Math.abs(sqrtN - approx);
//                    bestDenom =
                }
            }
        }
    }


}
