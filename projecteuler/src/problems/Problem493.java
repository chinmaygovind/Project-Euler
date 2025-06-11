package problems;


//Under the Rainbow.
public class Problem493 {
    public static void main(String[] args) {
        // 70 coloured balls are placed in an urn, 10 for each of the seven rainbow colours.
        // What is the expected number of distinct colours in 20 randomly picked balls?

        // Let X be a random variable denoting the number of distinct colors in 20 randomly picked balls.
        // Then, we can write X = X1 + X2 + ... + X7, where each Xi is an indicator random variable
        // Xi is 1 if color i was picked and 0 otherwise.
        // Then, by LoE, E[X] = E[X1] + E[X2] + ... + E[X7] = 7E[X1] (by symmetry)

        // Note that E[X1] is just the probability that a color appears among the 20 randomly picked balls.
        // That is, E[X1] = Pr[Color 1 appears].
        // Consider the sample space- the sample space is the set of all subsets of 20 balls out of the total 70.
        // Then, the cardinality of the sample space is 70choose20 and the sample space is uniform.
        // Using Complementary counting, Pr[Color 1 appears] = 1 - Pr[Color 1 doesn't appear]
        // The probability that Color 1 doesn't appear is the probability that the 20 randomly picked balls
        // all come from the 60 non-color 1 balls.
        // There are 60choose20 ways to pick the 20 balls from the 60 non-color 1 balls, so, by uniformity,
        // Pr[Color 1 doesn't appear] = (60choose20) / (70choose20)
        // Then, E[X1] = Pr[Color 1 appears] = 1 - 60choose20 / 70choose20
        // Therefore, E[X] = 7E[X1] = 7(1 - 60choose20 / 70choose20)
        //                          = 7 - 7 * (60! / 40!20!) / (70! / 50!20!)
        //                          = 7 - 7 * (41 * 42 8  ... * 49 * 50) / (61 * 62 * ... * 69 * 70)
        System.out.printf("The expected number of distinct colors in 20 randomly picked balls is: %.9f\n",
                        (7 - 7 * (41.0 * 42 * 43 * 44 * 45 * 46 * 47 * 48 * 49 * 50)/
                                (61.0 * 62 * 63 * 64 * 65 * 66 * 67 * 68 * 69 * 70))
                );

    }



}

