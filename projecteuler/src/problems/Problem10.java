package problems;

//Summation of Primes.
public class Problem10 {
    private static long primeSum = 0;

    public static void main(String[] args){
        for (int i = 1; i < 2000000; i++){
            if (isPrime(i)){
                primeSum += i;
            }
        }
        System.out.println("The sum of all primes below 2 million is: " + primeSum);
    }

    public static boolean isPrime(int i){
        if (i == 0 || i == 1){
            return false;
        }
        if (i == 2){
            return true;
        }
        for (int factor = 2; factor < Math.ceil(Math.sqrt(i)) + 1; factor++){
            if (i%factor == 0){
                return false;
            }
        }
        return true;
    }
}
