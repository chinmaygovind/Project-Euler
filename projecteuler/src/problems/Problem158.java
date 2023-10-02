package problems;

import util.Numbers;
import util.Timer;

//Lexicographical Neighbors.
public class Problem158 {

    private static final int NUM_LETTERS = 26;
    private static long[][] chooses = new long[NUM_LETTERS+1][NUM_LETTERS+1];
    public static void main(String[] args) {
        cacheChooses();
        long maxVal = 0;
        for (int n = 2; n <= NUM_LETTERS; n++) {
            maxVal = Math.max(maxVal, p(n));
            //System.out.println(n + ": " + p(n));
        }
        System.out.println("The max value of p(n) is: " + maxVal);

    }

    /*
     * p(n) counts the number of alphabet strings of length n such that exactly one character comes lexicographically after the previous one
     */
    public static long p(int n) {
        return stupidP(n) * choose(NUM_LETTERS, n);
    }

    public static long stupidP(int n) {
        if (n == 2) return 1;
        return (n - 1) + 2 * stupidP(n - 1);
    }
    public static void cacheChooses() {
        for (int i = 0; i <= NUM_LETTERS; i++) {
            chooses[0][i] = 0;
            chooses[i][0] = 1;
        }
        for (int i = 1; i <= NUM_LETTERS; i++) {
            for (int j = 1; j <= NUM_LETTERS; j++) {
                chooses[i][j] = chooses[i - 1][j] + chooses[i - 1][j - 1];
            }
        }
    }
    public static long choose(int n, int r) {
        if (n < 0 || r < 0) return 0;
        //System.out.printf("%dC%d = %d\n", n, r, chooses[n][r]);
        return chooses[n][r];
    } 
}


