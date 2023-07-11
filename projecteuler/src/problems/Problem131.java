package problems;


import java.util.ArrayList;

import util.Numbers;

//Prime Cube Partnership.
public class Problem131 {
    private static final int LIMIT = 1000000;
    private static ArrayList<Integer> primes = Numbers.generatePrimes(LIMIT);
    private static ArrayList<Long> goodPrimes = new ArrayList<>();
    public static void main(String[] args) {
        for (int n = 1; n < 3000; n++) {
            int p = 3 * n * n + 3 * n + 1;//difference between n + 1 cubed and n cubed
            if (primes.contains(p)) goodPrimes.add((long) p);
        }
        System.out.println("The sum of all primes below one million with the \"remarkable property\" is: " + goodPrimes.size());
    }



}

