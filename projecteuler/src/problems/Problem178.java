package problems;

import java.util.Arrays;

// Step Numbers.
public class Problem178 {

    private static int LEN = 40; //98765432101, 89876543210, 10123456789
    private static long[][][][] stepNumbers = new long[LEN + 1][1024][10][2];// [length], [digitsContainedBitmask], [last], [truezero]
    public static void main(String[] args) {
        for (int d = 0; d <= 9; d++) {
            stepNumbers[1][1 << d][d][0] = 1; // single digiterinos
        }
        long bigTotal = 0;
        System.out.println(Arrays.deepToString(stepNumbers[1]));
        for (int len = 2; len <= LEN; len++) {
            for (int bitmask = 0; bitmask < 1024; bitmask++) {
                for (int newDigit = 0; newDigit <= 9; newDigit++) {
                    int newBitmask = bitmask | (1 << newDigit);
                    if (newDigit > 0) {
//                        System.out.printf("The length %d numbers with bitmask %s and lastdigit %d contribute %d to the length %d numbers with new bitmask %s and new last digit %d\n",
//                                len-1, formatBitmask(bitmask), newDigit-1, stepNumbers[len-1][bitmask][newDigit-1], len, formatBitmask(newBitmask), newDigit);
                        stepNumbers[len][newBitmask][newDigit][0] += stepNumbers[len-1][bitmask][newDigit-1][0];
                        stepNumbers[len][newBitmask][newDigit][1] += stepNumbers[len-1][bitmask][newDigit-1][1];
                    }
                    if (newDigit < 9) {
//                        System.out.printf("The length %d numbers with bitmask %s and lastdigit %d contribute %d to the length %d numbers with new bitmask %s and new last digit %d\n",
//                                len-1, formatBitmask(bitmask), newDigit+1, stepNumbers[len-1][bitmask][newDigit+1], len, formatBitmask(newBitmask), newDigit);
                        stepNumbers[len][newBitmask][newDigit][newDigit == 0 ? 1 : 0] += stepNumbers[len-1][bitmask][newDigit+1][0];
                        stepNumbers[len][newBitmask][newDigit][1] += stepNumbers[len-1][bitmask][newDigit+1][1];
                    }
                }
            }
            for (int d = 1; d <= 9; d++) {
                //spawn fuckass numbers in
                stepNumbers[len][1 << d][d][0]++;
            }
//            System.out.println(Arrays.deepToString(stepNumbers[len]));
            long sum = 0;
            for (int d = 0; d <= 9; d++) {
                sum += stepNumbers[len][1023][d][1];
            }
            bigTotal += sum;
            System.out.printf("The number of pandigital step numbers below 10^%d is: %d\n", len, sum);
        }
        System.out.println("Big total: " + bigTotal);
    }

    public static String formatBitmask(int bitmask) {
        return String.format("%10s", Integer.toBinaryString(bitmask)).replace(' ', '0');
    }

}
