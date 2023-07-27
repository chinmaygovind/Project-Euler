package problems;

import java.util.Arrays;
import java.util.HashMap;

public class Main {

    static HashMap<State, Double> memo = new HashMap<State, Double>();

    public static void main(String[] args) throws Exception {
        memo.put(new State(0, 0, 0, 1), 0.0);
        System.out.println(rec(1, 1, 1, 1));
    }

    static double rec(int c1, int c2, int c3, int c4) {
        State st = new State(c1, c2, c3, c4);
        if (memo.containsKey(st)) {
            return memo.get(st);
        }
        int all = c1 + c2 + c3 + c4;
        double ret = all == 1 ? 1 : 0;
        if (c1 != 0) {
            ret += rec(c1 - 1, c2 + 1, c3 + 1, c4 + 1) * c1 / all;
        }
        if (c2 != 0) {
            ret += rec(c1, c2 - 1, c3 + 1, c4 + 1) * c2 / all;
        }
        if (c3 != 0) {
            ret += rec(c1, c2, c3 - 1, c4 + 1) * c3 / all;
        }
        if (c4 != 0) {
            ret += rec(c1, c2, c3, c4 - 1) * c4 / all;
        }

        memo.put(st, ret);
        return ret;
    }

    static class State {
        int[] c;

        State(int c1, int c2, int c3, int c4) {
            this.c = new int[] { c1, c2, c3, c4 };
        }

        public int hashCode() {
            return Arrays.hashCode(this.c);
        }

        public boolean equals(Object o) {
            if (!(o instanceof State)) return false;
            return Arrays.equals(this.c, ((State) o).c);
        }
    }
}