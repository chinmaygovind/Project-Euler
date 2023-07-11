package problems;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

//Path Sum: Two Ways.
public class Problem083 {
    private static final Scanner fileReader = new Scanner(Objects.requireNonNull(Problem083.class.getResourceAsStream("/problem083.txt")));
    private static final int size = 80;
    private static final int[][] matrix = new int[size][size];
    private static int[][] distanceMatrix = new int[size][size];
    private static ArrayList<Point> unsettled = new ArrayList<>();
    private static ArrayList<Point> settled = new ArrayList<>();
    public static void main(String[] args) {
        int line = 0;
        while (fileReader.hasNextLine()){
            matrix[line] = Stream.of(fileReader.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
            line++;
        }

        //fill up unsettled
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                unsettled.add(new Point(i, j));
                if (i + j == 0){
                    distanceMatrix[i][j] = matrix[i][j];
                } else {
                    distanceMatrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        while (unsettled.size() != 0){
            Point node = new Point();
            int lowestDistance = Integer.MAX_VALUE;
            for (Point potentialNextNode : unsettled){
                if (distanceMatrix[(int) potentialNextNode.getX()][(int) potentialNextNode.getY()] < lowestDistance){
                    lowestDistance = distanceMatrix[(int) potentialNextNode.getX()][(int) potentialNextNode.getY()];
                    node = potentialNextNode;
                }
            }
            ArrayList<Point> neighbors = new ArrayList<>();
            if (node.x < size - 1) neighbors.add(new Point((int) node.getX() + 1, (int) node.getY()));
            if (node.y < size - 1) neighbors.add(new Point((int) node.getX(), (int) node.getY() + 1));
            if (node.x > 0) neighbors.add(new Point((int) node.getX() - 1, (int) node.getY()));
            if (node.y > 0) neighbors.add(new Point((int) node.getX(), (int) node.getY() - 1));
            for (Point neighborNode : neighbors) {
                if (settled.contains(neighborNode)) continue;
                distanceMatrix[(int) neighborNode.getX()][(int) neighborNode.getY()] = Math.min(
                        distanceMatrix[(int) neighborNode.getX()][(int) neighborNode.getY()],
                        matrix[(int) neighborNode.getX()][(int) neighborNode.getY()] +
                                distanceMatrix[(int) node.getX()][(int) node.getY()]);

            }
            settled.add(node);
            unsettled.remove(node);
        }
        System.out.println("The shortest sum that can be obtained by traversing from the top left to the bottom right of the 80x80 martix is: " + distanceMatrix[size - 1][size - 1]);
    }

}
