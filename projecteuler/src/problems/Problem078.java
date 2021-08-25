package problems;

import util.Numbers;
import util.Timer;

import java.util.HashMap;
import java.util.List;

//Coin Partitions.
public class Problem078 {
    private static int answer = 2;
    private static HashMap<Integer, Integer> cachedPartitions = new HashMap<>();
    public static void main(String[] args) {
        while (coinPartition(5 * answer + 4) != 0){
            answer++;
        }
        System.out.println("The smallest number of coins that can be partitioned in piles in a multiple of a million different ways is: " + (5 * answer + 4));
    }

    public static long coinPartition(int num){
        if (num == 0) return 1;
        if (num < 0) return 0;
        if (cachedPartitions.containsKey(num)){
            return cachedPartitions.get(num);
        }
        int decrease = 1;
        int totalSum = 0;
        while (decrease * (3 * decrease - 1) / 2 <= num){
            totalSum += (decrease%2 == 1 ? 1 : -1) * coinPartition(num - decrease * (3 * decrease - 1) / 2);
            totalSum += (decrease%2 == 1 ? 1 : -1) * coinPartition(num - decrease * (3 * decrease - 1) / 2 - decrease);
            decrease++;
        }
        totalSum = totalSum%1000000;
        cachedPartitions.put(num, totalSum);
        return totalSum;
    }

}
