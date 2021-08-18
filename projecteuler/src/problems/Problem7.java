package problems;

//10001st Prime
public class Problem7 {
    private static int primes = 0;
    private static int candidate = 0;
    public static void main(String[] args){
        while (primes < 10001){
            candidate++;
            if (isPrime(candidate)){
                primes++;
            }
        }
        System.out.println("The 10001th prime is: " + candidate);
    }

    public static boolean isPrime(int i){
        if (i == 0 || i == 1){
            return false;
        }
        if (i == 2){
            return true;
        }
        for (int factor = 2; factor <= Math.ceil(Math.sqrt(i)); factor++){
            if (i%factor == 0){
                return false;
            }
        }
        return true;
    }
}
