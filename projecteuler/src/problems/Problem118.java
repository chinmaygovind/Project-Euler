package problems;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import util.Numbers;

//Pandigital Prime Sets.
public class Problem118 {
    public static ArrayList<Integer> numbers = new ArrayList<>();
    public static HashSet<HashSet<Integer>> sets = new HashSet<>();
    public static void main(String[] args) {
        for (String num : Numbers.getPermutations("123456789")) {
            numbers.add(Integer.parseInt(num));
        }
        for (Integer i : numbers) {
            subdivide(i, new ArrayList<>());
        }
        //System.out.println(subdivide(254789631, new ArrayList<>()));
        //System.out.println(sets);
        System.out.println("The number of pandigital distinct sets containing only primes is: " + sets.size());
    }

    public static int subdivide(int num, List<Integer> past) {
        if (num == 0) {
            sets.add(new HashSet<>(past));
            return 1;
        }
        int total = 0;
        //if (cache.containsKey(num)) return cache.get(num);
        String temp = String.valueOf(num);
        for (int i = 0; i <= temp.length(); i++){
            int half1 = Integer.parseInt("0" + temp.substring(0, i));
            if (Numbers.isPrime(half1)) {
                ArrayList<Integer> newList = new ArrayList<>(past);
                newList.add(half1);
                total += subdivide(Integer.parseInt("0" + temp.substring(i)), newList);
            }
        }
        //cache.put(num, total);
        return total;
    }
}

