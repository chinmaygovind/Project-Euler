package problems;

import util.Numbers;

import java.math.BigInteger;
import java.util.*;

//Few Repeated Digits.
public class Problem172 {

    public static HashMap<BigInteger, long[]> cache = new HashMap<>();

    public static void main(String[] args) {
        List<String> allCombos = allCombos(18, 9);
        long total = 0;
        for (String sol : allCombos) {
            long contribution = 6402373705728000L;
            int[] counts = new int[10];
            for (char c : sol.toCharArray()) counts[Character.getNumericValue(c)]++;
            long duplicates = 1;
            for (int i : counts) duplicates *= Numbers.factorial(i);
            contribution /= duplicates;
            if (counts[0] == 1) {
                contribution /= 18;
                contribution *= 17;
            } else if (counts[0] == 2) {
                //18, 17 / 2 = 9 * 17 = 153
                //17, 16 / 2 = 8 * 17 = 136
                contribution /= 9;
                contribution *= 8;
            } else if (counts[0] == 3) {
                //18, 17, 16 / 6
                //17, 16, 15 / 7
                contribution /= 18;
                contribution *= 15;
            }
            total += contribution;
        }
        System.out.println("The total number of 18-digit numbers n such that no digit occurs more than thrice in n is: " + total);
    }

    public static List<String> allCombos(int remainingDigits, int maxDigit) {
        //System.out.printf("allCombos(%d, %d)\n", remainingDigits, maxDigit);
        if (remainingDigits < 0 || remainingDigits > 3 * (maxDigit+1)) return new ArrayList<>();
        if (maxDigit == 0) return Collections.singletonList(new StringBuilder().repeat("0", remainingDigits).toString());
        List<String> allSols = new ArrayList<>();
        for (int r = 0; r <= 3; r++) {
            List<String> specificSols = new ArrayList<>(allCombos(remainingDigits - r, maxDigit-1));
            for (int i = 0; i < specificSols.size(); i++) {
                specificSols.add(i, specificSols.get(i) + new StringBuilder().repeat(String.valueOf(maxDigit), r));
                specificSols.remove(i+1);
            }
            allSols.addAll(specificSols);
        }
        return allSols;
    }


}
