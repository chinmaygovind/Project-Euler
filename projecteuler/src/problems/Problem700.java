package problems;


// ovo sign

import util.Numbers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

// Eulercoin.
public class Problem700 {
    private static long start = 1504170715041707L;
    private static long N = 4503599627370517L;

    private static long magic = 1076492;
    public static void main(String[] args) {
        long last = start;
        long n = start;
        long total = last;
        System.out.println(start);
        int i = 1;
        while (last > magic) { // trust
            n = (n + start) % N;
            i++;
            if (n < last) {
                last = n;
                total += last;
                System.out.println(last);
            }
        }
        BigInteger inv = new BigInteger(String.valueOf((Numbers.xgcd(start, N)[1] + N) % N));
        // System.out.println("inv: " + inv);
        HashMap<Long, Long> map = new HashMap<>();
        // System.out.println("magic!");
        BigInteger bigN = BigInteger.valueOf(N);
        for (long j = 1; j < magic; j++) {
            long a = inv.multiply(BigInteger.valueOf(j)).mod(bigN).longValueExact();
            // System.out.printf("1504170715041707 * %d = %d\n", a, j);
            map.put(a, j);
        }
        // System.out.println("inverted!");
        // i have no idea what i am doing
        while (!map.isEmpty()) {
            // get min A
            long minA = -1;
            for (long a : map.keySet()) {
                if (minA == -1 || a < minA) minA = a;
            }
            long minAFriend = map.get(minA);
            ArrayList<Long> toRemove = new ArrayList<>();
            for (long a : map.keySet()) {
                if (map.get(a) >= minAFriend) toRemove.add(a);
            }
            for (long a : toRemove) {
                map.remove(a);
            }
            total += minAFriend;
            System.out.println(minAFriend);
        }
        // wtf it works
        // im a genius
        System.out.println("The sum of all Eulercoins is: " + total);
    }
}

