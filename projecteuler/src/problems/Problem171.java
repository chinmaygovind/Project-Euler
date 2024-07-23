package problems;

import util.Numbers;
import util.Sets;

import java.math.BigInteger;
import java.util.*;

//Square Sum of the Digital Squares.
public class Problem171 {

    public static HashMap<BigInteger, long[]> cache = new HashMap<>();

    public static void main(String[] args) {
        long total = 0;
        final int MAX_DIGITS = 20;
        BigInteger slotVal = Numbers.factorial((long) MAX_DIGITS-1);
        BigInteger totalSlots = BigInteger.ZERO;
        for (int i = 0; i < MAX_DIGITS; i++) totalSlots = totalSlots.add(BigInteger.TEN.pow(i));
        HashSet<Long> combinedSols = new HashSet<>();
        for (int i = 1; i < Math.sqrt(MAX_DIGITS*81); i++) {
            int target = i*i;
            List<String> allSols = allCombos(target, 9, MAX_DIGITS);
            //System.out.println(allSols);
            //for each digit in the 20-digit solution combo, it appears in each spot 19! (121645100408832000) times
            //last 9 of 19! is 408832000
            //so take the sum of the list, multiply it by that for each slot, and you win
            //omg
            for (String sol : allSols) {
                //for (String perm : Numbers.getPermutations(sol)) {
                //    combinedSols.add(Long.parseLong(perm));
                //}
                BigInteger divideTerm = BigInteger.ONE;
                long[] counts = new long[10];
                int sum = 0;
                for (char c : sol.toCharArray()) {
                    counts[Character.getNumericValue(c)]++;
                    sum += Character.getNumericValue(c);
                }
                for (long c : counts) divideTerm = divideTerm.multiply(Numbers.factorial(c));
                BigInteger contribution = slotVal.multiply(BigInteger.valueOf(sum)).multiply(totalSlots).divide(divideTerm);
                //System.out.println(sol + ", " + divideTerm + " -> " + contribution);
                total += contribution.mod(BigInteger.valueOf(1_000_000_000)).longValue();
                total %= 1_000_000_000;
            }
        }
        System.out.println("The last nine digits of the sum of all n, 0 < n < 10^20, such that f(n) is a perfect square is: " + total);

    }

    public static List<String> allCombos(int target, int maxDepth, int digitsRemaining) {
        if (target < 0) return new ArrayList<>();
        if (target > maxDepth*maxDepth*digitsRemaining) return new ArrayList<>();
        if (target == 0) {
            String sol = new StringBuilder().repeat("0", digitsRemaining).toString();
            return Collections.singletonList(sol);
        }
        List<String> sols = new ArrayList<>();
        for (int i = 0; i <= digitsRemaining && target >= i * maxDepth * maxDepth; i++) {
            List<String> specificSols =
                    new ArrayList<>(
                            allCombos(target - i * maxDepth * maxDepth, maxDepth - 1, digitsRemaining - i));
            for (int j = 0; j < specificSols.size(); j++) {
                specificSols.add(j, new StringBuilder(specificSols.get(j)).repeat(String.valueOf(maxDepth), i).toString());
                specificSols.remove(j + 1);
            }
            sols.addAll(specificSols);
        }
        return sols;
    }


}
