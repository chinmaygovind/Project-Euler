package problems;

import util.Fraction;
import util.Numbers;

// Common Cathetus Right-angled Triangles.
public class Problem176 {
    public static void main(String[] args) {
//        // 2, 4, 6, 8
//        // diff of squares = n^2
//        // a^2 - b^2 = n^2 = (a + b)(a - b)
//        // # of solutions to (a + b)(a - b) = n^2
//        // 144 = (37 + 35)(37 - 35) = (15 + 9)(15 - 9) = (20 + 16)(20 - 16) = (13 + 5)(13 - 5)
//        // 1, 2, 3, 4, 6, 8, 9, 12, 18, 16, 24, 36, 48, 72, 144
//        // 16 * 9
//        // 1, 3, 9
//        // 45 = 1, 3, 5, 9, 15, 45
//        // 12 = 1, 2, 3, 4, 6, 12
//        // same parity factor pairs?
//        // if n odd, then # of odd factors
//        // if n even, then # of factors of n/2
//        int N = 5000;
//        Numbers.generateCachedPrimes(N*N);
//        // 47547 = 3*3*3*3*587
//        //
////        int highestPairs = 0;
////        for (int i = 1; i <= N; i++) {
////            int i2 = i * i;
////            int pairs = 0;
////            for (Integer f : Numbers.getFactors(i2, true)) {
////                if ((f + (i2 / f)) % 2 == 0) pairs++;
////            }
////            pairs /= 2;
////            if (pairs > highestPairs) {
////                System.out.println(i + ": " +
////                        pairs + " | true factors: " + Numbers.getFactors(i2, true).size() + " n/2 factors / 2: " + Numbers.getFactors(i2/4, true).size()/2);
////                highestPairs = pairs;
////            }
//
//            if (pairs == 47547) {
//                System.out.println("mememememe: " + i + " squared=" + i2);
//            }
//        }
        // im a genius
        // so find smallest number with 2*47547+1=95095 = 5*7*11*13*19 factors, multiply that shi by 4, take the sqrt, done
        // 47547 = 3*3*3*3*587
        // 2^4 * 3^2 * 4 = 2^6 * 3^2 = 3^3
        // 3^587 * 5^3 * 7^3 * 11^3
        // 2^18 * 3^12 * 5^10 * 7^6 * 11^4 * 2^2 -> sqrt -> 2^10 * 3^6 * 5^5 * 7^3 * 11^2 = 96818198400000
        // they call me the pen and paper goat
        System.out.println("The smallest integer that can be the length of a leg of exactly 47547 right triangles is: 96818198400000");
    }


}
