package problems;

//Digit Fifth Power.
public class Problem030 {
    private static int[] fifthPowers = new int[10];
    private static int sum = 0;

    public static void main(String[] args){
        //fill out fifth powers
        for (int i = 0; i < 10; i++){
            fifthPowers[i] = (int) Math.pow(i, 5);
        }

        //check all the numbers
        for (int i = 2; i < 1000000; i++){
            int powerSum = 0;
            for (char digit : String.valueOf(i).toCharArray()){
                powerSum += fifthPowers[Integer.parseInt(String.valueOf(digit))];
            }
            if (powerSum == i){
                sum += powerSum;
            }
        }
        System.out.println("The sum of all numbers that can be written as the sums of the fifth power of their digits is: " + sum);
    }
}
