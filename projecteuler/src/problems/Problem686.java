package problems;

import java.math.BigInteger;

// Powers of Two.
public class Problem686 {
    BigInteger smallShift = BigInteger.TWO.pow(196);
    BigInteger largeShift = BigInteger.TWO.pow(289);
    public static void main(String[] args) {
        int j = 90;
        BigInteger n = BigInteger.TWO.pow(j);
        int idx = 1;
        int last = 0;
        while (idx < 678910) {
            n = new BigInteger(n.toString().substring(0, 20));
            n = n.shiftLeft(196);
            j += 196;
            if (n.toString().startsWith("123")) {
                idx++;
//                System.out.printf("%d: 2^%d = %d\n", idx, j, n);
                continue;
            }
            n = n.shiftLeft(93); // 289 - 196
            j += 93;
            if (n.toString().startsWith("123")) {
                idx++;
//                System.out.printf("%d: 2^%d = %d\n", idx, j, n);
                continue;
            }
            n = n.shiftLeft(196); // 485 - 289
            j += 196;
            if (n.toString().startsWith("123")) {
                idx++;
//                System.out.printf("%d: 2^%d = %d\n", idx, j, n);
            } else {
                System.out.println("fuckidee doo");
                break;
            }
        }
        System.out.println("p(123, 678910) = " + j);
    }


}
