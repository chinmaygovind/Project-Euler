package problems;


import util.Numbers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

// Repeated Permutation.
public class Problem483 {

    private static int N = 350; // n = 350 has 279363328483702152 partitions...
    private static MathContext mc = new MathContext(50);
    public static DecimalFormat format = new DecimalFormat("0.##########E0");
    public static BigDecimal THRESHOLD = new BigDecimal("0.99999999999");
    // public static BigDecimal THRESHOLD = new BigDecimal("1");

    public static void main(String[] args) {
//          for (int i = 1; i < N; i++) {
//              System.out.printf("%d: %d partitions\n", i, getPartitions(i).size());
//          }
        g(10);
        trueG(10);
    }

    public static BigDecimal g(int n) {
        ArrayList<HashMap<ArrayList<Integer>, BigInteger>> poly = new ArrayList<>();
        ArrayList<Integer> constantTerm = new ArrayList<>();
        for (int i = 0; i < n; i++) constantTerm.add(0);
        poly.add(new HashMap<>());
        poly.getFirst().put(constantTerm, BigInteger.ONE);
        int i = 0;
        // Z(S0) = 1
        // Z(S1) = 0 + a1
        // Z(S2) = 0 + a1^2 + a2
        // Z(S3) = a1^3 + 2a1a2 + a3
        while (i < n) {
            System.out.println(poly.getLast());
            i++;
            HashMap<ArrayList<Integer>, BigInteger> newPoly = new HashMap<>();
            for (int l = 1; l <= i; l++) {
                for (ArrayList<Integer> term : poly.get(i - l).keySet()) {
                    ArrayList<Integer> newTerm = new ArrayList<>(term);
                    newTerm.set(l - 1, newTerm.get(l - 1) + 1);
                    if (!newPoly.containsKey(newTerm)) newPoly.put(newTerm, BigInteger.ZERO);
                    newPoly.put(newTerm, newPoly.get(newTerm).add(poly.get(i - l).get(term)));
                }
            }
            poly.add(newPoly);
        }
        System.out.println(poly.getLast());
        BigInteger squaredPeriods = BigInteger.ZERO;
        BigInteger x = BigInteger.ZERO;
        for (ArrayList<Integer> term : poly.getLast().keySet()) {
            x = x.add(poly.getLast().get(term));
        }
        System.out.println("x: " + x);
        return BigDecimal.ZERO;
    }

    public static void trueG(int n) {

        ArrayList<ArrayList<Integer>> partitions = getPartitions(n);
        System.out.printf("%d: %d partitions\n", n, partitions.size());
        HashMap<BigInteger, ArrayList<String>> contribs = new HashMap<>();
        // how many k-cycles are there? k-1!
        BigInteger totalSquaredPeriods = BigInteger.ZERO;
        BigInteger totalCombos = BigInteger.ZERO;
        for (ArrayList<Integer> partition : partitions) {
            int remaining = n;
            long period = 1;
            BigInteger combos = BigInteger.ONE;
            int lastGroupSize = -1;
            int numSameGroups = 0;
            for (int group : partition) {
                if (group == 1) break;
                period = group * period / Numbers.gcd(group, period);
                combos = combos.multiply(Numbers.bigChoose(remaining, group).multiply(Numbers.factorial((long) group-1)));
                remaining -= group;
                if (group == lastGroupSize) {
                    numSameGroups++;
                } else {
                    lastGroupSize = group;
                    combos = combos.divide(Numbers.factorial((long) numSameGroups));
                    numSameGroups = 1;
                }
            }
            combos = combos.divide(Numbers.factorial((long) numSameGroups));
            BigInteger squaredPeriods = combos.multiply(BigInteger.valueOf(period * period));
            String summary = String.format("%d: %s - period=%d, combos=%d, contrib=%d", n, partition, period, combos, squaredPeriods);
            if (!contribs.containsKey(squaredPeriods)) contribs.put(squaredPeriods, new ArrayList<>());
            contribs.get(squaredPeriods).add(summary);
            totalSquaredPeriods = totalSquaredPeriods.add(squaredPeriods);
            totalCombos = totalCombos.add(combos);
        }
        BigDecimal g = new BigDecimal(totalSquaredPeriods).divide(new BigDecimal(Numbers.factorial((long) n)), mc);
        System.out.printf("g(%d) = %s, total %d squared periods over %d permutations\n", n, format.format(g), totalSquaredPeriods, Numbers.factorial((long) n));
        // System.out.printf("%d \t%s\n", n, format.format(g));
        ArrayList<BigInteger> contribNumsList = new ArrayList<>(contribs.keySet());
        Collections.sort(contribNumsList);
        Collections.reverse(contribNumsList);
        BigDecimal runningTotal = BigDecimal.ZERO;
        int terms = 0;
        for (BigInteger i : contribNumsList) {
            // if (runningTotal.divide(new BigDecimal(totalSquaredPeriods), mc).compareTo(THRESHOLD) > 0) break;
            terms += contribs.get(i).size();
            runningTotal = runningTotal.add(new BigDecimal(i.multiply(BigInteger.valueOf(contribs.get(i).size()))));
            System.out.printf("%s | %.9f%%\n", contribs.get(i), runningTotal.divide(new BigDecimal(totalSquaredPeriods), mc).multiply(new BigDecimal(100)));
        }
        System.out.printf("needed %d / %d terms = %.10f\n", terms, partitions.size(), (double) terms / partitions.size());
    }
    public static ArrayList<ArrayList<Integer>> getPartitions(int n) {
        ArrayList<ArrayList<Integer>> partitions = getPartitions(n, n);
        return partitions;
    }
    public static ArrayList<ArrayList<Integer>> getPartitions(int n, int highest) {
        if (n == 1) {
            ArrayList<ArrayList<Integer>> single = new ArrayList<>();
            single.add(new ArrayList<>());
            single.getFirst().add(1);
            return single;
        } else if (n <= 0) {
            return new ArrayList<>();
        }
        ArrayList<ArrayList<Integer>> allPartitions = new ArrayList<>();
        for (int x = Math.min(n, highest); x >= 1; x--) {
            ArrayList<ArrayList<Integer>> partitions = new ArrayList<>();
            ArrayList<ArrayList<Integer>> subPartitions = getPartitions(n - x, x);
            for (ArrayList<Integer> partition : subPartitions) {
                partition.addFirst(x);
                partitions.add(partition);
            }
            allPartitions.addAll(partitions);
        }
        if (n <= highest) allPartitions.addFirst(new ArrayList<>(List.of(n)));
        return allPartitions;
    }
}

