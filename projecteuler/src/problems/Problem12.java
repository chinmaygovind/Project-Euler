package problems;

public class Problem12 {

    public static void main(String args[]){
        int candidate = 0;
        while (numDivisors((candidate * (candidate + 1)) / 2) < 500){
            candidate++;
        }
        System.out.println("The smallest triangle number with over 500 divisors is: " + (candidate * (candidate + 1)) / 2);
    }

    private static int numDivisors(int num) {
        int factors = 0;
        for (int factor = 1; factor <= num; factor++){
            if (num%factor == 0) factors++;
        }
        return factors;
    }
}
