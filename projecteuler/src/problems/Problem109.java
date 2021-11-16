package problems;


import java.util.ArrayList;
import java.util.Collections;

//Darts.
public class Problem109 {
    private static ArrayList<Integer> allScores = new ArrayList<>();
    private static ArrayList<Integer> doubles = new ArrayList<>();
    private static int checkOuts = 0;
    public static void main(String[] args) {
        for (int i = 1; i < 21; i++){
            allScores.add(i);
            allScores.add(2 * i);
            doubles.add(2 * i);
            allScores.add(3 * i);
        }
        allScores.add(25); allScores.add(50); doubles.add(50); allScores.add(0);
        Collections.sort(allScores);
        System.out.println(allScores);
        Collections.sort(doubles);
        for (int score = 0; score < 100; score++){
            for (Integer d : doubles){
                if (d > score) break;
                for (int a = 0; a < allScores.size(); a++){
                    if (d + allScores.get(a) > score) break;
                    for (int b = a; b < allScores.size(); b++){
                        if (d + allScores.get(a) + allScores.get(b) == score) {
                            checkOuts++;
                            } else if (d + allScores.get(a) + allScores.get(b) >= score) break;
                    }
                }
            }
        }
        System.out.println("The number of darts checkouts with a score of less than 100 is: " + checkOuts);
    }



}
