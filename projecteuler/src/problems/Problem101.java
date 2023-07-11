package problems;

import java.util.ArrayList;
import java.util.List;

import util.Matrix;

//Optimum Polynomial.
public class Problem101 {
    private static List<Double> terms = new ArrayList<>();
    private static long answer = 0;

    public static void main(String[] args) {
        for (int n = 1; n < 15; n++) {
            double total = 0;
            for (int i = 0; i <= 10; i++){
              total += (Math.pow(-1, i) * Math.pow(n, i));
            }
            terms.add(total);
        }
        //System.out.println(terms);
        for (int k = 1; k < 11; k++){
            answer += findBOP(terms.subList(0, k));
        }
        System.out.println("The sum of the FITs for the BOPs of the tenth degree polynomial generating function is: " + answer);
    }

    //TODO: Make the Matrix System work nicely.
    //Creates a system of equations for each integer in sequence, sticks it into a matrix equation and solves it. Math Magic!
    public static double findBOP(List<Double> sequence){
        double[][] a = new double[sequence.size()][sequence.size() + 1];
        for (int i = 0; i < sequence.size(); i++){
            for (int pow = 0; pow < sequence.size(); pow++){
                a[i][pow] = Math.pow(i + 1, pow);
            }
            a[i][sequence.size()] = sequence.get(i);
        }
        Matrix mat = new Matrix(a);
        //System.out.println(mat);
        mat.forwardElimination();
        //System.out.println(mat);
        mat.backSubstitution();
        //System.out.println(mat);
        double total = 0;
        List<Double> coefficients = new ArrayList<>();
        for (int i = 0; i < sequence.size(); i++){
            coefficients.add(mat.getElement(i, sequence.size()));
            total += mat.getElement(i, sequence.size()) * Math.pow(sequence.size() + 1, i);
        }
        return total;
    }





}
