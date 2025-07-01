package problems;


import util.Sets;

import java.util.ArrayList;
import java.util.List;

// ovo sign

// 2025.
public class Problem932 {
    // low hanging fruit
    private static long sqrtN = 100000000;
    public static void main(String[] args) {
        long sum = 0;
        for (long i = 4; i <= sqrtN; i++) {
            if (i % 9 > 1) continue;
            long i2 = i * i;
            String i2String = String.valueOf(i2);
            long first = Long.parseLong(i2String.substring(0, i2String.length()/2));
            long second = Long.parseLong(i2String.substring(i2String.length()/2));
            if (first + second == i && String.valueOf(first).length() + String.valueOf(second).length() == i2String.length()) {
                System.out.printf("%d = (%d + %d)^2\n", i2, first, second);
                sum += i2;
            }
            if (i2String.length() <= 3) continue;
            first = Long.parseLong(i2String.substring(0, i2String.length()/2 - 1));
            second = Long.parseLong(i2String.substring(i2String.length()/2 - 1));
            if (first + second == i && String.valueOf(first).length() + String.valueOf(second).length() == i2String.length()) {
                System.out.printf("%d = (%d + %d)^2\n", i2, first, second);
                sum += i2;
            }
            first = Long.parseLong(i2String.substring(0, i2String.length()/2 + 1));
            second = Long.parseLong(i2String.substring(i2String.length()/2 + 1));
            if (first + second == i && String.valueOf(first).length() + String.valueOf(second).length() == i2String.length()) {
                System.out.printf("%d = (%d + %d)^2\n", i2, first, second);
                sum += i2;
            }
        }
        System.out.println("The sum of all 2025 numbers with at most 16 digits is: " + sum);
    }
}

