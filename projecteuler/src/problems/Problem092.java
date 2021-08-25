package problems;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

//Square Digit Chains.
public class Problem092 {
    private static HashSet<Integer> to1 = new HashSet();
    private static HashSet<Integer> to89 = new HashSet();
    private static int limit = 10000000;
    public static void main(String[] args) {
        to1.add(1);
        to89.add(89);
        for (int i = 2; i < limit; i++){
            if (i == 89) continue;
            int temp = i;
            List<Integer> friendsAlongTheWay = new LinkedList<>();
            while (!to1.contains(temp) && !to89.contains(temp)){
                int newTemp = 0;
                for (char digit : String.valueOf(temp).toCharArray()){
                    newTemp += Math.pow(Character.getNumericValue(digit), 2);
                }
                friendsAlongTheWay.add(temp);
                temp = newTemp;

            }
            if (to1.contains(temp)){
                to1.addAll(friendsAlongTheWay);
            }
            if (to89.contains(temp)){
                to89.addAll(friendsAlongTheWay);
            }
        }
        System.out.println("The amount of numbers below ten million that form a square digit chain to 89 is: " + to89.size());
    }







}
