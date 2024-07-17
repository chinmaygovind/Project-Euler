package problems;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

//Intersections.
public class Problem165 {

    private static HashSet<Intersection165> foundPoints = new HashSet<>();
    private static ArrayList<Intersection165> allFoundPoints = new ArrayList<>();

    public static void main(String[] args) {
        long[] s = new long[20001];
        int[] t = new int[20001];
        s[0] = 290797; t[0] = (int) (s[0]%500);
        for (int i = 1; i < s.length; i++) {
            s[i] = (s[i - 1] * s[i - 1])%50515093L;
            t[i] = (int) (s[i]%500);
        }
        int intersections = 0;
        //System.out.println(intersects(27, 44, 12, 32, 46, 53, 17, 62));
        //System.out.println(intersects(27, 44, 12, 32, 46, 70, 22, 40));
        //System.out.println(intersects(46, 53, 17, 62, 46, 70, 22, 40));
        System.out.println(Arrays.toString(t));
        for (int l1 = 0; l1 < t.length/4; l1++) {
            int x1 = t[4*l1+1];
            int y1 = t[4*l1+2];
            int x2 = t[4*l1+3];
            int y2 = t[4*l1+4];
            //(y2 - y1)/(x2 - x1)
            //y = m(x - x1) + y1
            for (int l2 = l1+1; l2 < t.length/4; l2++) {
                int x3 = t[4*l2+1];
                int y3 = t[4*l2+2];
                int x4 = t[4*l2+3];
                int y4 = t[4*l2+4];
                Intersection165 intersection = intersects(x1, y1, x2, y2, x3, y3, x4, y4);
                if (!Objects.isNull(intersection)) foundPoints.add(intersection);

            }
        }
        System.out.println(foundPoints.size());
        System.out.println(allFoundPoints.size());
        System.out.println(foundPoints.toArray()[0]);
    }

    private static Intersection165 intersects(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        //System.out.printf("Checking intersection between L1: (%d, %d) to (%d, %d) and L2: (%d, %d) to (%d, %d).\n",
        //        (int) x1, (int) y1, (int) x2, (int) y2, (int) x3, (int) y3, (int) x4, (int) y4);
        double m1 = (y2 - y1) / (x2 - x1);
        double m2 = (y4 - y3) / (x4 - x3);
        //System.out.println(m1 + ", " + m2);
        if (m1 == m2) {
            return null;
        }
        //m1(x - x1) + y1 = m2(x - x3) + y3
        //x(m1 - m2) = x1 * m1 -x3 * m2 + y3 - y1
        //System.out.printf("Line A: y = %.2f(x - %.2f) + %.2f\n", m1, x1, y1);
        //System.out.printf("Line B: y = %.2f(x - %.2f) + %.2f\n", m2, x3, y3);
        //System.out.printf("(%.2f - %.2f)x = %.2f * %.2f - %.2f * %.2f + %.2f - %.2f\n", m1, m2, x1, m1, x3, m2, y3, y1);
        double x = (x1 * m1 - x3 * m2 + y3 - y1) / (m1 - m2);
        double y = (m1 * (x - x1)) + y1;
        double yCheck = (m2 * (x - x3)) + y3;
        if (Math.abs(y - yCheck) > 0.000001) {
            System.out.println(new Intersection165(x, y) + " vs. " + new Intersection165(x, yCheck));
        }
        Intersection165 sol = new Intersection165(x, y);
        //System.out.println(x);
        if (x >= Math.max(Math.min(x1, x2), Math.min(x3, x4)) &&
                x <= Math.min(Math.max(x1, x2), Math.max(x3, x4)) &&
                !sol.equals(new Intersection165(x1, y1)) &&
                !sol.equals(new Intersection165(x2, y2)) &&
                !sol.equals(new Intersection165(x3, y3)) &&
                !sol.equals(new Intersection165(x4, y4))) {
            return sol;
        }

        return null;
    }
}

class Intersection165 {
    double x;
    double y;

    public Intersection165(double x, double y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Intersection165 that = (Intersection165) o;
        return Double.compare(x, that.x) == 0 && Double.compare(y, that.y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public String toString() {
        return String.format("(%.4f, %.4f)", this.x, this.y);
    }
}


