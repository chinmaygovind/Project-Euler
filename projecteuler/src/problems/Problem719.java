package problems;


import util.Numbers;
import util.Sets;

import java.util.*;

// Number Splitting.
public class Problem719 {
    // takes a bit lmao but eh
    private static int sqrtN = 1_000_000;
    public static void main(String[] args) {
        long t = 0;
        // System.out.println(decompose("2025", 45));
        for (long i = 4; i <= sqrtN; i++) {
            // if (i % 1000 == 0) System.out.println(i);
            long i2 = i * i;
            // digital root check
            if (i2 % 9 != i % 9) continue;

            String i2String = String.valueOf(i2);
            for (List<Long> l : decompose(i2String, i)) {
                if (Sets.sumLongs(l) == i) {
                    t += i2;
                    break;
                }
            }
        }
        System.out.printf("The sum of all S numbers <= 10^12 is: %d", t);
    }

    public static List<List<Long>> decompose(String n, long original) {
        if (n.isEmpty()) {
            return new ArrayList<>();
        }
        if (n.length() == 1) {
            return new ArrayList<>(List.of(new ArrayList<>(List.of(Long.parseLong(n)))));
        }
        List<List<Long>> lists = new ArrayList<>();
        for (int idx = 1; idx <= n.length(); idx++) {
            Long sub = Long.parseLong(n.substring(0, idx));
            if (sub > original) break;
            List<List<Long>> rest = decompose(n.substring(idx), original);
            for (List l : rest) {
                l.add(0, sub);
            }
            lists.addAll(rest);
        }
        if (Long.parseLong(n) < original) lists.add(new ArrayList<>(List.of(Long.parseLong(n))));
        return lists;

    }
}

