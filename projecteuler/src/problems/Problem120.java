package problems;


import util.Sets;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

//Square Remainders.
public class Problem120 {
    private static ArrayList<Integer> rMax = new ArrayList<>();
    public static void main(String[] args) {
        for (int a = 3; a <= 1000; a++) {
           rMax.add(rMax(a));
        }
        System.out.println("The sum of all r-max values from a = 3 to 1000 is: " + Sets.sum(rMax));
    }

    public static int rMax(int a){
        return  a * (a - (a%2 == 0 ? 2 : 1));
    }

}

