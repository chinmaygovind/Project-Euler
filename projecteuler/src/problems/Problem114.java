package problems;


import util.Numbers;

import java.util.HashMap;

//Counting Blocks Combinations I.
public class Problem114 {
    public static HashMap<Integer, Long> cachedCounts = new HashMap<>();
    public static void main(String[] args) {
        System.out.println(countBlocks(50, 3));
    }

    public static long countBlocks(int size, int minSize) {
        if (size < minSize) return 1;
        if (size == minSize) return 2;
        if (cachedCounts.containsKey(size)) return cachedCounts.get(size);
        long total = 1;//case where there are no red blocks
        for (int offset = 0; offset <= size - minSize; offset++) {
            for (int blockSize = minSize; blockSize <= size - offset; blockSize++) {
                //System.out.println(offset + ", " + blockSize + ": countBlocks( " + (size - offset - blockSize - 1) + ", " + minSize + ")");
                total += countBlocks(size - offset - blockSize - 1, minSize);
            }
        }
        cachedCounts.put(size, total);
        return total;
    }
}

