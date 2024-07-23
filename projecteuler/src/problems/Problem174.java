package problems;

import java.util.HashMap;

//Hollow Square Laminae II.
public class Problem174 {

    //Laminae numbers:
    // 1,  2,  3,  4,  5,
    //12, 20, 28, 36, 44, ...
    //l(n) = 8n + 4
    //from a to b:
    //(b - a + 1) * (8a + 4) + (b - a) * (b - a + 1) / 2 * 8
    //4(b + 1)^2 - 4a^2

    //yeah i overthunk it
    public static void main(String[] args) {
        HashMap<Integer, Integer> laminae = new HashMap<>();
        for (int a = 1; a < 1000000; a++) {
            for (int b = a + 2; b < 1000001; b+=2) {
                if ((b + a) * (b - a) <= 1000000) {
                    int n = (b + a) * (b - a);
                    laminae.put(n, laminae.getOrDefault(n, 0) + 1);
                } else {
                    break;
                }
            }
        }
        int total = 0;
        for (int t : laminae.keySet()) {
            if (laminae.get(t) <= 10) total++;
        }
        System.out.println("The number of t <= 1,000,000 such that at most 10 distinct laminae can be formed with t tiles is: " + total);
    }


}
