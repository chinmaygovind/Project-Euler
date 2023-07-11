package problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import util.Coordinate;
import util.Geometry;
import util.Numbers;
import util.Sets;

//Investigating the Torricelli Point of a Triangle.
public class Problem143 {
    private static final int limit = 120000;

    //discovery: ATB, BTC, and CTA have 120 degree angles at T
    //given q and r forming BTC, solve for side a using
    //a = sqrt(q^2 + r^2 - 2qrcos(120)) = sqrt(q^2 + r^2 + qr)
    //so the new challenge is to find p, q, and r
    //such that (1) p^2 + q^2 + pq, (2) p^2 + r^2 + pr, and (3) q^2 + r^2 + qr are all square numbers
    //we know p^2 + q^2 + 2pq is a square as (p + q)^2
    //write (1) as (p + q)^2 - pq = a^2
    //rearrage to get (p + q)^2 - a^2 = pq => (p + q + a)(p + q - a) = pq
    //given p and q, finding a is trivial

    public static void main(String[] args) {
        HashMap<Integer, ArrayList<Integer>> workingPQ = new HashMap<>();
        for (long p = 1; p < limit; p++) {
            for (long q = p; q < limit; q++) {
                double a = Math.sqrt(p*p + q*q + q*p);
                if ((long) a == a) {
                    if (workingPQ.containsKey((int) p)) {
                        workingPQ.get((int) p).add((int) q);
                    } else {
                        workingPQ.put((int) p, new ArrayList<>(Collections.singletonList((int) q)));
                    }
                    if (workingPQ.containsKey((int) q)) {
                        workingPQ.get((int) q).add((int) p);
                    } else {
                        workingPQ.put((int) q, new ArrayList<>(Collections.singletonList((int) p)));
                    }
                }
            }
        }
        Set<Integer> pqr = new HashSet<>();
        for (int p : workingPQ.keySet()) {
            for (int q : workingPQ.get(p)) {
                for (int r : workingPQ.get(q)) {
                    if (workingPQ.get(r).contains(p) && p + q + r <= 120000) pqr.add(p + q + r);
                }
            }
        }

        System.out.println("The sum of all distinct p + q + r <= 120,000 for Toricelli triangles is: " + Sets.sum(new ArrayList<>(pqr)));
    }

    public static double getPQR(double a, double b, double c) {
        //given side lengths a, b, c, and assume triangle has AB on the x-axis in first quadrant
        //System.out.println(getPQR(a, b, c));
        /*
        Coordinate A = new Coordinate(0, 0);
        Coordinate B = new Coordinate(c, 0);
        Coordinate C = new Coordinate(Math.cos(angleA) * b, Math.sin(angleA) * b);
        //construct triangles M, N, O on sides b, a, c.
        Coordinate M = new Coordinate(
                (A.getX() + C.getX())/2 + Math.cos(Math.PI/2 + angleA) * b/2 * Math.sqrt(3),
                (A.getY() + C.getY())/2 + Math.sin(Math.PI/2 + angleA) * b/2 * Math.sqrt(3));
        Coordinate N = new Coordinate(
                (B.getX() + C.getX())/2 + Math.cos(Math.PI/2 - angleB) * a/2 * Math.sqrt(3),
                (B.getY() + C.getY())/2 + Math.sin(Math.PI/2 - angleB) * a/2 * Math.sqrt(3));
        Coordinate O = new Coordinate(
                (A.getX() + B.getX())/2,
                (A.getY() + B.getY())/2 - c/2 * Math.sqrt(3));
        //T is where M, N, and O's circumscribed circles meet.
        //(x - M.x)^2 + (y - M.y)^2 = broot3/2
        //(x - N.x)^2 + (y - N.y)^2 = aroot3/2
        //x^2 - 2 M.x x + M.x^2 + y^2 - 2 M.y y + M.y^2 = broot3/2
        //x^2 - 2 N.x x + N.x^2 + y^2 - 2 N.y y + N.y^2 = aroot3/2
        //x(-2M.y + 2N.y) + y(-2 M.y + 2 N.y) = (broot3 + aroot3)/ 2 + M.x^2 - N.x^2 + M.y^2 - N.y^2
        //x(-2M.y + 2O.y) + y(-2 M.y + 2 O.y) = (broot3 + aroot3)/ 2 + M.x^2 - O.x^2 + M.y^2 - O.y^2
        //(x - O.x)^2 + (y - O.y)^2 = croot3/2
         */
        //a, b, c
        //p : q : r = csc(A - pi/3) : csc(B - pi/3) : csc(C - pi/3)
        //k = 2 * sqrt(s(s - a)(s - b)(s - c)) / (a * csc(A - pi/3) +
        double angleA = Geometry.angleLOC(b, c, a);
        double angleB = Geometry.angleLOC(c, a, b);
        double angleC = Geometry.angleLOC(a, b, c);
        Coordinate A = new Coordinate(0, 0);
        Coordinate B = new Coordinate(c, 0);
        Coordinate C = new Coordinate(Math.cos(angleA) * b, Math.sin(angleA) * b);
        double x = 1 / Math.cos(angleA - Math.PI/6);
        double y = 1 / Math.cos(angleB - Math.PI/6);
        double z = 1 / Math.cos(angleC - Math.PI/6);
        Coordinate T = C.multiply(c * z / (a * x + b * y + c * z)).add(B.multiply(b * y / (a * x + b * y + c * z)));
        System.out.println("sides: " + a + ", " + b + ", " + c);
        System.out.println("trilinear: " + x + ", " + y + ", " + z);
        System.out.println("T: " + T);
        System.out.println("pqr: " + T.distance(A) + ", " + T.distance(B) + ", " + T.distance(C));
        double p = T.distance(A);
        double q = T.distance(B);
        double r = T.distance(C);
        if ((int) Numbers.dejankify(p) == Numbers.dejankify(p) &&
                (int) Numbers.dejankify(q) == Numbers.dejankify(q) &&
                (int) Numbers.dejankify(r) == Numbers.dejankify(r)) {
            return p + q + r;
        }
        return 0;
    }

}


