package problems;

import util.Numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

//Cube Digit Pairs.
public class Problem090 {
    private static final ArrayList<String> digits = Numbers.getPermutations("0123456789");
    private static final HashSet<String> uniqueCombinations = new HashSet<>();
    private static final ArrayList<String> squares = new ArrayList<>(
            Arrays.asList("01", "04", "06", "16", "25", "36", "46", "64", "81"));//09 and 49 replaced with sixes
    private static int workingCombinations = 0;

    public static void main(String[] args) {
        for (String digit : digits){
            String[] sortedDigits = digit.substring(0, 6).split("");
            Arrays.sort(sortedDigits);
            uniqueCombinations.add(String.join("", sortedDigits));
        }
        ArrayList<String> combinations = new ArrayList<>(uniqueCombinations);
        Collections.sort(combinations);
        for (int cube1 = 0; cube1 < combinations.size(); cube1++){
            for (int cube2 = cube1 + 1; cube2 < combinations.size(); cube2++){
                boolean working = true;
                for (String square : squares){
                    if (!((combinations.get(cube1).replace('9', '6').contains(square.substring(0, 1)) &&
                            combinations.get(cube2).replace('9', '6').contains(square.substring(1))) ||
                            (combinations.get(cube1).replace('9', '6').contains(square.substring(1)) &&
                            combinations.get(cube2).replace('9', '6').contains(square.substring(0, 1))))) working = false;
                }
                if (working){
                    workingCombinations++;
                }
            }
        }
        System.out.println("The number of distinct arrangements for the two cubes so that they can form all the 1 and 2 digit squares is: " + workingCombinations);
    }







}
