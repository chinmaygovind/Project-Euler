package problems;

import util.Numbers;
import util.Rational;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

// Twenty-two Foolish Primes.
public class Problem239 {
    public static void main(String[] args) {
        // ts gotta be free
        BigInteger num = BigInteger.ZERO;
        BigInteger denom = Numbers.factorial(100L);

        for (long bitmask = 0; bitmask < (1 << 22); bitmask++) {
            // choose which 3 primes to stay in place
            BigInteger combos = Numbers.bigChoose(25, 3);
            // now, for each of the remaining primes, choose whether to place it in another prime's slot or not.
            int mult = -(100 - 3 - 1); // 97 slots remain, disable 1 cuz own slot.
            for (int i = 0; i < 22; i++) {
                mult *= -1;
                combos = combos.multiply(BigInteger.valueOf(mult));
                // subtract maybe
                if (((1 << i) & bitmask) > 0) {
                    mult--;
                }
            }
            num = num.add(combos);
        }
        System.out.println(new BigDecimal(num.toString()).divide(new BigDecimal(denom.toString()), new MathContext(300)));
    }


}
