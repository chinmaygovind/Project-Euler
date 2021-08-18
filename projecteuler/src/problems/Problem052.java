package problems;


import util.Numbers;

import java.util.ArrayList;

//Prime Digit Replacements.
public class Problem052 {
    private static int answer = 1;
    private static int skipThreshold = 17;
    public static void main(String[] args){
        while (!checkNum(answer)){
            answer++;
            if (answer > skipThreshold){
                answer = (int) Math.pow(10, String.valueOf(skipThreshold).length());
                skipThreshold = (int) (answer * 10 / 6.0);
            }
        }
        System.out.println("The smallest integer where its first six multiples contain the same six digits is: " + answer);
    }


    private static boolean checkNum(int num) {
        ArrayList<String> permutations = Numbers.getPermutations(String.valueOf(num));
        for (int i = 1; i <= 6; i++){
            if (!permutations.contains(String.valueOf(num * i))){
                return false;
            }
        }
        return true;
    }
}
