package problems;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

//Path Sum: Two Ways.
public class Problem082 {
    private static final Scanner fileReader = new Scanner(Objects.requireNonNull(Problem082.class.getResourceAsStream("/problem082.txt")));
    private static final int size = 80;
    private static final int[][] matrix = new int[size][size];
    private static final int[][] minMatrix = new int[size][size];
    private static int minEnd = -1;
    public static void main(String[] args) {
        int line = 0;
        while (fileReader.hasNextLine()){
            matrix[line] = Stream.of(fileReader.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
            line++;
        }
        for (int j = 0; j < size; j++){//loop through each column
            for (int i = 0; i < size; i++){//loop through each entry in column
                if (j == 0) {
                    minMatrix[i][j] = matrix[i][j];
                    continue;
                }
                int minPathSum = -1;
                for (int k = 0; k < size; k++){//loop through the path from each previous entry in the column to the left
                    int pathSum = matrix[i][j] + minMatrix[k][j - 1];
                    for (int k2 = k; k2 != i; k2 += (k2 < i ? 1 : -1)){
                        pathSum += matrix[k2][j];
                    }
                    if (pathSum < minPathSum || minPathSum == -1) minPathSum = pathSum;
                }
                minMatrix[i][j] = minPathSum;
            }
        }

        //find minimum value on right side
        for (int i = 0; i < size; i++){
            if (minMatrix[i][size - 1] < minEnd || minEnd == -1) minEnd = minMatrix[i][size - 1];
        }
        System.out.println("The minimal sum that can be obtained from the left column to the right column of the 80x80 martix is: " +minEnd);

    }

}
