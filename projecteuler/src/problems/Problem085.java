package problems;

//Counting Rectangles.
public class Problem085 {
    private static int closestSolution = 0;
    private static long closestArea = 0;
    private static final int target = 2000000;
    public static void main(String[] args) {
        int width = 1;
        int rectangles;
        while (width < 2200){
            int height = width;
            rectangles = countRectangles(width, height);
            while (rectangles < target + 100000) {
                height++;
                rectangles = countRectangles(width, height);
                if (Math.abs(closestSolution - target) > Math.abs(rectangles - target)){
                    closestSolution = rectangles;
                    closestArea = (long) width * height;
                }
            }
            width++;
        }
        System.out.println("The area of the rectangle that has the number of sub-rectangles closest to 2 million is: " + closestArea);

    }

    public static int countRectangles(int width, int height){
        int total = 0;
        for (int i = 1; i <= width; i++){
            for (int j = 1; j <= height; j++){
                total += i * j;
            }
        }
        return total;
    }



}
