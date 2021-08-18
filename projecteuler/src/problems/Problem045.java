package problems;


import util.Numbers;

import java.util.ArrayList;

//Triangular, Pentagonal, and Hexagonal
public class Problem045 {
    private static int n = 144;
    public static void main(String[] args){
        while (!Numbers.isPentagonal(n * (2 * n - 1))){
            n++;
        }
        System.out.println("The second number that is both hexagonal and pentagonal is: " + n * (2 * n - 1));
    }

}
