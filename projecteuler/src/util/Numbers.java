package util;

import java.awt.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A utility class with lots of useful math methods.
 */
public class Numbers {
    private static ArrayList<Integer> cachedPrimes = new ArrayList<>();
    private static HashMap<Integer, Integer> cachedPartitions = new HashMap<>();

    /**
     * Generates prime cache up to a limit.
     * @param limit Limit to generate primes up to.
     */
    public static void cachePrimes(int limit){
        cachedPrimes = Numbers.generatePrimes(limit);
    }
    /**
     * Gets factors of an integer.
     * @param num Integer that the method will find the factors of.
     * @return an ArrayList of all the factors as Integers.
     */
    public static ArrayList<Integer> getFactors(int num){
        ArrayList<Integer> factors = new ArrayList<>();
        for (int i = 1; i <= Math.floor(num/2.0); i++){
            if (num%i == 0){
                factors.add(i);
            }
        }
        return factors;
    }

    /**
     * Gets factors of an integer using the prime factorization.
     * @param num Number to get factors of.
     * @return An ArrayList of Integers of all the factors.
     */
    public static ArrayList<Integer> getFactors(int num, boolean useCached) {
        if (cachedPrimes.size() == 0) Numbers.generateCachedPrimes(num);
        ArrayList<Integer> primeFactors = Numbers.getPrimeFactors(num, true);
        HashSet<Integer> factors = new HashSet<>();
        for (int i = 0; i < Math.pow(2, primeFactors.size()); i++){
            int newFactor = 1;
            int temp = i;
            int factorNum = primeFactors.size() - 1;
            while (temp != 0){
                if (temp%2 == 1) newFactor *= primeFactors.get(factorNum);
                factorNum--;
                temp /= 2;
            }
            factors.add(newFactor);
        }
        return new ArrayList<>(factors);
    }
    /**
     * Calculates the factorial of an long (e.g. 5! (5 factorial) = 5 * 4 * 3 * 2 * 1)
     * @param num Long to calculate factorial of
     * @return BigInteger of result, since result can get very large.
     */
    public static BigInteger factorial(long num){
        BigInteger total = BigInteger.ONE;
        long temp = num;
        while (temp != 1){
            total = total.multiply(new BigInteger(String.valueOf(temp)));
            temp--;
        }
        return total;
    }

    /**
     * Calculates the factorial of an integer (e.g. 5! (5 factorial) = 5 * 4 * 3 * 2 * 1)
     * @param num Integer to calculate factorial of
     * @return Long of result.
     */
    public static long factorial(int num){
        if (num == 1 || num == 0) return 1;
        return factorial(num - 1) * num;
    }

    /**
     * Choose Function. Calculates the number of ways to select k objects from n objects (n! / k! (n - k)!)/
     * @param n Total Number of Objects as an Integer.
     * @param k Number of Objects to be Selected, As An Integer.
     * @return The total number of ways to choose k objects from n objects.
     */
    public static long choose(int n, int k) {
        long total = 1;
        for (int m = Math.max(n - k, k) + 1; m <= n; m++) {
            total *= m;
        }
        total /= Numbers.factorial(Math.min(k, n - k));
        return total;
    }
    public static BigInteger bigChoose(int n, int k) {
        BigInteger total = BigInteger.ONE;
        for (int m = Math.max(n - k, k) + 1; m <= n; m++) {
            total = total.multiply(BigInteger.valueOf(m));
        }
        total = total.divide(Numbers.factorial((long) Math.min(k, n - k)));
        return total;
    }

    /**
     * Solves a quadratic equation of the form ax^2 + bx + c = 0;
     * @param a The coefficient of x^2.
     * @param b The coefficient of x.
     * @param c The number at the end.
     * @return The solutions of the equation given by the quadratic formula in a double array.
     */
    public static double[] solveQuadratic(double a, double b, double c){
        double discriminant = Math.sqrt(Math.pow(b, 2) - 4 * a * c);
        return new double[] {(-b + discriminant) / (2 * a),
                (-b - discriminant) / (2 * a)};
    }

