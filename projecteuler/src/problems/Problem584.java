package problems;


import util.Numbers;

import java.util.Arrays;
import java.util.Random;

// Birthday Problem Revisited.
public class Problem584 {

    public static void main(String[] args) {
        // System.out.println(stochasticBirthday(3, 1, 10, 1000000)); = 5.78688636
        // System.out.println(stochasticBirthday(3, 7, 100, 10000000)); = 8.48967364
        System.out.println(stochasticBirthday(4, 7, 365, 1000000)); // = 32.839655 += 0.006ish. so 32.84ish
    }

    public static double stochasticBirthday(int people, int window, int daysPerYear, int trials) {
        Random random = new Random();
        int step = trials / 100;
        double totalN = 0;
        int[] frequencies = new int[100];
        for (int i = 0; i < trials; i++) {
            if (i % step == 0) {
                System.out.println("Trial " + i + " / " + trials);
            }
            int[] birthdays = new int[daysPerYear];
            int n = 0;
            while (!checkWindow(birthdays, window, people)) {
                n++;
                birthdays[random.nextInt(daysPerYear)]++;
            }
            totalN += n;
            frequencies[n]++;
        }
        double mean = totalN / trials;
        double variance = 0;
        for (int d = 0; d < frequencies.length; d++) {
            double residual = mean - d;
            variance += frequencies[d] * residual * residual;
        }
        variance = variance / trials;
        double stddev = Math.sqrt(variance);
        double sample_variance = variance / trials;
        double sample_stddev = Math.sqrt(sample_variance);
        System.out.println(Arrays.toString(frequencies));
        System.out.printf("mean = %.8f, variance = %f, std. dev = %f, sample variance = %f, sample std. dev = %f\n", mean, variance, stddev, sample_variance, sample_stddev);
        return totalN / trials;
    }

    public static boolean checkWindow(int[] birthdays, int window, int pplTarget) {
        window++;
        int daysPerYear = birthdays.length;
        int ppl = 0;
        for (int j = daysPerYear - window; j < daysPerYear; j++) {
            ppl += birthdays[j];
        }
        for (int end = 0; end < daysPerYear; end++) {
            if (ppl >= pplTarget) return true;
            ppl += birthdays[end];
            ppl -= birthdays[(end - window + daysPerYear) % daysPerYear];
        }
        return ppl >= pplTarget;
    }

}

