package problems;

//Right Triangles With Integer Coordinates.
public class Problem091 {
    private static int workingTriangles = 0;
    private static final int limit = 50;
    public static void main(String[] args) {
        for (int x1 = 0; x1 <= limit; x1++){
            for (int y1 = 0; y1 <= limit; y1++){
                for (int x2 = 0; x2 <= limit; x2++){
                    for (int y2 = 0; y2 <= limit; y2++){
                        if (x1 + y1 == 0 || x2 + y2 == 0 || (x1 == x2 && y1 == y2)) continue;
                        if ((x1 * x1 + y1 * y1) + (x2 * x2 + y2 * y2) == ((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)) ||
                                (x1 * x1 + y1 * y1) + (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1) == (x2 * x2 + y2 * y2) ||
                                (x2 * x2 + y2 * y2) + (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1) == (x1 * x1 + y1 * y1)){
                            workingTriangles++;
                        }
                    }
                }
            }
        }
        workingTriangles/=2;
        System.out.println("The number of right angle integer triangles that can be formed with 0 <= x1, x2, y1, y2 <= 5 is: " + workingTriangles);
    }







}
