package problems;

import java.math.BigInteger;
import java.util.ArrayList;

// Number Mind.
public class Problem185 {
    
    private static String[] guesses = new String[]{
            "5616185650518293",
            "3847439647293047",
            "5855462940810587",
            "9742855507068353",
            "4296849643607543",
            "3174248439465858",
            "4513559094146117",
            "7890971548908067",
            "8157356344118483",
            "2615250744386899",
            "8690095851526254",
            "6375711915077050",
            "6913859173121360",
            "6442889055042768",
            "2321386104303845",
            "2326509471271448",
            "5251583379644322",
            "1748270476758276",
            "4895722652190306",
            "3041631117224635",
            "1841236454324589",
            "2659862637316867"
    };

    public static int[] correct = new int[]{
            2, 1, 3, 3, 3, 1, 2, 3, 1, 2, 3, 1, 1, 2, 0, 2, 2, 3, 1, 3, 3, 2, -1
    };

//    private static String[] guesses = new String[]{
//            "90342",
//            "70794",
//            "39458",
//            "34109",
//            "51545",
//            "12531"
//    };
//    public static int[] correct = new int[]{
//            2, 0, 2, 1, 2, 1, -1
//    };

    public static void main(String[] args) {
        System.out.println(solve("                "));
    }
    
    public static String solve(String guess) {
        if (!guess.contains(" ")) {
            boolean valid = true;
            for (int j = 0; j < guesses.length; j++) {
                int correctInJ = 0;
                for (int k = 0; k < guess.length(); k++) {
                    correctInJ += guesses[j].charAt(k) == guess.charAt(k) ? 1 : 0;
                }
                if (correctInJ != correct[j]) {
                    valid = false;
                    break;
                }
            }
            return valid ? guess : null;
        }
        System.out.println(guess);
        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == ' ') {
                for (int idx = 0; idx < guesses.length; idx++) {
                    String newGuess = guess.substring(0, i) + guesses[idx].charAt(i) + guess.substring(i + 1);
                    int blanks = 0;
                    for (char c : newGuess.toCharArray()) blanks += c == ' ' ? 1 : 0;
                    boolean valid = true;
                    for (int j = 0; j < guesses.length; j++) {
                        int correctInJ = 0;
                        for (int k = 0; k < newGuess.length(); k++) {
                            correctInJ += guesses[j].charAt(k) == newGuess.charAt(k) ? 1 : 0;
                        }
                        if (correctInJ > correct[j] || correctInJ < correct[j] - blanks) {
                            valid = false;
                            break;
                        }
                    }
                    if (valid) {
                        String result = solve(newGuess);
                        if (result != null) return result;
                    }
                }


            }
        }
        return null;
    }

}
