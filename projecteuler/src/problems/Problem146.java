package problems;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import util.Numbers;

//Investigating a Prime Pattern.
public class Problem146 {
    private static final int limit = 150000000;
    private static long sum = 0;
    private static ArrayList<Long> candidates = new ArrayList<>();
    private static ArrayList<Long> workingCandidates = new ArrayList<>();
    public static void main(String[] args) {
        Numbers.generateCachedPrimes(limit + 27);
        //so i'm pretty sure n^2 has to be 10 mod 30 {1, 7, 11, 13, 17, 19, 23, 29} only mod 10 is valid
        //so only multiples of ten non-divisible by 30 are good
        /*
        ArrayList<Integer> wheel = new ArrayList<>(List.of(1, 5));
        ArrayList<Integer> modPrimes = new ArrayList<>(List.of(2, 3));
        int mod = 6;
        while (Sets.max(wheel) < limit) {
            int p = wheel.get(1);
            ArrayList<Integer> lastWheel = (ArrayList<Integer>) wheel.clone();
            for (int k = 1; k < p; k++) {
                for (int r : lastWheel) {
                    wheel.add(r + k * mod);
                }
            }
            for (int strike = p; strike < mod * p; strike += p) {
                wheel.remove(Integer.valueOf(strike));
            }
            System.out.println(wheel);
            mod *= p;
            modPrimes.add(p);
        }
        */
        for (long i = 10; i < limit; i+=10) {
            if (i*i%3 == 0) continue;
            BigInteger i2 = BigInteger.valueOf(i).multiply(BigInteger.valueOf(i));
            if (i2.add(BigInteger.ONE).isProbablePrime(3) &&
                    i2.add(BigInteger.valueOf(3)).isProbablePrime(3) &&
                    i2.add(BigInteger.valueOf(7)).isProbablePrime(3) &&
                    i2.add(BigInteger.valueOf(9)).isProbablePrime(3) &&
                    i2.add(BigInteger.valueOf(13)).isProbablePrime(3) &&
                    i2.add(BigInteger.valueOf(27)).isProbablePrime(3)) {
                candidates.add(i);
            }
        }
        for (long i : candidates) {
            BigInteger i2 = BigInteger.valueOf(i).multiply(BigInteger.valueOf(i));
            ArrayList<Integer> addends = new ArrayList<>(List.of(1, 3, 7, 9, 13, 27));
            boolean trulyPrime = true;
            for (int a : addends) {
                if (!trulyPrime) break;
                BigInteger t = i2.add(BigInteger.valueOf(a));
                for (int p : Numbers.getCachedPrimes()) {
                    if (t.mod(BigInteger.valueOf(p)).equals(BigInteger.ZERO)) {
                        trulyPrime = false;
                        break;
                    }
                }
            }
            ArrayList<Integer> baddends = new ArrayList<>(List.of(5, 11, 15, 17, 19, 21, 23, 25));
            boolean trueJedi = true;
            for (int a : baddends) {
                if (!trueJedi) break;
                boolean stinkyPrime = true;
                BigInteger t = i2.add(BigInteger.valueOf(a));
                for (int p : Numbers.getCachedPrimes()) {
                    if (t.mod(BigInteger.valueOf(p)).equals(BigInteger.ZERO)) {
                        stinkyPrime = false;
                        break;
                    }
                }
                if (stinkyPrime) trueJedi = false;
            }
            if (trulyPrime && trueJedi || i == 10) {
                sum += i;
                workingCandidates.add(i);
            }
        }

        System.out.println("The sum of all integers n below 150 million with the pattern is: " + sum);
    }

}


