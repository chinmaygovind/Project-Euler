package problems;


import util.Numbers;

import java.util.ArrayList;

//Substring Divisibility.
public class Problem43 {
    private static ArrayList<String> permutations = Numbers.getPermutations("0123456789");
    private static long sum = 0;
    public static void main(String[] args){
        for (String permutation : permutations){
            if (permutation.charAt(0) != '0'){
                if (Integer.parseInt(permutation.substring(1, 4))%2 == 0 &&
                    Integer.parseInt(permutation.substring(2, 5))%3 == 0 &&
                    Integer.parseInt(permutation.substring(3, 6))%5 == 0 &&
                    Integer.parseInt(permutation.substring(4, 7))%7 == 0 &&
                    Integer.parseInt(permutation.substring(5, 8))%11 == 0 &&
                    Integer.parseInt(permutation.substring(6, 9))%13 == 0 &&
                    Integer.parseInt(permutation.substring(7, 10))%17 == 0){
                    sum += Long.parseLong(permutation);
                }
            }
        }
        System.out.println("The sum of all the 0-9 pandigital numbers with the sub-string divisibility property is: " + sum);
    }
}
