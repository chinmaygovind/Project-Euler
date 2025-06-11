package problems;

import util.Fraction;
import util.Rational;

import java.math.BigInteger;
import java.util.*;

//Luxury Hampers.
public class Problem236 {
    // first 80%er
    private static int[][] products = new int[][]{
            {5248, 640},
            {1312, 1888},
            {2624, 3776},
            {5760, 3776},
            {3936, 5664}
    };
    // A total: 18880
    // B total: 15744
    // sample m = 328/295, values = [(59, 8),(5, 8),(55, 88),(900, 656),(5, 8)]
    // m = 369/295, values = [(118, 18),(0, 0),(40, 72),(100, 82),(0, 0)]

    public static void main(String[] args) {
        // strat: pick a spoilage for product A0, loop through all possible ratios for B0
        // use product 3 to boost A's numbers
        // see what happens
        int aTotal = 0;
        int bTotal = 0;
        for (int i = 0; i < 5; i++) {
            aTotal += products[i][0];
            bTotal += products[i][1];
        }
        Rational bestM = Rational.ONE;
        HashSet<Rational> validM = new HashSet<>();
        for (int a0 = 59; a0 < products[0][0]; a0+=59) { // magic
            System.out.println("a0: " + a0);
            Rational a0Spoilage = new Rational(a0, products[0][0]);
            for (int b0 = 1; b0 < products[0][1]; b0++) {
                Rational b0Spoilage = new Rational(b0, products[0][1]);
                Rational m = b0Spoilage.divide(a0Spoilage);
                if (m.compareTo(bestM) <= 0 || m.compareTo(new Rational(5)) > 0) {
                    continue;
                }
                // equations:
                // a0 / A0 * m = b0 / B0
                // a1 / A1 * m = b1 / B1
                // a2 / A2 * m = b2 / B2
                // a3 / A3 * m = b3 / B3
                // a4 / A4 * m = b4 / B4
                // (a0 + a1 + a2 + a3 + a4) / (A0 + A1 + A2 + A3 + A4) = (b0 + b1 + b2 + b3 + b4) / (B0 + B1 + B2 + B3 + B4) * m
                // 6 degrees of freedom: a0, a1, a2, a3, a4, m
                List<List<Integer>> validA = new ArrayList<>(); // given m, find valid aXs
                validA.add(Collections.singletonList(a0));
                validA.add(new ArrayList<>());
                validA.add(new ArrayList<>());
                validA.add(new ArrayList<>());
                validA.add(new ArrayList<>());
                for (int i = 1; i < 5; i++) {
                    for (int aX = 0; aX < products[i][0]; aX++) {
                        Rational aXSpoilage = new Rational(aX, products[i][0]);
                        Rational bXSpoilage = aXSpoilage.multiply(m);
                        Rational bXRational = bXSpoilage.multiply(products[i][1]);
                        if (bXRational.isInteger()) {
                            int bX = (int) bXRational.value;
                            if (bX > (i == 3 ? 0 : -1) && bX < products[i][1]) {
                                validA.get(i).add(aX);
                            }
                        }
                    }
                    if (validA.get(i).isEmpty()) break;
                }
                double combos = (double) validA.get(0).size() *
                        (double) 1 *
                        (double) 1 *
                        (double) validA.get(3).size() *
                        (double) validA.get(4).size();
                if (combos > 0) {
                    //a0, b0, m fixed
                    //a1/A1 * m = b1/B1
                    // a1 = b1 * (A1/(B1 * m)), b1 = a1 / A1 * m * B1
                    // a2 = b2 * (A2/(B2 * m)), b2 = a2 / A2 * m * B2
                    // a3 = b3 * (A3/(B3 * m)), b3 = a3 / A3 * m * B3
                    // a4 = b4 * (A4/(B4 * m)), b4 = a4 / A4 * m * B4
                    // (a0 + a1 + a2 + a3 + a4) / (A0 + A1 + A2 + A3 + A4) = (b0 + b1 + b2 + b3 + b4) / (B0 + B1 + B2 + B3 + B4) * m
                    // a0 + a1 + a2 + a3 + a4 = b0 + b1 + b2 + b3 + b4 / (B0 + B1 + B2 + B3 + B4) * m * (A0 + A1 + A2 + A3 + A4)
                    //
                    // bad things
                    List<Integer> validA124 = new ArrayList<>(Collections.singletonList(0));
                    if (validA.get(4).size() >= 2) {
                        int a124High = validA.get(4).getLast() * 2; // engineer
                        int a124Mult = validA.get(4).get(1);
                        for (int i = a124Mult; i <= a124High; i += a124Mult) {
                            validA124.add(i);
                        }
                    }
                    int newCombos = validA.get(3).size() * validA.get(4).size();
                    // System.out.printf("a0 = %d, b0 = %d, m = %s, newCombos = %d, validA3 = %s, validA124 = %s\n", a0, b0, m, newCombos, validA.get(3), validA124);
                    checkMRatio:
                    for (int a3 : validA.get(3)) {
                        int b3 = (int) (new Rational(a3, products[3][0]).multiply(m).multiply(products[3][1])).value;
                        for (int a124 : validA124) {
                            int b124 = (int) (new Rational(a124, products[4][0]).multiply(m).multiply(products[4][1])).value;
                            int aTotalSpoiled = a0 + a124 + a3;
                            int bTotalSpoiled = b0 + b124 + b3;
                            Rational aTotalSpoilage = new Rational(aTotalSpoiled, aTotal);
                            Rational bTotalSpoilage = new Rational(bTotalSpoiled, bTotal);
                            if (aTotalSpoilage.divide(bTotalSpoilage).equals(m)) {
                                // System.out.printf("holy hell: m = %s, values = [(%d, %d),(%d, %d),(%d, %d),(%d, %d),(%d, %d)]\n", m, a0, b0, 0, 0, 0, 0, a3, b3, a124, b124);
                                validM.add(m);
                                if (m.compareTo(bestM) > 0) {
                                    bestM = m;
                                }
                                break checkMRatio; // goto goat
                            }
                        }

                    }
                }
            }
        }

        // System.out.println(validM);
        // System.out.println("Number of M Values Found: " + validM.size());
        // i only found 34. somehow i missed one oop
        // still got it tho
        System.out.println("The largest ratio m such that the surprising result occurs is: " + bestM);
    }


}
