package problems;

import java.text.DecimalFormat;

//Almost Equilateral Triangles.
public class Problem094 {
    private static long perimeters = 0;
    private static final int limit = 1000000000;
    public static void main(String[] args) {
        for (int m = 1; m <= Math.sqrt(limit); m++){
            for (int n = 1; n < m; n++){
                int a = m * m - n * n;
                int b = 2 * m * n;
                int c = m * m + n * n;
                if (Math.abs(2 * a - c) == 1){
                    if (2 * a + 2 * c < limit) perimeters += 2 * a + 2 * c;
                }
                if (Math.abs(2 * b - c) == 1){
                    if (2 * b + 2 * c < limit) perimeters += 2 * b + 2 * c;
                }
            }
        }
        System.out.println("The sum of the perimeters of all \"almost equilateral\" triangles with a perimeter below one billion is: " + DecimalFormat.getInstance().format(perimeters).replace(",", ""));
    }







}
