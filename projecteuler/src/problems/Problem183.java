package problems;

import util.Numbers;
import util.Rational;

// Maximum Product of Parts.
public class Problem183 {

    private static int Nlim = 10000;
    public static void main(String[] args) {
        long sum = 0;
        for (int N = 5; N <= Nlim; N++) {
//            System.out.println(N + ": " + D(N));
            sum += D(N);
        }
        System.out.printf("The sum of D(N) from N = 5 through %d is: %d", Nlim, sum);
    }

    public static int D(int N) {
        int parts = parts(N);
        double val = (double) N / parts;
        int denom = parts / Numbers.gcd(N, parts);
        while (denom % 2 == 0) denom /= 2;
        while (denom % 5 == 0) denom /= 5;
//        System.out.println(N + ": " + parts + " - " + (denom == 1 ? "terminates" : "nonterminating") + ": " + val);
        return denom == 1 ? -N : N; // if terminates, -N, otherwise +N
    }
    public static int parts(int N) {
        double low = Math.floor(N / Math.E);
        double high = low + 1;
        double lowVal = low * (Math.log(N) - Math.log(low));
        double highVal = high * (Math.log(N) - Math.log(high));
        return lowVal > highVal ? (int) low : (int) high;
    }


}
