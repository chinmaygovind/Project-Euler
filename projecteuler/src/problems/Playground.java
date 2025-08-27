package problems;


import util.Numbers;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.math.BigInteger;
import java.util.*;

// Problem 954.
public class Playground {
    public static void main(String[] args) {
        double trials = 1_000_000;
        double count = 0;
        Random random = new Random();
        for (int i = 0; i < trials; i++) {
            HashSet<Integer> seen = new HashSet<>();
            int next = 0;
            while (!seen.contains(1000 - next)) {
                seen.add(next);
                next = random.nextInt(1000);
            }
            seen.add(next);
            count += seen.size();
        }
        double eX = count / trials;
        System.out.println(eX);
    }



}

