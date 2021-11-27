package problems;


import util.Numbers;
import util.Sets;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

//Digit Power Sum.
public class Problem119 {
    private static HashSet<BigInteger> a = new HashSet<>();
    public static void main(String[] args) {
        BigInteger n = BigInteger.ONE;
        while (a.size() < 150) {
            for (int p = 0; p < 50; p++) {
                int sum = 0;
                for (char digit : String.valueOf(n.pow(p)).toCharArray()) {
                    sum += Integer.parseInt(String.valueOf(digit));
                }
                if (n.equals(BigInteger.valueOf(sum)) && n.pow(p).compareTo(BigInteger.TEN) > 0) {
                    a.add(n.pow(p));
                    //System.out.println(n + "^" + p + ": " + n.pow(p));
                }
            }
            n = n.add(BigInteger.ONE);
        }
        ArrayList<BigInteger> aList = new ArrayList<>(a);
        Collections.sort(aList);
        System.out.println("The 30th element of the series of integers that are powers of the sum of its digits is: " + aList.get(29));
    }

}

