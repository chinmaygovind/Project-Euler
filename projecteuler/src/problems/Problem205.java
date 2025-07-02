package problems;

import java.util.HashMap;

// Dice Game.
public class Problem205 {
    public static void main(String[] args) {
        HashMap<Integer, Long> peter = new HashMap<>();
        HashMap<Integer, Long> colin = new HashMap<>();
        for (int sum = 4; sum <= 36; sum++) {
            peter.put(sum, 0L);
            colin.put(sum, 0L);
        }
        for (int i = 0; i < (1 << 18); i++) { // 4^9
            int sum = 9;
            int j = i;
            while (j > 0) {
                sum += j % 4;
                j /= 4;
            }
            peter.put(sum, peter.get(sum) + 1);
        }
        for (int i = 0; i < 46656; i++) { // 6^6
            int sum = 6;
            int j = i;
            while (j > 0) {
                sum += j % 6;
                j /= 6;
            }
            colin.put(sum, colin.get(sum) + 1);

        }
        long wins = 0;
        for (int pSum = 5; pSum <= 36; pSum++) {
            for (int cSum = 4; cSum < pSum; cSum++) {
                wins += peter.get(pSum) * colin.get(cSum);
            }
        }
        System.out.println(peter);
        System.out.println(colin);
        double p = (double) wins / (Math.pow(4, 9) * Math.pow(6, 6));
        System.out.printf("The probability that Pyramidal Peter beats Cubic Colin is: %.7f", p);
    }


}
