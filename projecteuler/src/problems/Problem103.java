package problems;

import util.Sets;
import util.Timer;

import java.util.*;
import java.util.List;

//Special Subset Sums: Optimum
public class Problem103 {
    // 1.   S(B) ≠ S(C); that is, sums of subsets cannot be equal.
    // 2.    If B contains more elements than C then S(B) > S(C).
    //for a set a, b, c, ..., a + b > c, a + c > d, a + y > z
    //a and b can be whatever
    //then c has to be less than a more than b, d has to be < a more than c...
    private static int bestSetSum = Integer.MAX_VALUE;
    private static int bestSetLast = Integer.MAX_VALUE;
    public static void main(String[] args) {
        //11, 18, 19, 20, 22, 25
        Timer.startTimer();
        ArrayList<Integer> optimumSpecialSumSet = optimalSpecialSumSet(7);
        System.out.println("The Optimal Special Sum Set for n=7 is: " + optimumSpecialSumSet);
        System.out.println("It's corresponding set string is: " + optimumSpecialSumSet.toString()
                .replace(",", "")
                .replace(" ", "")
                .replace("[", "")
                .replace("]", ""));
        System.out.println("The set's sum is: " + Sets.sum(optimumSpecialSumSet));
        System.out.println(Timer.elapsedTime() + "ms");
    }

    public static ArrayList<Integer> optimalSpecialSumSet(int n){
        if (n == 1) return new ArrayList<>(Collections.singletonList(1));
        int a = 1;
        int b = a + 1;
        ArrayList<Integer> bestSubset = null;
        while (true) {
            ArrayList<ArrayList<Integer>> potentialSets = checkAB(new ArrayList<>(List.of(a, b)), n);
            if (potentialSets.size() > 0) {
                for (ArrayList<Integer> set : potentialSets){
                    if (bestSubset == null || (Sets.sum(set) < bestSetSum ||
                            (Sets.sum(set) == bestSetSum &&
                                    set.get(set.size()-1) < bestSetLast))){
                        bestSubset = set;
                        bestSetSum = Sets.sum(bestSubset);
                        bestSetLast = bestSubset.get(bestSubset.size()-1);

                    }
                }
            }
            b++;
            if (b >= 2 * a){
                a++;
                b = a + 1;
            }
            if (bestSubset != null &&  a + (n - 1) * b + (n - 1) * (n - 2) / 2 > Sets.sum(bestSubset)) break;
        }
        return bestSubset;
    }

    public static ArrayList<ArrayList<Integer>> checkAB(ArrayList<Integer> s, int n){
        if (s.size() < n){
            ArrayList<ArrayList<Integer>> totalSubsets = new ArrayList<>();
            for (int newNum = s.get(s.size()-1); newNum < Math.min(Math.min(s.get(1) + s.get(0), bestSetSum - Sets.sum(s)), bestSetLast); newNum++){
                ArrayList<Integer> temp = new ArrayList<>(List.copyOf(s));
                temp.add(newNum);
                ArrayList<ArrayList<Integer>> foundSubsets = checkAB(temp, n);
                if (foundSubsets != null){
                    totalSubsets.addAll(foundSubsets);
                }
            }
            return totalSubsets;
        } else {
            return (checkSpecialSumSet(s) ? new ArrayList<>(Collections.singletonList(s)) : null);
        }
    }

    public static boolean checkSpecialSumSet(List<Integer> s){
        HashMap<Integer, ArrayList<Integer>> sums = new HashMap<>();
        for (int i = 0; i <= s.size(); i++) sums.put(i, new ArrayList<>());
        for (int mask = 0; mask < Math.pow(2, s.size()); mask++){
            int total = 0;
            int shifts = 0;
            int temp = mask;
            int numbersUsed = 0;
            while (temp >= 1){
                total += (temp%2 == 1 ? s.get(shifts) : 0);
                numbersUsed += temp%2;
                shifts++;
                temp /= 2;
            }
            sums.get(numbersUsed).add(total);
        }
        ArrayList<Integer> allSums = new ArrayList<>();
        allSums.add(0);
        for (int i = 1; i <= s.size(); i++) {
            if (Sets.min(sums.get(i)) < Sets.max(allSums)) return false;
            allSums.addAll(sums.get(i));
        }
        if (allSums.size() == new HashSet<>(allSums).size()){
            return true;
        }
        return false;
    }




}
