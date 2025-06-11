package problems;

import util.Numbers;
import util.Rational;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

//Shortest Lattice Vector.
public class Problem507 {

    // we boutta pull out the moore penrose pseudoinverse??
    // nvm that wouldnt work dummy
    // alr we boutta pull out the orthogonal projecto
    private static long[] tribonacci = new long[12];
    private static int MODULO = 10_000_000;
    private static int n = 0;
    private static int N = 20_000_000; // target: 20000000
    public static void main(String[] args) {
        // WTF k Vn + l Wn s.t. close as possible to zero
        // k v1 + l w1 = 0
        // k v2 + l w2 = 0
        // k v3 + l w3 = 0

        // new idea:
        // project W onto V so you have some V parallel part and V perp part
        // V parallel part is good, V perp part is bad
        // because taxicab you dont have to think
        // find k and l that optimizes it
        // k V - lVParallel + lVPerp

        // new idea
        // V and W basically map lattice points 2d plane into some 3d plane
        // ( v1 w1 )          ( x )
        // ( v2 w2 ) ( k ) =  ( y )
        // ( v3 w3 ) ( l )    ( z )

        // want to minimize |x| + |y| + |z|, given k, l integers



        setupTribonacci();
        long totalD = 0;
        double maxRatio = 0;
        while (n <= N) {
//            if (n % 100000 == 0) System.out.println(n);
            // System.out.println(Arrays.toString(tribonacci));
            long v1 = tribonacci[0] - tribonacci[1];
            long v2 = tribonacci[2] + tribonacci[3];
            long v3 = tribonacci[4] * tribonacci[5];
            double vNorm = Math.sqrt(v1*v1 + v2*v2 + ((double) v3)*v3);
            long vTaxicab = Math.abs(v1) + Math.abs(v2) + Math.abs(v3);
            long w1 = tribonacci[6] - tribonacci[7];
            long w2 = tribonacci[8] + tribonacci[9];
            long w3 = tribonacci[10] * tribonacci[11];
            long dotProduct = w1 * v1 + w2 * v2 + w3 * v3;
            double wNorm = Math.sqrt(w1*w1 + w2*w2 + ((double) w3)*w3);
            double theta = Math.acos(dotProduct / (vNorm * wNorm));
            long wTaxicab = Math.abs(w1) + Math.abs(w2) + Math.abs(w3);
            // V = (v1, v2, v3), W = (w1, w2, w3), WTF k, l s.t. D = kVn + lWn is minimized
            // WLOG k > 0
            // project W onto V
            // so for every v i go out
            // i can go proj back but at the expense of an oproj
            // so D = ()
            // if oproj > proj, clearly set k = 1, l = 0 (uh)
            //    vNorm/(proj - oproj) = k/l
            HashSet<List<Long>> candidates = new HashSet<>();
            candidates.add(List.of(1L, 0L));
            candidates.add(List.of(0L, 1L));
            double ratio = wNorm / vNorm;

            ArrayList<Integer> continuedFrac = new ArrayList<>();
            double a = ratio;
            long An2 = 1; long An1 = (long) Math.floor(a); long An = -1;
            long Bn2 = 0; long Bn1 = 1;                    long Bn = -1;
            continuedFrac.add((int) Math.floor(a));
            candidates.add(List.of(An1, -Bn1));
            while(Bn < Long.MAX_VALUE / 100) {
                a = 1 / (a - Math.floor(a));
                continuedFrac.add((int) Math.floor(a));
                An = continuedFrac.getLast() * An1 + An2;
                Bn = continuedFrac.getLast() * Bn1 + Bn2;
                An2 = An1; Bn2 = Bn1;
                An1 = An; Bn1 = Bn;
                // System.out.printf("Target ratio: %f, Current Convergent: %s\n", ratio, new Rational(An, Bn));
                candidates.add(List.of(An, -Bn));
            }

            long minTaxicab = Long.MAX_VALUE;
            long bestK = 0;
            long bestL = 0;
            for (List<Long> candidate : candidates) {
                long k = candidate.get(0);
                long l = candidate.get(1);
                long taxicab = Math.abs(k * v1 + l * w1) + Math.abs(k * v2 + l * w2) + Math.abs(k * v3 + l * w3);
                if (taxicab < minTaxicab && taxicab >= 0) {
                    minTaxicab = taxicab;
                    bestK = k;
                    bestL = l;
                }
            }
            /*
            ArrayList<List<Long>> oldCandidates = new ArrayList<>(candidates);
            if (ratio < 1) {
                for (long k = 1; k < 5000; k++) {
                    double rawL = (vNorm / wNorm) * k;
                    long lowL = (long) Math.floor(-rawL);
                    long highL = (long) Math.ceil(-rawL);
                    candidates.add(List.of(k, lowL));
                    candidates.add(List.of(k, highL));
                }
            } else {
                for (long l = 1; l < 5000; l++) {
                    double rawK = (wNorm / vNorm) * l;
                    long lowK = (long) Math.floor(-rawK);
                    long highK = (long) Math.ceil(-rawK);
                    candidates.add(List.of(lowK, l));
                    candidates.add(List.of(highK, l));
                }
            }
            // System.out.println(candidates.size());

            long minTaxicab2 = Long.MAX_VALUE;
            long bestK2 = 0;
            long bestL2 = 0;
            for (List<Long> candidate : candidates) {
                long k = candidate.get(0);
                long l = candidate.get(1);
                long taxicab2 = Math.abs(k * v1 + l * w1) + Math.abs(k * v2 + l * w2) + Math.abs(k * v3 + l * w3);
                if (taxicab2 < minTaxicab2 && taxicab2 >= 0) {
                    minTaxicab2 = taxicab2;
                    bestK2 = k;
                    bestL2 = l;
                }
            }
            if (minTaxicab2 != minTaxicab) {
                System.out.println(oldCandidates);
                System.out.printf("V%d: (%d, %d, %d) \t| W: (%d, %d, %d) \t| k = %d, l = %d, k2 = %d, l2 = %d, D = %d, ratio = %f, k/l = %f, minTaxiCab2: %d\n", n, v1, v2, v3, w1, w2, w3, bestK, bestL, bestK2, bestL2, minTaxicab, (wNorm/vNorm), (bestK / (double) bestL),  minTaxicab2);

            }
            */

//            if (ratio > maxRatio) {
//                maxRatio = ratio;
//                System.out.printf("V%d: (%d, %d, %d) \t| W: (%d, %d, %d) \t| k = %d, l = %d, D = %d, ratio = %f, k/l = %f, error = %f\n", n, v1, v2, v3, w1, w2, w3, bestK, bestL, minTaxicab, (wNorm/vNorm), (bestK / (double) bestL), (wNorm/vNorm) / ((bestK / (double) bestL)));
//            }
            totalD += minTaxicab;
            updateTribonacci();
        }
        System.out.printf("The sum of S(n) from 1 to %d is: %d\n", N, totalD);




    }

