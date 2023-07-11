package problems;

import java.util.ArrayList;
import java.util.HashSet;

import util.Numbers;

//Prime Power Triples.
public class Problem087 {
    private static int limit = 50000000 ;
    private static ArrayList<Integer> primes = Numbers.generatePrimes((int) Math.sqrt(limit));
    private static HashSet<Integer> primePowers = new HashSet<>();
    public static void main(String[] args) {
        for (int square = 0; square < primes.size(); square++){
            for (int cube = 0; cube < primes.size(); cube++){
                for (int fourth = 0; fourth < primes.size(); fourth++){
                    int num = (int) (Math.pow(primes.get(square), 2) +
                                                Math.pow(primes.get(cube), 3) +
                                                Math.pow(primes.get(fourth), 4));
                    if (num < limit) primePowers.add(num);
                }
            }
        }
        System.out.println("The amount of numbers under fifty million that can be expressed as the sum of a prime square, cube and fourth power is: " + primePowers.size());
    }






}
