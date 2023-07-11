package problems;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

//Singular Integer Right Triangles.
public class Problem075 {
    private static HashMap<Long, Integer> triplesSums = new HashMap<>();
    private static final int limit = 1500000;
    private static final HashMap<Long, ArrayList<Long>> factorMap = new HashMap<>();
    private static int answer = 0;//15000 took 20447 ms
    public static void main(String[] args) {
        //fill out factor map
        for (long r = 2; r <= limit / 3; r+=2){
            factorMap.put(r*r/2, new ArrayList<>());
        }
        for (long halfSquare : factorMap.keySet()){
            for (int i = 1; i <= Math.sqrt(halfSquare); i++){
                if (halfSquare%i == 0){
                    factorMap.get(halfSquare).add((long) i);
                    factorMap.get(halfSquare).add(halfSquare/i);
                }
            }
        }
        //Uses Dickson's method to find Pythagorean triples: https://en.wikipedia.org/wiki/Formulas_for_generating_Pythagorean_triples#Dickson's_method
        for (long r = 2; r <= limit / 3; r+=2){
            ArrayList<Long> factors = factorMap.get(r * r / 2);
            for (int i = 0; i < factors.size(); i+=2){
                long s = factors.get(i);
                long t = factors.get(i + 1);
                long perimeter = 3 * r + 2 * s + 2 * t;
                if (perimeter <= limit){
                    //System.out.println((r + s) + ", " + (r + t) + ", " + (r + s + t));
                    if (triplesSums.containsKey(perimeter)){
                        triplesSums.put(perimeter, triplesSums.get(perimeter) + 1);
                    } else {
                        triplesSums.put(perimeter, 1);
                    }
                }
            }
        }
        answer = Collections.frequency(triplesSums.values(), 1);
        System.out.println("The number of perimeters of a right triangle that have only one integer solution is: " + answer);;
    }

}
