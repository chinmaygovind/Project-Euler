package problems;

import util.English;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;


public class Problem22 {

    private static String names[] = new Scanner(Objects.requireNonNull(Problem22.class.getResourceAsStream("/problem22.txt"))).nextLine().split(",");
    private static int scoreSum = 0;
    public static void main(String[] args){
        Arrays.sort(names);
        for (int name = 0; name < names.length; name++){
            int nameScore = 0;
            for (char letter : names[name].toCharArray()){
                nameScore += English.letters.indexOf(letter) + 1;
            }
            nameScore *= (name + 1);
            scoreSum += nameScore;
        }

        System.out.println("The total score of the names list is: " + scoreSum);
    }

}
