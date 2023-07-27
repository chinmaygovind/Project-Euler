package problems;

import java.util.ArrayList;

import util.Numbers;

//Investigating Gaussian Integers.
public class Problem153 {
    
    private static final int LIMIT = 5;
    private static final double SQRT_LIMIT = Math.sqrt(LIMIT);
    private static ArrayList<Integer> primes = Numbers.generatePrimes(2 * LIMIT);//generate excess so gaussian primes can be collected
    private static long total = 0;
    public static void main(String[] args) {

        //find all gaussian primes up to SQRT_LIMIT
        long total = 0;
        ArrayList<GaussianInteger> gaussianPrimes = new ArrayList<>();
        //a + bi is a gaussian prime iff:
        //1. a or b is zero and absolute value of the other is a prime of form 4n + 3
        for (int p : primes) {
            if (p%4 == 3) {
                gaussianPrimes.add(new GaussianInteger(p));
            }
        }
        for (int a = 1; a <= LIMIT/2; a++) {
            for (int b = 1; b <= LIMIT/2; b++) {
                //2. both a and b are non-zero and a^2 + b^2 is prime
                if (primes.contains(a*a + b*b)) {
                    gaussianPrimes.add(new GaussianInteger(a, b));
                    gaussianPrimes.add(new GaussianInteger(a, -b)); //ignore the conjugate
                }
            }
        }
        System.out.println(gaussianPrimes);
        //add up all the primes' factors, including multiples of the primes
        ArrayList<GaussianInteger> allTheShits = new ArrayList<>();
        for (int p1 = 0; p1 < gaussianPrimes.size(); p1++) {
            for (int p2 = 0; p2 <gaussianPrimes.size(); p2++) {

            }
        }
        System.out.println("fast method: " + total);
        System.out.println("slow method: " + slowSumDivisors(LIMIT));
        
        

    }


    public static long slowSumDivisors(int limit) {
        //stupid naive method
        //count all GaussianInteger     long total = 0;
        //stupid naive logic is correct
        long total = 0;
        for (long a = 1; a <= limit/2; a++) {
            if (a%1000 == 0) System.out.printf("a: %d\n", a);
            for (long  b = 1; b <= limit/2; b++) {
                long norm = a * a + b * b;//we know that if a + bi is a factor, a - bi is also a factor.
                long adjustedNorm = norm / Numbers.GCF(a, b);
                //System.out.printf("now considering %d + %di, which goes into multiples of %d\n", a, b, adjustedNorm);
                //so, (a + bi)(a - bi) = a^2 + b^2 is a factor. 
                //so, all multiples of a * a + b * b have a + bi and a - bi as a factor.
                //so, we can add (a + bi) + (a - bi) = 2a to the sum
                total += limit / adjustedNorm * (2 * a);
            }
        }
        System.out.println("slow method complex only: " + total);
        //count all rational integer divisors
        for (long f = 1; f <= limit; f++) {
            total += (limit / f) * f;//this is a cursed line of code but i promise it works
        }
        return total;
    }
 }


 class GaussianInteger {
    private int re;//re
    private int im;//im

    public GaussianInteger(int a, int b) {
        this.re = a;
        this.im = b;
    }

    public GaussianInteger(int a) {
        this.re = a;
        this.im = 0;
    }

    public GaussianInteger(GaussianInteger g) {
        this.re = g.getReal();
        this.im = g.getImaginary();
    }

    public int getReal() {
        return this.re;
    }

    public int getImaginary() {
        return this.im;
    }

    public GaussianInteger add(GaussianInteger a) {
        return new GaussianInteger(this.getReal() + a.getReal(), this.getImaginary() + a.getImaginary());
    }

    public GaussianInteger subtract(GaussianInteger s) {
        return new GaussianInteger(this.getReal() - s.getReal(), this.getImaginary() - s.getImaginary());
    }

    public GaussianInteger multiply(GaussianInteger m) {
        return new GaussianInteger(this.getReal() * m.getReal() - this.getImaginary() * m.getImaginary(), 
        this.getReal() * m.getImaginary() + this.getImaginary() * m.getReal());
    }

    public GaussianInteger conjugate() {
        return new GaussianInteger(this.getReal(), -this.getImaginary());
    }

    public Integer norm() {
        return this.getReal()*this.getReal() + this.getImaginary()*this.getImaginary();
    }
    
    public GaussianInteger divide(GaussianInteger d){
        GaussianInteger numerator = this.multiply(d.conjugate());
        return new GaussianInteger(numerator.getReal()/d.norm(), numerator.getImaginary()/d.norm());
    }

    public String toString() {
        if (this.getReal() == 0) {
            return String.format("%di", this.getImaginary());
        } else if (this.getImaginary() == 0) {
            return String.format("%d", this.getReal());
        } else if (this.getImaginary() < 0) {
            if (this.getImaginary() == -1) {
                return String.format("%d - i", this.getReal(), -this.getImaginary()); 
            } else {
                return String.format("%d - %di", this.getReal(), -this.getImaginary());
            }
        } else {
            if (this.getImaginary() == 1) {
                return String.format("%d + i", this.getReal(), this.getImaginary()); 
            } else {
                return String.format("%d + %di", this.getReal(), this.getImaginary());
            }
        }
    }
 }



