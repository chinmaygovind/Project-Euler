package problems;

import util.Numbers;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashSet;

// Flea Circus.
public class Problem213 {
    private static BigDecimal[][] grid = new BigDecimal[30][30];

    private static MathContext mc = new MathContext(100);

    private static DecimalFormat df = new DecimalFormat("#.#######E0");

    private static BigDecimal[][][][] fleaGrids = new BigDecimal[30][30][30][30];

    public static void main(String[] args) {
        for (int fleaRow = 0; fleaRow < grid.length; fleaRow++) {
            for (int fleaCol = 0; fleaCol < grid.length; fleaCol++) {
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid.length; j++) {
                        grid[i][j] = BigDecimal.ZERO;
                    }
                }
                grid[fleaRow][fleaCol] = BigDecimal.ONE;
                for (int i = 0; i < 50; i++) {
                    ringBell();
                }
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid.length; j++) {
                        fleaGrids[fleaRow][fleaCol][i][j] = grid[i][j];
                    }
                }
            }
        }
        BigDecimal expectedEmpty = BigDecimal.ZERO;
        for (int cellRow = 0; cellRow < grid.length; cellRow++) {
            for (int cellCol = 0; cellCol < grid.length; cellCol++) {
                BigDecimal unoccupied = BigDecimal.ONE;
                for (int fleaRow = 0; fleaRow < grid.length; fleaRow++) {
                    for (int fleaCol = 0; fleaCol < grid.length; fleaCol++) {
                        BigDecimal probEmpty = fleaGrids[fleaRow][fleaCol][cellRow][cellCol];
                        unoccupied = unoccupied.multiply(BigDecimal.ONE.subtract(probEmpty));
                    }
                }
//                System.out.printf("Analyzing flea[%d][%d]: %s\n", cellRow, cellCol, df.format(unoccupied));
                expectedEmpty = expectedEmpty.add(unoccupied);
            }
        }
        System.out.printf("The expected number of empty squares after 50 rings of the bell is: %.6f", expectedEmpty);

    }

    public static void ringBell() {
        BigDecimal[][] newGrid = new BigDecimal[30][30];
        for (int i = 0; i < newGrid.length; i++) {
            for (int j = 0; j < newGrid.length; j++) {
                newGrid[i][j] = BigDecimal.ZERO;
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j].equals(BigDecimal.ZERO)) continue;
                int neighbors = 4;
                if (i == 0 || i == grid.length - 1) neighbors--;
                if (j == 0 || j == grid.length - 1) neighbors--;
                BigDecimal fleas = grid[i][j].divide(BigDecimal.valueOf(neighbors), mc);
                if (i > 0) {
                    newGrid[i - 1][j] = newGrid[i - 1][j].add(fleas);
                }
                if (j > 0) {
                    newGrid[i][j - 1] = newGrid[i][j - 1].add(fleas);
                }
                if (i < grid.length - 1) {
                    newGrid[i + 1][j] = newGrid[i + 1][j].add(fleas);
                }
                if (j < grid.length - 1) {
                    newGrid[i][j + 1] = newGrid[i][j + 1].add(fleas);
                }
            }
        }
        grid = newGrid;
    }


}
