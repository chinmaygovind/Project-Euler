package problems;

import java.awt.*;
import java.awt.geom.Point2D;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

//Triominoes.
public class Problem161 {

    private static final int N = 6;
    private static final HashMap<String, Long> patterns = new HashMap<>();


    public static void main(String[] args) {
        //all it took was memoization
        //gosh dang it
        System.out.println("The number of ways to tile a 9x12 board with trinominoes is: " + fillBoard(new Board(9, 12)));
    }

    public static long fillBoard(Board b) {
        //System.out.println(b + "\n" + b.getPattern());
        if (patterns.containsKey(b.getPattern())) return patterns.get(b.getPattern());
        long solutions = 0;
        Point firstEmpty = b.firstEmpty();
        Point rect = b.emptyRectangle();
        if (!Objects.isNull(firstEmpty)) {
            for (Tile t : Tile.baseTiles) {
                //System.out.println("adding " + t + " at " + firstEmpty);
                Board newBoard = b.addTile(new Tile(t), firstEmpty.x, firstEmpty.y);
                if (!Objects.isNull(newBoard)) {
                    solutions += fillBoard(newBoard);
                }
            }
        } else {
            //System.out.println("filled board!!!! " + b);
            //System.out.println(b);
            solutions++;
        }
        patterns.put(b.getPattern(), solutions);
        return solutions;

    }
}

//I I I = 1 * T(n - 1)
//
class Board {
    ArrayList<Tile> tiles;
    int width;
    int height;
    public boolean[][] points;
    Board(int width, int height) {
        this.width = width;
        this.height = height;
        points = new boolean[height][width];
        tiles = new ArrayList<>();
    }

    boolean isOccupied(int x, int y) {
        return points[y][x];
    }

    public Board addTile(Tile t, int x, int y) {
        Board newBoard = new Board(this.width, this.height);
        for (int h = 0; h < height; h++)
            newBoard.points[h] = Arrays.copyOf(this.points[h], this.width);
        t.setOrigin(x, y);
        for (Point p : t.getPoints()) {
            try {
                if (newBoard.points[y + p.y][x + p.x])
                    return null;
                else {
                    newBoard.points[y + p.y][x + p.x] = true;
                }
            } catch (IndexOutOfBoundsException e) {
                return null;
            }
        }
        newBoard.tiles.addAll(this.tiles);
        newBoard.tiles.add(t);
        return newBoard;
    }

    public Point firstEmpty() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (!isOccupied(j, i)) return new Point(j, i);
            }
        }
        return null;
    }


    public Point emptyRectangle() {
        Point firstEmpty = this.firstEmpty();
        if (Objects.isNull(firstEmpty)) return null;
        Point lastEmpty = new Point(firstEmpty);
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (!isOccupied(j, i)) {
                    lastEmpty.x = Math.max(lastEmpty.x, j);
                    lastEmpty.y = Math.max(lastEmpty.y, i);
                }
            }
        }
        for (int i = firstEmpty.y; i <= lastEmpty.y; i++) {
            for (int j = firstEmpty.x; j <= lastEmpty.x; j++) {
                if (isOccupied(j, i)) return null;
            }
        }
        if (width == 0 || height == 0) return null;
        return new Point(width, height);
    }

    public String getPattern() {
        StringBuilder s = new StringBuilder();
        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                if (points[row][col]) s.append(1);
                else s.append(0);
            }
        }
        return s.toString();
    }

    public String toString() {
        int[][] points = new int[height][width];
        int i = 1;
        for (Tile t : this.tiles) {
            for (Point p : t.getPoints()) {
                points[t.getOrigin().y + p.y][t.getOrigin().x + p.x] = i;
            }
            i++;
        }
        return Arrays.deepToString(points);
    }
}

class Tile {
    private Point[] points = new Point[3];
    private Point origin;

    private static final Tile L1 = new Tile(new Point(0, 0), new Point(0, 1), new Point(1, 1));//L
    private static final Tile L2 = new Tile(new Point(0, 0), new Point(0, 1), new Point(1, 0));//Γ
    private static final Tile L3 = new Tile(new Point(0, 0), new Point(1, 0), new Point(1, 1));//7
    private static final Tile L4 = new Tile(new Point(0, 0), new Point(0, 1), new Point(-1, 1));//⅃

    private static final Tile I1 = new Tile(new Point(0, 0), new Point(0, 1), new Point(0, 2));//|
    private static final Tile I2 = new Tile(new Point(0, 0), new Point(1, 0), new Point(2, 0));//-

    public static Tile[] baseTiles = new Tile[]{L1, L2, L3, L4, I1, I2};


    Tile(Point p1, Point p2, Point p3) {
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
    }

    Tile(Tile t) {
        this.points = t.getPoints();
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    public void setOrigin(int x, int y) {
        this.origin = new Point(x, y);
    }

    public Point getOrigin() {
        return origin;
    }

    public Point[] getPoints() {
        return points;
    }

    public String toString() {
        String[] names = new String[]{"L", "Γ", "7", "⅃", "|", "-"};
        for (int i = 0; i < 6; i++) {
            if (baseTiles[i].equals(this)) return names[i];
        }
        return "none";
    }
    public boolean equals(Tile o) {
        for (int i = 0; i < 3; i++) {
            if (this.getPoints()[i] != o.getPoints()[i]) return false;
        }
        return true;
    }
}

