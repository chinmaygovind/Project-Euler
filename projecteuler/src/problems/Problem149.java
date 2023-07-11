package problems;


import java.math.BigInteger;

//Searching for a Maximum-Sum Subsequence.
public class Problem149 {
    private static final int[] s = new int[4000000];

    public static void main(String[] args) {
        for (int k = 1; k <= 4000000; k++) {
            if (k <= 55) {
                BigInteger bigK = BigInteger.valueOf(k);
                s[k - 1] = BigInteger.valueOf(100003)
                        .subtract(BigInteger.valueOf(200003).multiply(bigK))
                        .add(BigInteger.valueOf(300007).multiply(bigK.modPow(BigInteger.valueOf(3), BigInteger.valueOf(1000000))))
                        .mod(BigInteger.valueOf(1000000)).intValue() - 500000;
            } else {
                s[k - 1] = (s[k - 24 - 1] + s[k - 55 - 1] + 1000000)%1000000 - 500000;
            }
        }
        int[][] grid = new int[2000][2000];
        for (int k = 0; k < 4000000; k++) {
            grid[k/2000][k%2000] = s[k];
        }
        System.out.println("The maximum subsequence sum in the grid is: " + maxSumSubsequence(grid));
    }

    public static int maxSumSubsequence(int[][] grid) {
        int max = 0;

        for (int row = 0; row < grid.length; row++) {
            max = Math.max(maxFromRow(grid[row]), max);
        }
        for (int col = 0; col < grid.length; col++) {
            int[] column = new int[grid.length];
            for (int i = 0; i < grid.length; i++) column[i] = grid[i][col];
            max = Math.max(maxFromRow(column), max);
        }

        for (int diag = 0; diag < grid.length * 2 - 1; diag++) {
            int[] antidiagonal = new int[grid.length];
            int[] diagonal = new int[grid.length];
            for (int i = 0; i <= diag; i++) {
                int j = diag - i;
                if (i >= grid.length || j >= grid.length) {
                    continue;
                }
                antidiagonal[i] = grid[i][j];
                diagonal[i] = grid[grid.length - i - 1][j];
            }
            max = Math.max(Math.max(maxFromRow(antidiagonal), maxFromRow(diagonal)), max);
        }

        return max;
    }

    public static int maxFromRow(int[] row) {
        int start = 0;
        int working = 0;
        for (int i = 0; i < row.length; i++) {
            working += row[i];
            if (working < 0) {
                start = i + 1;
                working = 0;
            }
        }
        int end = row.length - 1;
        working = 0;
        for (int i = row.length - 1; i >= 0; i--) {
            working += row[i];
            if (working < 0) {
                end = i - 1;
                working = 0;
            }
        }
        //System.out.println(start + ", " + end);
        working = 0;
        for (int x = start; x <= end; x++)
            working += row[x];
        return working;
    }

}



