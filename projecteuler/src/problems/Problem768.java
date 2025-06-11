package problems;


import util.Numbers;

import java.util.*;
import java.util.stream.Collectors;

// Chandelier.
public class Problem768 {
    // iiiiiiiiii wanna swiiiiiiiiing


    // for whatever reason, f(n, m) symmetry about m/2 breaks down at n = 28.

    // f30 computed using python script that leverages CUDA gpu teehee
    /**
     * import numpy as np
     * import time
     * from numba import cuda, int32, float32, complex64
     * import math
     *
     * # Generate cexps on CPU and pass it to GPU
     * def generate_cexps(n):
     *     cexps = np.zeros(n, dtype=np.complex64)
     *     for i in range(n):
     *         cexps[i] = np.exp(2j * np.pi * i / n)
     *     return cexps
     *
     * # CUDA kernel to count valid subsets
     * @cuda.jit
     * def count_zero_sums(n, cexps, result):
     *     idx = cuda.grid(1)
     *     limit = 1 << n
     *     if idx >= limit:
     *         return
     *
     *     z = 0j
     *     count = 0
     *     for i in range(n):
     *         if idx & (1 << i):
     *             z += cexps[i]
     *             count += 1
     *
     *     if abs(z) < 0.00001:
     *         cuda.atomic.add(result, count, 1)
     *
     * # Run for each n
     * last = time.time()
     * for n in range(1, 100):  # You can increase the range
     *     total_subsets = 1 << n
     *
     *     # Generate cexps on CPU and move to device
     *     cexps_host = generate_cexps(n)
     *     cexps_dev = cuda.to_device(cexps_host)
     *
     *     # Allocate result array on device
     *     result_dev = cuda.to_device(np.zeros(n + 1, dtype=np.int32))
     *
     *     threads_per_block = 128
     *     blocks_per_grid = (total_subsets + threads_per_block - 1) // threads_per_block
     *
     *     # Launch kernel
     *     count_zero_sums[blocks_per_grid, threads_per_block](n, cexps_dev, result_dev)
     *
     *     # Copy result back
     *     counts = result_dev.copy_to_host()
     *
     *     dur = time.time() - last
     *     last = time.time()
     *     print(n, counts.tolist(), dur)
     */
    private static int[] f30 = new int[]{1, 0, 15, 10, 105, 126, 525, 780, 2055, 3060, 5955, 8010, 12285, 14190, 17715, 17190, 17715, 14190, 12285, 8010, 5955, 3060, 2055, 780, 525, 126, 105, 10, 15, 0, 1};
    private static List<Integer> primes = List.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
    private static HashMap<Integer, HashMap<Integer, Long>> cachedF = new HashMap<>();

