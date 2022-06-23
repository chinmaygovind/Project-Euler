package problems;

import java.awt.*;
import java.util.HashSet;

//Singleton Difference.
public class Problem136 {
    private static int one = 0;
    private static final int LIMIT = 50000000;
    private static final int[] sols = new int[LIMIT];
    private static final HashSet<Point> values = new HashSet<>();
    public static void main(String[] args) {
        for (int a = 1; a <= LIMIT; a++) {
            for (int b = 1; b <= LIMIT; b++) {
                if (((a + b) % 4 != 0)) continue;
                if (a * b >= LIMIT || a * b < 0) break;
                int diff = (a + b) / 4;
                int x = a - diff;
                if (x > 0) {//i''m gonna kms
                    sols[a*b]++;
                    //System.out.println("a: " + a + ", b: " + b + " | " + (x + 2 * diff) + "^2 - " + (x + diff) + "^2 - " + (x) + "^2 = " + a * b);
                }
            }
        }
        for (int i = 0; i < LIMIT; i++) {
            if (sols[i] == 1) one++;
        }
        System.out.println("The number of values of n below fifty million with exactly one solution to the sequence is: " + one);
    }

}

