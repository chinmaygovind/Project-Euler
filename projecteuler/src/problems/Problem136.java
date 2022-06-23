package problems;


import util.Numbers;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

//Singleton Difference.
public class Problem136 {
    private static int one = 0;
    private static final int LIMIT = 50000000;
    private static final int[] sols = new int[LIMIT];
    public static void main(String[] args) {
        for (int a = 1; a <= LIMIT; a++) {
            int bStart = a;
            while ((bStart + a)%4 != 0) bStart++;
            for (int b = bStart; b <= LIMIT; b+=4) {
                if (a * b >= LIMIT || a * b < 0) break;
                if (b - (a + b)/4 > 0) sols[a*b]++;
                if (a - (a + b)/4 > 0) sols[a*b]++;
                //int diff = (a + b)/4;
                //int x = a - diff;
                //int x2 = b - diff;
                //System.out.println("a: " + a + ", b: " + b + " | " + (x + 2 * diff) + "^2 - " + (x + diff) + "^2 - " + (x) + "^2 = " + a*b);
                //System.out.println("a: " + a + ", b: " + b + " | " + (x2 + 2 * diff) + "^2 - " + (x2 + diff) + "^2 - " + (x2) + "^2 = " + a*b);

            }
        }
        for (int i = 0; i < LIMIT; i++) {
            if (sols[i] == 1) {
                one++;
            }
            //System.out.println(i + ": " + sols[i]);
        }
        System.out.println("The number of values of n below fifty million with exactly one solution to the sequence is: " + one);
    }

}

