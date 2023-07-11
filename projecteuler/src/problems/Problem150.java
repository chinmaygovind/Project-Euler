package problems;


import util.Timer;

//Searching a Triangular Array for a Sub-Triangle Having Minimum-Sum.
public class Problem150 {
    private static final long[] s = new long[500500];

    public static void main(String[] args) {
        Timer.startTimer();

        long t = 0;
        for (int k = 0; k < s.length; k++) {
            t = (615949 * t + 797807) % (1 << 20);
            s[k] = t - (1 << 19);
        }
        int[][] grid = new int[1000][1000];
        int total = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int i = 0; i <= row; i++) {
                grid[row][i] = (int) s[total];
                total++;
            }
        }
        /*
        grid = new int[][]{
                {15, 0, 0, 0, 0, 0},
                {-14, -7, 0, 0, 0, 0},
                {20, -13, -5, 0, 0, 0},
                {-3, 8, 23, -26, 0, 0},
                {1, -4, -5, -18, 5, 0},
                {-16, 31, 2, 9, 28, 3}
        };
        */

        Timer.startTimer();
        long absoluteMin = Long.MAX_VALUE;
        for (int startRow = 0; startRow < grid.length; startRow++) {
            //System.out.println("startRow: " + startRow);
            //System.out.println(startRow);
            for (int startCol = 0; startCol <= startRow; startCol++) {
                int extend = grid.length - startRow;
                long working = 0;
                for (int i = 0; i < extend; i++) {
                    for (int j = 0; j <= i; j++) {
                        working += grid[startRow + i][startCol + j];
                    }
                    absoluteMin = Math.min(absoluteMin, working);//check after each row of triangle is added
                }
                //System.out.println("working: " + working);
                
            }
        }
        System.out.println("The smallest possible triangle sum is: " + absoluteMin);
        System.out.println("Ran in " + Timer.elapsedTime() + "ms");

        /*
        Timer.startTimer();
        int[][] sums = new int[1000 + 1][1000];//sum[i][j] contains sum(grid[0...i][j])
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (i == 0) sums[i + 1][j] = grid[i][j];
                else sums[i + 1][j] = sums[i][j] + grid[i][j];
            }
        }
        //brute force?
        int[][] newGrid = new int[1000][1000];
        int absoluteMin = grid[0][0];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col <= row; col++) {
                if (row == 0) continue;
                if (col == 0) continue;
                int best = 0;
                int running = 0;
                for (int size = 2; size <= col + 1; size++) {
                    int col2 = col + 1 - size;
                    int toAdd = sums[row + 1][col2] - sums[row - size + 1][col2];
                    //System.out.println(toAdd);
                    running += toAdd;
                    best = Math.min(running, best);
                }
                newGrid[row][col] = grid[row][col] + best;
                absoluteMin = Math.min(absoluteMin, newGrid[row][col]);
            }
        }

         */
        //System.out.println(Arrays.deepToString(newGrid).replace("],", "]\n"));
        //System.out.println("The smallest possible triangle sum is: " + absoluteMin);
    }


}


class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public String toString() {
        if (left != null && right != null)
            return "Value: " + value + " | Left: " + left.value + " | Right: " + right.value;
        else
            return "Value: " + value+ " | Left: " + null + " | Right: " + null;
    }
}
