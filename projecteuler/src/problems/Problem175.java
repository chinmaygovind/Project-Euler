package problems;

import util.Fraction;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Fractions and Sum of Power of Two.
public class Problem175 {

    // fucking shit just do the tinest bit of research you nut
    public static void main(String[] args) {
        // https://en.wikipedia.org/wiki/Calkin%E2%80%93Wilf_tree#Breadth_first_traversal
        Fraction target = new Fraction(123456789, 987654321);
        System.out.printf("The shortened binary expansion of the smallest n for which f(n)/f(n - 1) is %s is: %s\n",
                target,
                target.getContinuedFrac().subList(1, target.getContinuedFrac().size()).reversed());
        // hall of fame fuckass
//        System.out.println(bitString);
    }


}
