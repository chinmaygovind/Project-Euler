package problems;


import java.util.HashMap;

//Counting Blocks Combinations II.
public class Problem115 {
    public static HashMap<Integer, Long> cachedCounts = new HashMap<>();
    private static int n = 50;
    private static int m = 50;
    public static void main(String[] args) {
        while (countBlocks(n, m) < 1000000) {
            n++;
        }
        System.out.println("The smallest value of n where F(n, 50) > 1000000 is: " + n);
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

