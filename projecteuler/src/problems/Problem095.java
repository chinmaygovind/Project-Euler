package problems;

import util.Numbers;

import java.util.ArrayList;
import java.util.HashMap;

//Amicable Chains.
public class Problem095 {
    private static final int limit = 1000000;
    private static int maxChainLength = 0;
    private static int maxChainNum = 0;
    private static final HashMap<Integer, Integer> factorSums = new HashMap<>();
    public static void main(String[] args) {
        Numbers.generateCachedPrimes(limit);
        for (int i = 1; i < limit; i++){
            if (amicableChainLength(i) > maxChainLength){
                maxChainLength = amicableChainLength(i);
                maxChainNum = i;
            }
        }
        System.out.println("The smallest number under one million that generates the longest amicable chain with all elements below one million is: " + maxChainNum);
        System.out.println("The length of this chain is: " + maxChainLength);
    }

    public static int amicableChainLength(int num){
        int originalNum = num;
        ArrayList<Integer> friendsAlongTheWay = new ArrayList<>();
        while (num != 0){
            if (num >= limit) return -1;
            friendsAlongTheWay.add(num);
            int oldNum = num;
            num = factorSums.getOrDefault(num, Numbers.getFactors(num, true).stream().mapToInt(Integer::intValue).sum() - num);
            factorSums.put(oldNum, num);
            if (friendsAlongTheWay.contains(num)) break;
        }
        if (num != originalNum) return -1;
        return friendsAlongTheWay.size();
    }







}
