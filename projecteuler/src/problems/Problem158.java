package problems;

//Lexicographical Neighbors.
public class Problem158 {

    private static final int NUM_LETTERS = 26;
    private static long[][] chooses = new long[NUM_LETTERS][NUM_LETTERS];
    public static void main(String[] args) {
        cacheChooses();
        long max_p_n = 0;
        for (int n = 2; n <= 26; n++) {
            max_p_n = Math.max(max_p_n, p(n));
            System.out.println(n + ": " + p(n));
        }
        System.out.println(max_p_n);
    }

    /*
     * p(n) counts the number of alphabet strings of length n such that exactly one character comes lexiographically after the previous one
     */
    public static long p(int n) {
        long count = 0;
        for (int pos = 1; pos < n; pos++) {
            //pos is the character that comes after its previous
            //all other characters must be before it
            for (int posValue = 0; posValue < NUM_LETTERS; posValue++) {
                //# of letters before posValue = posValue
                //all chars to right of pos must be Lbefore posValue
                //# of letters after posValue = 25 - posValue
                //# of chars to right of pos = n - pos - 1
                //# of ways to do right half is (posValue)C(n - pos - 1)
                //# of chars to left of pos = pos
                //# of ways to do letter before pos is (posValue - (n - pos - 1)) = posValue - n + pos + 1
                //there are posValue - n + pos letters remaining
                //# of ways to do left half is (posValue - n + pos)C(pos - 1)
                long rightHalf = choose(posValue, n - pos - 1); 
                for (int beforeValue = 0; beforeValue < posValue; beforeValue++) {
                    count += rightHalf * choose(NUM_LETTERS - beforeValue - 2, pos - 1);
                }
                //start with 26 letters
                //used 1 for break, left with 26 - 1 = 25 letters.
                //posValue letters left before posValue for the righthalf.
                //n - pos - 1 letters on right have.
                //so, right half is posValueC(n - pos - 1)
                //that leaves 25 - (n - pos - 1) = 26 - n + pos letters for left half.
                //for the right before letter, it must be a letter before posValue.
                //posValue - (n - pos - 1) = posValue - n + pos + 1 letters are available.
                //that leaves 26 -  1 -  (n - pos - 1) - 1 = 25 - n + pos letters for left half.
                //(25 - posValue)C(pos - 1)

            }
        }

        return count;
    }

    public static void cacheChooses() {
        for (int i = 0; i < NUM_LETTERS; i++) {
            chooses[0][i] = 0;
            chooses[i][0] = 1;
        }
        for (int i = 1; i < NUM_LETTERS; i++) {
            for (int j = 1; j < NUM_LETTERS; j++) {
                chooses[i][j] = chooses[i - 1][j] + chooses[i - 1][j - 1];
            }
        }
    }
    public static long choose(int n, int r) {
        if (n < 0 || r < 0) return 0;
        return chooses[n][r];
    } 
}


