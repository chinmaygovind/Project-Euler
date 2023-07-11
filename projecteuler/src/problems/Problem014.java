package problems;

public class Problem014 {
    private static int longestChainStarter = 0;
    private static int longestChainLength = 0;
    public static void main(String args[]){
        for (int i = 1; i < 1000000; i++){
            int chainLength = collatzLength(i);
            if (chainLength > longestChainLength){
                longestChainStarter = i;
                longestChainLength = chainLength;
            }
        }
        System.out.println("The longest Collatz chain under one million starts at: " + longestChainStarter);
        System.out.println("The chain length is: " + longestChainLength);
    }

    private static int collatzLength(int num){

        long temp = num;
        int chainLength = 1;
        while (temp != 1){
            temp = collatz(temp);
            chainLength++;
        }
        return chainLength;
    }
    private static long collatz(long num){
        if (num%2 == 0){
            return num/2;
        }
        return 3 * num + 1;
    }
}
