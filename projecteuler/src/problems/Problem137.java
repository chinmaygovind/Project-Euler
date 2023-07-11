package problems;

import java.util.ArrayList;

//Fibonacci Golden Nuggets.
public class Problem137 {
    //my janky notepad++ notes to solve this
    //root8 - 2 / 2
    //root25 - 3 / 4
    //root 52 - 4 / 6
    //root 89 - 5 / 8
    //root 136 - 6 / 10
    //Af-1(x) = root(4x^2 + (x + 1)^2) - (x + 1)^2 / 4x^24x^2 + (x + 1)^2
    //5x^2 + 2x + 1
    //target: find values of x where 5x^2 + 2x + 1 is a square number
    //pythag triples of x + 1 and 2x
    //m^2 - n^2 = mn + 1
    //        2mn = 2x
    //        x = mn,
    //from quora: https://www.quora.com/How-do-you-solve-x-2-xy-y-2-1
    //m^2 - mn - n^2 = 1
    //m, n = (t, (-t + sqrt(5t^2 - 4)/2)

    public static void main(String[] args) {
        ArrayList<Long> nuggets = new ArrayList<>();
        long t = 2;//let sqrt(5t^2 - 4) = k, t = sqrt((k^2 + 4)/5) idk man k is t t isnt k whatever dude
        while (nuggets.size() < 15) {
            long k = (t*t*5 - 4);
            if (Math.sqrt(k) == Math.floor(Math.sqrt(k))) {
                double n = (-t + Math.sqrt(5 * t * t - 4))/2;
                nuggets.add((long) (t * n));
            }
            t++;
        }
        System.out.println("The 15th Fibonacci Golden Nugget is: " + nuggets.get(14));
    }


}

