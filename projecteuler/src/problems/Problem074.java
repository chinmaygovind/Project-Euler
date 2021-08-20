package problems;


import util.Numbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

//Digit Factorial Chains.
public class Problem074 {
    private static int[] digitFactorials = new int[10];
    private static HashMap<Integer, Integer> factorialSums = new HashMap<>();
    private static int length60loops = 0;
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++){
            digitFactorials[i] = (int) Numbers.factorial(i);
        }
        for (int i = 3; i < 1000000; i++){
            ArrayList<Integer> chain = new ArrayList<>(Collections.singletonList(i));
            int next = String.valueOf(chain.get(chain.size()-1)).chars().map(e -> digitFactorials[Character.getNumericValue(e)]).sum();
            while (!chain.contains(next)){
                chain.add(next);
                next = String.valueOf(chain.get(chain.size()-1)).chars().map(e -> digitFactorials[Character.getNumericValue(e)]).sum();
            }
            if (chain.size() == 60){
                length60loops++;
            }
        }
        System.out.println("The number of factorial that produce a factorial sum chain of length 60 is: " + length60loops);
    }

}
