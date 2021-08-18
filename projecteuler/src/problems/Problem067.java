package problems;

import java.util.*;

//Diophantine Equation.
public class Problem067 {
    private static Scanner fileReader = new Scanner(Objects.requireNonNull(Problem067.class.getResourceAsStream("/problem67.txt")));
    private static int[][] triangle = new int[100][100];
    private static int answer = 0;
    public static void main(String[] args) {
        int i = 0;
        while (fileReader.hasNextLine()){
            int j = 0;
            for (String num : fileReader.nextLine().split(" ")){
                triangle[i][j] = Integer.parseInt(num);
                j++;
            }
            i++;
        }
        for (int line = 1; line < 100; line++){
            for (int j = 0; j <= line; j++){
                if (j == 0){
                    triangle[line][j] = triangle[line - 1][j] + triangle[line][j];
                } else if (j == line){
                    triangle[line][j] = triangle[line - 1][j - 1] + triangle[line][j];
                } else {
                    triangle[line][j] = Math.max(triangle[line - 1][j], triangle[line - 1][j - 1]) + triangle[line][j];
                }
            }
        }
        for (int j = 0; j < 100; j++){
            if (triangle[99][j] > answer){
                answer = triangle[99][j];
            }
        }
        System.out.println("The largest sum that can be obtained by traversing down the triangle is: " + answer);
    }



}
