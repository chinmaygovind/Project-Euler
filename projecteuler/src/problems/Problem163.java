package problems;

import util.Numbers;

import java.util.*;

//Cross-hatched Triangles.
public class Problem163 {

    public static void main(String[] args) {
        //level 1: (0, 0), (2, 0), (4, 0), (1, 3), (3, 3), (2, 6), (2, 2)
        //upside down: (0, 0), (2, 0), (4, 0), (1, -3), (3, -3), (2, -6), (1, -2)
        //lines:
        //sides: y = 6n, y = 3x + 6n, y = -3x + 6n
        //thru: y = 2x + 4n, y = -2x + 4n, x = n

        int N = 1;
        HashSet<Intersection> intersections = new HashSet<>();
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N - row; col++) {
                int startX = 4 * col + 2 * row;
                int startY = row * 6;
                intersections.add(new Intersection(startX, startY));
                int[][] points = {{2, 0}, {4, 0}, {1, 3}, {3, 3}, {2, 6}, {2, 2}};
                for (int[] p : points) {
                    intersections.add(new Intersection(startX + p[0], startY + p[1]));
                    if (row > 0) intersections.add(new Intersection(startX + p[0], startY - p[1]));
                }
            }
        }
        System.out.println("All points: " + intersections);
        ArrayList<Intersection> intersectionList = new ArrayList<>(intersections);
        HashMap<Intersection, List<Intersection>> graph = new HashMap<>();
        for (int i = 0; i < intersectionList.size(); i++) {
            graph.put(intersectionList.get(i), new ArrayList<>());
            for (int j = 0; j < intersectionList.size(); j++) {
                if (i == j) continue;
                if (intersectionList.get(i).connectsTo(intersectionList.get(j))) {
                    graph.get(intersectionList.get(i)).add(intersectionList.get(j));
                }
            }
            System.out.println(intersectionList.get(i) + ": " + graph.get(intersectionList.get(i)));
            System.out.println(intersectionList.get(i) + ": " + Arrays.toString(intersectionList.get(i).lines));
        }
    }

}

class Intersection {
    public int x;
    public int y;

    //x, y, y - 3x, y + 3x, y - 2x, y + 2x
    //sides: y = 6n, y = 3x + 12n, y = -3x + 12n
    //thru: y = x + 4n, y = -x + 4n, x = n
    public int[] lines = new int[6];
    public Intersection(int x, int y) {
        this.x = x;
        this.y = y;
        this.lines[0] = x;
        this.lines[1] = y;
        this.lines[2] = (y - 3 * x)%12 == 0 ? y - 3 * x : -1;
        this.lines[3] = (y + 3 * x)%12 == 0 ? y + 3 * x : -1;
        this.lines[4] = (y - x)%4 == 0 ? y - x : -1;
        this.lines[5] = (y + x)%4 == 0 ? y + x : -1;
    }

    public boolean connectsTo(Intersection o) {
        for (int i = 0; i < 6; i++) {
            if (lines[i] == o.lines[i]) return true;
        }
        return false;
    }

    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

}

