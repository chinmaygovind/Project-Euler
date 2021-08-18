package problems;

//Smallest Multiple
public class Problem5 {
    private static int smallestDivisible = 1;
    public static void main(String[] args){
        for (int i = 20; i > 1; i--){
            int multiplicator = 2;
            while (smallestDivisible%i != 0){
                if (smallestDivisible * multiplicator % i == 0){
                    smallestDivisible *= multiplicator;
                } else {
                    multiplicator++;
                }
            }
        }
        System.out.println("The smallest number divisible by all integers 1-20 is: " + smallestDivisible);
    }
}
