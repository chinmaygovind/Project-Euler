package problems;

import util.Numbers;

import java.util.ArrayList;
import java.util.Arrays;

public class Problem21 {

    private static int[] factorSums = new int[30000];
    private static ArrayList<Integer> amicableNumbers = new ArrayList<>();
    private static int total = 0;

    public static void main(String[] args){
        //fill out factor sums
        for (int i = 0; i < 30000; i++){
            int sumOfFactors = 0;
           for (int factor : Numbers.getFactors(i)){
               sumOfFactors += factor;
           }
            factorSums[i] = sumOfFactors;
        }

        //search for amicable pairs
        for (int i = 0; i < 10000; i++){
            if (factorSums[factorSums[i]] == i && factorSums[i] != i){
                amicableNumbers.add(i);
            }
        }
        //sum amicable numbers
        amicableNumbers.forEach((integer -> total += integer));
        System.out.println("The sum of all amicable numbers below 10,000 is: " + total);

    }

}
