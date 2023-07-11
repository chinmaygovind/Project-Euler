package problems;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import util.Files;
import util.Geometry;

//Triangle Containment.
public class Problem102 {
    private static Scanner fileScan = Files.fileScanner("/problem102.txt");
    private static List<List<Point>> triangles = new ArrayList<>();
    private static int containsOrigin = 0;
    public static void main(String[] args) {
        while (fileScan.hasNextLine()){
            List<Integer> points = Arrays.stream(fileScan.nextLine().split(",")).map(Integer::parseInt).collect(Collectors.toList());
            List<Point> triangle = new ArrayList<>();
            for (int i = 0; i < 3; i++){
                triangle.add(new Point(points.get(2*i), points.get(2*i + 1)));
            }
            triangles.add(triangle);
        }
        for (List<Point> triangle : triangles) {
            //LOOK AT THIS ONE LINER CHEESE AHAHAHA
            containsOrigin += Geometry.convexHull(triangle).equals(Geometry.convexHull((triangle.add(new Point(0, 0)) ? triangle : null))) ? 1 : 0;
        }
        System.out.println("Out of the thousand random triangles, the amount of triangles containing the origin is: " + containsOrigin);
    }





}
