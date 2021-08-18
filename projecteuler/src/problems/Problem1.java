package problems;

//Multiples of 3 and 5
public class Problem1 {
    private static int sum = 0;

    public static void main(String[] args){
        for (int i = 0; i < 1000; i++){
            if (i%3 == 0 || i%5 == 0){
                sum += i;
            }
        }

        System.out.println("The sum of the multiples of 3 and 5 below 1000 is: " + sum);
    }
}