    public static void setupTribonacci() {
        tribonacci[0] = 0;
        tribonacci[1] = 1;
        tribonacci[2] = 1;
        for (int i = 3; i < 12; i++) {
            tribonacci[i] = (tribonacci[i - 3] + tribonacci[i - 2] + tribonacci[i - 1]) % MODULO;
        }
        n++;

    }

    public static void updateTribonacci() {
        tribonacci[0] = (tribonacci[9] + tribonacci[10] + tribonacci[11]) % MODULO;
        tribonacci[1] = (tribonacci[10] + tribonacci[11] + tribonacci[0]) % MODULO;
        tribonacci[2] = (tribonacci[11] + tribonacci[0] + tribonacci[1]) % MODULO;
        for (int i = 3; i < 12; i++) {
            tribonacci[i] = (tribonacci[i - 3] + tribonacci[i - 2] + tribonacci[i - 1]) % MODULO;
        }
        n++;
    }



}

/**
 V1: (-1, 3, 28) 	| W: (-11, 125, 40826) 	| k = 1, l = 0, D = 32, ratio = 1448.867369, k/l = Infinity, error = 0.000000
 V2: (-423, 4841, 61192712) 	| W: (-16377, 187427, 91726117848) 	| k = -1499, l = 1, D = 9444372, ratio = 1498.971276, k/l = -1499.000000, error = -0.999981
 V3: (-634061, 7256527, 51034179551824) 	| W: (5451345, 10947697, 27019957959298) 	| k = 854, l = -1613, D = 23647158760, ratio = 0.529448, k/l = -0.529448, error = -1.000000
 V4: (-439251, 7325813, 1519968928764) 	| W: (2270355, 12538567, 29598175385728) 	| k = -1071, l = 55, D = 20675140680, ratio = 19.472882, k/l = -19.472727, error = -1.000008
 V5: (-1173049, 9367867, 237701895700) 	| W: (-1162067, 14748389, 18844122092410) 	| k = -872, l = 11, D = 18306629846, ratio = 79.276280, k/l = -79.272727, error = -1.000045
 V6: (9853281, 4679201, 44315896299552) 	| W: (5887647, 9624427, 1493453856824) 	| k = 49, l = -1454, D = 24831608026, ratio = 0.033700, k/l = -0.033700, error = -1.000001
 V7: (70075, 8629831, 716483308840) 	| W: (-2177911, 5373913, 16896746865570) 	| k = -283, l = 12, D = 6237736254, ratio = 23.582890, k/l = -23.583333, error = -0.999981
 V8: (178293, 4278893, 12154975012020) 	| W: (980523, 16393615, 5447271356736) 	| k = 121, l = -270, D = 15441562054, ratio = 0.448152, k/l = -0.448148, error = -1.000008
 V9: (-5898737, 7657075, 27026998408396) 	| W: (-658203, 12575053, 10243649512890) 	| k = 177, l = -467, D = 10858176662, ratio = 0.379015, k/l = -0.379015, error = -1.000001
 V10: (196713, 12592857, 16460435886840) 	| W: (-986697, 10452915, 373318457304) 	| k = 11, l = -485, D = 10754817036, ratio = 0.022680, k/l = -0.022680, error = -0.999970
 Total: 130762273722
 *
 * N = 1000 -> Total <= 15785611955222?
 * N = 20000 -> Total <= 318657435896808?
 */