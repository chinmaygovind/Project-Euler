package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

//Criss Cross.
public class Problem166 {
    //cha cha real smooth
    //one shot johnson too
    public static void main(String[] args) {
        int totalSols = 0;
        for (int digits = 0; digits < 1_000_000_000; digits++) {
            //if (digits % 10_000_000 == 0) System.out.println(digits);
            totalSols += numValidPositions(digits);
        }
        System.out.println("The total number of ways to fill a 4x4 grid with digits 0-9 with all rows, columns and diagonals having the same sum is: " + totalSols);

    }

    public static int numValidPositions(int digits) {
        int[] grid = new int[9];
        for (int i = 0; i < 9; i++) {
            grid[8 - i] = digits%10;
            digits/=10;
        }
        int row1sum = grid[0] + grid[1] + grid[2];
        int row2sum = grid[3] + grid[4] + grid[5];
        int row3sum = grid[6] + grid[7] + grid[8];
        int col1sum = grid[0] + grid[3] + grid[6];
        int col2sum = grid[1] + grid[4] + grid[7];
        int col3sum = grid[2] + grid[5] + grid[8];
        int diag1sum = grid[0] + grid[4] + grid[8];
        int diag2partial = grid[5] + grid[7];
        int minSum = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(row1sum, row2sum), row3sum), col1sum), col2sum), col3sum), diag1sum);
        int maxSum = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(row1sum, row2sum), row3sum), col1sum), col2sum), col3sum), diag1sum);
        int diff = maxSum - minSum;
        if (diff > 9) return 0;
        int sols = 0;
        for (int target = maxSum; target < minSum + 10; target++) {
            int row1end = target - row1sum;
            int row2end = target - row2sum;
            int row3end = target - row3sum;
            int col1end = target - col1sum;
            int col2end = target - col2sum;
            int col3end = target - col3sum;
            int diag1end = target - diag1sum;
            int row4 = col1end + col2end + col3end + diag1end;
            int col4 = row1end + row2end + row3end + diag1end;
            if (row4 == target && col4 == target &&
                diag2partial + row1end + col1end == target &&
                0 <= row1end && row1end <= 9 &&
                0 <= row2end && row2end <= 9 &&
                0 <= row3end && row3end <= 9 &&
                0 <= col1end && col1end <= 9 &&
                0 <= col2end && col2end <= 9 &&
                0 <= col3end && col3end <= 9 &&
                0 <= diag1end && diag1end <= 9) {
                //System.out.printf("%d %d %d %d / %d %d %d %d / %d %d %d %d / %d %d %d %d\n",
                //        grid[0], grid[1], grid[2], row1end,
                //        grid[3], grid[4], grid[5], row2end,
                //        grid[6], grid[7], grid[8], row3end,
                //        col1end, col2end, col3end, diag1end);
                sols++;
            }
        }
        return sols;
    }

}