    public static void main(String[] args) {
        /**
        Set<BitSet> fourteens = f(30, 14, new BitSet());
        System.out.println("computed f(30, 14)");
        ArrayList<BitSet> sixteens = new ArrayList<>(f(30, 16, new BitSet()));
        ArrayList<BitSet> sixteensFlipped = new ArrayList<>();
        for (BitSet b : sixteens) {
            BitSet flipped = (BitSet) b.clone();
            flipped.flip(0, 30);
            sixteensFlipped.add(flipped);
        }
        System.out.println("computed f(30, 16)");
        for (int i = 0; i < sixteens.size(); i++) {
            BitSet b = sixteens.get(i);
            BitSet flipped = sixteensFlipped.get(i);
            if (!fourteens.contains(flipped)) {
                System.out.println(b + ", " + flipped);
            }
        }
        **/

        int n = 90;
        for (int m = 0; m <= 20; m++) {
            System.out.printf("sneakyF(%d, %d): %d\n", n, m, sneakyF(n, m, 3));
        }
        n = 360;
        for (int m = 0; m <= 20; m++) {
            System.out.printf("sneakyF(%d, %d): %d\n", n, m, sneakyF(n, m, 4));
        }
        // that's disgusting
        /**
         * f(24, 0): 1
         * f(24, 1): 0
         * f(24, 2): 12
         * f(24, 3): 8
         * f(24, 4): 66
         * f(24, 5): 72
         * f(24, 6): 244
         * f(24, 7): 288
         * f(24, 8): 639
         * f(24, 9): 704
         * f(24, 10): 1152
         * f(24, 11): 1104
         * f(24, 12): 1420
         * f(24, 13): 1104
         * f(24, 14): 1152
         * f(24, 15): 704
         * f(24, 16): 639
         * f(24, 17): 288
         */
        /**
         * f(24, 0): 1
         * f(24, 1): 0
         * f(24, 2): 12
         * f(24, 3): 8
         * f(24, 4): 66
         * f(24, 5): 72
         * f(24, 6): 244
         * f(24, 7): 288
         * f(24, 8): 639
         * f(24, 9): 704
         * f(24, 10): 1152
         * f(24, 11): 1104
         * f(24, 12): 1420
         * f(24, 13): 1104
         * f(24, 14): 1152
         * f(24, 15): 704
         * f(24, 16): 639
         * f(24, 17): 288
         * f(24, 18): 244
         * f(24, 19): 72
         */
        // 6:
        // A: 3 x 2 -> 180choose3
        // B: 2 x 3 -> 120choose2
        // A N B: 1 x 6 -> 60choose1
        // total= A + B - ANB = 962940 correct

        // 7:
        // 2 x 2 + 1 x 3 -> 120 * 177choose2
        // 1 x 2 + 1 x 5 -> 72 * 175
        // no overlap
        // total: 1881720

        // 8:
        // A: 4 x 2 -> 180choose4
        // B: 1 x 2 + 2 x 3 -> 120choose2 + 180choose1
        // ANB: 1x2 + 1x6 -> 60choose1 * 179choose1
        // C: 1x3 + 1x5 -> 72choose1 * 115choose1
        // ANC: 0
        // BNC:
        // total = A + B - ANB + C = 43579545
        //
        // 20:
        // 10 x 2
        // 7 x 2 + 2 x 3
        // 6 x 2 + 1 x 3 + 1 x 5
        // 5 x 2 + 2 x 5
        // 4 x 2 + 4 x 3
        // 3 x 2 + 3 x 3 + 1 x 5
        // 2 x 2 + 2 x 3 + 2 x 5
        // 1 x 2 + 6 x 3
        // 1 x 2 + 1 x 3 + 3 x 5
        // 5 x 3 + 1 x 5
        // 4 x 5

        // with n = 60
        // okay so PIE is working wonders
        // m = 02 -> 30choose1 = 30
        // m = 03 -> 20choose1 = 20
        // m = 04 -> 30choose2 = 435
        // m = 05 -> (1x2 + 1x3 | 1x5) 20 * 27 + 12 = 552
        // m = 06 -> (3x2 | 2x3) = 30choose3 + 20choose2 - 10choose1 = 4240
        // m = 07 -> (2x2 + 1x3 | 1x2 + 1x5) = 20 * 27choose2 + 12 * 25 = 7320
        // m = 08 -> (4x2 | 1x2 + 2x3 | 1x3 + 1x5) = 30choose4 + 20choose2 * 24 - 10 * 24 + 12 * 15 = 31905
        // m = 09 -> (3x2 + 1x3 | 2x2 + 1x5 | 3x3) = 20 * 27choose3 + 12 * 25choose2 + 20choose3 - 10 * 18 = 63060
        // m = 10 -> (5x2 | 2x2 + 2x3 | 1x2 + 1x3 + 1x5 | 2x5)
        // A: 30choose5
        // B: 20choose2 * 24choose2
        // ANB: 10 * 24choose2
        // C: 12 * 15 * 22
        // D: 12choose2
        // AND: 6
        // total = 30choose5 + 20choose2 * 24choose2 - 10 * 24choose2 + 12 * 15 * 22 + 12choose2 - 6
        /**
         * 0, 4, 8, 1, 7, 3, 9
         * 2, 5, 6, 10, 11
         */
        /**
         * f(60, 2): 30
         * f(60, 3): 20
         * f(60, 4): 435
         * f(60, 5): 552
         * f(60, 6): 4240
         * f(60, 7): 7320
         * f(60, 8): 31905
         * f(60, 9): 63060
         * f(60, 10): 196266
         * f(60, 11): 399960
         * f(60, 12): 1005555
         * f(60, 13): 1992300
         * f(60, 14): 4316100
         * f(60, 15): 8075760
         * f(60, 16): 15578565
         * f(60, 17): 27173220
         */
    }


