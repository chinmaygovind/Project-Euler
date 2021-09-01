package problems;

import util.Files;
import util.Numbers;

import java.util.*;

//Su Doku.
public class Problem096 {
    private static final Scanner fileReader = Files.fileScan("/problem096.txt");
    private static int[][][] grids = new int[50][9][9];
    private static int sum = 0;
    public static void main(String[] args) {
        for (int line = 0; line < 500; line++){
            if (line%10 == 0) {
                fileReader.nextLine();
                continue;
            }
            grids[line/10][line%10 - 1] = Arrays.stream(fileReader.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        for (int[][] grid : grids){
            int[][] solved = solveSudoku(grid);
            sum += solved[0][0] * 100 + solved[0][1] * 10 + solved[0][2];
        }
        System.out.println("The sum of the three-digit numbers in the top left of the fifty Su Doku grids is: " + sum);
    }

    private static int[][] solveSudoku(int[][] board){
        ArrayList<Integer> minCandidates = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        int minCandRow = -1;
        int minCandCol = -1;
        boolean isFull = true;
        for (int row = 0; row < 9; row++){
            for (int col = 0; col < 9; col++){
                if (board[row][col] != 0) {
                    continue;
                } else {
                    isFull = false;
                }

                ArrayList<Integer> candidates = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
                //Remove things already in rows, cols, and bloccs
                for (Integer i : getRow(board, row)){
                    candidates.remove(i);
                }
                for (Integer i : getCol(board, col)){
                    candidates.remove(i);
                }
                for (Integer i : getBlock(board, row, col)){
                    candidates.remove(i);
                }
                if (candidates.size() < minCandidates.size()){
                    minCandidates = candidates;
                    minCandRow = row;
                    minCandCol = col;
                }

            }
        }

        //break condition: if board is already full, return itself.
        if (isFull) return board;
        //if no solutions, return null.
        if (minCandidates.size() == 0) {
            return null;
        }
        while (minCandidates.size() >= 1){
            int[][] newBoard = new int[board.length][board.length];
            for (int row = 0; row < board.length; row++){
                System.arraycopy(board[row], 0, newBoard[row], 0, board.length);
            }
            newBoard[minCandRow][minCandCol] = minCandidates.get(0);
            newBoard = solveSudoku(newBoard);
            if (newBoard != null) return newBoard;
            minCandidates.remove(0);

        }
        return null;

    }

    private static int[] getRow(int[][] board, int row){
        return board[row];
    }

    private static int[] getCol(int[][] board, int col){
        int[] nums = new int[9];
        for (int i = 0; i < 9; i++){
            nums[i] = board[i][col];
        }
        return nums;
    }

    private static int[] getBlock(int[][] board, int rowNum, int colNum){
        int[] nums = new int[9];
        int indx = 0;
        for (int col = colNum - colNum%3; col < colNum - colNum%3 + 3; col++){
            for (int row = rowNum - rowNum%3; row < rowNum - rowNum%3 + 3; row++){
                nums[indx] = board[row][col];
                indx++;
            }
        }
        return nums;
    }





}
