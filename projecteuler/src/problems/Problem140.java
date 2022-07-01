package problems;

import util.Numbers;

import java.awt.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

//Modified Fibonacci Golden Nuggets.
public class Problem140 {
    //same cheese strat as 137 except this time the radical part is
    //5n^2 + 14n + 1
    //never mind cheese won't work we gotta get the big boys out

    //make quadratic of form 25g^2 + 70g + 5 = 5k^2, solve for g
    //25g^2 + 70g + 49 - (5 - 5k^2) = 49
    //(5g + 7)^2 = 44 + 5k^2 //HAHA I GOT IT ON THE CAR RIDE TO WALMART I FIGURED IT OUT
    //let x = 5g + 7, y = k
    //x^2 - 5y^2 = 44 (flavor of Pell's equation)

    //from https://en.wikipedia.org/wiki/Pell's_equation#Generalized_Pell's_equation


    public static void main(String[] args) {
        ArrayList<Point> sols = Numbers.solveGeneralPellEquation(5, 44, 80);
        System.out.println(sols);
    }


}

