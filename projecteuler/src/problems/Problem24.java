package problems;

import util.Numbers;

import java.util.ArrayList;
import java.util.Collections;

//Lexicographic Permutations.
public class Problem24 {
    private static int[] factorialCounts = new int[10];
    private static ArrayList<Integer> numbers = new ArrayList<Integer>();
    private static String millionthPermutation = "";
    public static void main(String[] args){
        int permutationCount = 1000000;
        for (int i = 9; i > 0; i--){
            numbers.add(i);
            System.out.println(i + ": " + Numbers.factorial(i));
            while (permutationCount > Numbers.factorial(i)){
                permutationCount -= Numbers.factorial(i);
                factorialCounts[9 - i] += 1;
            }
        }
        numbers.add(0);
        Collections.reverse(numbers);
        for (int count : factorialCounts){
            millionthPermutation += numbers.get(count);
            numbers.remove(count);
        }
        System.out.println("The millionth lexographic permutation of the digits 0-9 is: " + millionthPermutation);
    }

}
