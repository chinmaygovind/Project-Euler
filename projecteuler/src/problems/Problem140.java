package problems;

import util.Numbers;

import java.awt.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

//Modified Fibonacci Golden Nuggets.
public class Problem140 {
    //same cheese strat as 137 except this time the radical part is
    //5n^2 + 14n + 1
    //never mind cheese won't work we gotta get the big boys out

    //make quadratic of form 25g^2 + 70g + 5 = 5k^2, solve for g
    //25g^2 + 70g + 49 - (5 - 5k^2) = 49
    //(5g + 7)^2 = 44 + 5k^2 //HAHA I GOT IT ON THE CAR RIDE TO WALMART I FIGURED IT OUT
    //let x = 5g + 7, y = k
    //x^2 - 5y^2 = 44 (flavor of Pell's equation)

    //from https://en.wikipedia.org/wiki/Pell's_equation#Generalized_Pell's_equation

    private static int neededSols = 100;
    private static BigInteger sum = BigInteger.ZERO;
    public static void main(String[] args) {
        //find fundamental solutions
        ArrayList<Solution> fundamentals = new ArrayList<>();
        for (int y = 1; fundamentals.size() < 6; y++) {
            double x2 = 44 + 5 * y * y;
            if (Math.sqrt(x2) == Math.floor(Math.sqrt(x2))) {
                fundamentals.add(new Solution(BigInteger.valueOf((long) Math.sqrt(x2)), BigInteger.valueOf(y)));
            }
        }
        //get resolvent
        BigInteger D = BigInteger.valueOf(5);
        Point resolvent = Numbers.solvePellEquation(D.intValue());
        BigInteger t = BigInteger.valueOf(resolvent.x);
        BigInteger u = BigInteger.valueOf(resolvent.y);

        ArrayList<Solution> sols = new ArrayList<>();
        int n = 0;
        while (sols.size() < neededSols) {
            //generate solutions by (r + srootD) * (t + urootD)^n
            for (Solution rs : fundamentals) {
                BigInteger r = rs.x;
                BigInteger s = rs.y;
                //get expansion of (t + u sqrtD)^n
                BigInteger whole = (n == 0 ? BigInteger.ONE : BigInteger.ZERO);
                BigInteger rad = BigInteger.ZERO;
                if (n != 0) {
                    for (int i = 0; i <= n; i++) {
                        if (i % 2 == 0)
                            whole = whole.add(BigInteger.valueOf(Numbers.choose(n, i)).multiply(t.pow(n - i)).multiply(u.pow(i)).multiply(D.pow((i/2))));
                        if (i % 2 == 1)
                            rad = rad.add(BigInteger.valueOf(Numbers.choose(n, i)).multiply(t.pow(n - i)).multiply(u.pow(i)).multiply(D.pow((i - 1)/2)));
                    }
                }
                //System.out.println(n + ": " + whole + ", " + rad);
                Solution finalSol = new Solution(r.multiply(whole).add(rad.multiply(s).multiply(D)), s.multiply(whole).add(r.multiply(rad)));
                if (finalSol.x.compareTo(BigInteger.ZERO) > 0 && finalSol.y.compareTo(BigInteger.ZERO) > 0) sols.add(finalSol);
            }
            n++;
        }
        //extract nuggs
        ArrayList<BigInteger> nuggs = new ArrayList<>();
        for (Solution s : sols) {
            BigInteger g = s.x.subtract(BigInteger.valueOf(7));
            if (g.divideAndRemainder(BigInteger.valueOf(5))[1].compareTo(BigInteger.ZERO) == 0 && g.divide(BigInteger.valueOf(5)).compareTo(BigInteger.ZERO) > 0)
                nuggs.add(g.divide(BigInteger.valueOf(5)));
        }
        //sum nuggs
        for (int i = 0; i < 30; i++) {
            sum = sum.add(nuggs.get(i));
        }
        System.out.println("The sum of the first thirty modified golden nuggets is: " + sum);


    }


}

class Solution {
    BigInteger x;
    BigInteger y;
    Solution(BigInteger x, BigInteger y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        //return "Solution{" +
        //        "x=" + x +
        //        ", y=" + y +
        //        '}';
        return "(" + x + ", " + y + ")";
    }
}