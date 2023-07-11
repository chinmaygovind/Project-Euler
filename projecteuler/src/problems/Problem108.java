package problems;

import java.util.ArrayList;

import util.Numbers;

//Diophantine Reciprocals I.
public class Problem108 {
    //1/x + 1/y = 1/n
    //n/x + n/y = 1
    //ny + nx = xy
    //n(x + y) = xy
    //n = x - xn/y
    public static void main(String[] args) {
        //System.out.println("The value of n for which 1/x + 1/y = 1/n has over one thousand integer solutions is: " + n);
        //System.out.println("The number of solutions for n="+n+ " : " + countSolutions(n));
        int product = 1999;
        int minN = Integer.MAX_VALUE;
        while (product < 2500) {
            ArrayList<Integer> factors = Numbers.getPrimeFactors(product);
            ArrayList<Integer> primes = Numbers.generatePrimes(factors.size()*factors.size());
            int newN = 1;
            boolean possible = true;
            for (int i = 0; i < factors.size(); i++){
                factors.set(i, (factors.get(i) - 1)/2);
                if (factors.get(i) > 40) {
                    possible = false;
                    break;
                }
                newN *= Math.pow(primes.get(factors.size() - i - 1), factors.get(i));
            }
            if (possible) {
                if (minN > newN){
                    minN = newN;
                }
            }
            product+=2;
        }
        System.out.println("The smallest number with over 1000 positive integer solutions to the equation 1/x + 1/y = 1/n is: " + minN);
        System.out.println("Its number of solutions is: " + countSolutions(minN));
    }

    public static int countSolutions(long n) {
        int count = 0;
        for (long x = n + 1; x <= 2 * n; x++) {
            if ((n * x)%(x - n) == 0) {count++;}
        }
        return count;
    }
    //smart method to get number of solutions for number n
    //take the number of each prime factor it has (e.g. 96 will be 5 2's and 1 3)
    //multiply each number by 2 and add 1 (5, 1 -> 11, 3)
    //multiply all these numbers, divide by 2, and add 1 (11 * 3 / 2 + 1 = 17)

}
