package problems;

import util.Numbers;
import util.Sets;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// RSA Encryption.
public class Problem182 {

    private static int p = 1009;
    private static int q = 3643;
    private static int n = p * q;
    private static BigInteger bigN = BigInteger.valueOf(n);
    private static int phi = (p - 1) * (q - 1);
    public static void main(String[] args) { // 3, 6, 10, 13, 17, 20, 24, 27, 31
        int high = 100000;
        int minUnconcealed = n;
        List<Integer> bestE = new ArrayList<>();
        long[] messages = new long[n];
        for (int m = 0; m < n; m++) messages[m] = m;
//        ArrayList<Integer> magicMessages = new ArrayList<>(List.of(0, 1, 346086, 1664850, 1664851, 2010936, 2010937, 3329701, 3675786));
        for (int e = 2; e < high; e++) {
            int unconcealed = 0;
            for (int m = 0; m < n; m++) {
                messages[m] = (messages[m] * m) % n;
                if (unconcealed <= 10 && messages[m] == m) {
                    unconcealed++;
                }
            }
            if (Numbers.gcd(e, phi) > 1) continue;
            // 3, 7, 10, 14, 17, 21, 24,
//            System.out.println(e + " out of " + phi);
            if (unconcealed < minUnconcealed) {
                minUnconcealed = unconcealed;
                bestE = new ArrayList<>(List.of(e));
                System.out.println("best E added: " + e + " | # unconcealed: " + minUnconcealed);
            } else if (unconcealed == minUnconcealed) {
                bestE.add(e);
                System.out.println("best E added: " + e + ", + 1 / 12: " + ((e + 1) / 12));
            }
        }
        // magic:
        // sum of 11 + 23 + 47 + ... + 3671135
        int i = 11;
        int[] toAdd = {12, 24, 12, 24, 12};
        int j = 0;
        ArrayList<Integer> fakeE = new ArrayList<>();
        while (i < high) {
            if ((i + 1) % 3036 != 0 && (i + 1) % 20640 != 0) {
//                System.out.println("Adding " + i);
                fakeE.add(i);
            }
            i += toAdd[j];
            j = (j + 1) % 5;
        }
        System.out.println(bestE);
        System.out.println(fakeE);
        System.out.println("The sum of the values of e such that the number of unconcealed messages is at a minimum is: " + Sets.sum(fakeE));
        // ( 3, 6, 10, 13, 17, 20, 24, 27, 31- 305928) * 12 - 1
//        System.out.println("The sum of the values of e such that the number of unconcealed messages is at a minimum is: " + Sets.sum(bestE));

    }


}
