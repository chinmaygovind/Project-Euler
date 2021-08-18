package problems;


import util.Numbers;

import java.util.ArrayList;
import java.util.HashMap;

//Prime Pair Sets.
public class Problem060 {
    private static HashMap<Integer, ArrayList<Integer>> primeMap = new HashMap<>();
    private static ArrayList<Integer> answerSet = new ArrayList<>();
    private static int sum = 0;
    public static void main(String[] args) {
        ArrayList<Integer> primes = Numbers.generatePrimes(100000);
        for (int prime : primes){
            if (!answerSet.isEmpty()){
                break;
            }
            addNode(prime);
            //generate subsets to check
            ArrayList<Integer> potentialLinks = primeMap.get(prime);
            for (Integer link : (ArrayList<Integer>) potentialLinks.clone()){
                if (!primeMap.get(link).contains(prime)){
                    potentialLinks.remove(link);
                }
            }
            if (potentialLinks.size() >= 4){
                int[] indices = new int[]{0, 1, 2, 3};
                while (true){
                    boolean workingPool = true;
                    ArrayList<Integer> pool = new ArrayList<>();
                    pool.add(prime);
                    for(int i : new int[] {potentialLinks.get(indices[0]),  potentialLinks.get(indices[1]),  potentialLinks.get(indices[2]), potentialLinks.get(indices[3])}){
                        for (int pooled : pool){
                            if (!primeMap.get(pooled).contains(i) || !primeMap.get(i).contains(pooled)){
                                workingPool = false;
                                break;
                            }
                        }
                        pool.add(i);
                    }
                    if (workingPool){
                        answerSet = pool;
                        break;
                    }
                    //update indices
                    if (indices[3] >= potentialLinks.size() - 1) {
                        if (indices[2] >= potentialLinks.size() - 2) {
                            if (indices[1] >= potentialLinks.size() - 3) {
                                if (indices[0] >= potentialLinks.size() - 4) {
                                    break;
                                } else {
                                    indices[0]++;
                                    indices[1] = indices[0] + 1;
                                    indices[2] = indices[1] + 1;
                                    indices[3] = indices[2] + 1;
                                }

                            } else {
                                indices[1]++;
                                indices[2] = indices[1] + 1;
                                indices[3] = indices[2] + 1;
                            }
                        } else {
                            indices[2]++;
                            indices[3] = indices[2] + 1;
                        }
                    } else {
                        indices[3]++;
                    }
                }
            }
        }
        for (int ans : answerSet){
            sum += ans;
        }
        System.out.println("The sum of the set of five smallest primes that can be concatenated with each other to form new primes is: " + sum);
        System.out.println("The set is: " + answerSet);
    }

    private static void addNode(int prime){
        ArrayList<Integer> concatenatable = new ArrayList<>();
        for (int node : primeMap.keySet()){
            if (Numbers.isPrime(Integer.parseInt(node + String.valueOf(prime)))){
                primeMap.get(node).add(prime);
            }
            if (Numbers.isPrime(Integer.parseInt( String.valueOf(prime)+ node))){
                concatenatable.add(node);
            }
        }
        primeMap.put(prime, concatenatable);
    }

}
