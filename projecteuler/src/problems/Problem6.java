package problems;

//Sum Square Difference
public class Problem6 {
    private static int sumOfSquares = 0;
    private static int squareOfSum = (int) Math.pow(5050, 2);
    public static void main(String[] args){
        for (int i = 0; i <= 100; i++){
            sumOfSquares += Math.pow(i, 2);
        }
        System.out.println("The difference between the sum of the first hundred squares and the square of the sum is: " + (squareOfSum - sumOfSquares));
    }
}
