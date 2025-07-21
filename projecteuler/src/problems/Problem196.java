package problems;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

// Prime Triplets.
public class Problem196 {
    public static void main(String[] args) {
//        System.out.println(S(8));
//        System.out.println(S(9));
//        System.out.println(S(10_000));
        System.out.println("The value of S(5678027) + S(7208785) is: " + (S(5678027) + S(7208785)));
    }

    private static long S(long n) {
        ArrayList<ArrayList<Boolean>> rows = new ArrayList<>();
        rows.add(getRow(n - 2));
        rows.get(0).add(false); rows.get(0).add(false); rows.get(0).add(false); rows.get(0).add(false);
        rows.add(getRow(n - 1));
        rows.get(1).add(false); rows.get(1).add(false); rows.get(1).add(false);
        rows.add(getRow(n));
        rows.get(2).add(false); rows.get(2).add(false);
        rows.add(getRow(n + 1));
        rows.get(3).add(false);
        rows.add(getRow(n + 2));
        for (ArrayList<Boolean> row : rows) {
            row.add(0, false);
            row.add(false);
        }
        int[][] trips = new int[3][(int) n + 1];
        for (int row = 0; row < 3; row++) {
            for (int i = 0; i < n + 1; i++) {
//                if (i % 100000 == 0) System.out.println("building row " + row + " num " + i + " out of " + n);
                if (!rows.get(row + 1).get(i + 1)) continue;
                int neighbors = 0;
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        if (x == 0 && y == 0) continue;
                        neighbors += rows.get(row + 1 + x).get(i + 1 + y) ? 1 : 0;
                    }
                }
                if (neighbors >= 2) trips[row][i] = 1;
            }
        }
//        for (int[] row : trips) {
//            System.out.println(Arrays.toString(row));
//        }
        long sum = 0;
        long start = n * (n - 1) / 2 + 1;
        for (int i = 0; i < n; i++) {
//            if (i % 100_000 == 0) System.out.println("checking " + (i + start));
            if (!isPrime(i + start)) continue;
            int valid = 0;
            if (i > 0) {
                valid |= trips[0][i - 1];
                valid |= trips[1][i - 1];
                valid |= trips[2][i - 1];
            }
            valid |= trips[0][i];
            valid |= trips[1][i];
            valid |= trips[2][i];
            if (i < n - 1) {
                valid |= trips[0][i + 1];
                valid |= trips[1][i + 1];
                valid |= trips[2][i + 1];
            }
            if (valid > 0) {
//                System.out.println(i + start);
                sum += i + start;
            }
        }
        return sum;
    }

    private static ArrayList<Boolean> getRow(long n) {
//        System.out.println("Building row " + n);
        long start = n * (n - 1) / 2 + 1;
        ArrayList<Boolean> row = new ArrayList<>();
        for (long i = 0; i < n; i++) {
//            if (i % 100_000 == 0) System.out.println("Building row " + n + " number " + i + " / " + n);
            row.add(isPrime(start + i));
        }
        return row;
    }

    private static boolean isPrime(long l) {
        return BigInteger.valueOf(l).isProbablePrime(20);
    }
}