    /**
     * Tests if a given integer is prime.
     * @param num Number to be tested for primeness.
     * @return A boolean representing true if the number is prime.
     */
    public static boolean isPrime(int num){
        if (num <= 1){
            return false;
        }
        if (num == 2){
            return true;
        }
        for (int factor = 2; factor <= Math.ceil(Math.sqrt(num)); factor++){
            if (num%factor == 0){
                return false;
            }
        }
        return true;
    }
    public static boolean isPrime(long num){
        if (num <= 1){
            return false;
        }
        if (num == 2){
            return true;
        }
        for (int factor = 2; factor <= Math.ceil(Math.sqrt(num)); factor++){
            if (num%factor == 0){
                return false;
            }
        }
        return true;
    }
    /**
     * Gets prime factors of a number. (e.g. 6 -> 2, 3)
     * @param num Number to get prime factors of.
     * @return ArrayList of integers containing all the prime factors.
     */
    public static ArrayList<Integer> getPrimeFactors(int num){
        if (cachedPrimes.size() == 0 || cachedPrimes.get(cachedPrimes.size()-1) < num) generateCachedPrimes(num * 2);
        ArrayList<Integer> primeFactors = new ArrayList<>();
        int temp = num;
        int divisor = 0;
        while (temp != 1){
            if (temp%cachedPrimes.get(divisor) == 0){
                temp /= cachedPrimes.get(divisor);
                primeFactors.add(cachedPrimes.get(divisor));
            } else {
                divisor++;
            }
        }
       return primeFactors;
    }

    /**
     * Gets prime factors of a number using cached primes to save time. (e.g. 6 -> 2, 3)
     * @param num Number to get prime factors of.
     * @param useCached A boolean that indicates to use the cached primes.
     * @return An ArrayList of Integers containing all the prime factors.
     */
    private static ArrayList<Integer> getPrimeFactors(int num, boolean useCached){
        ArrayList<Integer> factors = new ArrayList<>();
        for (Integer prime : cachedPrimes) {
            if (num == 1) break;
            while (num % prime == 0) {
                factors.add(prime);
                num /= prime;
            }
        }
        if (num != 1) {
            factors.add(num);
        }
        return factors;
    }
    public static ArrayList<Long> getPrimeFactors(long num, boolean useCached){
        ArrayList<Long> factors = new ArrayList<>();
        for (Integer prime : cachedPrimes) {
            if (num == 1) break;
            while (num % prime == 0) {
                factors.add((long) prime);
                num /= prime;
            }
        }
        if (num != 1) {
            factors.add(num);
        }
        return factors;
    }
    public static ArrayList<Long> getPrimeFactors(double num, boolean useCached){
        ArrayList<Long> factors = new ArrayList<>();
        for (Integer prime : cachedPrimes) {
            if (num == 1) break;
            while (num % prime == 0) {
                factors.add((long) prime);
                num /= prime;
            }
        }
        if (num != 1) {
            factors.add((long) num);
        }
        return factors;
    }
    public static ArrayList<BigInteger> getPrimeFactors(BigInteger num, boolean useCached){
        ArrayList<BigInteger> factors = new ArrayList<>();
        for (Integer prime : cachedPrimes) {
            if (num.equals(BigInteger.ONE)) break;
            while (num.mod(BigInteger.valueOf(prime)).equals(BigInteger.ZERO)) {
                factors.add(BigInteger.valueOf(prime));
                num = num.divide(BigInteger.valueOf(prime));
            }
        }
        if (!num.equals(BigInteger.ONE)) {
            factors.add(num);
        }
        return factors;
    }

    /**
     * Greatest Common Factor. Finds the greatest common factor between two numbers, recursively.
     * @param a The greater of the two integers to take the GCF of.
     * @param b The smaller of the two integers to take the GCF of.
     * @return The GCF of a and b.
     */
    public static int GCF(int a, int b){
        return b == 0 ? a : GCF(b, a % b);
    }

