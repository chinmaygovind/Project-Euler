package problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import util.Fraction;
import util.Numbers;
import util.Sets;

//Investigating Progressive Numbers, n, which are also Square.
public class Problem141 {
    //n / d = q, r
    private static final HashSet<Long> progressiveSquares = new HashSet<>();
    private static final long limit = 1000000000000L;
    public static void main(String[] args) {
        /*
        for (long rootN = 1; rootN < 1000000; rootN++) {
            long n = rootN*rootN;
            boolean progressive = false;
            long d = (long) Math.floor(Math.pow(n, 1/3.0));
            while (d < rootN && !progressive) {
                double q = n / d;
                long r = n%d;
                if (r == d / q * d) {
                    sum += n;
                    System.out.println("rootN: " + rootN + ", n: " + n + ", d: " + d + ", q: " + q + ", r: " + r);
                    progressive = true;
                }
                d++;
            }
        }

         */
        for (int r = 1; r < Math.sqrt(limit); r++) {
            Fraction fracR = new Fraction(r, 1);
            //find value of a for which r + r^2 * a^3 = k^2
            ArrayList<Integer> factors = Numbers.getPrimeFactors(r);
            ArrayList<Integer> squareFactors = new ArrayList<>();
            int copy = r;
            for (int f : factors) {
                while (copy%(f*f) == 0) {
                    copy /= f*f;
                    squareFactors.add(f);
                }
            }
            //binary mask through square factors
            for (int b = 0; b < Math.pow(2, squareFactors.size()); b++) {
                Fraction a = new Fraction(1, 1);
                for (int i = 0; i < squareFactors.size(); i++) {
                    if ((b >> i)%2 == 1) a.denominator *= squareFactors.get(i);
                }
                for (int num = (int) a.denominator + 1; num <= a.denominator*200; num++) {
                    a = new Fraction(num, a.denominator);
                    Fraction fracN = fracR.clone();
                    fracN.multiply(fracR);
                    fracN.multiply(a); fracN.multiply(a); fracN.multiply(a);
                    fracN.add(fracR);
                    double n = fracN.value;//it was rounding jank again gosh dang it
                    if (n >= limit) break;
                    if (n != r && Math.floor(n) == n && Math.sqrt(n) == Math.floor(Math.sqrt(n))) {
                        //System.out.println("n: " + (long) n + ", rootN: " + Math.sqrt(n) + ", d: " + r * a.value + ", q: " + r * a.value * a.value + ", r: " + r + ", a: " + a.value);
                        if (n < limit) progressiveSquares.add((long) n);
                        //break findA;
                    }
                }
            }
            //System.out.println(r + ": " + Numbers.getPrimeFactors((int) r));
        }
        ArrayList<Long> sorted = new ArrayList<>(progressiveSquares);
        Collections.sort(sorted);
        System.out.println(sorted);
        System.out.println("The sum of all progressive squares below one trillion is: " + Sets.sumLongs(new ArrayList<>(progressiveSquares)));
    }
}

