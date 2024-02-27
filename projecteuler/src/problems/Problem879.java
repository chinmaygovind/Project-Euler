package problems;


import util.Numbers;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

//Touch-screen Password.
public class Problem879 {

    private static HashMap<Integer, Set<List<Integer>>> blocks = new HashMap<>();
    private static long fullyFilled;

    private static Set<String> passwords = new HashSet<>();
    public static void main(String[] args) {
        System.out.println(solve(4));
    }

    public static long solve(int n) {
        int[][] grid = new int[n][n];
        for (int i = 0; i < n*n; i++) {
            grid[(i)/n][(i)%n] = i;
            blocks.put(i, new HashSet<>());
        }
        fullyFilled = (1L << (n*n)) - 1;
        for (int start = 0; start < n*n; start++) {
            for (int end = 0; end < n*n; end++) {
                int startRow = start/n;
                int startCol = start%n;
                int endRow = end/n;
                int endCol = end%n;
                List<Integer> path = new ArrayList<>();
                if (startRow == endRow) {
                    if (startCol == endCol) {
                        path.add(end);
                    } else {
                        for (int col = startCol; col != endCol; col += (startCol < endCol ? 1 : -1)) {
                            path.add(grid[startRow][col]);
                        }
                        path.add(end);
                    }
                } else if (startCol == endCol) {
                    for (int row = startRow; row != endRow; row += (startRow < endRow ? 1 : -1)) {
                        path.add(grid[row][startCol]);
                    }
                    path.add(end);
                } else if (endCol - startCol == endRow - startRow) {
                    for (int offset = 0; startRow + offset != endRow; offset += (startRow < endRow ? 1 : -1)) {
                        path.add(grid[startRow + offset][startCol + offset]);
                    }
                    path.add(end);
                } else if (endCol - startCol == -(endRow - startRow)) {
                    for (int offset = 0; startRow + offset != endRow; offset += (startRow < endRow ? 1 : -1)) {
                        path.add(grid[startRow + offset][startCol - offset]);
                    }
                    path.add(end);
                }else {
                    path.add(start);
                    path.add(end);
                }
                path.removeFirst();
                if (!path.isEmpty()) blocks.get(start).add(path);
                
            }
        }

        long total = 0;
        int oldSize = 0;
        for (int start = 0; start < n*n; start++) {
            //System.out.println(blocks.get(start));
            long addition = findPasswords(start, 1L << start, String.valueOf(start));
            //System.out.println(start + ": " + addition);
            System.out.println("new additions: " + start + ": " + (passwords.size() - oldSize));
            oldSize = passwords.size();
            total += addition;
        }


        return passwords.size();
    }

    private static char[] key = "0123456789ABCDEF".toCharArray();
    public static long findPasswords(int start, long filled, String password) {
        long total = 0;
        if (filled == fullyFilled) return 0;
        for (List<Integer> path : blocks.get(start)) {
            //System.out.println(start + " going down" + path + ", having visited " + decodeVisited(filled));
            long newFilled = tryPath(filled, path);
            int end = path.getLast();
            if (newFilled > 0) {
                String newPassword = password;
                passwords.add(password);
                for (int i : path) {
                    if (!password.contains(String.valueOf(key[i]))) {
                        newPassword += key[i];
                    }
                }
                //System.out.println(newPassword);
                total += 1 + findPasswords(end, newFilled, newPassword);
            }
        }
        return total;
    }

    public static long tryPath(long filled, List<Integer> path) {
        if ((filled & (1L << path.getLast())) >= 1) return -1;
        for (int p : path) {
            filled = filled | (1L << p);
        }
        return filled;
    }

    public static String decodeVisited(long visited) {
        ArrayList<Integer> l = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if ((visited & (1L << i)) > 0) l.add(i);
        }
        return l.toString();
    }


}

