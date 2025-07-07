package problems;


import util.Numbers;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

// Problem 953.
public class Problem953 {

    // nim sum zero -> first loses
    // find all 1 <= n <= 10^14 w/ nim sum zero
    private static long N = 1000;
    // Populated XORS
    // total size of XORS keys: 1750221
    // total size of XORS values: 1750221
    // Processing XOR = 0 with 6332 elements, completed 0/1750221, processed 0/9999999
    private static long sqrtN = (int) Math.sqrt(N);
    private static boolean writeMap = true;
    public static void main(String[] args) {
        long pow2 = 1;
        while (pow2 < sqrtN) {
            pow2 = pow2 << 1;
        }
        System.out.println("true: " + naive((int) N));
        Numbers.generateCachedPrimes((int) pow2);
        HashMap<Integer, ArrayList<Long>> xors = new HashMap<>();
        try {
            System.out.println("Attempting to read XOR map...");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                    "C:/Users/Chinmay/Documents/Project-Euler/projecteuler/files/xors" + pow2 + ".hashmap"
            ));
            xors = (HashMap<Integer, ArrayList<Long>>) ois.readObject();
            System.out.println("Successfully read XORs...");
            writeMap = false;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed to read XORS hashmap. Computing manually...");
//            throw new RuntimeException(e);

            xors.put(1, new ArrayList<>());
            HashSet<Integer> primes = new HashSet<>(Numbers.getCachedPrimes());
            for (int i = 2; i <= pow2; i++) {
                if (i % 100000 == 0) System.out.println(i);
                if (primes.contains(i)) {
                    xors.put(i, new ArrayList<>(List.of((long) i)));
                } else {
                    long x = 0;
                    for (Long p : Numbers.getPrimeFactors(i, true)) {
                        x = x ^ p;
                    }
                    if (!xors.containsKey((int) x)) {
                        xors.put((int) x, new ArrayList<>());
                    }
                    xors.get((int) x).add((long) i);
                }
            }
            System.out.println("Populated XORS");
        }
//        for (Integer p : Numbers.getCachedPrimes()) {
//            xors.put(p, new ArrayList<>(List.of(p)));
//            for (Integer q : Numbers.getCachedPrimes()) {
//                int prod = p * q;
//                if (!xors.containsKey(prod)) {
//                    xors.put(prod, new ArrayList<>(List.of(prod)));
//                }
//            }
//        }
        if (writeMap) {
            try {

                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
                        "C:/Users/Chinmay/Documents/Project-Euler/projecteuler/files/xors" + pow2 + ".hashmap"));
                oos.writeObject(xors);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

//        System.out.println(xors);
        long total = 0;
        HashSet<Long> sols = new HashSet<>();
        System.out.println("total size of XORS keys: " + xors.keySet().size());
        System.out.println("total size of XORS values: " + xors.values().size());
        int done = 0;
        int toProcess = 0;
        long productUpperBound = 0;
        for (ArrayList<Long> v : xors.values()) {
            toProcess += v.size();
            productUpperBound += ((long) v.size() * (v.size() - 1));
        }
        System.out.println("upper bound on products: " + productUpperBound);
        int processed = 0;
//        System.out.println(xors);
        ArrayList<Integer> xorKeys = new ArrayList<>(xors.keySet());
        HashMap<Integer, ArrayList<Long>> finalXors = xors;
        xorKeys.sort(Comparator.comparingInt(o -> -finalXors.get(o).size()));
        for (Integer x : xorKeys) {
            if (done < 10_000 || done % 100 == 0) {
                System.out.printf("Processing XOR = %d with %d elements, completed %d/%d, processed %d/%d\n",
                        x, xors.get(x).size(), done, xors.keySet().size(), processed, toProcess);
            }
//            if (done < 1000) {
//                Runtime runtime = Runtime.getRuntime();
//                long totalMemory = runtime.totalMemory();
//                long freeMemory = runtime.freeMemory();
//                long maxMemory = runtime.maxMemory();
//                long usedMemory = totalMemory - freeMemory;
//
//                System.out.println("Max Memory: " + maxMemory / (1024 * 1024) + " MB");
//                System.out.println("Total Memory: " + totalMemory / (1024 * 1024) + " MB");
//                System.out.println("Free Memory: " + freeMemory / (1024 * 1024) + " MB");
//                System.out.println("Used Memory: " + usedMemory / (1024 * 1024) + " MB");
//            }
            ArrayList<Long> l = xors.get(x);

            for (int i = 0; i < l.size(); i++) {
                for (int j = i; j < l.size(); j++) {
                    long prod = l.get(i) * (long) l.get(j);
                    if (prod <= N) sols.add(prod);

                }
            }
            processed += l.size();
            done++;
        }
        sols.add(1L);
//        sols.addAll(xors.get(0));
        for (Number i : (xors.get(0) == null ? new ArrayList<Integer>() : xors.get(0))) {
            sols.add((long) i);
        }
        System.out.println("num of sols: " + sols.size());
        System.out.println("adding it up...");
        int added = 0;
        for (long l : sols) {
            if (added % 1_000_000 == 0) System.out.println("added " + added);
            total += l;
            total %= 1_000_000_007;
            added++;
        }
        System.out.println(sols);
        System.out.println("The value of S(10^14) (mod 10^9 + 7) is: " + total);
    }

    public static int naive(int N) {
        long total = 1;
        Numbers.generateCachedPrimes(N);
        HashSet<Integer> sols = new HashSet<>();
        sols.add(1);
        for (int i = 2; i <= N; i++) {
            total += nimSum(Numbers.getPrimeFactors(i)) == 0 ? i : 0;
            if (nimSum(Numbers.getPrimeFactors(i)) == 0) sols.add(i);
            total %= 1_000_000_007;
        }
        System.out.println("true sols: " + sols);
        return (int) total;
    }
    public static long nimSum(ArrayList<Integer> piles) {
        long x = 0;
        for (long l : piles) {
            x = x ^ l;
        }
        return x;
    }


}

