package problems;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashSet;

//Intersections.
public class Problem165 {

    private static HashSet<Double> foundPoints = new HashSet<>();

    public static void main(String[] args) {
        long[] s = new long[20001];
        int[] t = new int[20001];
        s[0] = 290797; t[0] = (int) (s[0]%500);
        for (int i = 1; i < s.length; i++) {
            s[i] = (s[i - 1] * s[i - 1])%50515093L;
            t[i] = (int) (s[i]%500);
        }
        int intersections = 0;
        for (int l1 = 0; l1 < t.length/4; l1++) {
            int x1 = t[4*l1+1];
            int y1 = t[4*l1+2];
            int x2 = t[4*l1+3];
            int y2 = t[4*l1+4];
            //(y2 - y1)/(x2 - x1)
            //y = m(x - x1) + y1
            for (int l2 = l1; l2 < t.length/4; l2++) {
                int x3 = t[4*l2+1];
                int y3 = t[4*l2+2];
                int x4 = t[4*l2+3];
                int y4 = t[4*l2+4];
                intersects(x1, y1, x2, y2, x3, y3, x4, y4);

            }
        }
        System.out.println(foundPoints.size());
    }

    private static void intersects(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        double m1 = (y2 - y1) / (x2 - x1);
        double m2 = (y4 - y3) / (x4 - x3);
        //System.out.println(m1 + ", " + m2);
        if (m1 == m2) {
            return;
        }
        //m1(x - x1) + y1 = m2(x - x3) + y3
        //x(m1 - m2) = x1 * m1 -x3 * m2 + y3 - y1
        //System.out.printf("Line A: y = %.2f(x - %.2f) + %.2f\n", m1, x1, y1);
        //System.out.printf("Line B: y = %.2f(x - %.2f) + %.2f\n", m2, x3, y3);
        //System.out.printf("(%.2f - %.2f)x = %.2f * %.2f - %.2f * %.2f + %.2f - %.2f\n", m1, m2, x1, m1, x3, m2, y3, y1);
        double x = (x1 * m1 - x3 * m2 + y3 - y1) / (m1 - m2);
        //System.out.println(x);
        if (x > Math.max(Math.min(x1, x2), Math.min(x3, x4)) && x < Math.min(Math.max(x1, x2), Math.max(x3, x4))) {
            foundPoints.add(x * (m1 * (x - x1) + y1));//xy
        }
    }
}


