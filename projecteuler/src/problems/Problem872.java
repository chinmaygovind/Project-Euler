package problems;


// ovo sign

import java.math.BigInteger;

// Recursive Tree.
public class Problem872 {
    private static BigInteger curr = BigInteger.valueOf(9).pow(17);
    private static final BigInteger root = BigInteger.TEN.pow(17);
    public static void main(String[] args) {
        // thonk
        BigInteger total = curr;
        while (!curr.equals(root.subtract(BigInteger.ONE))) {
            BigInteger shift = BigInteger.ONE;
            while (curr.add(shift).compareTo(root) < 0) {
                // System.out.printf("%d + %d\n", curr, shift);
                shift = shift.multiply(BigInteger.TWO);
            }
            shift = shift.divide(BigInteger.TWO);
            // System.out.println(curr);
            curr = curr.add(shift);
            total = total.add(curr);
        }
        total = total.add(root);
        System.out.printf("f(10^17, 9^17) is: %s", total);
        // vibe solution
        // could prove by induction


    }
}

