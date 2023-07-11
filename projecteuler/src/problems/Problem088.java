package problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import util.Numbers;

//Product-Sum Numbers.
public class Problem088 {
    private static int limit = 12000;
    private static ArrayList<Integer> primes = Numbers.generatePrimes(limit);
    private static HashMap<Integer, Integer> productSums = new HashMap<>();
    private static HashMap<Integer, ArrayList<ArrayList<Integer>>> factorizations = new HashMap<>();
    private static int n = 1;
    public static void main(String[] args) {
        while (productSums.size() < limit - 1){
            for (ArrayList<Integer> factorization : getMultiplications(n)){
                int k = n - factorization.stream().mapToInt(Integer::intValue).sum() + factorization.size();
                if (!productSums.containsKey(k) && k <= limit && k != 1) productSums.put(k, n);
            }
            n++;
        }
        System.out.println("The sum of all minimal product-sum numbers for 2 <= k <= 12000 is: " + new HashSet<Integer>(productSums.values()).stream().mapToInt(Integer::intValue).sum());
    }

    public static ArrayList<ArrayList<Integer>> getMultiplications(int num){
        if (num == 1 || primes.contains(num)) {
            ArrayList<ArrayList<Integer>> one = new ArrayList<>();
            ArrayList<Integer> factors = new ArrayList<>();
            factors.add(num);
            one.add(factors);
            return one;
        }
        if (factorizations.containsKey(num)) return factorizations.get(num);
        ArrayList<ArrayList<Integer>> multiplications = new ArrayList<>();
        for (int factor : Numbers.getFactors(num)){
            ArrayList<Integer> factorization = new ArrayList<>();
            if (factor == 1) {
                factorization.add(num);
                multiplications.add(factorization);
                factorization = new ArrayList<>();
                continue;
            }
            for (ArrayList<Integer> factors : getMultiplications(num/factor)){
                factorization.add(factor);
                factorization.addAll(factors);
                multiplications.add(factorization);
                factorization = new ArrayList<>();
            }
        }
        factorizations.put(num, multiplications);
        return multiplications;
    }






}
