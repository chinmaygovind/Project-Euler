package problems;


import util.Numbers;

import java.util.ArrayList;

//Prime Permutations
public class Problem49 {
    private static ArrayList<Integer> failed = new ArrayList<>();
    private static String answer = "notFound";

    public static void main(String[] args){
        int num = 1000;
        while (answer.equals("notFound")){
            if (!checkNum(num).equals("fail") && !checkNum(num).equals("148748178147")){
                answer = checkNum(num);
            }
            num++;
        }
        System.out.println("The concatenated three four-digit primes that are permutations of each other and are an arithmetic sequence are: " + answer);
    }

    private static String checkNum(int num){
        ArrayList<String> permutations = Numbers.getPermutations(String.valueOf(num));
        if (permutations.size() < 3){
            return "fail";
        }
        for (int i = 0; i < permutations.size() - 2; i++){
            for (int j = i + 1; j < permutations.size() - 1; j++){
                for (int k = j + 1; k < permutations.size(); k++){
                    int num1 = Integer.parseInt(permutations.get(i));
                    int num2 = Integer.parseInt(permutations.get(j));
                    int num3 = Integer.parseInt(permutations.get(k));
                    if (num3 - num2 == num2 - num1 && num3 > num2 &&
                            Numbers.isPrime(num3) && Numbers.isPrime(num2) && Numbers.isPrime(num1)){
                        return permutations.get(i) + permutations.get(j) + permutations.get(k);
                    }
                }
            }
        }
        return "fail";
    }
}
