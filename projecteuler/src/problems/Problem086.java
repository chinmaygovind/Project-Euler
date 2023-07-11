package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

//Cuboid Route.
public class Problem086 {
    private static int cuboids = 0;
    private static int m = 0;
    public static void main(String[] args) {
        while (cuboids < 1000000){
            m++;
            for (int j = 1; j <= m; j++){
                for (int k = j; k <= m; k++){
                    cuboids += (checkCuboid(m, j, k) ? 1 : 0);
                }
            }
        }
        System.out.println("The value of M so that over one million cuboids up to dimensions MxMxM have an integer shortest path between opposite corners is: " + m);
    }


    public static boolean checkCuboid(int width, int height, int depth){
        ArrayList<Integer> sides = new ArrayList<>(Arrays.asList(width, height, depth));
        int max = Collections.max(sides);
        sides.remove((Integer) max);
        return (Math.sqrt(max * max + Math.pow(sides.get(0) + sides.get(1), 2))%1 == 0);
    }



}
