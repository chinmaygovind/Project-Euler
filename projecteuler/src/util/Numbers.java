package util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

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
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 0; i <= limit; i++){
            if (isPrime(i)){
                primes.add(i);
            }
        }
        return primes;


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
}
