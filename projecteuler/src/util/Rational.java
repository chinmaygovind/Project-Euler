package util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

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

    public Rational pow(int exp) {
        if (exp == 0) return Rational.ONE;
        Rational newRational = new Rational((long) Math.pow(this.numerator, Math.abs(exp)), (long) Math.pow(this.denominator, Math.abs(exp)));
        if (exp < 0) newRational = newRational.reciprocal();
        return newRational;
    }

    public Rational reciprocal(){
        return new Rational(denominator, numerator);
    }

    private void simplify() {
        double GCF = Numbers.gcd(Math.min(numerator, denominator), Math.max(numerator, denominator));
        numerator /= (long) GCF;
        denominator /= (long) GCF;
        if (denominator < 0){
            denominator *= -1;
            numerator *= -1;
        }
        this.value = (double) numerator / denominator;
    }

    public boolean isInteger() {
        return denominator == 1;
    }

    @Override
    public int compareTo(Rational f) {
        return Double.compare(this.value, f.value);
    }

    public String toString() {
        return (DecimalFormat.getInstance().format(numerator) + (denominator == 1 ? "" : "/" + DecimalFormat.getInstance().format(denominator))).replace(",", "");

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

    @Override
    public Rational clone(){
        return new Rational(numerator, denominator);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()) {
            return ((Rational) obj).numerator == this.numerator && ((Rational) obj).denominator == this.denominator;
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