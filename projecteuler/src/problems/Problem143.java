package problems;

import util.Coordinate;
import util.Geometry;

import java.awt.*;
import java.util.ArrayList;

//Investigating the Torricelli Point of a Triangle.
public class Problem143 {

    public static void main(String[] args) {
        //given side lengths a, b, c, and assume triangle has AB on the x-axis in first quadrant
        double a = 399;
        double b = 455;
        double c = 511;
        double angleA = Geometry.angleLOC(b, c, a);
        double angleB = Geometry.angleLOC(c, a, b);
        double angleC = Geometry.angleLOC(a, b, c);
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
    }
}


