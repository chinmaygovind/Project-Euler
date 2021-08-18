package problems;

import util.Numbers;

import java.util.ArrayList;

//Pandigital Products.
public class Problem032 {
    private static ArrayList<Integer> products = new ArrayList<>();
    private static int pandigitalProductsSum = 0;

    public static void main(String[] args){
        for (int i = 123456789; i <= 987654321; i++){
            if (Numbers.isPandigital(i, 1, 9)){
                for (int multiply = 1; multiply <= 7; multiply++){
                    for (int equal = multiply+1; equal <= 8; equal++){
                        int a = Integer.parseInt(String.valueOf(i).substring(0, multiply));
                        int b = Integer.parseInt(String.valueOf(i).substring(multiply,equal));
                        int product = Integer.parseInt(String.valueOf(i).substring(equal, 9));
                        if (a * b == product){
                            if (!products.contains(product)){
                                products.add(product);
                            }
                        }
                    }
                }
            }
        }

        for (int product : products){
            pandigitalProductsSum += product;
        }
        System.out.println("The sum of all products whose multiplicand/multiplier/product identity can be written as a 1-9 pandigital is: " + pandigitalProductsSum);
    }
}
