package problems;

import util.Coordinate;
import util.Numbers;

import java.util.ArrayList;

//Investigating Multiple Reflections of a Laser Beam.
public class Problem145 {
    private static int reversibles = 0;
    public static void main(String[] args) {
        n:
        for (int n = 1; n < 1000000000; n++) {
            if (n%10 == 0) continue;
            int digits = (int) Math.log10(n) + 1;
            int sum = 0;
            for (int i = 0; i < digits; i++) {
                sum += (int) Math.pow(10, i) *
                        ((n % ((int) Math.pow(10, i + 1)) / ((int) Math.pow(10, i)))
                 + (n % ((int) Math.pow(10, digits - i))) / ((int) Math.pow(10, digits - i - 1)));
            }
            for (int i = 0; i < (int) Math.log10(sum) + 1; i++) {
                if (((sum % (int) Math.pow(10, i + 1))/ (int) Math.pow(10, i))%2 != 1)
                    continue n;
            }
            reversibles++;
        }
        System.out.println(reversibles);
    }

}


