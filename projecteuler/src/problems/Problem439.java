package problems;


import util.Numbers;
import util.Sets;

import java.util.ArrayList;

// Sum of Sum of Divisors.
public class Problem439 {

    private static long N = 10000000;
    private static ArrayList<Long> pentagonal = new ArrayList<>();
    private static ArrayList<Long> dCache = new ArrayList<>();
    public static void main(String[] args) {
        dCache.add(0L);
        pentagonal.add(1L);
        pentagonal.add(2L);
        long m = 2;
        while (pentagonal.getLast() < N) {
            pentagonal.add(m * (3 * m - 1) / 2);
            pentagonal.add(-m * (3 * -m - 1) / 2);
            m++;
        }
        long step = N / 1_000;
        System.out.println(pentagonal);
        for (long i = 1; i <= N; i++) {
            long d = d(i);
            if (i % step == 0) System.out.printf("d(%d) = %d\n", i, d);
            dCache.add(d);
        }
    }

    public static long d(long i) {
        if (i < dCache.size()) {
            return dCache.get((int) i);
        } else {
            long s = 0;
            int sign = 0;
            for (long p : pentagonal) {
                if (p == i) {
                    s += i * (sign % 4 < 2 ? 1 : -1);
                } else if (p > i) {
                    break;
                }
                s += d(i - p) * (sign % 4 < 2 ? 1 : -1);
                sign++;
            }
            return s;
        }
    }
    public static long trueD(long i) {
        long s = 0;
        for (long x = 1; x <= i; x++) {
            if (i % x == 0) s += x;
        }
        return s;
    }
}

