package problems;

import java.math.BigInteger;
import java.util.ArrayList;

import util.Sets;

//Special Isosceles Triangles.
public class Problem138 {
    //thought it was the same as 137 for a second hehe (not exactly the same but very similar approach)
    //target: find x, 2x + 1 and x, 2x - 1 triples
    //let a = m^2 - n^2, b = 2mn, c = m^2 + n^2
    //x = 2mn, 2x +- 1 = m^2 - n^2 (must be odd)
    //4mn +- 1 = m^2 - n^2
    //m^2 - 4mn - n^2 +- 1 = 0
    //by quadratic formula, n = sqrt(5m^2 +- 1) - 2m=
    public static void main(String[] args) {
        /*
        ArrayList<Double> Ls = new ArrayList<>();
        long m = 1;
        while (Ls.size() < 12) {
            double n1 = Math.sqrt(5 * m * m + 1) - 2 * m;
            double n2 = Math.sqrt(5 * m * m - 1) - 2 * m;
            if (n1 == Math.floor(n1) && n1 > 0) {
                long x = 2 * m * (long) n1;
                Ls.add(Math.sqrt(x*x + (2 * x - 1) * (2 * x - 1)));
            }
            if (n2 == Math.floor(n2) && n2 > 0) {
                long x = 2 * m * (long) n2;
                Ls.add(Math.sqrt(x*x + (2 * x + 1) * (2 * x + 1)));
            }
            m++;
        }
        */
        ArrayList<BigInteger> Ls = new ArrayList<>();
        BigInteger m = BigInteger.TWO;
        while (Ls.size() < 12) {
            BigInteger rad = m.multiply(m).multiply(BigInteger.valueOf(5));
            BigInteger[] n1r = rad.add(BigInteger.ONE).sqrtAndRemainder();
            BigInteger[] n2r = rad.subtract(BigInteger.ONE).sqrtAndRemainder();
            if (!n1r[0].equals(BigInteger.ZERO) && n1r[1].equals(BigInteger.ZERO)) {
                BigInteger x = BigInteger.TWO.multiply(m).multiply(n1r[0].subtract(m.multiply(BigInteger.TWO)));
                BigInteger x2 = x.multiply(BigInteger.TWO).subtract(BigInteger.ONE);
                Ls.add(x.multiply(x).add(x2.multiply(x2)).sqrt());
            }
            if (!n2r[0].equals(BigInteger.ZERO) && n2r[1].equals(BigInteger.ZERO)) {
                BigInteger x = BigInteger.TWO.multiply(m).multiply(n2r[0].subtract(m.multiply(BigInteger.TWO)));
                BigInteger x2 = x.multiply(BigInteger.TWO).add(BigInteger.ONE);
                Ls.add(x.multiply(x).add(x2.multiply(x2)).sqrt());
            }
            m = m.add(BigInteger.ONE);
        }
        System.out.println("The sum of the first 12 values of L of the 12 smallest isosceles triangles for which h = b ± 1 is: " + Sets.sumBigIntegers(Ls));;
    }


}

