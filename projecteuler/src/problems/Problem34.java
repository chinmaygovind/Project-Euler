package problems;

import util.Numbers;

import java.util.ArrayList;

//Digit Factorials.
public class Problem34 {
    private static ArrayList<Integer> digitFactorials = new ArrayList<>();
    private static int[] factorials = new int[10];
    private static int factorialsSum = 0;

    public static void main(String[] args){
        for (int i = 0; i < 10; i++){
            factorials[i] = (int) Numbers.factorial(i);
        }
        for (int i = 3; i < 10000000; i++){
            int sum = 0;
            for (char digit : String.valueOf(i).toCharArray()){
                sum += factorials[Integer.parseInt(String.valueOf(digit))];
            }
            if (sum == i){
                digitFactorials.add(sum);
            }
        }
        for (int factorial : digitFactorials){
            factorialsSum += factorial;
        }
        System.out.println("The sum of all numbers that can be written as the sum of the factorials of their digits is: " + factorialsSum);
    }
}
