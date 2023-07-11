package problems;


import java.util.ArrayList;

import util.Numbers;

//k^x * x^k = n
//x = 1/log(k) * k * W(1/k * log(k) * n^(1/k))
//Hybrid Integers.
public class Problem800 {
    private static int n = 800800;
    private static final ArrayList<Integer> primes = Numbers.generatePrimes((int) Math.ceil(n * Math.log(n)/Math.log(2)));
    public static void main(String[] args) {
        //System.out.println(C(n));
        System.out.println(C2(n));
    }

    public static int C(double n) {
        int total = 0;
        int baseJ = primes.size() + 1;
        for (int i = 0; i < primes.size(); i++) {
            double p = primes.get(i);
            //bro this is sick
            double maxQ = p / Math.log(p) * Numbers.productLog(Math.log(p) / p * Math.pow(n, n/p));
            for (int j = Math.min(primes.size()-1, baseJ); primes.get(j) >= maxQ; j--) {
                if (primes.get(j) < p)
                    return total;
                if (primes.get(j) <= maxQ) {
                    total += j - i;
                    baseJ = j;
                    break;
                }
            }
        }
        return total;
    }

    public static int C2(double n) {
        double limit = Math.log(n) * n;
        int total = 0;
        int baseJ = primes.size() + 1;
        for (int i = 0; i < primes.size(); i++) {
            double p = primes.get(i);
            for (int j = Math.min(baseJ, primes.size() - 1); j > i; j--) {
                double q = primes.get(j);
                double pq = p * q;
                double k = (p * Math.log(q) + q * Math.log(p))/Math.log(pq);
                if (k * Math.log(pq) <= limit) {
                    total += j - i;
                    baseJ = j;
                    break;
                }
            }
        }
        return total;
    }

}

