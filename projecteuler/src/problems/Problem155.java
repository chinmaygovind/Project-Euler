package problems;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import util.Rational;

//Counting Capacitor Circuits.
public class Problem155 {
    
    private static int N = 18;
    public static void main(String[] args) {

        HashMap<Integer, HashSet<Rational>> circuits = new HashMap<>();
        circuits.put(1, new HashSet<>(Collections.singletonList(new Rational(60))));

        for (int n = 2; n <= N; n++) {
            circuits.put(n, new HashSet<>());
            for (int c1 = 1; c1 <= n/2; c1++) {
                int c2 = n - c1;
                for (Rational capacitance1 : circuits.get(c1)) {
                    for (Rational capacitance2 : circuits.get(c2)) {
                        circuits.get(n).add(capacitance1.add(capacitance2));
                        circuits.get(n).add(capacitance1.multiply(capacitance2).divide(capacitance1.add(capacitance2)));
                    }
                }
            }
            System.out.println(n + ": " + circuits.get(n).size());
        }
        HashSet<Rational> uniqueValues = new HashSet<>();
        for (int n = 1; n <= N; n++) {
            uniqueValues.addAll(circuits.get(n));
        }
        System.out.println("The number of unique capacitances using up to 18 60 microFarad capacitors is: " + uniqueValues.size());
    }
 }



