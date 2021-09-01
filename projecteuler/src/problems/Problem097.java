package problems;

//Large Non-Mersenne Prime.
public class Problem097 {
    private static long lastTen = 1;
    private static long modulus = 10000000000L;
    public static void main(String[] args) {
        //do the 2 ^ 7830457
        for (int i = 0; i < 7830457; i++){
            lastTen = (2 * lastTen)%modulus;
        }
        lastTen = (28433 * lastTen)% modulus;
        lastTen++;
        System.out.println("The last ten digits of 2004 massive non-Mersenne prime are: " + lastTen);
    }







}
