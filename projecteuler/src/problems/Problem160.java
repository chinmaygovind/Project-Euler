package problems;

import util.Numbers;
import util.Sets;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;

//Factorial Trailing Digits.
public class Problem160 {

    private static final long N = 1_000_000;
    private static final BigInteger lastFiveMod = BigInteger.valueOf(100_000);
    private static final BigInteger lastSixMod = BigInteger.valueOf(1_000_000);
    private static final BigInteger lastSevenMod = BigInteger.valueOf(10_000_000);
    private static final BigInteger bigMod = BigInteger.TEN.pow(250);
    private static final BigInteger[] bigInts = new BigInteger[1_000_000];
    public static void main(String[] args) {
        for (int i = 0; i < 1_000_000; i++) {
            bigInts[i] = BigInteger.valueOf(i);
        }
        System.out.println("computed value = " + fastF(6));
        System.out.println("true value = " + trueF(1_000_000));

    }

    public static int f(long N) {
        BigInteger lastFive = bigInts[36288];
        for (long i = 10; i <= N; i+=10) {
            if (i == N) {
                lastFive = lastFive.multiply(bigInts[(int) N%100_000]);
                while (lastFive.mod(BigInteger.TEN).equals(BigInteger.ZERO))
                    lastFive = lastFive.divide(BigInteger.TEN);
                lastFive = lastFive.mod(lastSixMod);
                break;
            }
            if (i%1_000_000 == 0) System.out.println(i);
            long m = i;
            while (m % 10 == 0)
                m /= 10;
            m = m % 1_000_000;
            long n = i % 1_000_000;
            lastFive = lastFive.multiply(bigInts[(int) m]);
            lastFive = lastFive.multiply(bigInts[(int) n + 1])
                    .multiply(bigInts[(int) n + 2])
                    .multiply(bigInts[(int) n + 3])
                    .multiply(bigInts[(int) n + 4])
                    .multiply(bigInts[(int) n + 5])
                    .multiply(bigInts[(int) n + 6])
                    .multiply(bigInts[(int) n + 7])
                    .multiply(bigInts[(int) n + 8])
                    .multiply(bigInts[(int) n + 9]);
            while (lastFive.mod(BigInteger.TEN).equals(BigInteger.ZERO))
                lastFive = lastFive.divide(BigInteger.TEN);
            lastFive = lastFive.mod(lastSixMod);
        }
        return lastFive.mod(lastFiveMod).intValueExact();

    }

    public static BigInteger fastF(int tenTo) {
        BigInteger target = BigInteger.ONE;
        int totalInstances = 0;
        for (int i = 1; i < 100_000; i++) {
            if (i%10 == 0) continue;
            //how many times is i the mod 100k of n with 10s removed?
            int occurences = (int) Math.log10(Math.pow(10, tenTo+1)/i);//10, 100, 1000, etc.
            occurences += (int) (Math.pow(10, tenTo - 5) - (tenTo - 5));
            totalInstances += occurences;
            if (occurences > 0) {
                //if (i < 99990) System.out.printf("i: %d, instances: %d\n", i, occurences);
                target = target.multiply(bigInts[i].modPow(BigInteger.valueOf(occurences), bigMod));
                while (target.mod(BigInteger.TEN).equals(BigInteger.ZERO)) target = target.divide(BigInteger.TEN);
                target = target.mod(bigMod);
            }
        }
        System.out.println("total = " + totalInstances);
        return target.mod(lastFiveMod);

    }
    public static BigInteger specialF(long N) {
        BigInteger lastSeven = BigInteger.ONE;
        for (long i = N/10; i <= N; i++) {
            if (i%10 == 0) continue;
            lastSeven = lastSeven.multiply(BigInteger.valueOf(i));
            while (lastSeven.mod(BigInteger.TEN).equals(BigInteger.ZERO)) lastSeven = lastSeven.divide(BigInteger.TEN);
            lastSeven = lastSeven.mod(lastSevenMod);
        }
        return lastSeven;

    }

    public static BigInteger trueF(long N) {
        BigInteger lastFive = BigInteger.ONE;
        for (long i = 1; i <= N; i++) {
            lastFive = lastFive.multiply(BigInteger.valueOf(i));
            while (lastFive.mod(BigInteger.TEN).equals(BigInteger.ZERO)) lastFive = lastFive.divide(BigInteger.TEN);
            lastFive = lastFive.mod(bigMod);
        }
        return lastFive.mod(lastFiveMod);

    }
}


