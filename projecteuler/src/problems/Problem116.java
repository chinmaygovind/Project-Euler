package problems;


import java.util.ArrayList;
import java.util.HashMap;

//Red, Green or Blue Tiles.
public class Problem116 {
    public static ArrayList<HashMap<Integer, Long>> cachedCounts = new ArrayList<>();
    public static void main(String[] args) {
        cachedCounts.add(new HashMap<>());
        cachedCounts.add(new HashMap<>());
        cachedCounts.add(new HashMap<>());
        cachedCounts.add(new HashMap<>());
        cachedCounts.add(new HashMap<>());
        System.out.println("The number of ways to replace 50 tiles with red, green or blue tiles is: " + (
                countBlocks(50, 2, true) +
                countBlocks(50, 3, true) +
                countBlocks(50, 4, true)));
    }

    public static long countBlocks(int size, int blockSize, boolean initial) {
        if (size < blockSize) return 1;
        if (size == blockSize) return 2;
        if (cachedCounts.get(blockSize).containsKey(size)) return cachedCounts.get(blockSize).get(size);
        long total = 1;//case where there are no red blocks
        for (int offset = 0; offset <= size - blockSize; offset++) {
            //System.out.println(offset + ", " + blockSize + ": countBlocks( " + (size - offset - blockSize) + ", " + blockSize + ")");
            total += countBlocks(size - offset - blockSize, blockSize, false);

        }
        cachedCounts.get(blockSize).put(size, total);
        return total + (initial ? -1 : 0);
    }
}

