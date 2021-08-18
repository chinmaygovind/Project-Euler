package problems;


import util.Numbers;

import java.util.ArrayList;

//Number Spiral Diagonals.
public class Problem28 {
    private static ArrayList<Integer> diagonalNumbers = new ArrayList<>();
    private static int diagonalSum = 0;

    public static void main(String[] args){
        int currentNum = 1;
        diagonalNumbers.add(currentNum);
        for (int step = 2; step < 1001; step += 2){
            for (int i = 0; i < 4; i++){
                currentNum += step;
                diagonalNumbers.add(currentNum);

            }
        }
        for (int num : diagonalNumbers){
            diagonalSum += num;
        }
        System.out.println("The sum of the diagonals of a 1001x1001 spiral is: " + diagonalSum);
    }
}
