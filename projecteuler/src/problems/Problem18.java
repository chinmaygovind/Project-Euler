package problems;

import java.util.Arrays;

public class Problem18 {

    private static final int[][] triangle = new int[][] {
            {75},
            {95, 64},
            {17, 47, 82},
            {18, 35, 87, 10},
            {20,  4, 82, 47, 65},
            {19,  1, 23, 75,  3, 34},
            {88,  2, 77, 73,  7, 63, 67},
            {99, 65,  4, 28,  6, 16, 70, 92},
            {41, 41, 26, 56, 83, 40, 80, 70, 33},
            {41, 48, 72, 33, 47, 32, 37, 16, 94, 29},
            {53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14},
            {70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57},
            {91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48},
            {63, 66,  4, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31},
            { 4, 62, 98, 27, 23,  9, 70, 98, 73, 93, 38, 53, 60,  4, 23}};

    private static int maxTotal = 0;
    private static int[] maxPath;

    public static void main(String[] args){
        for (int path = 0; path < 16384; path++){
            int currentTotal = 0;
            int currentIndex = 0;
            int[] currentPath = new int[15];
            int currentRow = 0;
            String moves = Integer.toBinaryString(path);
            while (moves.length() <= 14){
                moves = "0" + moves;
            }
            for (char move:moves.toCharArray()) {
                currentIndex += Integer.parseInt(String.valueOf(move));
                currentTotal += triangle[currentRow][currentIndex];
                currentPath[currentRow] = triangle[currentRow][currentIndex];
                currentRow++;

            }
            if (currentTotal > maxTotal){
                maxTotal = currentTotal;
                maxPath = currentPath;
            }
        }

        System.out.println("The highest total by moving down the triangle is: " + maxTotal);
        System.out.println("The highest total path is: " + Arrays.toString(maxPath));
    }
}
