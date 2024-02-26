package problems;

import util.Numbers;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

//Hexadecimal Numbers.
public class Problem162 {

    public static void main(String[] args) {
        //int n = 16;
        //3: 4, 4: 262, 5: 10190, 6: 309680, 7: 8133504
        int n = 16;
        System.out.printf("The number of %d digit hexadecimal numbers with 0, 1 and A, in hex, is: %s", n, Long.toHexString(count(n)).toUpperCase());
    }

    public static long count(int N) {
        long total = 0;
        for (int digits = 3; digits <= N; digits++) {
            long count = 0;
            for (int zeroes = 1; zeroes <= digits; zeroes++) {
                for (int ones = 1; ones + zeroes <= digits; ones++) {
                    for (int as = 1; as + ones + zeroes <= digits; as++) {
                        long zeroesC = Numbers.choose(digits-1, zeroes);
                        long onesC = Numbers.choose(digits-zeroes, ones);
                        long asC = Numbers.choose(digits-zeroes-ones, as);
                        long mult = (long) Math.pow(13, digits-zeroes-ones-as);
                        long prod = zeroesC * onesC * asC * mult;
                        //System.out.printf("d = %d, %d 0s, %d 1s, %d As => %d * %d * %d * %d = %d\n", digits, zeroes, ones, as, zeroesC, onesC, asC, mult, prod);
                        count += prod;
                    }
                }
            }
            total += count;
        }
        return total;
    }

    public static long naiveCount(int N) {
        long limit = 1L << (4*N);
        long count = 0;
        for (int i = 0; i < limit; i++) {
            String s = Long.toHexString(i);
            if (s.contains("a") && s.contains("0") && s.contains("1")) {
                //System.out.println(s);
                count++;
            }
        }
        return count;
    }
}

