package problems;

import util.Numbers;

// Bitwise-OR Operations on Random Integers.
public class Problem323 {
    public static void main(String[] args) {
        // use the definition of expectation E[X] = Pr[X > 0] + Pr[X > 1] + Pr[X > 2] ...
        double x = 0;
        for (int i = 0; i < 100; i++) {
            // Pr[X > i] = 1 - Pr[X <= i] = 1 - Pr[all bits 1 after i XORs] = 1 - (1 - Pr[a bit 0 after i XORs])
            // 1 - ((1 - (1/2)^i)^32))
            x += 1 - Math.pow(1 - Math.pow(0.5, i), 32);
        }
        System.out.printf("The expected number of the index N such that the sequence is all 1 bits is: %.10f\n", x);
    }
}
