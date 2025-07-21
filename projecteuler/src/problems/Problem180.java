package problems;

import util.Rational;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

// Golden Triplets.
public class Problem180 {
    private static int k = 35;
    private static MathContext mc = new MathContext(30);
    private static BigDecimal THRESHOLD = BigDecimal.TEN.pow(-25, mc);
    public static void main(String[] args) {
        ArrayList<Rational> candidates = new ArrayList<>();
        for (int b = 1; b <= k; b++) {
            for (int a = 1; a < b; a++) {
                candidates.add(new Rational(a, b));
            }
        }
        System.out.println(candidates.size());
        for (Rational x : candidates) {
            for (Rational y : candidates) {
                if (y.compareTo(x) < 0) continue;
                System.out.printf("x = %s, y = %s\n", x, y);
                for (Rational z : candidates) {
                    if (z.compareTo(y) < 0) continue;
                    for (int n = -10; n < 10; n++) {
//                        System.out.printf("f_%d(%s, %s, %s) = %s\n", n, x, y, z, f(x, y, z, n));
                        if (f(x, y, z, n).abs().compareTo(THRESHOLD) < 0) {
                            System.out.printf("f_%d(%s, %s, %s) = %s\n", n, x, y, z, f(x, y, z, n));
                        }
                    }
                }
            }
        }
    }
    //f1 = x^n+3 + y^n+3 - z^n+3
    //f2 = (xy+yz+zx)(x^n+1 + y^n+1 - z^n+1)
    //f3 = xyz(x^n+y^n-z^n)
    //f = (x^n+y^n-z^n)(xyz + (xy+yz+zx)(x + y - z))
    public static BigDecimal f(Rational xRat, Rational yRat, Rational zRat, int n) {
        BigDecimal x = BigDecimal.valueOf(xRat.numerator).divide(BigDecimal.valueOf(xRat.denominator), mc);
        BigDecimal y = BigDecimal.valueOf(yRat.numerator).divide(BigDecimal.valueOf(yRat.denominator), mc);
        BigDecimal z = BigDecimal.valueOf(zRat.numerator).divide(BigDecimal.valueOf(zRat.denominator), mc);
        BigDecimal f1 = x.pow(n + 1, mc).add(y.pow(n + 1, mc)).subtract(z.pow(n + 1, mc));
        BigDecimal f2 = (x.multiply(y).add(y.multiply(z)).add(z.multiply(x))).multiply(
                x.pow(n - 1, mc).add(y.pow(n - 1, mc)).subtract(z.pow(n - 1, mc))
        );
        BigDecimal f3 = (x.multiply(y).multiply(z)).multiply(
                x.pow(n - 2, mc).add(y.pow(n - 2, mc)).subtract(z.pow(n - 2, mc))
        );
        return f1.add(f2).add(f3);
    }


}
