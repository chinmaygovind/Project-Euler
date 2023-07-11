package problems;

import java.util.Objects;
import java.util.Scanner;

import util.English;

//Roman Numerals.
public class Problem089 {
    private static final Scanner fileReader = new Scanner(Objects.requireNonNull(Problem089.class.getResourceAsStream("/problem089.txt")));
    private static String[] romanNumerals = new String[1000];
    private static int unneccesaryDigits = 0;
    public static void main(String[] args) {
        int line = 0;
        while (fileReader.hasNextLine()){
            romanNumerals[line] = fileReader.nextLine();
            line++;
        }
        for (int i = 0; i < 1000; i++){
            unneccesaryDigits += romanNumerals[i].length() - English.romanNumeral(English.parseRomanNumeral(romanNumerals[i])).length();
        }
        System.out.println("The number of characters that could be saved by minimizing the thousand Roman Numerals is: " + unneccesaryDigits);
    }







}
