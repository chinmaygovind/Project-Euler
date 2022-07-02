package util;

import java.text.DecimalFormat;

/**
 * A fraction class, to represent a fraction with a numerator and denominator.
 */
public class Fraction implements Comparable<Fraction>, Cloneable {
    public double numerator;
    public double denominator;
    public double value;


    public static final Fraction ZERO = new Fraction(0, 1);
    public static final Fraction ONE = new Fraction(1, 1);

    public Fraction(long numerator, long denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }

    public Fraction(double numerator, double denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        this.value = this.numerator / this.denominator;
    }

    public void add(Fraction addend) {
        double oldDenom = denominator;
        denominator *= addend.denominator;
        numerator *= addend.denominator;
        numerator += addend.numerator * oldDenom;
        simplify();
    }

    public void multiply(int factor) {
        numerator *= factor;
        simplify();
    }

    public void multiply(Fraction factor) {
        numerator *= factor.numerator;
        denominator *= factor.denominator;
        simplify();
    }

    public void divide(int factor) {
        denominator *= factor;
        simplify();
    }

    public void divide(Fraction factor) {
        denominator *= factor.numerator;
        numerator *= factor.denominator;
        simplify();
    }

    public Fraction reciprocal(){
        return new Fraction(denominator, numerator);
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
    public int compareTo(Fraction f) {
        return Double.compare(this.value, f.value);
    }

    public String toString() {

        return (DecimalFormat.getInstance().format(numerator) + (denominator == 1 ? "" : "/" + DecimalFormat.getInstance().format(denominator))).replace(",", "");

    }

    public Fraction clone(){
        return new Fraction(numerator, denominator);
    }
}