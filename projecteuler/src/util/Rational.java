package util;

import java.text.DecimalFormat;

/**
 * A Rational class, to represent a fraction with a numerator and denominator.
 * Basically a better version of the fraction Class.
 */
public class Rational implements Comparable<Rational>, Cloneable {
    public long numerator;
    public long denominator;
    public double value;


    public static final Rational ZERO = new Rational(0, 1);
    public static final Rational ONE = new Rational(1, 1);
    public static final Rational ONE_HALF = new Rational(1, 2);

    public Rational(long numerator, long denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }

    public Rational(long integer) {
        this.numerator = integer;
        this.denominator = 1;
        this.value = this.numerator / this.denominator;
        simplify();
    }
    
    public Rational(Rational f) {
        this.numerator = f.numerator;
        this.denominator = f.denominator;
        this.value = this.numerator / this.denominator;
        simplify();
    }

    public Rational add(Rational addend) {
        return new Rational(this.numerator * addend.denominator + addend.numerator * this.denominator, this.denominator * addend.denominator);
    }

    public Rational multiply(int factor) {
        return new Rational(this.numerator * factor, denominator);
    }

    public Rational multiply(Rational factor) {
        return new Rational(this.numerator * factor.numerator, this.denominator * factor.denominator);
    }

    public Rational divide(int factor) {
        return new Rational(this.numerator, this.denominator * factor);
    }

    public Rational divide(Rational factor) {
        return new Rational(this.numerator * factor.denominator, this.denominator * factor.numerator);
    }

    public Rational reciprocal(){
        return new Rational(denominator, numerator);
    }

    private void simplify() {
        double GCF = Numbers.GCF(Math.min(numerator, denominator), Math.max(numerator, denominator));
        numerator /= GCF;
        denominator /= GCF;
        if (denominator < 0){
            denominator *= -1;
            numerator *= -1;
        }
        this.value = numerator / denominator;
    }

    @Override
    public int compareTo(Rational f) {
        return Double.compare(this.value, f.value);
    }

    public String toString() {
        return (DecimalFormat.getInstance().format(numerator) + (denominator == 1 ? "" : "/" + DecimalFormat.getInstance().format(denominator))).replace(",", "");

    }

    @Override
    public Rational clone(){
        return new Rational(numerator, denominator);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()) {
            if (((Rational) obj).numerator == this.numerator && ((Rational) obj).denominator == this.denominator) {
                return  true;
            }
        }
        return false;
    }

    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) numerator;
        result = prime * result + (int) denominator;
        return result;
    }
}