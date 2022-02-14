package problems;


import util.Numbers;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//Ordered Radicals.
public class Problem124 {
    private static final ArrayList<Point> radicals = new ArrayList<>();
    private static final int limit = 100000;
    private static final int toCheck = 10000;
    public static void main(String[] args) {
        for (int n = 0; n <= limit; n++) radicals.add(new Point(n, 1));
        for (int p : Numbers.generatePrimes(limit)) {
            for (int k = 0; k < limit; k += p) {
                radicals.get(k).y *= p;
            }
        }
        radicals.sort((o1, o2) -> (o1.y < o2.y ? -1 : o1.y > o2.y ? 1 : o1.x < o1.y ? -1 : 1));
        //System.out.println(radicals);
        System.out.println("The 10000th element in the sorted n column is: " + radicals.get(toCheck-1).x);
    }

}

