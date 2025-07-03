package problems;

import java.util.Arrays;

// Nim.
public class Problem301 {
    public static void main(String[] args) {
        int zeros = 0;
        for (int n = 1; n <= 1 << 30; n++) {
            if (((n ^ (2 * n)) ^ (3 * n)) == 0) zeros++;
        }
        System.out.println("The number of positive integers n below 2^30 for which X(n, 2n, 3n) = 0 is: " + zeros);
    }


}
