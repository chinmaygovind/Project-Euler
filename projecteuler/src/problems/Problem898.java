package problems;


import util.Numbers;

import java.math.BigDecimal;
import java.util.*;

//Claire Voyant.
public class Problem898 {
    //up to 45: 0.940321979
    //up to 49: 0.940772683603327102853916824326027794505932800000
    //full?: 0.978153483394006102092138455654302186134404132896247180804784643861755663138028838191104000000000
    public static void main(String[] args) {
        ArrayList<BigDecimal> probabilities = new ArrayList<>();
        for (int i = 20; i <= 40; i+=20) probabilities.add(BigDecimal.valueOf(i).divide(BigDecimal.valueOf(100)));
        long possibilities = 1l << probabilities.size();
        BigDecimal total_chance = BigDecimal.ZERO;
        ArrayList<BigDecimal[]> cachedProbs = new ArrayList<>();
        for (int i = 0; i < possibilities; i++) {
            if (i%1_000_000 == 0) System.out.printf("i: %d\n", i);
            BigDecimal chance_if_zero = new BigDecimal("0.5");
            BigDecimal chance_if_one = new BigDecimal("0.5");
            int mask = i;
            for (int k = 0; k < probabilities.size(); k++) {
                if ((mask & 1 << k) == 0) {
                    chance_if_zero = chance_if_zero.multiply(BigDecimal.ONE.subtract(probabilities.get(k)));
                    chance_if_one = chance_if_one.multiply(probabilities.get(k));
                } else {
                    chance_if_zero = chance_if_zero.multiply(probabilities.get(k));
                    chance_if_one = chance_if_one.multiply(BigDecimal.ONE.subtract(probabilities.get(k)));
                }
            }
            cachedProbs.add(new BigDecimal[]{chance_if_zero, chance_if_one});
            System.out.println(Arrays.toString(new BigDecimal[]{chance_if_zero, chance_if_one}));
        }
        ArrayList<BigDecimal> probabilities2 = new ArrayList<>();
        for (int i = 60; i <= 80; i+=20) probabilities2.add(BigDecimal.valueOf(i).divide(BigDecimal.valueOf(100)));
        long possibilities2 = 1l << probabilities.size();
        for (int i = 0; i < possibilities2; i++) {
            if (i%1_000_000 == 0) System.out.printf("i: %d\n", i);
            BigDecimal chance_if_zero = cachedProbs.get(i)[0];
            BigDecimal chance_if_one = cachedProbs.get(i)[1];
            int mask = i;
            for (int k = 0; k < probabilities.size(); k++) {
                if ((mask & 1 << k) == 0) {
                    chance_if_zero = chance_if_zero.multiply(BigDecimal.ONE.subtract(probabilities.get(k)));
                    chance_if_one = chance_if_one.multiply(probabilities.get(k));
                } else {
                    chance_if_zero = chance_if_zero.multiply(probabilities.get(k));
                    chance_if_one = chance_if_one.multiply(BigDecimal.ONE.subtract(probabilities.get(k)));
                }
            }
            //System.out.printf("%d: chance if zero: %f, chance if one: %f, chance of correct guess: %f\n", i, chance_if_zero, chance_if_one, Math.max(chance_if_zero, chance_if_one) / (chance_if_zero + chance_if_one));
            total_chance = total_chance.add(chance_if_one.max(chance_if_zero));
        }
        //System.out.printf("%d: chance if zero: %f, chance if one: %f, chance of correct guess: %f\n", i, chance_if_zero, chance_if_one, Math.max(chance_if_zero, chance_if_one) / (chance_if_zero + chance_if_one));
        //total_chance = total_chance.add(chance_if_one.max(chance_if_zero));
        System.out.println(total_chance);

    }



}

