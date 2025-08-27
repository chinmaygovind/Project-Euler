package problems;

import util.Numbers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import static util.Numbers.df8;

// Licence Plates.
public class Problem371 {
    // mce - 40.6207031

    private static final int totalPlates = 520;
    private static final int maxGoodPlates = 501;
    private static final int maxZeros = 15;
    private static final int max500s = 3;

    private static final BigDecimal GIVE_UP = new BigDecimal("0.0000000000000001");
    private static final BigDecimal THOUSANDTH = new BigDecimal("0.001");
    // zeroth axis # of plates seen, first axis # of unique plates from 1-499 seen, second axis # of 0s seen, third axis # of 500s seen, 4th axis won
    private static final BigDecimal[][][][][] plates = new BigDecimal[totalPlates][maxGoodPlates][maxZeros][3][2];
    public static void main(String[] args) {
        // ALMOST isomorphic to birthday problem with 500 days
        // but its not soo DP
        for (int n = 0; n < totalPlates; n++) {
            for (int g = 0; g < maxGoodPlates; g++) {
                for (int z = 0; z < maxZeros; z++) {
                    for (int f = 0; f < 3; f++) {
                        for (int w = 0; w < 2; w++) {
                            plates[n][g][z][f][w] = BigDecimal.ZERO; // maybe the worst line of code i've ever written
                        }
                    }
                }
            }
        }
        plates[0][0][0][0][0] = BigDecimal.ONE;
        double x = 0;
        for (int n = 0; n < totalPlates - 1; n++) {
            System.out.println(n);
            for (int goodPlates = 0; goodPlates < maxGoodPlates; goodPlates++) {
                for (int zeros = 0; zeros < maxZeros; zeros++) {
                    for (int seen500s = 0; seen500s < 3; seen500s++) {
                        BigDecimal prob = plates[n][goodPlates][zeros][seen500s][0];
                        if (prob.compareTo(GIVE_UP) < 0) continue;
                        // case 1: get a 0 (p = 1/1000)
                        if (zeros < maxZeros - 1) {
                            plates[n + 1][goodPlates][zeros + 1][seen500s][0] = plates[n + 1][goodPlates][zeros + 1][seen500s][0].add(
                                    prob.multiply(THOUSANDTH)
                            );

                        }
                        // case 2: get 1-999
                        // case a: get a repeat. (p = goodPlates / 1000)
                        plates[n + 1][goodPlates][zeros][seen500s][0] = plates[n + 1][goodPlates][zeros][seen500s][0].add(
                                prob.multiply(BigDecimal.valueOf(goodPlates/1000.0))
                        );
                        // case b: get a partner -> win. (p = goodPlates / 1000)
                        plates[n + 1][goodPlates][zeros][seen500s][1] =  plates[n + 1][goodPlates][zeros][seen500s][1].add(
                                prob.multiply(BigDecimal.valueOf(goodPlates/1000.0))
                        );
                        // case c: get a new plate, not repeat or partner. (can only happen when <= 499 good plates
                        if (goodPlates < maxGoodPlates - 1) {
                            plates[n + 1][goodPlates + 1][zeros][seen500s][0] = plates[n + 1][goodPlates + 1][zeros][seen500s][0].add(
                                prob.multiply(BigDecimal.valueOf((998 - 2 * goodPlates)/1000.0))
                        );
                        }

                        // case 3: get a 500.
                        if (seen500s == 0) {
                            plates[n + 1][goodPlates][zeros][seen500s + 1][0] = plates[n + 1][goodPlates][zeros][seen500s + 1][0].add(
                                    prob.multiply(THOUSANDTH)
                            );
                        } else if (seen500s == 1) {
                            plates[n + 1][goodPlates][zeros][seen500s + 1][1] = plates[n + 1][goodPlates][zeros][seen500s + 1][1].add(
                                    prob.multiply(THOUSANDTH)
                            );
                        }
//                        // case 1: got a 0
//                        if (zeros > 0) {
//                            plates[n + 1][goodPlates][zeros][seen500s][0] = plates[n + 1][goodPlates][zeros][seen500s][0].add(
//                                    plates[n][goodPlates][zeros-1][seen500s][0].multiply(BigDecimal.valueOf(1 / 1000.0))
//                            );
//                        }
//                        // case 2: got 1-499.
//                        if (goodPlates > 0) {
//                            // case a: got a repeat, either same as before or the partner
//                            plates[n + 1][goodPlates][zeros][seen500s][0] = plates[n + 1][goodPlates][zeros][seen500s][0].add(
//                                    plates[n][goodPlates - 1][zeros][seen500s][0].multiply(BigDecimal.valueOf((goodPlates-1) / 1000.0))
//                            );
//
//                            plates[n + 1][goodPlates][zeros][seen500s][1] = plates[n + 1][goodPlates][zeros][seen500s][1].add(
//                                    plates[n][goodPlates - 1][zeros][seen500s][0].multiply(BigDecimal.valueOf((goodPlates-1) / 1000.0))
//                            );
//                            // case b: new unique plate
//                            plates[n + 1][goodPlates][zeros][seen500s][0] = plates[n + 1][goodPlates][zeros][seen500s][0].add(
//                                    plates[n][goodPlates - 1][zeros][seen500s][0].multiply(BigDecimal.valueOf(((998 - 2 * (goodPlates - 1)) / 1000.0)))
//                            );
//                        }
//                        // case 3: got 500
//                        if (seen500s > 0) {
//                            // case a: no 500s so far, case b: one 500 so far
//                            if (seen500s == 1) {
//                                plates[n + 1][goodPlates][zeros][seen500s][0] = plates[n + 1][goodPlates][zeros][seen500s][0].add(
//                                        plates[n][goodPlates][zeros][seen500s - 1][0].multiply(BigDecimal.valueOf(1 / 1000.0))
//                                );
//                            } else {
//                                plates[n + 1][goodPlates][zeros][seen500s][1] = plates[n + 1][goodPlates][zeros][seen500s][1].add(
//                                        plates[n][goodPlates][zeros][seen500s - 1][0].multiply(BigDecimal.valueOf(1 / 1000.0))
//                                );
//                            }
//                        }
                    }
                }
            }
        }
        BigDecimal eX = BigDecimal.ZERO;
        // ideally all the completion states should add up to 1?
        for (int n = 1; n < totalPlates; n++) {
//            System.out.println(n);
            BigDecimal bigN = BigDecimal.valueOf(n);
            for (int goodPlates = 0; goodPlates < maxGoodPlates; goodPlates++) {
                for (int zeros = 0; zeros < maxZeros; zeros++) {
                    for (int seen500s = 0; seen500s < 3; seen500s++) {
//                        for (int win = 0; win < 2; win++) {
//                            if (plates[n][goodPlates][zeros][seen500s][win].compareTo(BigDecimal.valueOf(0.000000000001)) > 0) {
//                                System.out.println(n + ", " + goodPlates + ", " + zeros + ", " + seen500s + ", " + win + ": " +
//                                        df8.format(plates[n][goodPlates][zeros][seen500s][win]));
//                            }
//                        }
                        eX = eX.add(plates[n][goodPlates][zeros][seen500s][1].multiply(bigN));
                    }
                }
            }
        }
        System.out.printf("The expected number of plates Seth needs to see until he wins is: %.8f\n", eX);
    }
}
