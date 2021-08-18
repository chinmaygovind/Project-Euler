package problems;


import util.Numbers;

import java.util.ArrayList;

//Pentagon Numbers.
public class Problem044 {
    private static int smallestDifference = 99999999;
    private static ArrayList<Integer> pentagons = new ArrayList<>();
    private static int n = 2;
    public static void main(String[] args){
        pentagons.add(1);
        pentagons.add(5);
        while (smallestDifference == 99999999){
            pentagons.add(n * (3 * n - 1) / 2);
            //check for pentagon-sum difference pairs
            for (int i = 0; i < n; i++){
                if (isPentagonal(pentagons.get(n) + pentagons.get(i)) &&
                        isPentagonal(pentagons.get(n) - pentagons.get(i)) &&
                pentagons.get(n) - pentagons.get(i) < smallestDifference){
                    smallestDifference = pentagons.get(n) - pentagons.get(i);
                    break;

                }
            }
            n++;
        }
        System.out.println("The difference of the pair of pentagonal numbers where their sum and difference are pentagonal and the difference is minimized is: " + smallestDifference);
    }

    private static boolean isPentagonal(int num){
        return (Numbers.solveQuadratic(3/2.0, -1/2.0, -num)[0]%1 == 0);
    }
}
