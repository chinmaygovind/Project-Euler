package problems;

import util.Coordinate;
import util.Geometry;
import util.Numbers;
import util.Sets;

import java.util.*;

//Investigating the Torricelli Point of a Triangle.
public class Problem144 {
    private static ArrayList<Coordinate> points = new ArrayList<>();
    public static void main(String[] args) {
        points.add(new Coordinate(0, 10.1));
        points.add(new Coordinate(1.4, -9.6));
        while (points.get(points.size()-1).getY() < 9.99997999998) {
            points.add(nextPoint(points.get(points.size()-2), points.get(points.size()-1)));
            System.out.println(points.get(points.size()-1));
        }
        System.out.println("The number of times the laser bounces inside the cell is: " + (points.size()-2));
    }

    public static Coordinate nextPoint(Coordinate source, Coordinate bounce) {
        //System.out.println("points size: " + points.size());
        double inSlope = (bounce.getY() - source.getY()) / (bounce.getX() - source.getX());
        double inAngle = Math.atan(inSlope);
        //System.out.println("in angle: " + inAngle);
        double normalAngle = Math.atan(bounce.getY() / (4 * bounce.getX()));
        //System.out.println("normal angle: " + normalAngle);
        double outAngle = normalAngle - (inAngle - normalAngle);
        //System.out.println("out angle: " + outAngle);
        double outSlope = Math.tan(outAngle);
        //System.out.println("out slope: " + outSlope);
        //exit line: y = outSlopex + (bounce.y - outSlope * bounce.x)
        double c = bounce.getY() - outSlope * bounce.getX();
        //System.out.println("c: " + c);
        //4x^2 + outSlopex^2 + 2c outSlopex + c^2 - 100 = 0
        double[] sols = Numbers.solveQuadratic(4 + outSlope*outSlope, 2 * c * outSlope, c*c - 100);
        //System.out.println("sols: " + Arrays.toString(sols));
        double newX = (Math.abs(bounce.getX() - sols[1]) > Math.abs(bounce.getX() - sols[0])
                ? sols[1] : sols[0]);
        double newY = Math.sqrt(100 - 4 * newX * newX) * Math.signum(outSlope * newX + c);
        //System.out.println("coords: " + newX + ", " + newY);
        return new Coordinate(newX, newY);
    }

}