    /**
     * Greatest Common Factor. Finds the greatest common factor between two numbers, recursively.
     * @param a The greater of the two integers to take the GCF of.
     * @param b The smaller of the two integers to take the GCF of.
     * @return The GCF of a and b.
     */
    public static long GCF(long a, long b){
        return b == 0 ? a : GCF(b, a % b);
    }

    /**
     * Greatest Common Factor. Finds the greatest common factor between two numbers, recursively.
     * @param a The greater of the two integers to take the GCF of.
     * @param b The smaller of the two integers to take the GCF of.
     * @return The GCF of a and b.
     */
    public static double GCF(double a, double b){
        return b == 0 ? a : GCF(b, a % b);
    }

    /**
     * Euler's totient function. Returns numbers of integers below n that are relatively prime to n (gcd(n, m) will be 1).
     * @param num Number to compute totient of.
     * @return Number of integers below n that are relatively prime to n.
     */
    public static int totient(int num){
        double totient = num;
        for (double primeFactor : new HashSet<>(getPrimeFactors(num))){
            totient *= (1 - 1/primeFactor);
        }
        return (int) Math.round(totient);
    }

    /**
     * Partition function. Returns number of unique ways to partition a number. Uses the method described here: https://en.wikipedia.org/wiki/Partition_function_(number_theory)#Recurrence_relations
     * @param num Number to be checked.
     * @return The number of unique ways to partition num.
     */
    public static long partition(int num){
        if (num == 0) return 1;
        if (num < 0) return 0;
        if (cachedPartitions.containsKey(num)){
            return cachedPartitions.get(num);
        }
        int decrease = 1;
        int totalSum = 0;
        while (decrease * (3 * decrease - 1) / 2 <= num){
            totalSum += (decrease%2 == 1 ? 1 : -1) * partition(num - decrease * (3 * decrease - 1) / 2);
            totalSum += (decrease%2 == 1 ? 1 : -1) * partition(num - decrease * (3 * decrease - 1) / 2 - decrease);
            decrease++;
        }
        cachedPartitions.put(num, totalSum);
        return totalSum;
    }

    /**
     * Generates a list of primes from 1 to a limit.
     * @param limit Limit of where to stop searching for primes.
     * @return An ArrayList of Longs of the primes.
     */
    public static ArrayList<Integer> generatePrimes(int limit){
        boolean[] nums = new boolean[limit];
        for (int i = 2; i < limit; i++) nums[i] = true;
        int p = 2;
        int n = p;
        while (p*p < limit) {
            if (n*p >= limit) {
                p++;
                while (!nums[p] && p*p < limit) p++;
                if (p*p >= limit) {
                    ArrayList<Integer> primes = new ArrayList<>();
                    for (int i = 2; i < limit; i++)
                        if (nums[i]) primes.add(i);
                    return primes;
                }
                n = p;
            }
            nums[n*p] = false;
            n++;
        }
        return new ArrayList<>();
    }

    public static ArrayList<ArrayList<Integer>> generateUniqueFactorizations(int limit){
        ArrayList<ArrayList<Integer>> factorizations = new ArrayList<>();
        for (int i = 0; i < limit; i++){
            factorizations.add(new ArrayList<>());
        }
        ArrayList<Integer> primes = Numbers.generatePrimes(limit);
        System.out.println("Generated primes");
        for (int i = 2; i < limit; i++){
            if (primes.contains(i)){
                for (int indx = 0; indx < limit; indx += i){
                    factorizations.get(indx).add(i);
                }
            }
        }
        return factorizations;
    }

    public static void generateCachedPrimes(int limit){
        cachedPrimes = generatePrimes(limit);
    }
    public static ArrayList<Integer> getCachedPrimes() { return cachedPrimes; }
    public static void setCachedPrimes(ArrayList<Integer> newCache) { cachedPrimes = newCache; }

