package problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

//Triangle Centers.
public class Problem264 {
    // d1 procrastinator

    // fromhttps://en.wikipedia.org/wiki/Orthocenter
    // In the complex plane, let the points A, B, C represent the numbers zA, zB, zC and
    // assume that the circumcenter of triangle △ABC is located at the origin of the plane.
    // Then, the complex number zH = zA + zB + zC is represented by the point H.
    // ts free

    private static int P = 100000;
    private static List<Integer> badPrimes = List.of(3, 7, 19, 23, 31);
    // p = 100 -> r2 = 145, r = 12.5, 500 -> r2 = 7225, r = 85, 1000 -> r2 = 29665, r=172, 5000 -> r2 = 417625, r = 646, 10000 -> 83 sols
    // approx p^2 / 25
    // for some reason for all solutions the perimeter to radius ratio approaches 5.196151??? WHICH IS 3root3
    // they approach equilateral
    // therefore, we can bound r2 at p^2 / 25 ish

    // 40k -> 126 sols
    public static void main(String[] args) {
        // just find all points (a, b), (c, d), (e, f) such that
        // a^2 + b^2 = c^2 + d^2 = e^2 + f^2 AND
        // a + c + e = 5, b + d + f = 0
        HashSet<HashSet<List<Integer>>> triangles = new HashSet<>();
        double sumPerimeter = 0;
        System.out.println("Caching squares...");
        long[] squares = new long[P / 5];
        HashMap<Long, Integer> squareMap = new HashMap<>();
        for (int i = 0; i < squares.length; i++) {
            squares[i] = i * (long) i;
            squareMap.put(i * (long) i, i);
        }
        System.out.println("Finished caching squares. Finding solutions...");
        // for some reason its always an even tens-digit and a 5 in the ones digit for R
        for (int r2 = 5; r2 <= P * (long) P / 25; r2+=20) { // magic
            if (r2 % 1_000_000 == 5) System.out.printf("Reached r2 = %d\n", (r2 - 5));
            HashSet<List<Integer>> sols = new HashSet<>();
            // darker, yet darker magic
            // https://en.wikipedia.org/wiki/Sum_of_two_squares_theorem
            boolean decomposable = true;
            for (int p : badPrimes) {
                int k = 0;
                int r2Copy = r2;
                while (r2Copy % p == 0) {
                    r2Copy /= p;
                    k++;
                }
                if (k % 2 == 1) {
                    decomposable = false;
                    break;
                }
            }
            if (!decomposable) continue;

            for (int a = 0; a < squares.length; a++) { // only need to search first half of solutions because complements
                long a2 = squares[a];
                if (a2 > r2) break;
                long b2 = r2 - a2;
                if (squareMap.containsKey(b2)) {
                    int b = squareMap.get(b2);
                    sols.add(List.of(a, b));
                    sols.add(List.of(a, -b));
                    if (a > 0) {
                        sols.add(List.of(-a, b));
                        sols.add(List.of(-a, -b));
                    }
                }
            }
            // System.out.println(sols);
            List<List<Integer>> solsList = new ArrayList<>(sols);
            // if (r2 % 1000000 == 0) System.out.println("r2 = " + r2 + ": " + sols.size() + " solutions, " + sols);
            checkSols:
            for (int a = 0; a < solsList.size(); a++) {
                for (int b = a + 1; b < solsList.size(); b++) {
                    List<Integer> zA = solsList.get(a);
                    List<Integer> zB = solsList.get(b);
                    List<Integer> zC = List.of(5 - zA.getFirst() - zB.getFirst(), -zA.getLast() - zB.getLast());
                    if (sols.contains(zC) && !zA.equals(zC) && !zB.equals(zC)) {
                        double AB = Math.sqrt((zA.getFirst() - zB.getFirst()) * (zA.getFirst() - zB.getFirst())
                                + (zA.getLast() - zB.getLast()) * (zA.getLast() - zB.getLast())
                        );
                        double AC = Math.sqrt((zA.getFirst() - zC.getFirst()) * (zA.getFirst() - zC.getFirst())
                                + (zA.getLast() - zC.getLast()) * (zA.getLast() - zC.getLast())
                        );
                        double BC = Math.sqrt((zC.getFirst() - zB.getFirst()) * (zC.getFirst() - zB.getFirst())
                                + (zC.getLast() - zB.getLast()) * (zC.getLast() - zB.getLast())
                        );
                        double p = AB + AC + BC;
                        HashSet<List<Integer>> triangle = new HashSet<>();
                        triangle.add(zA);
                        triangle.add(zB);
                        triangle.add(zC);
                        List<Object> stupid =  new ArrayList<>();
                        stupid.add(stupid);


                        if (!triangles.contains(triangle)) {
                            System.out.printf("r^2 = %d, (53 | r^2 OR 73 | r^2) = %s, r = %f, A = %s, B = %s, C = %s, AB = %f, AC = %f, BC = %f, p = %f, p / r = %f\n", r2, (r2 % 53 == 0 || r2 % 73 == 0), Math.sqrt(r2), zA, zB, zC, AB, AC, BC, p, p / Math.sqrt(r2));
                            if (p < P) {
                                sumPerimeter += p;
                                triangles.add(triangle);
                            }
                        }

                    }
                }
            }
        }
        System.out.printf("The number of triangles with vertices on lattice points, circumcenter at the origin and orthocenter at (5, 0) with maximum perimeter %d is: %d\n", P, triangles.size());
        System.out.printf("The sum of their perimeters is: %.4f\n", sumPerimeter);
    }



}
