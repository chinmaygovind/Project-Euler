package problems;


import util.Numbers;
import util.Sets;

import java.util.*;

//ABC-Hits.
public class Problem127 {

    private static final int limit = 120000;
    private static int abcHits = 0;
    private static long sumOfC = 0;
    private static final HashMap<Integer, Long> cachedRadicals = new HashMap<>();
    private static final HashMap<Integer, Boolean> reducible = new HashMap<>();
    private static ArrayList<Integer> workingFactors;
    private static ArrayList<Integer> possibleAddends = new ArrayList<>();
    public static void main(String[] args) {
        Numbers.generatePrimes(2 * limit);
        for (int i = 1; i < limit; i++) {
            rad(i);
            reducible.put(i, Numbers.isPrime(i) || rad(i) < i || i == 1);
        }
        for (int a = 1; a < limit/2; a++) {
            //if (!reducible.get(a)) continue;
            //if (a%100==0) System.out.println(a);
            workingFactors = new ArrayList<>(new HashSet<>(Numbers.getPrimeFactors(a)));
            possibleAddends.clear();
            if (a == 1) possibleAddends.add(0);
            for (int i = 1; i < a; i++) {
                boolean relPrime = true;
                for (int factor : workingFactors) {
                    if (i%factor == 0) {
                        relPrime = false;
                        break;
                    }
                }
                if (relPrime) possibleAddends.add(i);
            }
            //System.out.println(a + ": " + possibleAddends);
            for (int k = 1; k * a < limit - a; k++) {
                for (Integer addend : possibleAddends) {
                    int b = k * a + addend;
                    if (!reducible.get(a) && !reducible.get(b)) continue;
                    if (a + b >= limit) break;
                    //if (!reducible.get(b) || !reducible.get(a + b)) continue;
                    if (Numbers.GCF(a, b) == 1 &&
                            rad(a + b) * rad(a) * rad(b)
                                    < a + b) {
                        //if (!reducible.get(a) && !reducible.get(b)) System.out.println(a + ", " + b + ", " + (a + b));
                        abcHits++;
                        sumOfC += a + b;
                    }
                }
            }
        }
        System.out.println("The sum of all c values of the " + abcHits + " abc-hits with c < " + limit + " is: " + sumOfC);
    }

    public static long rad(int n) {
        if (cachedRadicals.containsKey(n)) return cachedRadicals.get(n);
        cachedRadicals.put(n, (long) Sets.product(new ArrayList<>(new HashSet<>(Numbers.getPrimeFactors(n)))));
        return rad(n);
    }

}