    /**
     * Checks if a number is Pandigital. (e.g. 51342 is 1-5 pandigital, since it has all digits from 1-5.)
     * @param num Integer to be checked for pandigitality.
     * @param lowestDigit Lowest digit to check, commonly 0 or 1.
     * @param highestDigit Highest digit to check.
     * @return A boolean representing true if the number is pandigital.
     */
    public static boolean isPandigital(int num, int lowestDigit, int highestDigit){
        for (int digit = lowestDigit; digit <= highestDigit; digit++){
            if (!String.valueOf(num).contains(String.valueOf(digit))){
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a string is a palindrome, reading the same forward and backward. (e.g. kayak, racecar, 121)
     * @param str String to be tested.
     * @return A boolean representing true of the string is a palindrome.
     */
    public static boolean isPalindrome(String str){
        return (new StringBuilder(str).reverse().toString().equals(str));
    }

    /**
     * Returns all permutations of a string. (e.g. "123" returns "123", "132", "213", etc.)
     * @param str String to generate permutations from.
     * @return An ArrayList of all the permutations.
     */
    public static ArrayList<String> getPermutations(String str){
        if (str.length() == 1){
            ArrayList<String> permutations = new ArrayList<>();
            permutations.add(str);
            return permutations;
        }
        ArrayList<String> permutations = new ArrayList<>();
        for (int i = 0; i < str.length(); i++){
            for (String permutation : getPermutations(str.substring(0, i) + str.substring(i + 1))){
                permutations.add(str.charAt(i) + permutation);
            }
        }
        return permutations;
    }

    public static int decToBase(int dec, int base){
        int converted = 0;
        int digits = 0;
        while (dec >= 1){
            converted += (dec%base) * Math.pow(10, digits);
            dec /= base;
            digits++;
        }
        return converted;

    }
    /**
     * Raw Digits. Gets raw digits of a double as a string, to avoid the scientific notation default.
     * @param num Number to get digits of.
     * @return A string of the digits of the number.
     */
    public static String rawDigits(double num){
        return DecimalFormat.getInstance().format(num).replace(",", "");
    }

    /**
     * The product log (Lambert W) function. Uses Newton's method approximation: https://en.wikipedia.org/wiki/Lambert_W_function#Numerical_evaluation
     * @param z the value to input into the product log (z = x * e^x)
     * @return x, where x is the approximate solution to z = x * e^x
     */
    public static double productLog(double z) {
        ArrayList<Double> approximations = new ArrayList<>(Collections.singletonList(2 * Math.log10(z)));
        while (approximations.size() < 50) {
            double w = approximations.get(approximations.size()-1);
            double newW = w - (w * Math.exp(w) - z) / ((w + 1) * Math.exp(w));
            approximations.add (newW);
        }
        return approximations.get(approximations.size()-1);
    }

    /**
     * Get Fibonacci. Generates an ArrayList of the first n fibonacci numbers (1, 1, 2, 3, 5, ...).
     * @param terms The number of terms to generate.
     * @return An ArrayList of Integers containing the first n fibonacci numbers.
     */
    public static ArrayList<Integer> getFibonacci(int terms) {
        ArrayList<Integer> f = new ArrayList<>(Collections.singletonList(1));
        f.add(1);
        for (int i = 0; i < terms - 2; i++) {
            f.add(f.get(f.size() - 2) + f.get(f.size() - 1));
        }
        return f;
    }

    /**
     * Solve Pell Equation. Finds the smallest integer solution (fundamental solution) of the equation x^2 - D * y^2 = 1.
     * Implementation based upon: https://crypto.stanford.edu/pbc/notes/ep/pell.html
     * @param D, an integer representing a non-square, positive integer.
     * @return a Point(x, y), representing the smallest solution to the equation.
     */
    public static Point solvePellEquation(int D) {
        //compute continued fraction expansion of D
        ArrayList<Integer> P = new ArrayList<>();
        ArrayList<Integer> Q = new ArrayList<>();
        ArrayList<Integer> a = new ArrayList<>();
        P.add(0); Q.add(1); a.add((int) Math.floor(Math.sqrt(D)));
        P.add(a.get(0)); Q.add(D - a.get(0)*a.get(0));
        int k = 0;
        for (int n = 1; n < 100; n++) {
            if (a.size() < 3 || !a.get(a.size() - 1).equals(a.get(1))) {
                k = a.size() - 2;
            }
            if (n > 1) {
                P.add(a.get(n - 1) * Q.get(n - 1) - P.get(n - 1));
                Q.add((D - P.get(n)*P.get(n))/Q.get(n - 1));
            }
            a.add((int) Math.floor((double) (a.get(0) + P.get(n))/Q.get(n)));
        }
        //generate convergents, p and q
        ArrayList<Integer> p = new ArrayList<>();
        p.add(a.get(0)); p.add(a.get(0) * a.get(1) + 1);
        ArrayList<Integer> q = new ArrayList<>();
        q.add(1); q.add(a.get(1));
        for (int n = 2; n < (k%2 == 0 ? 2 * k + 1 : k); n++) {
            p.add(a.get(n) * p.get(n - 1) + p.get(n - 2));
            q.add(a.get(n) * q.get(n - 1) + q.get(n - 2));
        }
        return new Point(p.get(p.size()-1), q.get(q.size()-1));
    }

    /**
     * Solve General Pell Equation. Finds solutions of the equation x^2 - D * y^2 = N.
     * Implementation based upon: https://crypto.stanford.edu/pbc/notes/ep/pell.html
     * @param D, an integer representing a non-square, positive integer.
     * @param N, an integer, assumes N^2 > D. (N^2 < D case doesn't work D:)
     * @param sols, an integer, representing how many solutions to find for the equation.
     * @return an ArrayList of Points, representing the fundamental solutions of the equation.
     */
    @Deprecated
    public static ArrayList<Point> solveGeneralPellEquation(int D, int N, int sols) {
        Point resolvent = solvePellEquation(D);
        int t = resolvent.x;
        int u = resolvent.y;
        ArrayList<Point> fundamentals = new ArrayList<>();
        HashSet<Point> solutions = new HashSet<>();
        //TODO: fix n^2 < D case
        if (N * N > D) {
            int L1 = (N > 0 ? 0 : (int) Math.sqrt(-N/ (double) D));
            int L2 = (int) Math.ceil(Math.sqrt((N > 0 ? 1 : -1) * N * (t + (N > 0 ? -1 : + 1)) / (2.0 * D)));
            for (int y = L1; y <= D*N; y++) {//+3 for good luck
                int x2 = D * y * y + N;
                if (Math.sqrt(x2) == Math.floor(Math.sqrt(x2))) {
                    fundamentals.add(new Point((int) Math.sqrt(x2), y));
                    fundamentals.add(new Point(-((int) Math.sqrt(x2)), y));
                }
            }

        } else {
            ArrayList<Integer> P = new ArrayList<>();
            ArrayList<Integer> Q = new ArrayList<>();
            ArrayList<Integer> a = new ArrayList<>();
            P.add(0); Q.add(1); a.add((int) Math.floor(Math.sqrt(D)));
            P.add(a.get(0)); Q.add(D - a.get(0)*a.get(0));
            int k = 0;
            for (int n = 1; n < 100; n++) {
                if (a.size() < 3 || !a.get(a.size() - 1).equals(a.get(1))) {
                    k = a.size() - 2;
                }
                if (n > 1) {
                    P.add(a.get(n - 1) * Q.get(n - 1) - P.get(n - 1));
                    Q.add((D - P.get(n)*P.get(n))/Q.get(n - 1));
                }
                a.add((int) Math.floor((double) (a.get(0) + P.get(n))/Q.get(n)));
            }
            //generate convergents, p and q
            ArrayList<Integer> p = new ArrayList<>();
            p.add(a.get(0)); p.add(a.get(0) * a.get(1) + 1);
            ArrayList<Integer> q = new ArrayList<>();
            q.add(1); q.add(a.get(1));
            for (int n = 2; n < (k%2 == 0 ? 2 * k + 1 : k); n++) {
                p.add(a.get(n) * p.get(n - 1) + p.get(n - 2));
                q.add(a.get(n) * q.get(n - 1) + q.get(n - 2));
                double f2 = (double) N / (p.get(n)*p.get(n) + q.get(n)*q.get(n));
                if (Math.sqrt(f2) == Math.floor(Math.sqrt(f2)) || n == (k%2 == 0 ? 2 * k  : k - 1)) {
                    fundamentals.add(new Point(p.get(n), q.get(n)));
                }
            }

        }
        //family of sols given by x + y*sqrtD = (r + s*sqrtD)(t + u*sqrtD)^n
        //where (r, s) is on fundamentals list and (t, u) is resolvent
        int n = 0;
        while (solutions.size() < sols) {
            for (Point rs : fundamentals) {
                int r = rs.x;
                int s = rs.y;
                //get expansion of (t + u sqrtD)^n
                int whole = (n == 0 ? 1 : 0);
                int rad = 0;//implied sqrtD
                if (n != 0) {
                    for (int i = 0; i <= n; i++) {
                        if (i % 2 == 0)
                            whole += choose(n, i) * Math.pow(t, n - i) * Math.pow(u, i) * Math.pow(D, i / 2);
                        if (i % 2 == 1)
                            rad += choose(n, i) * Math.pow(t, n - i) * Math.pow(u, i) * Math.pow(D, (i - 1) / 2);
                    }
                }
                Point finalSol = new Point(whole * r + rad * s * D, whole * s + r * rad);
                if (solutions.size() < sols && finalSol.x > 0 && finalSol.y > 0) solutions.add(finalSol);
            }
            n++;
        }
        ArrayList<Point> finalSolutions = new ArrayList<>(solutions);
        finalSolutions.sort(Comparator.comparingInt(o -> o.x));
        return finalSolutions;
    }

    /**
     * Turns "janky" doubles (e.g. 2.3999999999995) into cleaner ones (e.g. 2.4)
     * @param x The double to be dejankified.
     * @return A double, representing the input with jank removed.
     */
    public static double dejankify(double x) {
        String m = String.valueOf(x);
        String up = String.valueOf(Math.ulp(x) + x);
        String down = String.valueOf(Math.ulp(x) - x);
        if (up.length() < m.length()) return Math.ulp(x) + x;
        if (down.length() < m.length()) return Math.ulp(x) - x;
        return x;
    }

    public static long[] xgcd(long a, long b){
        long[] retvals={0,0,0};
        long aa[]={1,0}, bb[]={0,1}, q=0;
        while(true) {
            q = a / b; a = a % b;
            aa[0] = aa[0] - q*aa[1];  bb[0] = bb[0] - q*bb[1];
            if (a == 0) {
                retvals[0] = b; retvals[1] = aa[1]; retvals[2] = bb[1];
                return retvals;
            };
            q = b / a; b = b % a;
            aa[1] = aa[1] - q*aa[0];  bb[1] = bb[1] - q*bb[0];
            if (b == 0) {
                retvals[0] = a; retvals[1] = aa[0]; retvals[2] = bb[0];
                return retvals;
            };
        }
    }

    public static BigInteger[] xgcd(BigInteger a, BigInteger b) {
        BigInteger x = a, y=b;
        BigInteger[] qrem;
        BigInteger[] result = new BigInteger[3];
        BigInteger x0 = BigInteger.ONE, x1 = BigInteger.ZERO;
        BigInteger y0 = BigInteger.ZERO, y1 = BigInteger.ONE;
        while (true){
            qrem = x.divideAndRemainder(y); x = qrem[1];
            x0 = x0.subtract(y0.multiply(qrem[0]));
            x1 = x1.subtract(y1.multiply(qrem[0]));
            if (x.equals(BigInteger.ZERO)) {result[0]=y; result[1]=y0; result[2]=y1; return result;};
            qrem = y.divideAndRemainder(x); y = qrem[1];
            y0 = y0.subtract(x0.multiply(qrem[0]));
            y1 = y1.subtract(x1.multiply(qrem[0]));
            if (y.equals(BigInteger.ZERO)) {result[0]=x; result[1]=x0; result[2]=x1; return result;};
        }
    }
}
