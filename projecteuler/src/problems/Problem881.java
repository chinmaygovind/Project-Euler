package problems;


import util.Numbers;
import util.Sets;

import java.util.*;

//Divisor Graph Width.
public class Problem881 {

    private static HashMap<Integer, Set<List<Integer>>> blocks = new HashMap<>();
    public static void main(String[] args) {
        Numbers.generateCachedPrimes(10_000_000);
        System.out.println(arrangedGraph(5040));
        //2^4, 3^2, 5^1, 7^1 = 7c4
        //i need a soup of K primes such that there's 10^4 ways to choose N of them

    }


    public static HashMap<Integer, HashSet<Integer>> graph(int n) {
        if (Numbers.getCachedPrimes().size() < n) Numbers.generateCachedPrimes(2 * n);
        ArrayList<Integer> divisors = Numbers.getFactors(n, true);
        HashMap<Integer, HashSet<Integer>> myGraph = new HashMap<>();
        for (int i : divisors) myGraph.put(i, new HashSet<>());
        for (int i = 0; i < divisors.size(); i++) {
            for (int j = 1; j < divisors.size(); j++) {
                int a = divisors.get(i);
                int b = divisors.get(j);
                if (b%a == 0 && Numbers.getCachedPrimes().contains(b / a)) {
                    myGraph.get(b).add(a);
                    myGraph.get(a).add(b);
                }
            }
        }
        return myGraph;
    }

    public static ArrayList<ArrayList<Integer>> arrangedGraph(int n) {
        HashMap<Integer, HashSet<Integer>> myGraph = graph(n);
        if (Numbers.getCachedPrimes().size() < n) Numbers.generateCachedPrimes(2 * n);
        ArrayList<Integer> divisors = Numbers.getFactors(n, true);
        HashSet<Integer> visited = new HashSet<>(divisors.size());
        ArrayList<Integer> toVisit = new ArrayList<>();
        toVisit.add(n);
        int k = 0;
        ArrayList<ArrayList<Integer>> arrangedGraph = new ArrayList<>();
        while (!toVisit.isEmpty()) {
            ArrayList<Integer> newVisits = new ArrayList<>();
            arrangedGraph.add(new ArrayList<>());
            for (int visitor : toVisit) {
                if (visited.contains(visitor)) continue;
                visited.add(visitor);
                arrangedGraph.get(k).add(visitor);
                for (int nextVisitor : myGraph.get(visitor)) {
                    if (!visited.contains(nextVisitor)) newVisits.add(nextVisitor);
                }
            }
            toVisit = newVisits;
            k++;
        }
        return arrangedGraph;

    }
    public static int g(int n) {
        ArrayList<ArrayList<Integer>> myGraph = arrangedGraph(n);
        int max = 0;
        for (ArrayList<Integer> level : myGraph) {
            max = Math.max(max, level.size());
        }
        return max;
    }

    public static double getProportion(int n) {
        ArrayList<Integer> divisors = Numbers.getFactors(n, true);
        return (double) g(n) / divisors.size();
    }


}

