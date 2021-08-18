package problems;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//Diophantine Equation.
public class Problem066 {
    private static BigInteger maxX = BigInteger.ZERO;
    private static int maxD = 0;
    public static void main(String[] args) {
        for (int d = 0; d <= 1000; d++){
            BigInteger x = findMinX(d);
            if (x.compareTo(maxX) >= 0) {
                maxX = x;
                maxD = d;
            }
        }
        System.out.println("The value of D that produces the largest minimum value of X in the equation x^2 - D * y^2 = 1 is: " + maxD);
        System.out.println("The value of x is: " + maxX);
    }

    private static LinkedList<Integer> findExpansion(int num){
        int initial = (int) Math.sqrt(num);
        int currentNumerator = 1;
        int currentInt = -initial;
        LinkedList<Integer> expansion = new LinkedList<>();
        ArrayList<String> pastFractions = new ArrayList<>();
        while (!pastFractions.contains("" + currentNumerator + num + currentInt)){
            pastFractions.add("" + currentNumerator + num + currentInt);
            //step 1: multiply by complex conjugate
            int numeratorInt = -currentInt * currentNumerator;
            int denominator = num - currentInt*currentInt;
            //System.out.println("converted: " + currentNumerator + " * sqrt(" + currentRoot + ") + " + numeratorInt + " / " + denominator);
            expansion.add((int) ((currentNumerator * Math.sqrt(num) + numeratorInt) / denominator));
            currentInt = (numeratorInt / currentNumerator) - expansion.getLast() * denominator / currentNumerator;
            currentNumerator = denominator / currentNumerator;
        }
        return expansion;
    }
    private static BigInteger[] getFraction(int term, List<Integer> coefficients){
        BigInteger[] fraction = new BigInteger[2];//0 = numerator, 1 = denominator
        for (int i = term - 1; i >= 0; i--){
            if (fraction[0] == null){
                fraction[0] = BigInteger.valueOf(coefficients.get(i));
                fraction[1] = BigInteger.ONE;
            } else {
                BigInteger temp = fraction[0];
                fraction[0] = fraction[1].add(fraction[0].multiply(BigInteger.valueOf(coefficients.get(i))));
                fraction[1] = temp;
            }
        }
        return fraction;
    }

    private static BigInteger findMinX(int n){
        if (Math.sqrt(n) % 1 == 0) return BigInteger.ZERO;
        BigInteger bigN = BigInteger.valueOf(n);
        ArrayList<Integer> coefficients = new ArrayList<>();
        coefficients.add((int) Math.sqrt(n));
        LinkedList<Integer> loop = findExpansion(n);
        for (int i = 0; i < 100; i++){
            coefficients.addAll(loop);
        }
        int term = 1;
        BigInteger[] fraction = getFraction(term, coefficients);
        while (!fraction[0].multiply(fraction[0]).subtract(bigN.multiply(fraction[1]).multiply(fraction[1])).equals(BigInteger.ONE)){
            term++;
            fraction = getFraction(term, coefficients);
        }
        return fraction[0];

    }


}
