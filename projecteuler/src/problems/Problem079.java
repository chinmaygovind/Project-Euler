package problems;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import util.Numbers;

//Passcode Derivation.
public class Problem079 {
    private static Scanner fileReader = new Scanner(Objects.requireNonNull(Problem079.class.getResourceAsStream("/problem079.txt")));
    private static ArrayList<String> attempts = new ArrayList<>();
    private static final ArrayList<String> possibleCombinations = Numbers.getPermutations("01236789");

    public static void main(String[] args) {
        while (fileReader.hasNextLine()) attempts.add(fileReader.nextLine());
        for (String code : possibleCombinations){
            boolean workingCode = true;
            for (String attempt : attempts){
                String newCode = "";
                for (char digit : code.toCharArray()){
                    if (attempt.contains(String.valueOf(digit))){
                        newCode += digit;
                    }
                }
                if (!newCode.equals(attempt)){
                    workingCode = false;
                }
            }
            if (workingCode){
                System.out.println("The shortest possible code that matches the fifty login attempts is: " + code);
                break;
            }
        }

    }

}
