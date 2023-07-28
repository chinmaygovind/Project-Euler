package problems;

import util.Timer;

//Exploring Pascal's Pyramid.
public class Problem154 {
    
    private static long solutions = 0;
    private static final int N = 200_000;
    private static final int[] fives_cache = new int[N + 1];
    private static final int[] twos_cache = new int[N + 1];
    public static void main(String[] args) {
        //number of coeffs in expansion of (x + y + z)^200000 that are multiples of 10^12.

        //generate coeffs as follows:
        //(x + y + z)^n
        //for (zI = 0; zI <= n; zI++) {
            //for (yI = 0; yI <= n - zI; yI++) {
                //int xI = n - zI - yI;
                //long coeff = nCzI * (n - zI)CyI
                //this needs to have a 10^12
            //}
        //}
        Timer.startTimer();
        for (int i = 0; i <= N; i++) {
                int fives = 0;
                int divider = 5;
                while (divider <= i) {
                    fives += i / divider;
                    divider *= 5;
                }
                fives_cache[i] = fives;
        }
        for (int i = 0; i <= N; i++) {
                int twos = 0;
                int divider = 2;
                while (divider <= i) {
                    twos += i / divider;
                    divider *= 2;
                }
                twos_cache[i] = twos;
        }
        for (int a = 0; a <= N; a++) {
            //if (a%1000 == 0) System.out.println(a);
            for (int b = 0; b <= N - a; b++) {
                int c = N - a - b;
                int fives = fives_cache[a] + fives_cache[b] + fives_cache[c];
                int twos = twos_cache[a] + twos_cache[b] + twos_cache[c];
                if (fives_cache[N] - fives >= 12 && twos_cache[N] - twos >= 12) solutions++;

            }
        }
        System.out.println("The number of coefficients in the expansion of (x + y + z)^200,000 that are multiples of 10^12 is: " + solutions);
        System.out.println("Ran in " + Timer.elapsedTime() + "ms");
    }

    //private static int tens(int n) {
        
    //}
 }



