package problems;


import util.English;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

//Pandigital Prime.
public class Problem042 {
    private static String words[] = new Scanner(Objects.requireNonNull(Problem042.class.getResourceAsStream("/problem42.txt"))).nextLine().split(",");
    private static int triangleWords = 0;
    private static ArrayList<Integer> triangleNumbers = new ArrayList<>();

    public static void main(String[] args){
        for (int i = 1; i < 20; i++){
            triangleNumbers.add(i * (i + 1) / 2);
        }
        for (String word : words){
            int wordScore = 0;
            for (char letter : word.toCharArray()){
                wordScore += English.letterToNum(letter);
            }
            if (triangleNumbers.contains(wordScore)){
                triangleWords++;
            }
        }
        System.out.println("The number of words with a triangle number word score is: " + triangleWords);
    }
}
