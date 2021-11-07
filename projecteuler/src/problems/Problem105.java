package problems;

import util.Files;
import util.Sets;
import util.Timer;

import java.util.*;
import java.util.stream.Collectors;

//Special Subset Sums: Testing
public class Problem105 {
    private static Scanner fileScan = Files.fileScanner("/problem105.txt");
    private static ArrayList<List<Integer>> sets = new ArrayList<>(1000);
    private static int totalSum = 0;
    public static void main(String[] args) {
        while (fileScan.hasNextLine()) sets.add(Sets.stringArrayToIntList(fileScan.nextLine().split(",")));
        for (List<Integer> set : sets){
            totalSum += checkSpecialSumSet(set);
        }
        System.out.println("The sum of all the special sum subsets in the file is: " + totalSum);
    }

    public static int checkSpecialSumSet(List<Integer> s){
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
            if (Sets.min(sums.get(i)) < Sets.max(allSums)) return 0;
            allSums.addAll(sums.get(i));
        }
        if (allSums.size() == new HashSet<>(allSums).size()){
            return allSums.get(allSums.size()-1);
        }
        return 0;
    }




}
