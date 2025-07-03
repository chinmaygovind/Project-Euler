package problems;

import util.Numbers;

import java.util.ArrayList;

// Firecracker.
public class Problem317 {
    // crazy that they named a problem after a fast, single-target, air-targeting, long-ranged, ground troop
    private static double g = 9.81;
    private static double THETA_PRECISION = 0.000001;
    private static double dx = 0.00000005;
    public static void main(String[] args) {
        // so first start with 2D case
        // so from some experimenting, you can parametrize the trajectory of each firecracker piece and it
        // forms a parabola from the start point at (0, 100)
        // wiggling around the starting angle, you can guess that the envelope of all these curves forms another
        // parabola. finding the extreme points (highest point, leftmost/rightmost points), and fitting
        // a parabola to it seems to confirm this
        // https://www.desmos.com/calculator/g1a7fkfmiu

        // so, we just need to find those extreme points, fit a parabola to it, and rotate it in 3D

        // X(t) = 20 cosTheta * t
        // Y(t) = 100 + 20 sinTheta * t - 9.81t^2 / 2
        double x0 = 0;
        // highest when shooting straight up, Theta = 0 -> Y(t) = 100 + 20t - 9.81t^2 / 2
        // so max at t = 20 / 9.81
        double y0 = 100 + 20 * (20 / g) - (g / 2) * (20 / g) * (20 / g);

        // search for optimal theta for farthest right piece, desmos sketching constraints to 0.1 < alpha < 0.7
        double thetaLow = 0.1;
        double thetaHigh = 0.7;
        double optimalTheta = 0.5;
        double x1 = 0;
        double y1 = 0;
        for (double theta = thetaLow; theta < thetaHigh; theta += THETA_PRECISION) {
            // solve for t when piece hits ground
            // Y(t) = 100 + 20 sinTheta * t - 9.81t^2 / 2 = 0
            double[] sols = Numbers.solveQuadratic(-g / 2, 20 * Math.sin(theta), 100);
            double t = sols[0] > 0 ? sols[0] : sols[1]; // take positive solution
            // now solve for x
            // X(t) = 20cosTheta * t
            double x = t * 20 * Math.cos(theta);
            if (x > x1) {
                x1 = x;
                optimalTheta = theta;
            }
        }
        double x2 = -x1;
        double y2 = 0;
        // three points define a parabola!
        // y = -y0 * (x - x1)(x + x1) / x1^2
        // now we just need to rotate this about y axis
//        double volume = 2 * Math.PI * -y0 / (x1 * x1) * (x1 * x1 * x1 / 3 - x1 * x1 * x1 * x1 / 2);
//        // shell method - cylinders coming outward, 2pir * hdx
//        for (double x = 0; x < x1; x += dx) {
//            volume += 2 * Math.PI * x * (-y0 * (x - x1) * (x + x1) / (x1 * x1)) * dx;
//        }

        // numerical integration might be cooked. let's actually do the integral
        // int 2 pi r h dx = 2pi -y0 / (x1^2) int x * (x^2 - x1^2) dx = 2pi -y0 / (x1^2) (x^3/3 - x^2 * x1^2 / 2) at x1
        // = 2pi -y0 / (x1^2) (x1^3/3 - x1^4 / 2)
        // h = -y0 * (x - x1)(x + x1) / x1^2 = (-y0/x1^2) * (x^2 - x1^2)
        // int 2 pi r h dx = 2 pi (-y0/x1^2) * int x^3 - x1^2x = 2 pi (-y0/x1^2) * (x^4/4 - x1^2x^2/2) at x1
        // = 2 pi (-y0/x1^2) * (-x1^4/4)
        double trueVolume = 2 * Math.PI * (-y0 / (x1 * x1)) * (-x1 * x1 * x1 * x1 / 4);
//        System.out.printf("(%f, %f), (%f, %f), (%f, %f)\n", 0.0, y0, x1, 0.0, -x1, 0.0);
        System.out.printf("The true  volume of the region the firecracker fragments fly through is: %.4f\n", trueVolume);



    }
}
