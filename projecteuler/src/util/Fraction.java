package util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A fraction class, to represent a fraction with a numerator and denominator.
 */
public class Fraction implements Comparable<Fraction>, Cloneable {
    public double numerator;
    public double denominator;
    public double value;


    public static final Fraction ZERO = new Fraction(0, 1);
    public static final Fraction ONE = new Fraction(1, 1);
    public static final Fraction ONE_HALF = new Fraction(1, 2);

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

    //i regret making the class use all void methods, silly chinmay
    public Fraction(Fraction f) {
        this.numerator = f.numerator;
        this.denominator = f.denominator;
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

    public ArrayList<Integer> getContinuedFrac() {
        ArrayList<Integer> cf = new ArrayList<>();
        MathContext mc = new MathContext(100);
        BigDecimal zeroIsh = BigDecimal.valueOf(0.1).pow(30);
        BigDecimal newValue = new BigDecimal(numerator).divide(new BigDecimal(denominator), mc);
        cf.add(newValue.intValue());
        newValue = newValue.subtract(newValue.setScale(0, RoundingMode.FLOOR));
//        System.out.println(newValue);
        int i = 0;
        while (newValue.abs().compareTo(zeroIsh) >= 0 && i < 10) {
            newValue = BigDecimal.ONE.divide(newValue, mc).setScale(50, RoundingMode.HALF_DOWN);
//            System.out.println(newValue);
            cf.add(newValue.intValue());
            newValue = newValue.subtract(newValue.setScale(0, RoundingMode.FLOOR));
            i++;
        }
        return cf;

    }

    private void simplify() {
        double GCF = Numbers.gcd(Math.min(numerator, denominator), Math.max(numerator, denominator));
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        return Double.compare(numerator, fraction.numerator) == 0 && Double.compare(denominator, fraction.denominator) == 0 && Double.compare(value, fraction.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator, value);
    }

    public String toString() {

        return (DecimalFormat.getInstance().format(numerator) + (denominator == 1 ? "" : "/" + DecimalFormat.getInstance().format(denominator))).replace(",", "");

    }

    public Fraction clone(){
        return new Fraction(numerator, denominator);
    }
}