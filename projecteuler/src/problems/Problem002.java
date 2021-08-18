package problems;

//Even Fibonacci Numbers.
public class Problem002 {
    private static long sum = 0;
    private static long[] lastTwo = new long[] {1, 1};
    public static void main(String[] args){
        while (lastTwo[1] < 4_000_000){
            long temp = lastTwo[0];
            lastTwo[0] = lastTwo[1];
            lastTwo[1] = temp + lastTwo[0];

            if (lastTwo[1]%2 == 0){
                sum += lastTwo[1];
            }
        }
        System.out.println("The sum of the even Fibonacci numbers below 4,000,000 is: " + sum);
    }
}
