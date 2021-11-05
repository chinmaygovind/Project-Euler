package util;

import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
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

    /**
     * Determins if a set of three points is oriented clockwise, counterclockwise, or collinear.
     * https://www.geeksforgeeks.org/orientation-3-ordered-points/
     * @param a The first point of the set.
     * @param b The second point of the set.
     * @param c The third point of the set.
     * @return The orientation of the three points: 0 for collinear, 1 for clockwise, and -1 for counterclockwise.
     */
    public static int orientation(Point a, Point b, Point c){
       double val = (b.y - a.y) * (c.x - b.x) - (c.y - b.y) * (b.x - a.x);
       if (val == 0) return 0;
       return (val > 0) ? 1 : -1;
    }

    /**
     * Determins the convex hull of a set of points.
     * https://www.geeksforgeeks.org/convex-hull-set-1-jarviss-algorithm-or-wrapping/
     * @param points List of points to get convex hull of.
     * @return A list of points of the convex hull of the set of points.
     */
    public static List<Point> convexHull(List<Point> points){
        List<Point> convexHull = new ArrayList<>();
        //Find left-most point.
        convexHull.add(points.get(0));
        for (Point p : points){
            if (p.x < convexHull.get(0).x) {
                convexHull.remove(0);
                convexHull.add(p);
            }
        }

        Point next = new Point();
        while (!next.equals(convexHull.get(0))){
            Point i = null;
            for (Point p : points){
                if (convexHull.get(convexHull.size()-1).equals(p)) continue;
                if (i == null) {
                    i = p;
                    continue;
                }
                if (orientation(convexHull.get(convexHull.size()-1), i, p) == -1) i = p;
            }
            next = i;
            convexHull.add(i);
        }
        convexHull.remove(convexHull.size()-1);
        return convexHull;
    }
}