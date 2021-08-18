package problems;

import java.util.ArrayList;
import java.util.LinkedList;

//Odd Period Square Roots.
public class Problem064 {
    private static int oddPeriods = 0;
    public static void main(String[] args) {
        for (int n = 0; n < 10000; n++){
            oddPeriods += findExpansion(n) %2;
        }
        System.out.println("The number of continued fractions of square roots of all integers up to 10,000 that have odd periods is: " + oddPeriods);
    }

    private static int findExpansion(int num){
        if (Math.sqrt(num)%1 == 0) return 0;
        int initial = (int) Math.sqrt(num);
        int currentNumerator = 1;
        int currentRoot = num;
        int currentInt = -initial;
        LinkedList<Integer> expansion = new LinkedList<>();
        ArrayList<String> pastFractions = new ArrayList<>();
        while (!pastFractions.contains("" + currentNumerator + currentRoot + currentInt)){
            pastFractions.add("" + currentNumerator + currentRoot + currentInt);
            //step 1: multiply by complex conjugate
            int numeratorInt = -currentInt * currentNumerator;
            int denominator = currentRoot - currentInt*currentInt;
            //System.out.println("converted: " + currentNumerator + " * sqrt(" + currentRoot + ") + " + numeratorInt + " / " + denominator);
            expansion.add((int) ((currentNumerator * Math.sqrt(currentRoot) + numeratorInt) / denominator));
            currentInt = (numeratorInt / currentNumerator) - expansion.getLast() * denominator / currentNumerator;
            currentNumerator = denominator / currentNumerator;
        }
        return expansion.size();
    }

}
