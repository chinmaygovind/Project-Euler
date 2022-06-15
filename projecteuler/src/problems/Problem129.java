package problems;


import util.Numbers;

import java.math.BigInteger;

//Repunit Divisibility.
public class Problem129 {
    private static final int LIMIT = 1000000;
    public static void main(String[] args) {
        int n = LIMIT + 1;
        while (A(n) < LIMIT)
            n++;
        System.out.println(n);


    }

    private static int A(int n) {
        if (n%2 == 0 || n%5 == 0) return 0;
        int residue = 1;
        int a = 1;
        while (residue != 0) {
            a++;
            residue = (10 * residue + 1)%n;
        }
        return a;
    }

}

