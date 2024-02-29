package problems;

//Intersections.
public class Problem165 {

    public static void main(String[] args) {
        long[] s = new long[20001];
        int[] t = new int[20001];
        s[0] = 290797; t[0] = (int) (s[0]%500);
        for (int i = 1; i < s.length; i++) {
            s[i] = (s[i - 1] * s[i - 1])%50515093L;
            t[i] = (int) (s[i]%500);
        }
        int intersections = 0;
        System.out.println(intersects(27, 44, 12, 32, 46, 53, 17, 62));
        System.out.println(intersects(46, 70, 22, 40, 46, 53, 17, 62));
        System.out.println(intersects(27, 44, 12, 32, 46, 70, 22, 40));
        for (int l1 = 0; l1 < t.length/8; l1++) {
            int x1 = t[8*l1+1];
            int y1 = t[8*l1+2];
            int x2 = t[8*l1+3];
            int y2 = t[8*l1+4];
            //(y2 - y1)/(x2 - x1)
            //y = m(x - x1) + y1
            for (int l2 = l1+1; l2 < t.length/8; l2++) {
                int x3 = t[8*l1+5];
                int y3 = t[8*l1+6];
                int x4 = t[8*l1+7];
                int y4 = t[8*l1+8];
                intersections += intersects(x1, y1, x2, y2, x3, y3, x4, y4) ? 1 : 0;

            }
        }
        System.out.println(intersections);
    }

    public static boolean intersects(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        double m1 = ((double) (y2 - y1)) / (x2 - x1);
        double m2 = ((double) (y4 - y3)) / (x4 - x3);
        if (m1 == m2) {
            return false;
        }
        //m1(x - x1) + y1 = m2(x - x3) + y3
        //x(m1 - m2) = x1 * m1 -x3 * m2 + y3 - y1
        double x = (x1 * m1 - x3 * m2 + y3 - y1) / (m1 - m2);
        return x > Math.max(Math.min(x1, x2), Math.min(x3, x4)) && x < Math.min(Math.max(x1, x2), Math.max(x3, x4));
    }
}


