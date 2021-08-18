package problems;


import util.Numbers;

import java.util.ArrayList;

//Distinct Prime Factors.
public class Problem047 {
    private static int num = 1;
    private static int consecutive = 0;
    public static void main(String[] args){
        while (consecutive != 4){
            if (getUniquePrimeFactors(num) == 4){
                consecutive++;
            } else {
                consecutive = 0;
            }
            num++;
        }
        num -= 4;
        System.out.println("The first of the first four consecutive numbers with four unique prime factors is: " + num);
    }

    private static int getUniquePrimeFactors(int num){
        ArrayList<Integer> factors = Numbers.getPrimeFactors(num);
        ArrayList<Integer> uniqueFactors = new ArrayList<>();
        for (int factor : factors){
            if (!uniqueFactors.contains(factor)){
                uniqueFactors.add(factor);
            }
        }
        return uniqueFactors.size();
    }
}
