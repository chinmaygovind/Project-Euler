package problems;

import util.Numbers;

//Pythagorean Tiles.
public class Problem139 {
    //given a^2 + b^2 = c^2, when does c%(b - a) = 0.
    //a = m^2 - n^2, b = 2mn, c = m^2 + n^2 = k(m^2 - n^2 - 2mn)
    //a + b + c = 2m(m + n) < 100,000,000
    //m^2 + n^2 = k(m^2 - n^2 - 2mn) three variables 1 equation. hm.
    //three problems in a row involving m and n. hilarious
    private static final long LIMIT = 100000000;
    private static int total = 0;
    public static void main(String[] args) {
        for (long n = 1; n < LIMIT; n++) {
            for (long m = n + 1; 2 * m * (m + n) <= LIMIT; m+=2) {
                if (Numbers.GCF(m, n) != 1) continue;
                long a = m * m - n * n;
                long b = 2 * m * n;
                long c = m * m + n * n;
                if ((c) % Math.abs(b - a) == 0 && a + b + c < LIMIT) {
                    total+= (LIMIT - 1) / (a + b + c);
                }
            }
        }
        System.out.println("The number of Pythagorean triangles with perimeters under one hundred million that allow a tiling to take place is: " + total);
    }


}

