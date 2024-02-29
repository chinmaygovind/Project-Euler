package problems;

import util.Timer;

import java.util.*;

//Three Consecutive Digital Sum Limit.
public class Problem164 {

    public static void main(String[] args) {
        long[] last2 = new long[100];
        int[] last2sums = new int[100];
        for (int i = 0; i < 100; i++) {
            last2[i] = 1;
            last2sums[i] = i/10 + i%10;
        }
        int n = 20;
        for (int digits = 2; digits < n; digits++) {
            long[] newlast2 = new long[100];
            for (int i = 0; i < 100; i++) {
                for (int newD = 0; newD < 10; newD++) {
                    if (last2sums[i] + newD <= 9) {
                        newlast2[10 * newD + i/10]+= last2[i];
                    }
                }
            }
            last2 = newlast2;
        }
        long total = 0;
        for (int i = 10; i < 100; i++) total += last2[i];
        System.out.printf("The number of 20-digit numbers with no 3 consecutive digits totalling over 9 is: %d\n", total);

    }


    public static long count(int N) {
        long low = (long) Math.pow(10, N - 1);
        long high = (long) Math.pow(10, N);
        long total = 0;
        for (long i = low; i < high; i++) {
            if (checkNum(i)) total++;
        }
        return total;
    }

    public static boolean checkNum(long n) {
        long temp = n;
        while (temp > 99) {
            long last3 = (temp%10) + (temp/10)%10 + (temp/100)%10;
            if (last3 > 9) return false;
            temp /= 10;
        }
        return true;
    }
}


