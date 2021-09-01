package util;

/**
 * A utility class with lots of useful geometry methods.
 */
public class Geometry {


    /**
     * Checks if a number is a pentagonal number.
     * @param num Number to check if pentagonal.
     * @return A boolean representing true if the number is pentagonal.
     */
    public static boolean isPentagonal(int num){
        return (Numbers.solveQuadratic(3/2.0, -1/2.0, -num)[0]%1 == 0);
    }

    /**
     * Checks if a number is a hexagonal number.
     * @param num Number to check if hexagonal.
     * @return A boolean representing true if the number is hexagonal.
     */
    public static boolean isHexagonal(int num){
        return (Numbers.solveQuadratic(2, -1, -num)[0]%1 == 0);
    }

    /**
     * Calculates area of a triangle using Heron's Formula.
     * @param a First side length of triangle.
     * @param b Second side length of triangle.
     * @param c Third side length of triangle.
     * @return The area of the triangle as a double.
     */
    public static double triangleArea(double a, double b, double c){
        double s = (a + b + c)/2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
}