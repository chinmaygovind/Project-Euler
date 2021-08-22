package problems;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

//Path Sum: Two Ways.
public class Problem081 {
    private static final Scanner fileReader = new Scanner(Objects.requireNonNull(Problem081.class.getResourceAsStream("/problem081.txt")));
    private static int[][] matrix = new int[80][80];
    public static void main(String[] args) {
        int line = 0;
        while (fileReader.hasNextLine()){
            matrix[line] = Stream.of(fileReader.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
            line++;
        }
        for (int i = 0; i < 80; i++){
            for (int j = 0; j < 80; j++){
                if (i > 0 && j > 0){
                    matrix[i][j] += Math.min(matrix[i - 1][j], matrix[i][j - 1]);
                } else if (i > 0){
                    matrix[i][j] += matrix[i - 1][j];
                } else if (j > 0){
                    matrix[i][j] += matrix[i][j - 1];
                }
            }
        }
        System.out.println("The minimal sum that can be obtained by traversing down and right through the 80x80 martix is: " + matrix[79][79]);

    }

}
