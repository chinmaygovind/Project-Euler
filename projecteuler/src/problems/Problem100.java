package problems;

import java.text.DecimalFormat;
import java.util.ArrayList;

//Largest Exponential.
public class Problem100 {
    //Woohoo, 100!
    private static long limit = 1000000000000L;
    public static void main(String[] args) {
        //We want a/b * (a - 1)/(b - 1) = 1/2. So,
        //2 * a * (a - 1) = b * (b - 1).
        //2a^2 - 2a = b^2 - b,
        //2a^2 - 2a - (b^2 - b) = 0. Applying the quadratic formula,
        //a = (2 +- sqrt(4 + 8 * b * (b - 1))) / 4.
        //a = 1/2 + sqrt(b^2 + (b - 1)^2)/2
        //From here, you need b^2 + (b - 1)^2 to be a perfect square.
        //I looked here: https://en.wikipedia.org/wiki/Pythagorean_triple#Almost-isosceles_Pythagorean_triples
        //Once I have that, I can solve for values of b until it's over one trillion.
        ArrayList<Long> bValues = new ArrayList<>();
        bValues.add(4L); bValues.add(21L);
        while (bValues.get(bValues.size()-1) < limit){
            bValues.add(6 * bValues.get(bValues.size()-1) - bValues.get(bValues.size() - 2) - 2);
        }
        System.out.println("The smallest number of blue discs in an arrangement with a half chance to select two blue discs with a total number of discs over one trillion is: " + DecimalFormat.getInstance().format(solveA(bValues.get(bValues.size()-1))).replace(",", ""));



    }
    public static double solveA(double b){
        return (2 + 2 * Math.sqrt(b * b + (b-1) * (b-1))) / 4;
    }





}
