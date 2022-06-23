package problems;


import util.Numbers;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;

//Same Differences.
public class Problem135 {
    private static int tenSolutions = 0;

    public static void main(String[] args) {
        Numbers.generateCachedPrimes(200000);
        for (int n = 1; n < 1000000; n++) {
            if (findSolutions(n) == 10) {
                tenSolutions++;
            }
        }
        System.out.println("The number of values of n with ten solutions to the sequence under one million is: " + tenSolutions);
    }

    public static int findSolutions(int n) {
        ArrayList<Integer> factors = Numbers.getFactors(n, true);
        int solutions = 0;
        HashSet<Point> sols = new HashSet<>();
        for (int i = 0; i < factors.size(); i++) {
            int a = factors.get(i);
            int b = n/a;
            if ((a+b)%4 != 0) continue;
            int diff = (a + b)/4;
            //x + d = a, 3d - x = b
            //pick two numbers, a and b, for which b is int < 3a
            int x = a - diff;
            if (x > 0) {
                sols.add(new Point(x, diff));
                //System.out.println((x + 2 * diff) + "^2 - " + (x + diff) + "^2 - " + (x) + "^2 = " + n);
            }
            int x2 = b - diff;
            if (x2 > 0) {
                sols.add(new Point(x2, diff));
                //System.out.println((x2 + 2 * diff) + "^2 - " + (x2 + diff) + "^2 - " + (x2) + "^2 = " + n);
            }
        }
        return sols.size();
    }
}

