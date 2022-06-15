package problems;


import util.Numbers;

import java.util.ArrayList;

//Prime Cube Partnership.
public class Problem131 {
    private static final int LIMIT = 100;
    private static ArrayList<Integer> primes = Numbers.generatePrimes(LIMIT);
    private static ArrayList<Integer> goodPrimes = new ArrayList<>();
    private static ArrayList<Long> cubes = new ArrayList<>();
    public static void main(String[] args) {
        for (long i = 1; i < 10000; i++) {
            cubes.add(i*i*i);
        }
        System.out.println("coobs");
        for (int p : primes) {
            for (long n = 1; n < 10000; n++) {
                for (long c : cubes) {
                    if (c == (n + p) * n * n) {
                        goodPrimes.add(p);
                        break;
                    } else if (c > (n + p) * n * n) {
                        break;
                    }
                }
            }
        }
        System.out.println(goodPrimes);
    }



}

