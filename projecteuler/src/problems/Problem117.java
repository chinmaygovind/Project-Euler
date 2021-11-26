package problems;


import java.util.ArrayList;
import java.util.HashMap;

//Red, Green and Blue Tiles.
public class Problem117 {
    public static HashMap<Integer, Long> cachedCounts = new HashMap<>();
    public static void main(String[] args) {
        //System.out.println("The number of ways to replace 50 tiles with red, green or blue tiles is: " + (
         //       countBlocks(5, 2, true) +
          //      countBlocks(5, 3, true) +
           //     countBlocks(5, 4, true)));
        System.out.println(countBlocks(50));
    }

    public static long countBlocks(int size) {
        if (cachedCounts.containsKey(size)) return cachedCounts.get(size);
        long total = 1;//include the null case
        for (int offset = 0; offset <= size - 2; offset++) {
            for (int blockSize = 2; blockSize <= Math.min(size - offset, 4); blockSize++) {
                total += countBlocks(size - offset - blockSize);
            }

        }
        cachedCounts.put(size, total);
        return total;
    }
}

