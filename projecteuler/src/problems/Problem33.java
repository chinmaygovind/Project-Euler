package problems;

import java.util.ArrayList;

//Digit Cancelling Fractions.
public class Problem33 {
    private static ArrayList<Integer> denominators = new ArrayList<>();
    private static int numerator = 1;
    private static int leastCommonDenominator = 1;
    public static void main(String[] args){
        //Find all non-trivial fractions.
        for (double i = 10; i <= 98; i++){
            for (double j = i + 1; j <= 99; j++){
                if (i%10 == 0 && j%10 == 0){
                    continue;
                } else {
                    if (i / j == (i % 10) / (j % 10) && Math.floor(i / 10) == Math.floor(j / 10)) {
                        denominators.add((int) j);
                        numerator *= i;
                    }
                    if (i / j == (i % 10) / Math.floor(j / 10) && Math.floor(i / 10) == (j % 10)) {
                        denominators.add((int) j);
                        numerator *= i;
                    }
                    if (i / j == Math.floor(i / 10) / (j % 10) && (i % 10) == Math.floor(j / 10)) {
                        denominators.add((int) j);
                        numerator *= i;
                    }
                    if (i / j == Math.floor(i / 10) / Math.floor(j / 10) && (i % 10) == (j % 10)) {
                        denominators.add((int) j);
                        numerator *= i;
                    }
                }

            }
        }
        //Find LCD.
        System.out.println(denominators);
        for (int d : denominators){
            leastCommonDenominator *= d;
        }
        leastCommonDenominator /= numerator;
        System.out.println("The least common denominator of the product of the four non-trivial digit cancelling fractions is: " + leastCommonDenominator);
    }
}
