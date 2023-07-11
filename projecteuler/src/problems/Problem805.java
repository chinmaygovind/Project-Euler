package problems;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

import util.Files;
import util.Numbers;


//Shifted Multiples.
public class Problem805 {

    private static final long mod = 1000000007L;
    private static final BigInteger bigMod = new BigInteger("1000000007");
    private static final HashMap<Long, Integer> cachedPeriods = new HashMap<>();
    //N(r) is smallest positive integer n for which s(n) = r * n
    public static void main(String[] args) {
        ArrayList<Integer> primes = (ArrayList<Integer>) Arrays.stream(Files.fileScanner("/primes80mil.txt").nextLine().split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        Numbers.setCachedPrimes(primes);
        //Numbers.generateCachedPrimes(1000000);
        //System.out.println("primes");
        //System.out.println(N(64, 343));
        //System.out.println(temp.mod(bigMod));
        System.out.println("The value of T(200) mod 1000000007 is: " + T(200));
    }


    public static long T(int M) {
        long sum = 0;
        for (int u = 1; u <= M; u++) {
            for (int v = 1; v <= M; v++) {
                if (Numbers.GCF(u, v) != 1) continue;
                sum = (sum + N(u*u*u, v*v*v))%mod;
                //long optimizedN = N(u*u*u, v*v*v);
                //BigInteger rawN = trueN(u*u*u, v*v*v);
                //if (rawN.mod(bigMod).longValueExact() != optimizedN) System.out.println(u*u*u + "/" + v*v*v + ": " + optimizedN + " | " + rawN.mod(bigMod) + " |\n " + rawN + " \n" + temp);
                //sum++;
            }
        }
        return sum;
    }

    //https://en.wikipedia.org/wiki/Transposable_integer
    //I STRUCK GOLD -- update: after hours of trying still have not struck gold
    public static long N(int n, int s) {
        /*
        double r = p / (double) q;
        //largest ten = 10^floor(log10(n))
        //s(n) = n%largest10 * 10 + floor
        //d = first digit
        for (int d = 1; d <= 9; d++) {
            //r * (d * 10^floor(log10(x)) + x) = 10x + d
            //(rd 10^floor(log10(x)) + d)/(10 - r) = x

            //find magnitude of x
            long log10x = 0;
            double eval = (r * d * Math.pow(10, log10x) + d)/(10 - r);
            while (Math.log10(eval) < log10x) {
                if ((long) eval > 0 && (long) eval == inverseS((long) eval) * r && inverseS((long) eval) > 0) return inverseS((long) eval);
                if ((long) eval > 0 && (long) (eval * r) == s((long) eval)) return (long) eval;
                log10x++;
                eval = (r * d * Math.pow(10, log10x) + d)/(10 - r);
            }

        }
        */
        //integral multiplier
        if (s == 1) {
            return (n == 1 ? 1 : 0);
            //below is all valid but for our purposes we need only 1^3/1^3\
            //long F0 = 10 - n;
            //while (Numbers.GCF(10, F0) != 1 && F0 > 0) {
            //    if (F0%2 == 0) F0 /= 2;
            //    if (F0%5 == 0) F0 /= 5;
            //}
            //if (F0 < n) return 0;
            //System.out.println("lol its only " + n);
            //okay we have F
            //long invF = Numbers.xgcd(F0, mod)[1]%mod;
            //return BigInteger.TEN.modPow(BigInteger.valueOf(period(F0)), bigMod).subtract(BigInteger.ONE).multiply(BigInteger.valueOf(invF)).mod(bigMod).longValueExact();



            //return BigInteger.TEN.pow(period(F0)).subtract(BigInteger.ONE).divide(BigInteger.valueOf(F0)).mod(bigMod);

        } else {
            int F0 = s * 10 - n;
            while (Numbers.GCF(10, F0) != 1 && F0 > 0) {
                if (F0%2 == 0) F0 /= 2;
                if (F0%5 == 0) F0 /= 5;
            }
            if (F0 <= 0 || s > F0 || n > F0) return 0;
            BigInteger F = BigInteger.valueOf(F0);
            ArrayList<Integer> factors = Numbers.getFactors(F0, true);
            double fScore = period(F0) - Math.log10(F0);
            for (int f : factors) {
                if (f > n && f > s) {
                    double cFScore = period(f) - Math.log10(f);
                    if (cFScore < fScore) {
                        F = BigInteger.valueOf(f);
                        fScore = cFScore;
                    }
                    //System.out.println(n + "/" + s + " | " + f + ": " + cFScore + " | X: (" + X.toString().length() + ") " + X);
                    //System.out.println(factors);
                }
            }
            BigInteger invF = Numbers.xgcd(F, bigMod)[1].add(bigMod).mod(bigMod);
            BigInteger X = BigInteger.TEN.modPow(BigInteger.valueOf(period(F.longValueExact())), bigMod)
                    .subtract(BigInteger.ONE)
                    .multiply(invF).mod(bigMod);
            //cachedResidues.put(F0, X.longValueExact());
            //if (n == 64 && s == 343)
            //    System.out.println("hi");
            //temp = BigInteger.TEN.pow(period(F0)).subtract(BigInteger.ONE).divide(BigInteger.valueOf(F0)).multiply(BigInteger.valueOf(s));

            return X.multiply(BigInteger.valueOf(s)).mod(bigMod).longValueExact();

            //if (cachedResidues.containsKey(F0)) return (cachedResidues.get(F0) * s)%mod;
            //BigInteger F = BigInteger.valueOf(F0);
            //BigInteger theConstruction = BigInteger.ONE;
            //for (int mag = 0; mag < period(F0); mag++) {
            //    while (theConstruction.divide(Bi))
            //}
            //BigDecimal d = BigDecimal.ONE.divide(BigDecimal.valueOf(F0), new MathContext(period(F0)));
            //BigInteger X = BigInteger.TEN.pow(period(F0)).sub;
            }
    }

    public static BigInteger trueN(long n, long s) {
        /*
        double r = p / (double) q;
        //largest ten = 10^floor(log10(n))
        //s(n) = n%largest10 * 10 + floor
        //d = first digit
        for (int d = 1; d <= 9; d++) {
            //r * (d * 10^floor(log10(x)) + x) = 10x + d
            //(rd 10^floor(log10(x)) + d)/(10 - r) = x

            //find magnitude of x
            long log10x = 0;
            double eval = (r * d * Math.pow(10, log10x) + d)/(10 - r);
            while (Math.log10(eval) < log10x) {
                if ((long) eval > 0 && (long) eval == inverseS((long) eval) * r && inverseS((long) eval) > 0) return inverseS((long) eval);
                if ((long) eval > 0 && (long) (eval * r) == s((long) eval)) return (long) eval;
                log10x++;
                eval = (r * d * Math.pow(10, log10x) + d)/(10 - r);
            }

        }
        */
        //integral multiplier
        if (s == 1) {
            long F0 = 10 - n;
            while (Numbers.GCF(10, F0) != 1 && F0 > 0) {
                if (F0%2 == 0) F0 /= 2;
                if (F0%5 == 0) F0 /= 5;
            }
            if (F0 < n) return BigInteger.ZERO;
            //okay we have F
            //return BigInteger.TEN.modPow(BigInteger.valueOf(period(F0)), bigMod).subtract(BigInteger.ONE).multiply(BigInteger.valueOf(invF));
            return BigInteger.TEN.pow(period(F0)).subtract(BigInteger.ONE).divide(BigInteger.valueOf(F0)).mod(bigMod);
        } else {
            long F0 = s * 10 - n;
            while (Numbers.GCF(10, F0) != 1 && F0 > 0) {
                if (F0%2 == 0) F0 /= 2;
                if (F0%5 == 0) F0 /= 5;
            }

            if (F0 <= 0 || s > F0 || n > F0) return BigInteger.ZERO;
            BigInteger F = BigInteger.valueOf(F0);
            ArrayList<Integer> factors = Numbers.getFactors((int) F0, true);
            double fScore = period(F0) - Math.log10(F0);
            for (int f : factors) {
                if (f > n && f > s) {
                    double cFScore = period(f) - Math.log10(f);
                    if (cFScore < fScore) {
                        F = BigInteger.valueOf(f);
                        fScore = cFScore;
                    }
                    //System.out.println(n + "/" + s + " | " + f + ": " + cFScore + " | X: (" + X.toString().length() + ") " + X);
                    //System.out.println(factors);
                }
            }
            BigInteger X = BigInteger.TEN.pow(period(F.longValueExact())).subtract(BigInteger.ONE).divide(F).multiply(BigInteger.valueOf(s));
            if (!X.multiply(BigInteger.valueOf(n)).divide(BigInteger.valueOf(s)).equals(s(X)))
                System.out.println(n + "/" + s + " | f: " + F + " X: " + X);
            return X;
        }
    }

    public static int period(long F) {
        if (cachedPeriods.containsKey(F)) return cachedPeriods.get(F);
        long x = 1;
        for (int k = 1; k < F; k++) {
            x = (10 * x)%F;
            if (x == 1) {
                cachedPeriods.put(F, k);
                return k;
            }
        }
        return 0;
    }

    public static long s(long n) {
        long largest10 = (long) Math.pow(10, (long) Math.log10(n));
        return (n%largest10)*10 + n / largest10;
    }

    public static BigInteger s(BigInteger n) {
        return new BigInteger(n.toString().substring(1) + n.toString().substring(0, 1));
    }

    public static long inverseS(long n) {
        long largest10 = (long) Math.pow(10, (long) Math.log10(n));
        return (n%10)*largest10 + n / 10;
    }
}

