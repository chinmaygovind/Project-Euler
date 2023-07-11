package problems;


import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

//Efficient Exponentiation.
public class Problem122 {
    //for "Efficient Exponentiation" this program isn't very efficient ;(
    private static final HashMap<Integer, HashSet<HashSet<Integer>>> exponentiation = new HashMap<>();
    public static int total = 0;
    public static void main(String[] args) {
        exponentiation.put(1, new HashSet<>(
                Collections.singletonList(new HashSet<>(Collections.singletonList(1))))
        );
        for (int k = 2; k <= 200; k++) {
          minMultiplications(k);
          total += exponentiation.get(k).stream().iterator().next().size() - 1;
          //System.out.println(k + ": " + (exponentiation.get(k).stream().iterator().next().size() - 1));
        }
        System.out.println("The sum of all m(k) for k from 1 to 200 is: " + total);
    }

    public static void minMultiplications(int k) {
        int min = Integer.MAX_VALUE;
        HashSet<HashSet<Integer>> bestCombined = new HashSet<>();
        for (int i = 1; i < k; i++) {
            for (HashSet<Integer> addend1Choice : exponentiation.get(i)) {
                for (HashSet<Integer> addend2Choice : exponentiation.get(k - i)) {
                    HashSet<Integer> combinedList = new HashSet<>(addend1Choice);
                    combinedList.addAll(addend2Choice);
                    combinedList.add(k);
                    if (combinedList.size() + 1 < min) {
                        min = combinedList.size() + 1;
                        bestCombined = new HashSet<>(Collections.singletonList(combinedList));
                    } else if (combinedList.size() + 1 == min) {
                        bestCombined.add(combinedList);
                    }
                }
            }
        }
        exponentiation.put(k, bestCombined);
    }

}

