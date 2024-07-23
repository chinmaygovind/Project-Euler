package problems;

import util.Numbers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

//Hollow Square Laminae I.
public class Problem173 {

    //Laminae numbers:
    // 1,  2,  3,  4,  5,
    //12, 20, 28, 36, 44, ...
    //l(n) = 8n + 4
    //from a to b:
    //(b - a + 1) * (8a + 4) + (b - a) * (b - a + 1) / 2 * 8
    //4(b + 1)^2 - 4a^2

    //yeah i overthunk it
    public static void main(String[] args) {
        long total = 0;
        for (int a = 1; a < 1000000; a++) {
            for (int b = a + 2; b < 1000001; b+=2) {
                if ((b + a) * (b - a) <= 1000000) {
                    total++;
                } else {
                    break;
                }
            }
        }
        System.out.println("The number of hollow square laminae that can be formed with up to 1,000,000 tiles is: " + total);
    }


}
