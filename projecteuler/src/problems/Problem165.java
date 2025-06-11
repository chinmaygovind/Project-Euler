package problems;

import util.Fraction;
import util.Rational;

import java.util.*;

//Intersections.
public class Problem165 {
    public static void main(String[] args) {
        long[] s = new long[20001];
        int[] t = new int[20001];
        s[0] = 290797;
        t[0] = (int) (s[0] % 500);
        for (int i = 1; i < s.length; i++) {
            s[i] = (s[i - 1] * s[i - 1]) % 50515093L;
            t[i] = (int) (s[i] % 500);
        }

        Set<Intersection165> intersections = new HashSet<>();
        int sols = 0;
        for (int line1 = 0; line1 < 5000; line1++) {
            int x1 = t[4*line1+1];
            int y1 = t[4*line1+2];
            int x2 = t[4*line1+3];
            int y2 = t[4*line1+4];
            // System.out.println(line1 + ": " + x1 + ", " + y1 + " to " + x2 + ", " + y2);
            double m1 = ((double) (x2 - x1)) / (y2 - y1);
            // (y - y1) = m1(x2 - x)
            // y = m1(x2 - x) + y1
            for (int line2 = 0; line2 < 5000; line2++) {
                int x3 = t[4*line2+1];
                int y3 = t[4*line2+2];
                int x4 = t[4*line2+3];
                int y4 = t[4*line2+4];
                /*

                double m2 = ((double) (x4 - x3)) / (y4 - y3);
                // y = m2(x3 - x) + y3
                // m1(x2 - x) + y1 = m2(x3 - x) + y3
                // m1x2 - m1x + y1 = m2x3 - m2x + y3
                // m2x - m1x = m2x3 + y3 - m1x2 - y1
                // x = (m2x3 + y3 - m1x2 - y1) / (m2 - m1)
                double x = (m2*x3 + y3 - m1*x2 - y1) / (m2 - m1);
                double y = m1 * (x2 - x) + y1;
                double altY = m2 * (x3 - x) + y3;
                if (Math.abs(y - altY) > Intersection165.TOLERANCE) {
                    System.out.println("whadafuq");
                }
                if (x > Math.max(Math.min(x1, x2), Math.min(x3, x4)) &&
                        x < Math.min(Math.max(x1, x2), Math.max(x3, x4)) &&
                        y > Math.max(Math.min(y1, y2), Math.min(y3, y4)) &&
                        y < Math.min(Math.max(y1, y2), Math.max(y3, y4))) {
                    System.out.printf("(%d, %d) to (%d, %d) and (%d, %d) to (%d, %d) at (%.4f, %.4f)\n",
                            x1, y1, x2, y2, x3, y3, x4, y4, x, y);
                    intersections.add(new Intersection165(x, y));
                }
                 */
                // https://en.wikipedia.org/wiki/Line%E2%80%93line_intersection#Given_two_points_on_each_line_segment
                int tNum = (x1 - x3)*(y3 - y4) - (y1 - y3)*(x3 - x4);
                int uNum = -((x1 - x2)*(y1 - y3) - (y1 - y2)*(x1 - x3));
                int det = (x1 - x2)*(y3 - y4) - (y1 - y2)*(x3 - x4);
                if (0 < tNum && tNum < det && 0 < uNum && uNum < det) {
                    Rational tVal = new Rational(tNum, det);
                    Rational uVal = new Rational(uNum, det);
                    Rational x = tVal.multiply(x2 - x1).add(new Rational(x1));
                    Rational y = tVal.multiply(y2 - y1).add(new Rational(y1));
                    intersections.add(new Intersection165(x, y));
                    sols++;
                }
            }
        }
        System.out.println("The number of distinct true intersection poitns among the 500 line segments is: " + intersections.size());

    }
}

class LineSegment {

    double x1;
    double y1;
    double x2;
    double y2;

    double vX;
    double vY;
    double length;
    public LineSegment(double x1, double y1, double x2, double y2) {
        if (x1 < x2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        } else {
            this.x1 = x2;
            this.x2 = x1;
            this.y1 = y2;
            this.y2 = y1;
        }//left point is x1
        this.length = Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
        this.vX = (x2-x1);
        this.vY = (y2-y1);
    }

    public Intersection165 intersects(LineSegment o) {
        //(x, y) = (x1, y1) + L(vX, vY)
        //(x, y) = (o.x1, o.y1) + M(o.vX, o.vY)
        //this.x1 + L this.vX = o.x1 + M o.vX
        //this.y1 + L this.vY = o.y1 + M O.vY
        //L = (o.x1 + M o.vX - this.x1) / this.vX
        //this.y1 + ((o.x1 + M o.vX - this.x1) / this.vX) * this.vY = o.y1 + M o.vY
        //M =
        return null;
    }
}

class Intersection165 {
    Rational x;
    Rational y;
    public final static double TOLERANCE = 0.000001;

    public Intersection165(Rational x, Rational y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Intersection165 that = (Intersection165) o;
        return this.x.equals(that.x) && this.y.equals(that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}


