package problems;

import java.math.BigInteger;

// Integer Partition Equations.
public class Problem207 {
    public static void main(String[] args) {
            //

    //        for (int t = 1; t <= 100; t++) {
    //            BigInteger fourT = BigInteger.TWO.pow(2 * t);
    //            BigInteger twoT = BigInteger.TWO.pow(t);
    //            BigInteger k = fourT.subtract(twoT);
    //            System.out.println(k);
    //        }

    // 2 = 2 * 1
    //12 = 4 * 3
    //56 = 8 * 7
    //240 = 16 * 15
    //992 = 32 * 31
    //4032
    //16256
    //65280
    //261632
        double target = 1 / 12345.0;
        int perfect = 0;
        for (long n = 1; n <= 1_000_000; n++) {
            // when n + 1 is power of 2, that's golden - so n is all 1s in binary
            if ((n & n + 1) == 0) {
                perfect++;
//                System.out.println(n);
            }
            long m = n * (n + 1);
            double frac = (double) perfect / n;
            if (frac < target) {
                System.out.println("The smallest m for which P(m) < 1/12345 is: " + m);
                break;
            }

        }
        // bounded at n = 209866, m = 44043947822
        // 17 perfect below this
        //
    }


}
