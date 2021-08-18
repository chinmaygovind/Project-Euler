package problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

//Powerful Digit Counts.
public class Problem063 {
    private static Set<Long> numberSet = new HashSet<>();
    public static void main(String[] args) {
        ArrayList<Long> powers = new ArrayList<>();
        for (int base = 0; base < 10; base++) {
            int exponent = 1;
            while (powers.add(pow(base, exponent)) && powers.get(powers.size() - 1).toString().length() >= exponent) {
                if (powers.get(powers.size() - 1).toString().length() == exponent){
                    numberSet.add(powers.get(powers.size() - 1));
                    //System.out.println(base + "^" + exponent + " = " + powers.get(powers.size() - 1) + ", " + powers.get(powers.size() - 1).toString().length() + " digits");
                }
                exponent++;
            }
        }
        System.out.println("There number of n-digit positive integers that are also an n-th power is: " + numberSet.size());
    }

    //Java's built-in Math.pow only returns doubles, I need longs here ;-;
    public static long pow(int base, int exponent){
        if (exponent == 0) return 1;
        return base * pow(base, exponent - 1);
    }

}
