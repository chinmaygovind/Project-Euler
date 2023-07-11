package problems;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import util.Numbers;
import util.Sets;

//Primes with Runs.
public class Problem111 {
    private static long total = 0;
    public static void main(String[] args) {
        for (int d = 0; d < 10; d++) total += S(10, d);
        System.out.println("The total of the ten-digit primes with runs is: " + total);
    }

    public static int M(int n, int d) {
        //n = num digits
        //d = digit to repeat
        int m = n - 1;
        while (m > 0) {
            for (int toAdd = (int) Math.pow(10, n - m - 1); toAdd < Math.pow(10, n - m); toAdd++) {
                if (String.valueOf(toAdd).contains(String.valueOf(d))) continue;
                String base = "";
                for (int k = 0; k < n; k++) base += d;
                for (String p : getPossibleNumbers(String.valueOf(d), base, toAdd)){
                    long possible = Long.parseLong(p);
                    //System.out.println(getPossibleNumbers(String.valueOf(d), base, toAdd));
                    if (Numbers.isPrime(possible) && possible > Math.pow(10, n - 1)){
                        //System.out.println(getPossibleNumbers(String.valueOf(d), base, toAdd));
                        return m;
                    }
                }

            }
            m--;
        }
        return m;
    }

    public static long S(int n, int d) {
        int m = M(n, d);
        HashSet<Long> nums = new HashSet<>();
        for (int toAdd = (int) Math.pow(10, n - m - 1); toAdd < Math.pow(10, n - m); toAdd++) {
            if (String.valueOf(toAdd).contains(String.valueOf(d))) continue;
            String base = "";
            for (int k = 0; k < n; k++) base += d;
            for (String p : getPossibleNumbers(String.valueOf(d), base, toAdd)){
                long possible = Long.parseLong(p);
                //System.out.println(getPossibleNumbers(String.valueOf(d), base, toAdd));
                if (Numbers.isPrime(possible) && possible > Math.pow(10, n - 1)){
                    nums.add(possible);
                }
            }

        }
        return Sets.sumLongs(new ArrayList<>(nums));
    }

    public static ArrayList<String> getPossibleNumbers(String d, String source, int toAdd){
        if (toAdd < 1) return new ArrayList<>(Collections.singletonList(source));
        ArrayList<String> possible = new ArrayList<>();
        for (int i = 0; i < source.length(); i++){
            if (String.valueOf(source.charAt(i)).equals(d))
                possible.addAll(getPossibleNumbers(d, source.substring(0, i) + toAdd%10 + (i <= source.length() - 2 ? source.substring(i + 1) : ""), toAdd/10));
        }
        return possible;
    }
}

