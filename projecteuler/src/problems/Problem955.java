package problems;


import util.Numbers;

import java.math.BigInteger;
import java.util.*;

// Finding Triangles.
public class Problem955 {
    private static long N = 200;
    public static void main(String[] args) {
        BigInteger last = BigInteger.valueOf(2);
//        BigInteger last = new BigInteger("4173630053778408183884776");
        BigInteger curr = BigInteger.ZERO;
        int triangles = 2;
        while (triangles <= N) {
//            System.out.println(triangles);
//            System.out.println(last);
            BigInteger a2a = last.multiply(last).add(last);
            // factorize a^2 + a = a (a + 1)
            ArrayList<BigInteger> factors = new ArrayList<>();
            ArrayList<BigInteger> aFactors = Numbers.getFactors(last);
            ArrayList<BigInteger> a1Factors = Numbers.getFactors(last.add(BigInteger.ONE));
            // a, a1 coprime -> divisors unique, can take product to get divisors of a^2 + a
            for (BigInteger f : aFactors) {
                for (BigInteger f2 : a1Factors) {
                    factors.add(f.multiply(f2));
                }
            }


            // find (c + b + 1)(c - b)
            BigInteger smallestB = null;
            BigInteger smallestC = null;
//            System.out.println(a2a + ": " + factors);
            for (BigInteger f : factors) {
                BigInteger f2 = a2a.divide(f);
                //f = c + b + 1
                //f2 = c - b
                //f + f2 = 2c + 1
                if (f.add(f2).mod(BigInteger.TWO).equals(BigInteger.ZERO)) continue;
                BigInteger c = f.add(f2).subtract(BigInteger.ONE).divide(BigInteger.TWO);
                BigInteger b = c.subtract(f2);
//                System.out.println(b + ", " + c);
                if ((smallestC == null || c.compareTo(smallestC) < 0) && c.compareTo(last) > 0 && b.compareTo(BigInteger.ZERO) > 0) {
                    smallestB = b;
                    smallestC = c;
                }
            }
            BigInteger next = smallestC.multiply(smallestC.add(BigInteger.ONE)).divide(BigInteger.TWO);
            curr = curr.add(smallestB);
//            System.out.println("Found " + triangles + "th triangle " + next + " at sequence length " + curr + " with base num " + smallestC);
            triangles++;
            last = smallestC;
        }
        System.out.printf("The %dth triangle number in the sequence is found at index %d\n", N, curr);

    }



}

