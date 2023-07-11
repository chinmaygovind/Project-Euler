package problems;


import util.Numbers;

//Pandigital Multiples.
public class Problem038 {
    private static long largestPandigital = 0;

    public static void main(String[] args){
        for (int i = 0; i < 10000; i++){
            String concatProduct = "";
            int multiplier = 1;
            while (concatProduct.length() < 9){
                concatProduct += String.valueOf(i * multiplier);
                multiplier++;
            }
            if (concatProduct.length() == 9 && Numbers.isPandigital(Integer.parseInt(concatProduct), 1, 9) && Integer.parseInt(concatProduct) > largestPandigital){
                largestPandigital = Integer.parseInt(concatProduct);
            }
        }
        System.out.println("The largest 1-9 pandigital number made of the concatenated product of an integer's multiples is: " + largestPandigital);
    }
}
