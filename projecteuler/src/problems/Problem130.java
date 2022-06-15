package problems;


import util.Numbers;
import util.Sets;

import java.util.ArrayList;

//Composites With Prime Repunit Property.
public class Problem130 {
    private static ArrayList<Integer> primes = Numbers.generatePrimes(1000000);
    private static ArrayList<Integer> composites = new ArrayList<>();

    public static void main(String[] args) {
        for (int n = 6; composites.size() < 25; n++) {
            if (n%2 == 0 || n%5 == 0 || primes.contains(n)) continue;
            if ((n - 1)%A(n) == 0) composites.add(n);
        }
        System.out.println("The sum of the first twenty-five composite numbers with the prime repunit property is: " + Sets.sum(composites));

    }

    private static int A(int n) {
        if (n%2 == 0 || n%5 == 0) return 0;
        int residue = 1;
        int a = 1;
        while (residue != 0) {
            a++;
            residue = (10 * residue + 1)%n;
        }
        return a;
    }

}

