package problems;



import java.util.ArrayList;

//Champernowne's Constant.
public class Problem040 {
    private static StringBuilder champernowne = new StringBuilder(".");
    private static int answer = 1;
    public static void main(String[] args){
        int counter = 1;
        while (champernowne.length() <= 1000000){
            champernowne.append(counter);
            counter++;
        }
        for (int i = 0; i <= 6; i++){
            answer *= Integer.parseInt(String.valueOf(champernowne.charAt((int) Math.pow(10, i))));
        }

        System.out.println("The value of the expression of the product of digits in Champernowne's Constant is: " + answer);    }
}
