package problems;


import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.*;

// St. Petersburg Lottery.
public class Problem499 {

    private static MathContext mc = new MathContext(1000);

    public static DecimalFormat format = new DecimalFormat("0.#####E0");
    private static BigDecimal PROBABILITY_THRESHOLD = BigDecimal.TEN.pow(-700, mc);
    public static void main(String[] args) {
        // System.out.println(p(2, 2, 10000)); // 0.2522
        System.out.println(BigDecimal.ONE.subtract(notP(6, 10_000)));
        System.out.println(cachedNotP);
        //System.out.println(p(2, 5, 10000)); // 0.6873
        //System.out.println(p(6, 10_000, 10000)); // 0.9952
    }
    //EV = -m + 1 * 1 + 2 * 0.5 + 4 * 0.25 + 8 * 0.125 + 16 * 0.0625 + ... = infinite.
    public static double p(int m, long s, int trials) {
        double wins = 0;
        int step = trials / 100;
        for (int i = 1; i <= trials; i++) {
            if (i % step == 0) {
                System.out.printf("Trial %d / %d\r", i, trials);
            }
            wins += play(m, s, 1000) ? 1 : 0;
        }
        System.out.println();
        return wins / trials;
    }

    public static boolean play(int m, long s, long mult) {
        Random r = new Random();
        long money = s;
        while (m <= money && money <= m * mult) {
            money -= m;
            int pot = 1;
            while (r.nextBoolean()) {
                pot = pot << 1;
            }
            money += pot;
        }
        return money > m;
    }

    // chance of going broke
    private static HashMap<Integer, HashMap<Long, Double>> cachedNotP = new HashMap<>();

    public static BigDecimal notP(int m, long s) {
        PriorityQueue<Entry> queue = new PriorityQueue<>();
        HashMap<Long, BigDecimal> elements = new HashMap<>();
        BigDecimal p = BigDecimal.ONE;
        BigDecimal totalNotP = BigDecimal.ZERO;
        queue.add(new Entry(s, p));
        elements.put(s, p);
        long iters = 0;
        while (!queue.isEmpty()) {
            iters++;
            // System.out.println(queue);
            Entry head = queue.poll();
            if (iters % 1000 == 0) System.out.println(head + ", " + format.format(totalNotP) + ", " + format.format(PROBABILITY_THRESHOLD));
            elements.remove(head.money);
            long money = head.money;
            BigDecimal prob = head.p;
            BigDecimal newP = prob.divide(BigDecimal.TWO, mc);
            long spentMoney = money - m;
            long pot = 1;
            long nextMoney = spentMoney + pot;
            while (newP.compareTo(PROBABILITY_THRESHOLD) > 0 && nextMoney < 100 * s) {
                // System.out.println(nextMoney + ": " + newP);
                if (nextMoney < m) {
                    totalNotP = totalNotP.add(newP);
                } else {
                    if (elements.containsKey(nextMoney)) {
                        queue.remove(new Entry(nextMoney, elements.get(nextMoney)));
                    }
                    elements.put(nextMoney, elements.getOrDefault(nextMoney, BigDecimal.ZERO).add(newP));
                    queue.add(new Entry(nextMoney, elements.get(nextMoney)));
                }
                newP = newP.divide(BigDecimal.TWO, mc);
                pot = pot << 1;
                nextMoney = spentMoney + pot;
            }
        }

        return totalNotP;
    }

}

class Entry implements Comparable<Entry> {
    public long money;
    public BigDecimal p;

    public Entry(long money, BigDecimal p) {
        this.money = money;
        this.p = p;
    }

    @Override
    public int compareTo(Entry o) {
        return Double.compare(this.money + p.toString().length(), o.money + p.toString().length());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Entry o)) {
            return false;
        }
        return (this.money == o.money && this.p.equals(o.p));
    }

    @Override
    public String toString() {
        return String.format("%d: %s", money, Problem499.format.format(p));
    }
}

