package problems;

import util.Numbers;

import java.math.BigInteger;
import java.util.HashMap;

//Pandigital Concatenating Products.
public class Problem170 {

    public static HashMap<BigInteger, long[]> cache = new HashMap<>();

    public static void main(String[] args) {
        long maxProduct = 0;
        //just check everything
        for (String p : Numbers.getPermutations("0123456789").reversed()) {
            String pandigitalProd = p;
            if (checkPandigitalProduct(pandigitalProd)) {
                //System.out.println(pandigitalProd + " works");
                maxProduct = Math.max(maxProduct, Long.parseLong(pandigitalProd));
                break;
            }
        }
        System.out.println("The largest to pandigital 10-digit concatenated product of an integer with two or more other integers, \nsuch that the concatenation of the input numbers is also a to pandigital 10-digit number is: " + maxProduct);
    }

    public static boolean checkPandigitalProduct(String prod) {
        //check 2
        for (int breakpoint = 1; breakpoint < prod.length()-1; breakpoint++) {
            long prod1 = Long.parseLong(prod.substring(0, breakpoint));
            long prod2 = Long.parseLong(prod.substring(breakpoint));
            for (int mult = 2; mult < 1000; mult++) {
                if (prod1 % mult != 0 || prod2 % mult != 0) continue;
                long mult1 = prod1 / mult;
                long mult2 = prod2 / mult;
                if (checkPandigital(String.valueOf(mult) + String.valueOf(mult1) + String.valueOf(mult2))) {
                    System.out.printf("%d * %d = %d, %d * %d = %d\n", mult, mult1, prod1, mult, mult2, prod2);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkPandigital(String s) {
        if (s.length() != 10) return false;
        for (int i = 0; i < 10; i++) {
            if (!s.contains(String.valueOf(i))) return false;
        }
        return true;
    }

}
