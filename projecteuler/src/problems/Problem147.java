package problems;


import util.Geometry;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

//Rectangles in Cross-Hatched Grids.
public class Problem147 {
    private static long rects = 0;
    private static ArrayList<Integer> inducts = new ArrayList<>();
    public static void main(String[] args) {
        for (int i = 0; i <= 47; i++) {
            if (i == 0) inducts.add(0);
            else {
                inducts.add(inducts.get(inducts.size()-1) + (2 * i - 1) * (2 * i - 1));
            }
        }
        for (int i = 1; i <= 47; i++) {
            for (int j = 1; j <= 43; j++) {
                rects += countRects(i, j);
            }
        }
        System.out.println("The total number of rectangles that could fit in 47x43 grids is: " + rects);
    }

    //i just stuck the first few squares into wolfram alpha and got a formula uhhh
    //cheating? maybe
    //but like
    //well
    //hm.
    public static long getSquareDiags(long n) {
        return (-3 * n - n * n + 4 * n * n * n * n) / 6;
    }
    public static long getRectDiags(long w, long h) {
        return getSquareDiags(Math.min(w, h)) + Math.abs(h - w) * inducts.get((int) Math.min(w, h));
    }

    public static long countRects(long w, long h) {
        long rects = w * (w + 1) * h * (h + 1) / 4;//# of normal rects
        /*
        long S = 2 * (Math.max(w, h) - 1);
        if (w == 1 || h == 1) S--;
        long diags = 0;
        if (w == 1 || h == 1) diags = Math.max(w, h) - 1;
        else {
            ArrayList<Point> grid = getGrid(w, h);
            //System.out.println(grid);
            for (int dW = 1; dW <= S; dW++) {
                for (int dH = 1; dH <= S; dH++) {
                    for (Point tl : grid) {
                        boolean valid = true;
                        for (int i = 0; i < dW; i++) {
                            if (!valid) break;
                            for (int j = 0; j < dH; j++) {
                                valid = grid.contains(new Point(tl.x + i, tl.y + j));
                                if (!valid) break;
                            }
                        }
                        diags += valid ? 1 : 0;
                    }
                }
            }
        }

         */
        //System.out.println(grid);
        //System.out.println(w + "," + h + "| S: " + S + " | diags: " + diags + " | total: " + rects);
        /*
        System.out.println(rects);
        //# of diagonal rects
        for (double diag = 1; diag <= Math.min(w, h)*2 - 1; diag++) {
            for (double antiDiag = diag; antiDiag <= Math.max(w, h)*2 - 1; antiDiag++) {

                rects += (diag == antiDiag ? 1 : 2) * Math.max(0,
                        (int) (w - diag) * h + (int) (h - antiDiag) * w);
                System.out.println(diag + ", " + antiDiag + ": " + (diag == antiDiag ? 1 : 2) *
                        Math.max(0, (int) (w - diag) * h + (int) (h - antiDiag) * w));

            }
        }

         */
        //System.out.println("Total: " + rects);
        return rects + getRectDiags(w, h);
    }

    public static ArrayList<Point> getGrid(long w, long h) {
        //start by extending out the bottom row
        ArrayList<Point> topRow = new ArrayList<>(Collections.singletonList(new Point(0, 0)));
        while (topRow.size() < w-1) {
            Point prev = topRow.get(topRow.size()-1);
            topRow.add(new Point(prev.x + 1, prev.y - 1));
        }
        //System.out.println(topRow);
        HashSet<Point> grid = new HashSet<>();
        for (int i = 0; i < h; i++) {
            for (Point p : topRow) {
                if (i == 0) grid.add(p);
                else {
                    grid.add(new Point(p.x + i, p.y + i));
                    grid.add(new Point(p.x + i - 1, p.y + i));
                    grid.add(new Point(p.x + i, p.y + i - 1));
                }
            }
        }
        return new ArrayList<>(grid);
    }
}



