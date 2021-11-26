package problems;


import util.Numbers;

import java.util.ArrayList;

//Non-Bouncy Numbers.
public class Problem113 {
    private static int bouncies = 0;
    private static double n = 1;
    public static void main(String[] args) {
        System.out.println("The amount of numbers below one googol that are not bouncy is: " + nonBounciesUpTo(100));
    }

    public static long nonBounciesUpTo(int e){
        if (e == 1) return 9;
        long total = 0;
        int[] distributions = new int[]{1, 9, 81, 204, 336, 378, 294, 156, 54, 11, 1};
        for (int i = 1; i <= Math.min(e, 10); i++){
            total += (long) distributions[i] * Numbers.choose(e - 1, i - 1);
            //System.out.println(distributions[i] + " * " + Numbers.choose(e - 1, i - 1));
        }

        return total + nonBounciesUpTo(e - 1);
    }
}

