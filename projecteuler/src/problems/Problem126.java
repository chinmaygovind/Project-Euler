package problems;


import util.Sets;

import java.awt.*;
import java.util.*;
import java.util.List;

//Cuboid Layers.
public class Problem126 {
    private static final int limit = 150;
    private static final int shellsLimit = 20000;
    public static void main(String[] args) {
        HashMap<Integer, Integer> frequencies = new HashMap<>();
        for (int x = 1; x < shellsLimit; x++) {
            for (int y = x; y < shellsLimit; y++) {
                if (2 * (x * y) > shellsLimit) break;
                List<Integer> shells = generateShells(x, y);
                for (int z = y; z < shellsLimit; z++) {
                    if (2 * (x * y + x * z + y * z) > shellsLimit) break;
                    for (int n = 1; n < Math.min(limit/2, shells.size() - 1); n++) {
                        int sum = 2 * Sets.sum(shells.subList(0, n)) + shells.get(n) * z;
                        if (!frequencies.containsKey(sum)) frequencies.put(sum, 1);
                        else frequencies.put(sum, frequencies.get(sum) + 1);
                    }
                }
            }
        }
        assert frequencies.get(22) == 2;
        assert frequencies.get(46) == 4;
        assert frequencies.get(78) == 5;
        assert frequencies.get(118) == 8;
        assert frequencies.get(154) == 10;

        int theKing = Integer.MAX_VALUE;
        for (Integer layerCount : frequencies.keySet()) {
            if (frequencies.get(layerCount) == 1000 && layerCount < theKing) theKing = layerCount;
        }
        System.out.println("The minimum n where C(n) = " + frequencies.get(theKing) + " is: " + theKing);
    }



    public static List<Integer> generateShells(int x, int y) {
        //if (cachedShells.containsKey(new Point(x, y))) return cachedShells.get(new Point(x, y));
        ArrayList<Integer> shells = new ArrayList<>();
        for (int i = 0; i <= limit/2; i++) {
            shells.add((x + 2 * i) * (y + 2 * i) - 2 * i * (i + 1) - Sets.sum(shells));
            if (shells.get(shells.size()-1) > shellsLimit) break;
        }
        //cachedShells.put(new Point(x, y), shells);
        return shells;
    }
}

