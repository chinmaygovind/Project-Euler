package problems;

import util.Numbers;

import java.util.*;

//Arithmetic Expressions.
public class Problem093 {
    private static int maxN = 0;
    private static String maxABCD = "";
    public static void main(String[] args) {
        for (int a = 0; a < 10; a++){
            for (int b = a + 1; b < 10; b++){
                for (int c = b + 1; c < 10; c++){
                    for (int d = c + 1; d < 10; d++){
                        ArrayList<Double> possibleTargets = new ArrayList<>();
                        String numString = String.valueOf(a) + b + c + d;
                        for (String digitOrder : Numbers.getPermutations(numString)){
                            for (int i = 0; i < 64; i++){
                                double total = Character.getNumericValue(digitOrder.charAt(0));//no parenth
                                double total2left = Character.getNumericValue(digitOrder.charAt(0));//a + (b + c + d)
                                double total2right = Character.getNumericValue(digitOrder.charAt(1));
                                double total3left = Character.getNumericValue(digitOrder.charAt(0));//(a + b) + (c + d)
                                double total3right = Character.getNumericValue(digitOrder.charAt(2));
                                int[] toBeUsed = new int[]{Character.getNumericValue(digitOrder.charAt(1)),
                                        Character.getNumericValue(digitOrder.charAt(2)),
                                                Character.getNumericValue(digitOrder.charAt(3))};
                                int j = i;
                                for (int operations = 0; operations < 3; operations++){
                                    if (j%4 == 0) total += toBeUsed[operations];
                                    if (j%4 == 1) total -= toBeUsed[operations];
                                    if (j%4 == 2) total /= toBeUsed[operations];
                                    if (j%4 == 3) total *= toBeUsed[operations];
                                    if (operations == 0){
                                        if (j%4 == 0) total2right += toBeUsed[operations + 1];
                                        if (j%4 == 1) total2right -= toBeUsed[operations + 1];
                                        if (j%4 == 2) total2right /= toBeUsed[operations + 1];
                                        if (j%4 == 3) total2right *= toBeUsed[operations + 1];
                                        if (j%4 == 0) total3left += toBeUsed[operations];
                                        if (j%4 == 1) total3left -= toBeUsed[operations];
                                        if (j%4 == 2) total3left /= toBeUsed[operations];
                                        if (j%4 == 3) total3left *= toBeUsed[operations];
                                    } else if (operations == 1){
                                        if (j%4 == 0) total2right += toBeUsed[operations + 1];
                                        if (j%4 == 1) total2right -= toBeUsed[operations + 1];
                                        if (j%4 == 2) total2right /= toBeUsed[operations + 1];
                                        if (j%4 == 3) total2right *= toBeUsed[operations + 1];
                                        if (j%4 == 0) total3right += toBeUsed[operations + 1];
                                        if (j%4 == 1) total3right -= toBeUsed[operations + 1];
                                        if (j%4 == 2) total3right /= toBeUsed[operations + 1];
                                        if (j%4 == 3) total3right *= toBeUsed[operations + 1];
                                    } else {
                                        if (j%4 == 0) total2left += total2right;
                                        if (j%4 == 1) total2left -= total2right;
                                        if (j%4 == 2) total2left /= total2right;
                                        if (j%4 == 3) total2left *= total2right;
                                        if (j%4 == 0) total3left += total3right;
                                        if (j%4 == 1) total3left -= total3right;
                                        if (j%4 == 2) total3left /= total3right;
                                        if (j%4 == 3) total3left *= total3right;
                                    }
                                    j /= 4;
                                }
                                possibleTargets.add(total);
                                possibleTargets.add(total2left);
                                possibleTargets.add(total3left);
                            }
                        }
                        Collections.sort(possibleTargets);
                        double max = 0;
                        while (possibleTargets.contains(max + 1)){
                            max++;
                        }
                        if (max > maxN){
                            maxN = (int) max;
                            maxABCD = numString;
                        }
                    }
                }
            }
        }
        System.out.println("The concatenation of digits a, b, c, and d that produce the most consecutive numbers from 1 to n using the four basic operators is: " + maxABCD);

    }







}
