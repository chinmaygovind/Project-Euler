package problems;



import util.Numbers;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Shortest Distance Among Points.
public class Problem816 {
    // closest pair
    private static int N = 2_000_000;
    private static List<Point> pX;
    private static List<Point> pY;


    public static void main(String[] args) {
        long[] s = new long[2 * N];
        s[0] = 290797;
        for (int i = 1; i < s.length; i++) {
            s[i] = (s[i - 1] * s[i - 1]) % 50515093;
        }
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            points[i] = new Point((int) s[2 * i], (int) s[2 * i + 1]);
        }
//        System.out.println("Generated points...");
        pX = Arrays.asList(points);
        pY = Arrays.asList(points);
        pX.sort(Comparator.comparingInt(o -> o.x));
        pY.sort(Comparator.comparingInt(o -> o.y));
//        System.out.println("Sorted points...");
        System.out.printf("The smallest distance between any 2 distinct points from P0 to P2000000 is: %.9f\n", closestPair(pX));
    }

    public static double closestPair(List<Point> points) {
        // assume points given sorted by x coordinate
        if (points.size() < 1000) {
            double d = Double.MAX_VALUE;
            for (int i = 0; i < points.size(); i++) {
                for (int j = i + 1; j < points.size(); j++) {
                    d = Math.min(d, points.get(i).distance(points.get(j)));
                }
            }
            return d;
        } else {
            double dLeft = closestPair(points.subList(0, points.size()/2));
            double dRight = closestPair(points.subList(points.size()/2, points.size()));
            double stripX = (points.get(points.size()/2).getX() + points.get(points.size()/2 + 1).getX()) / 2.0;
            double delta = Math.min(dLeft, dRight);
            List<Point> sY = new ArrayList<>();
            for (Point p : pY) {
                if (Math.abs(p.getX() - stripX) < delta) {
                    sY.add(p);
                }
            }
            double d = Double.MAX_VALUE;
            for (int i = 0; i < sY.size(); i++) {
                for (int j = i + 1; j < Math.min(sY.size(), i + 16); j++) {
                    d = Math.min(d, sY.get(i).distance(sY.get(j)));
                }
            }
            return Math.min(delta, d);
        }
    }
}

