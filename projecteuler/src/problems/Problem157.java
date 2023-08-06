package problems;

import java.util.ArrayList;
import java.util.HashSet;

import util.Numbers;

//Base-10 Diophantine Reciprocal.
public class Problem157 {
    //1/a + 1/b = p / 10^n.
    //(a + b) / ab = p / 10^n
    //(a + b) * 10^n / ab = p.
    private static long solutions = 0;
    public static void main(String[] args) {
        //ArrayList<Solution> solutions = new ArrayList<>();
        
        Numbers.generateCachedPrimes(1_000_000);

        for (int N = 1; N <= 9; N++) {
            long TEN_TO_N = (long) Math.pow(10, N);
            ArrayList<Long> TEN_TO_N_FACTORS = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                for (int j = 0; j <= N; j++) {
                    TEN_TO_N_FACTORS.add((long) (Math.pow(2, i) * Math.pow(5,  j)));
                }
            }
            for (long a = 1; a <= 2 * TEN_TO_N; a++) {
                if (a < Math.sqrt(TEN_TO_N)) {
                    long mod = (a - (TEN_TO_N % a)) % a;
                    ArrayList<Integer> factors = Numbers.getFactors((int) a, true);
                    HashSet<Long> totalFactors = new HashSet<>();
                    for (Long f : TEN_TO_N_FACTORS) {
                        for (Integer f2 : factors) {
                            if (f*f2 <= TEN_TO_N) totalFactors.add(f*f2);
                        }
                    }   
                    //System.out.println(a + ": " + totalFactors);
                    for (Long f : totalFactors) {
                        if (f % a == mod) {
                            solutions++;
                        }
                    }

                } else if (a == TEN_TO_N) {
                    solutions++;
                    continue;
                } else {
                    long MAX_P = 2 * TEN_TO_N / a;
                    for (long p = TEN_TO_N/a; p <= MAX_P; p++) {
                    //(a + b) * 10^n / ab = p.
                    //10^n / a + 10^n / b = p
                    //p - 10^n / a = 10^n / b
                    //b = 10^n / (p - 10^n / a)
                    //b = 10^n / ((ap - 10^n) / a)
                    //b = 10^n * a / (ap - 10^n)
                    //same as counting divisors of 10^n * a that are equivalent to 10^n mod a
                    long temp = a * p - TEN_TO_N;
                    if (temp > 0 && (TEN_TO_N * a) % temp == 0) {
                        solutions++;
                    }
                    }
                }
            }
        }
        System.out.println("The number of integer solutions to 1/a + 1/b = p / 10^n for n from 1 to 9 is: " + solutions);
    }

    
    static class Solution {
            private long a;
            private long b;

            public Solution(long a, long b) {
                this.a = a;
                this.b = b;
            }

            public String toString() {
                return String.format("(%d, %d)", a, b);
            }
    }
}


