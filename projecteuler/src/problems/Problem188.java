package problems;

import java.math.BigInteger;

// Hyperexponentiation.
public class Problem188 {
    private static int a = 1777;
    private static int b = 1855;
    private static BigInteger mod = BigInteger.valueOf(1_00_000_000);
    public static void main(String[] args) {
        BigInteger n = BigInteger.valueOf(a);
        for (int i = 0; i < b - 1; i++) {
            BigInteger c = BigInteger.valueOf(a).modPow(n, mod);
            n = c;
        }
        System.out.printf("The last 8 digits of %d ^^ %d is: %d\n", a, b, n);
    }


}
