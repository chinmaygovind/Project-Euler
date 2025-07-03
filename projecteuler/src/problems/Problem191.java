package problems;

import util.Numbers;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;

// Prize Strings.
public class Problem191 {
    private static int N = 30;
    private static int[][][] strings = new int[N + 1][2][3];
    // [0] = no late, [1] = late
    // [][0] = no absence, [][1] = 1 absence, [][2] = 2 absence
    public static void main(String[] args) {
        strings[0][0][0] = 1; // 0-len string, only the no late, no absence string has 1
        for (int i = 1; i <= N; i++) {
            //[0][0] - no late, no absence. can either add Good, Late, or Absent
            strings[i][0][0] += strings[i - 1][0][0]; // good
            strings[i][1][0] += strings[i - 1][0][0]; // late
            strings[i][0][1] += strings[i - 1][0][0]; // absent
            //[0][1] - no late, 1 absence. can either add Good, Late, or Absent
            strings[i][0][0] += strings[i - 1][0][1]; // good
            strings[i][1][0] += strings[i - 1][0][1]; // late
            strings[i][0][2] += strings[i - 1][0][1]; // absent
            //[0][2] - no late, 2 absence. can either add Good, Late
            strings[i][0][0] += strings[i - 1][0][2]; // good
            strings[i][1][0] += strings[i - 1][0][2]; // late
            //[1][0] - yes late, no absence. can either add Good, Absent
            strings[i][1][0] += strings[i - 1][1][0]; // good
            strings[i][1][1] += strings[i - 1][1][0]; // absent
            //[1][1] - yes late, 1 absence. can either add Good,  Absent
            strings[i][1][0] += strings[i - 1][1][1]; // good
            strings[i][1][2] += strings[i - 1][1][1]; // absent
            //[1][1] - yes late, 2 absence. can only add Good
            strings[i][1][0] += strings[i - 1][1][2]; // good
        }
        int d = 0;
        for (int[][] day : strings) {
            System.out.println(d + ": " + Arrays.deepToString(day));
            d++;
        }
        int total = strings[N][0][0] +
                strings[N][0][1] +
                strings[N][0][2] +
                strings[N][1][0] +
                strings[N][1][1] +
                strings[N][1][2];
        System.out.println(total);
    }


}
