package problems;

import util.Numbers;

import java.util.HashSet;
import java.util.Objects;

// Integer Angled Quadrilaterals.
public class Problem177 {
    public static void main(String[] args) {
        // # of non-similar integer angled quadrilaterals.
        // whaddahelly
        // a, b, c, d, e, f, g, h
        // can tune a, b
        // c fixes h = 180 - (a + b) - c
        // can vary d, g, but then f = 180 - a - (g + h), e = 180 - b - (c + d), and then d + e + f + g = 180
        // 8 variables
        // all add to 360
        // 4 subtriangles add to 180
        // 3 free variables, no?
        // x1, x2, x3, x4, x5, x6, x7, x8
        // x1 + x2 + x3 + x4 + x5 + x6 + x7 + x8 = 360 - repeat condition. probably 2 of them are
        // x1 + x2 + x3 + x4 = 180
        // x3 + x4 + x5 + x6 = 180
        // x5 + x6 + x7 + x8 = 180
        // x1 + x2 + x7 + x8 = 180
        // x1 + x2 = x5 + x6
        // x3 + x4 = x7 + x8
        // vary x1, x2, x8 -> fix x7. -> vary x5 -> fix x6 -> vary x3 -> fix x4
        // x1 + x2 <= 180, etc
        HashSet<Quad> sols = new HashSet<>();
        System.out.println(sols.size());
        boolean[][][][] solsArray = new boolean[180][180][180][180];
        for (int x1 = 1; x1 <= 180; x1++) {
            System.out.println(x1);
            for (int x2 = 1; x1 + x2 < 180; x2++) {
                for (int x8 = 1; x1 + x2 + x8 < 180; x8++) {
                    int x7 = 180 - x1 - x2 - x8;
                    for (int x5 = 1; x5 + x7 + x8 < 180; x5++) {
                        int x6 = 180 - x5 - x7 - x8;
                        for (int x3 = 1; x1 + x2 + x3 < 180; x3++) {
                            int x4 = 180 - x1 - x2 - x3;
//                            System.out.printf("%d, %d, %d, %d, %d, %d, %d, %d\n",
//                                    x1, x2, x3, x4, x5, x6, x7, x8);
//                            sols.add(new Quad(x8+x1, x2+x3, x4+x5, x6+x7));
                            solsArray[x8+x1-1][x2+x3-1][x4+x5-1][x6+x7-1] = true;
                        }
                    }
                }
            }
        }
        for (int a = 0; a < 180; a++) {
            for (int b = 0; b < 180; b++) {
                for (int c = 0; c < 180; c++) {
                    for (int d = 0; d < 180; d++) {
                        if (solsArray[a][b][c][d]) {
                            sols.add(new Quad(a+1, b+1, c+1, d+1));
                        }
                    }
                }
            }
        }
        System.out.println(sols.size() + " quads");
    }


}

class Quad {
    int a, b, c, d;
    public Quad(int a, int b, int c, int d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Quad)) return false;
        Quad o = (Quad) obj;
        return (a == o.a && b == o.b && c == o.c && d == o.d) ||
                (a == o.b && b == o.c && c == o.d && d == o.a) ||
                (a == o.c && b == o.d && c == o.a && d == o.b) ||
                (a == o.d && b == o.a && c == o.b && d == o.c) ||
                (a == o.d && b == o.c && c == o.b && d == o.a) ||
                (a == o.c && b == o.b && c == o.a && d == o.d) ||
                (a == o.b && b == o.a && c == o.d && d == o.c) ||
                (a == o.a && b == o.d && c == o.c && d == o.b);
    }

    @Override
    public int hashCode() {
        return a * c + b * d;
    }

    @Override
    public String toString() {
        return String.format("Quad(%d, %d, %d, %d)", a, b, c, d);
    }
}
