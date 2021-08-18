package problems;


import util.Numbers;

//Quadratic Primes.
public class Problem027 {
    private static int greatestConsecutivePrimes = 0;
    private static int[] coefficients;

    public static void main(String[] args){
        for (int a = -999; a < 1000; a++){
            for (int b = -1000; b <= 1000; b++){
                if (getQuadraticPrimes(a, b) > greatestConsecutivePrimes){
                    greatestConsecutivePrimes = getQuadraticPrimes(a, b);
                    coefficients = new int[] {a, b};
                }
            }
        }

        System.out.println("The product of the coefficients a, b within one thousand of the quadratic that produces the most consecutive primes from 0 is: " + coefficients[0] * coefficients[1]);
        System.out.println("The equation is: n^2 + " + coefficients[0] + " * n + " + coefficients[1]);
        System.out.println("The number of values of n this will produce to is: " + greatestConsecutivePrimes);

    }

    private static int getQuadraticPrimes(int a, int b){
        int n = 0;
        int output = b;
        while (Numbers.isPrime(output)){
            n++;
            output = (int) Math.pow(n, 2) + a * n + b;
        }
        return n;
    }
}
