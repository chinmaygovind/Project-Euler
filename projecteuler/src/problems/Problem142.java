package problems;

import java.util.ArrayList;

//Perfect Square Collection.
public class Problem142 {
    //x + y + z, x > y > z > 0
    //perf: x + y, x - y, x + z, x - z, y + z, y - z

    private static ArrayList<Long> squares = new ArrayList<>();
    private static boolean found = false;
    public static void main(String[] args) {
        for (long i = 0; i < 1000; i++) {
            squares.add(i*i);
        }
        for (int j = 1; j < 1000 && !found; j++) {
            for (int k = j + 2; k < 1000 && !found; k+=2) {
                long x = (squares.get(k) + squares.get(j))/2;
                long y = squares.get(k) - x;
                for (int l = 1; l < j; l++) {
                    long z = x - squares.get(l);
                    if (squares.contains(x + z) && squares.contains(y + z) && squares.contains(Math.abs(y - z))) {
                        System.out.println("The sum of the smallest square collection is: " + (x + y + z));
                        found = true;
                        break;
                    }
                }
            }
        }
    }
}

