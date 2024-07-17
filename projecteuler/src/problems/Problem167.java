package problems;

import util.Sets;

import java.util.*;

//Investigating Ulam Sequences.
public class Problem167 {

    //From https://doi.org/10.1016/0097-3165(92)90042-S
    private static Map<Integer, Integer> periods = Map.of(
        5, 32,
        7, 26,
        9, 444,
        11, 1628,
        13, 5906,
        15, 80,
        17, 126960,
        19, 380882,
        21, 2097152
    );

    private static Map<Integer, Integer> fundamental_differences = Map.of(
            5, 126,
            7, 126,
            9, 1778,
            11, 6510,
            13, 23622,
            15, 510,
            17, 507842,
            19, 1523526,
            21, 8388606
    );
    public static void main(String[] args) {
        long total = 0;
        for (int n = 2; n <= 10; n++) {
            total += U(2, 2 * n + 1, 100_000_000_000l);
        }
        System.out.println("The sum of U(2, 2n + 1) of k, k = 10^11, from n = 2 to 10 is: " + total);
    }

    /*
        From https://en.wikipedia.org/wiki/Ulam_number#Generalizations:
        The idea can be generalized as (u, v)-Ulam numbers by selecting different starting values (u, v).
        A sequence of (u, v)-Ulam numbers is regular if the sequence of differences between consecutive
        numbers in the sequence is eventually periodic. When v is an odd number greater than three, the
        (2, v)-Ulam numbers are regular.
     */
    public static long U(int a, int b, long k) {
        ArrayList<Long> sequence = new ArrayList<>();
        sequence.add((long) a);
        sequence.add((long) b);
        sequence.add((long) a + b);
        long next = a + b + 1;
        int needed_terms = (int) (k % periods.get(b)) - 1 + periods.get(b);
        boolean foundBothEvens = false;//hyper specificing
        int secondEven = 2 * b + 2;
        HashSet<Long> sequenceSet = new HashSet<>();
        while (sequenceSet.size() <= needed_terms) {
            int ways = 0;
            if (!foundBothEvens) {
                for (Long el : sequence) {
                    if (sequence.contains(next - el) && next != 2 * el) {
                        ways++;
                    }
                    if (ways > 2) break;
                }
                if (ways == 2) {
                    sequence.add(next);
                    if (next%2 == 0) {
                        foundBothEvens = true;
                        sequenceSet.addAll(sequence);
                    }
                }
                next++;
            } else {
                if (sequenceSet.contains(next - 2)) ways++;
                if (sequenceSet.contains(next - secondEven)) ways++;
                if (ways == 1) sequenceSet.add(next);
                next += 2;
            }
        }
        System.out.printf("Solving U(%d, %d) for k = %d\n", a, b, k);
        //System.out.println(sequence);
        sequence = new ArrayList<>(sequenceSet);
        Collections.sort(sequence);
        long base_ans = sequence.get(needed_terms);
        System.out.println("Period: " + periods.get(b));
        long num_periods = k / periods.get(b) - 1;
        base_ans += num_periods * fundamental_differences.get(b);
        System.out.println("Result: " + base_ans);
        return base_ans;

    }

}