    /**
     * The number of ways of arranging m identical candles in distinct sockets of a chandelier with
     * n candleholders such that the chandelier is perfectly balanced.
     * @param n # of candleholders (size of chandelier)
     * @param m # of candles
     * @return # of arrangements
     */
    public static long f(int n, int m) {
        // symmetry assumption is fucked at n=30- no clue why.
        if (n == 30) {
            return f30[m];
        }
        if (cachedF.containsKey(n) && cachedF.get(n).containsKey(m)) {
            return cachedF.get(n).get(m);
        }
        long total = f(n, m, new BitSet(m)).size();
        if (!cachedF.containsKey(n)) {
            cachedF.put(n, new HashMap<>());
        }
        cachedF.get(n).put(m, total);
        return total;
    }

    public static long sneakyF(int n, int m, int divisions) {
        if ((n / divisions) % 30 != 0) {
            throw new IllegalArgumentException("prolly cooked");
        }
        // assumes n is a multiple of... 4?
        int targetN = n / divisions;
        // precache true f's
        long total = sneakyRecurse(n, m, targetN);
        if (!cachedF.containsKey(n)) {
            cachedF.put(n, new HashMap<>());
        }
        cachedF.get(n).put(m, total);
        return total;

    }

    public static long sneakyRecurse(int n, int mRemaining, int targetN) {
        if (mRemaining == 0 || n == 0) {
            return 1;
        }
        if (n == targetN) {
            return f(targetN, mRemaining);
        }
        long total = 0;
        for (int m = 0; m <= mRemaining; m++) {
            total += f(targetN, m) * sneakyRecurse(n - targetN, mRemaining - m, targetN);
        }
        return total;
    }

    public static Set<BitSet> f(int n, int m, BitSet chandelier) {
        BitSet mine = new BitSet(30);
        mine.set(0);
        mine.set(1);
        mine.set(5);
        mine.set(6);
        mine.set(10);
        mine.set(11);
        mine.set(12);
        mine.set(13);
        mine.set(16);
        mine.set(17);
        mine.set(20);
        mine.set(21);
        mine.set(23);
        mine.set(27);
        mine.set(28);
        mine.set(29);
        BitSet mine2 = new BitSet(30);
        mine.set(0);
        mine.set(1);
        mine.set(5);
        mine.set(6);
        mine.set(10);
        mine.set(11);
        mine.set(12);
        mine.set(13);
        mine.set(16);
        mine.set(17);
        mine.set(20);
        mine.set(21);
        mine.set(23);
        mine.set(27);
        mine.set(28);
        mine.set(29);
        mine2.flip(0, 30);
        // System.out.printf("f(%d, %d, %s)\n", n, m, chandelier);
        if (n <= 0 || m < 0) {
            throw new IllegalArgumentException(String.format("Invalid parameters, n = %d, m = %d, chandelier=%s", n, m, chandelier));
        } else if (m == 0) {
            return Collections.singleton(chandelier);
        } else if (m == 1) { // assuming n not equal 1
            return new HashSet<>();
        }
        HashSet<BitSet> chandelierSet = new HashSet<>();
        for (int toPlace : primes) {
            if (n % toPlace != 0 || toPlace > m) {
                continue;
            }
            int gap = n / toPlace;
            for (int start = 0; start < gap; start++) {
                BitSet newChandelier = (BitSet) chandelier.clone();
                boolean spaceAvailable = true;
                for (int i = 0; i < toPlace; i++) {
                    if (newChandelier.get((i * gap + start) % n)) {
                        spaceAvailable = false;
                        break;
                    } else {
                        newChandelier.set((i * gap + start) % n);
                    }
                }
                if (spaceAvailable) {
                    chandelierSet.addAll(f(n, m - toPlace, newChandelier));
                }
            }
        }
        return chandelierSet;
    }

    public static List<List<Integer>> getAllCombos(int m) {
        List<List<Integer>> combos = new ArrayList<>();
        for (int twos = 0; twos <= m/2; twos++) {
            for (int threes = 0; threes <= m/3; threes++) {
                for (int fives = 0; fives <= m/5; fives++) {
                    if (2 * twos + 3 * threes + 5 * fives == m) {
                        combos.add(List.of(twos, threes, fives));
                    }
                }
            }
        }
        Collections.reverse(combos);
        return combos;
    }

}

