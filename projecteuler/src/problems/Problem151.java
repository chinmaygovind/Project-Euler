package problems;


import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

//A Preference for A5.
public class Problem151 {
    //they changed the name of this problem??? hah
    private static int START = 16;
    private static MathContext mc = new MathContext(50, RoundingMode.FLOOR);
    public static void main(String[] args) {
        //System.out.println("approximation: " + monteCarloPick(START));
        System.out.println("The expected number of single sheets is: " + 
        new DecimalFormat("#0.000000").format(pick(new ArrayList<>(List.of(START)), 0, 1)));
        
    }

    
    //returns 0.46130365304324899113588392374841740738797443540710944670064 for 16
    //i hate this game
    //i needed to make the denominator parameter a long
    //i'm done
    //i'm never using ints again
    public static BigDecimal pick(ArrayList<Integer> papers, int depth, long denominator) {
        //System.out.println(papers + ", odds at 1/" + denominator);
        //System.out.println(papers + " | odds at 1/" + denominator);
        BigDecimal total = BigDecimal.ZERO;
        if (papers.size() == 1 && depth != (START - 1) && depth != 0) {
            total = total.add(new BigDecimal(1).divide(new BigDecimal(denominator), mc));
        }
        for (int picked : new LinkedHashSet<>(papers)) {
            //System.out.println("TOTAL: " + total);
            ArrayList<Integer> temp = new ArrayList<>(papers);
            temp.remove((Integer) picked);
            for (int newPaper = 1; newPaper < picked; newPaper *= 2) {
                temp.add(newPaper);
            }
            //if (depth == 1) System.out.println(picked);
            total = total.add(
                pick(temp, depth + 1, denominator*papers.size())
                .multiply(new BigDecimal(Collections.frequency(papers, picked))));

        }
        return total;
    }

    public static int randomPick(int start) {
        ArrayList<Integer> papers = new ArrayList<>(List.of(start));
        int total = 0;
        while (papers.size() > 0) {
            if (papers.size() == 1 && papers.get(0) != start && papers.get(0) != 1) total++;
            Integer toRemove = papers.get((int) (Math.random() * papers.size()));
            papers.remove((Integer) toRemove);
            for (int i = 1; i < toRemove; i = i << 1) {
                papers.add(i);
            }
            //System.out.println(papers);
        }
        return total;
    }

    public static double monteCarloPick(int start) {
        double total = 0;
        int ITERATIONS = 1000000;
        for (int i = 0; i < ITERATIONS; i++) {
            //if (i%100000 == 0) System.out.println(i);
            total += randomPick(start);
        }
        return total/ITERATIONS;
    }
}



