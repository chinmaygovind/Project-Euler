package problems;


//Ordered Fractions.
public class Problem072 {
    private static final double limit = 3/7.0;
    private static double maxFraction = 0;
    private static int maxFractionNumerator = 0;
    private static int maxFractionDenominator = 0;
    public static void main(String[] args) {
        for (double i = 1; i < 3.1 / 7.0 * 1000000; i++){
            for (int offset = -2; offset < 3; offset++) {
                int denominator = (int) Math.round(7.0 / 3 * i) + offset;
                double fraction = i / denominator;
                if (fraction < limit && fraction > maxFraction && denominator <= 1000000) {
                    maxFraction = fraction;
                    maxFractionNumerator = (int) i;
                    maxFractionDenominator = denominator;
                }
            }
        }

        System.out.println("The numerator of the proper fraction in the ordered list of proper fractions with the denominator below 1,000,000 is: " + maxFractionNumerator);
        System.out.println("The fraction is: " + maxFractionNumerator + "/" + maxFractionDenominator);
        System.out.println("The value of the fraction is: " + maxFraction);
    }

}
