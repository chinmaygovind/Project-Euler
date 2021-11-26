package problems;


import util.Numbers;
import util.Sets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

//Bouncy Numbers.
public class Problem112 {
    private static int bouncies = 0;
    private static double n = 1;
    public static void main(String[] args) {
        while (bouncies/n != 0.99) {
            n++;
            if (isBouncy((int) n)) bouncies++;
        }
        System.out.println("The least number for which the proportion of bouncy numbers is exactly 99% is: " + n);
    }

    public static boolean isBouncy(int n) {
        boolean increasing = true;
        boolean decreasing = true;
        String num = String.valueOf(n);
        for (int i = 0; i < num.length() - 1; i++){
            if (Integer.parseInt(String.valueOf(num.charAt(i))) <
                    Integer.parseInt(String.valueOf(num.charAt(i + 1)))) decreasing = false;
            if (Integer.parseInt(String.valueOf(num.charAt(i))) >
                    Integer.parseInt(String.valueOf(num.charAt(i + 1)))) increasing = false;
        }
        return !(increasing || decreasing);
    }
}

