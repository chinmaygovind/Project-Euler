package problems;

import util.Numbers;
import util.Sets;

import java.util.*;
import java.util.function.Predicate;

//Digital Root Sums of Factorisations.
public class Problem159 {

    private static final int LIMIT = 1_000_000;

    public static void main(String[] args) {
        Numbers.generateCachedPrimes(LIMIT);
//        for (int i = 2; i < 100; i++) {
//            System.out.printf("i = %d, true = %d, fast = %d\n", i, mdrs(i), fastMDRS(i));
//            if (fastMDRS(i) != mdrs(i)) {
//                int val = 1 / 0;
//            };
//        }
//        System.out.println("simple check passed");
        //for (int i = 0; i < 100; i++) {
            //int rand = (int) (Math.random() * 1_000_000);
            //System.out.printf("i = %d, true = %d, fast = %d\n", rand, mdrs(rand), fastMDRS(rand));
        //}
        //System.out.println("random check passed");
        long totalMDRS = 0;
        for (int i = 2; i < LIMIT; i++) {
            totalMDRS += fastMDRS(i);
            //System.out.println(i + ": " + fastMDRS(i));
            //if (i%10_000 == 0) System.out.println(i);
        }
        //int test = 2 * 2 * 2 * 2 * 2 * 3 * 3 * 3 * 5;
        //System.out.println(test);
        //System.out.println(fastMDRS(test));
        //System.out.println(mdrs(test));
        System.out.println("The sum of mdrs(n) from 1 < n < 1,000,000 is: " + totalMDRS);
    }

    public static long drs(long n) {
        int digitSum = 0;
        int m = (int) n;
        while (m > 0) {
            digitSum += m%10;
            m /= 10;
        }
        if (digitSum == 9) return 9;
        return n%9; }

    public static long fastMDRS(long n) {
        if (Numbers.getCachedPrimes().contains((int) n) || n == 1) return drs(n);
        ArrayList<Long> factors = Numbers.getPrimeFactors(n, true);
        ArrayList<Long> OGFactors = (ArrayList<Long>) factors.clone();
        for (int i = 0; i < factors.size(); i++) factors.set(i, factors.get(i)%9);
        int toAdd = 0;
        LinkedHashMap<String, Integer> optimizations = new LinkedHashMap<>();
        //optimizations.put("2222223333", 25);//TODO: fix big chains of 2s and 3s
        //optimizations.put("22222333", 23);
        //optimizations.put("222333", 18);
        //optimizations.put("24", 8);
        //optimizations.put("222", 8);
        //optimizations.put("23", 6);
        //optimizations.put("22", 4);
        int best23Plan = 0;
        int numNines = 0;
        int numSixes = 0;
        int numFourEights = 0;
        int numTwoEights = 0;
        int numTwos = 0;
        int numThrees = 0;
        for (int nines = 0; nines <= Collections.frequency(OGFactors, 3L)/2; nines++) {
            for (int sixes = 0; sixes <=
                    Math.min(Collections.frequency(factors, 2L), Collections.frequency(factors, 3L) - 2 * nines);
                 sixes++) {
                for (int twoFourEights = 0; twoFourEights <= Math.min(Collections.frequency(factors, 2L) - sixes,
                        Collections.frequency(factors, 4L)); twoFourEights++) {
                    for (int pureTwoEights = 0; pureTwoEights <= (Collections.frequency(factors, 2L) - sixes - twoFourEights) / 3; pureTwoEights++) {
                        int remainingThrees = Collections.frequency(factors, 3L) - 2 * nines - sixes;
                        int remainingTwos = Collections.frequency(factors, 2L) - sixes - twoFourEights - 3 * pureTwoEights;
                        int newPlan = 9 * nines + 6 * sixes + 8 * (twoFourEights + pureTwoEights) + 3 * remainingThrees + 2 * remainingTwos;
                        if (newPlan > best23Plan) {
                            best23Plan = newPlan;
                            numNines = nines;
                            numSixes = sixes;
                            numFourEights = twoFourEights;
                            numTwoEights = pureTwoEights;
                            numThrees = remainingThrees;
                            numTwos = remainingTwos;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < numNines; i++) {
            factors.remove(3L);
            factors.remove(3L);
        }
        for (int i = 0; i < numSixes; i++) {
            factors.remove(2L);
            factors.remove(3L);
        }
        for (int i = 0; i < numTwoEights; i++) {
            factors.remove(2L);
            factors.remove(2L);
            factors.remove(2L);
        }
        for (int i = 0; i < numFourEights; i++) {
            factors.remove(2L);
            factors.remove(4L);
        }
        factors.removeIf(aLong -> aLong == 2 || aLong == 3);
        toAdd += 9 * numNines + 6 * numSixes + 8 * (numTwoEights + numFourEights) + 3 * numThrees + 2 * numTwos;
        return Math.max(toAdd + Sets.sumLongs(factors), drs(n));
    }

    public static long mdrs(int n) {
        if (Numbers.getCachedPrimes().contains((int) n)) return drs(n);
        long drs = drs(n);
        ArrayList<Long> factors = Numbers.getPrimeFactors(n, true);
        for (int i = 0; i < (int) Math.pow(factors.size(), factors.size()); i++) {
            long[] buckets = new long[factors.size()];
            Arrays.fill(buckets, 1);
            int j = i;
            for (Long factor : factors) {
                buckets[j % factors.size()] *= factor;
                j /= 4;
            }
            int tempDRS = 0;
            for (long k : buckets) {
                if (k == 1) continue;
                tempDRS += drs(k);
            }
            drs = Math.max(drs, tempDRS);
            //System.out.println(Arrays.toString(buckets));
        }
        return drs;
    }
}


