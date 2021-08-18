package problems;

import util.Numbers;

import java.util.*;

//Totient Maximum.
public class Problem69 {
    private static int answer = 0;
    private static double totientRatio = 0;
    public static void main(String[] args) {
        Numbers.generateCachedPrimes(1000000);
        System.out.println("hi");
        for (int i = 2; i < 1000000; i++){
            double newTotientRatio = i / (double) Numbers.totient(i);
            if (newTotientRatio > totientRatio){
                totientRatio = newTotientRatio;
                answer = i;
            }
        }
        System.out.println("The value of n under one million that maximizes n/φ(n) is: " + answer);
        System.out.println("That value of n/φ(n) is: " + totientRatio);
    }






}
