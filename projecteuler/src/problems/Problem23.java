package problems;


import util.Numbers;

import java.util.ArrayList;

public class Problem23 {
    private static ArrayList<Integer> abundantNumbers = new ArrayList<>();
    private static int unsummableNumbersSum = 0;
    public static void main(String[] args){
        //create list of abundant numbers
        for (int i = 1; i <= 28123; i++){
            int factorSum = 0;
            for (int factor : Numbers.getFactors(i)){
                factorSum += factor;
            }
            if (factorSum > i){
                abundantNumbers.add(i);
            }
        }
        for (int i = 1; i <= 28123; i++){
            unsummableNumbersSum += (isSummable(i) ? 0 : i);
        }
        System.out.println("The sum of all numbers that cannot be written as the sum of two abundant numbers is: " + unsummableNumbersSum);
    }

    public static boolean isSummable(int num){
        for (int i = 0; i < abundantNumbers.size(); i++){
            for (int j = i; j < abundantNumbers.size(); j++){
                if (abundantNumbers.get(j) > num){
                    break;
                }
                if (abundantNumbers.get(j) + abundantNumbers.get(i) == num){
                    return true;
                }
            }
            if (abundantNumbers.get(i) > num){
                return false;
            }
        }
        return false;
    }

}
