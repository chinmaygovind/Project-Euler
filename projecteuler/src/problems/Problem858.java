package problems;


import util.Numbers;
import util.Sets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//LCM.
public class Problem858 {

    public static void main(String[] args) {
        System.out.println(G(5));
        System.out.println(G(20));
        System.out.println(G(25));
    }

    public static long G(int n) {
        long total = 0;
        long mod = 1_000_000_007;
        ArrayList<Long> res = GHelper(n);
        for (long i6 : res) {
            total += n;
            total %= mod;
        }
        return total;
    }
    public static ArrayList<Long> GHelper(int n) {
        if (n == 1) {
            return new ArrayList<>(List.of(1L, 1L));
        }
        ArrayList<Long> smaller = GHelper(n - 1);
        ArrayList<Long> newList = new ArrayList<>();
        for (long el : smaller) {
            newList.add((n * el) / Numbers.GCF(n, el));
        }
        newList.addAll(smaller);
        return newList;

    }



}

