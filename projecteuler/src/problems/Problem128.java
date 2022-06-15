package problems;


import util.Numbers;

import java.util.ArrayList;
import java.util.Collections;

//Hexagonal Tile Differences.
public class Problem128 {
    /*
    private static final ArrayList<Tile> tiles = new ArrayList<>();
    private static final ArrayList<Long> hexgaonals = Geometry.generateHexagonals(1000);
    public static void main(String[] args) {
        long counter = 1;
        long surrounded = 1;
        tiles.add(new Tile(1));
        while (surrounded <= 100000) {
            //add new tile to current row
            Tile t = tiles.get(surrounded-1);
            //loop thru tiles neighbor's
            boolean placed = false;
            for (long pos = t.isAnnoying() ? 3 : 6; pos >= (t.isAnnoying() ? -3 : 1); pos--) {
                if (t.getNeighbor((pos+6)%6) == null) {
                    counter++;
                    Tile t2 = new Tile(counter);
                    t.addNeighbor(t2, (pos+6)%6, 0);
                    tiles.add(t2);
                    placed = true;
                } else {
                    if (placed) break;
                }
                //System.out.prlongln(surrounded + ": " + hexagon);
            }
            surrounded++;
        }
        ArrayList<Long> pd3 = new ArrayList<>();
        long n = 1;
        while (n < surrounded) {
            try {
                if (tiles.get(n - 1).PD() == 3) pd3.add(tiles.get(n - 1).getValue());
                n++;
            } catch (NullPolongerException e) {
                break;
            }
        }
        System.out.prlongln(pd3);
    }
     */
    private static ArrayList<Long> middle = new ArrayList<>(Collections.singletonList(1L));
    private static ArrayList<Long> right = new ArrayList<>();
    private static ArrayList<Long> left = new ArrayList<>();
    private static ArrayList<Long> pd3 = new ArrayList<>();
    public static void main(String args[]) {
        //populate top+side list
        middle.add(2L);
        while (middle.size()<100000) {
            middle.add(middle.get(middle.size()-1) + (middle.size()-1) * 6);
            right.add(middle.get(middle.size()-1)-1);
            left.add(middle.get(middle.size()-2)+1);
        }
        int i = 0;
        while (pd3.size() < 2020) {
            if (middlePD(middle.get(i)) == 3) pd3.add(middle.get(i));
            if (rightPD(right.get(i)) == 3) pd3.add(right.get(i));
            i++;
        }
        Collections.sort(pd3);
        System.out.println("The 2000th tile in the sequence for which PD(n) = 3 is: " + pd3.get(1999));
    }

    public static long middlePD(long n) {
        if (n == 1) return 3;
        if (!middle.contains(n)) return 0;
        int row = middle.indexOf(n);
        int pd = 0;
        if (Numbers.isPrime(n - middle.get(row - 1))) pd++;
        if (Numbers.isPrime(-(n - middle.get(row + 1)))) pd++;
        if (Numbers.isPrime(Math.abs(n - right.get(row)))) pd++;
        if (Numbers.isPrime(Math.abs(n - right.get(row - 1)))) pd++;
        if (Numbers.isPrime(Math.abs(n - left.get(row)))) pd++;
        if (Numbers.isPrime(Math.abs(n - left.get(row - 1)))) pd++;
        return pd;
    }
    public static long rightPD(long n) {
        if (n == 7) return 2;
        if (!right.contains(n)) return 0;
        int row = right.indexOf(n);
        int pd = 0;
        if (Numbers.isPrime(n - (n - 1))) pd++;
        if (Numbers.isPrime(n - middle.get(row))) pd++;
        if (Numbers.isPrime(n - middle.get(row + 1))) pd++;
        if (Numbers.isPrime(right.get(row + 1) - n)) pd++;
        if (Numbers.isPrime((right.get(row + 1) - 1) - n)) pd++;
        if (Numbers.isPrime(n - right.get(row - 1))) pd++;
        return pd;
    }

}

/*
class Tile {
    private final Tile[] neighbors = new Tile[6];
    private final long value;
    private boolean annoying = false;

    public Tile(long value) {
        this.value = value;
        if (value == 7) annoying = true;
    }

    public void addNeighbor(Tile neighbor, long pos, long depth) {
        if (depth > 2) return;
        if (annoying && depth == 0) neighbor.annoying = true;
        neighbors[pos] = neighbor;
        neighbor.setNeighbor(this, (pos+3)%6);
        //if current node has neighbor adjacent to new neighbor, and new neighbor does not have
        //adjacent neighbor updated, update new neighbor
        if (neighbors[(pos+5)%6] != null && neighbors[(pos+5)%6].getValue() != neighbor.getValue()) {
            neighbor.addNeighbor(neighbors[(pos+5)%6], (pos+4)%6, depth + 1);
            neighbors[(pos+5)%6].addNeighbor(neighbor, (pos+1)%6, depth + 1);
        }
        if (neighbors[(pos+1)%6] != null && neighbors[(pos+1)%6].getValue() != neighbor.getValue()) {
            neighbor.addNeighbor(neighbors[(pos+1)%6], (pos+2)%6, depth + 1);
            neighbors[(pos+1)%6].addNeighbor(neighbor, (pos+5)%6, depth + 1);
        }
    }

    public void setNeighbor(Tile neighbor, long pos) {
        neighbors[(pos+6)%6] = neighbor;
    }

    public long getValue() { return this.value; }
    public Tile getNeighbor(long pos) { return neighbors[(pos+6)%6]; }

    public boolean isAnnoying() { return annoying; }

    public long PD() {
        long primeDifferences = 0;
        for (long i = 0; i < 6; i++)
            primeDifferences += Numbers.isPrime(Math.abs(getValue() - neighbors[i].getValue())) ? 1 : 0;
        return primeDifferences;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Tile{value=" + getValue() + ", neighbors={");
        for (long i = 0; i < 6; i++)
            sb.append((getNeighbor(i) != null ? getNeighbor(i).getValue() : 0)).append(i == 5 ? "}}" : ",");
        return sb.toString();
    }
}
*/

