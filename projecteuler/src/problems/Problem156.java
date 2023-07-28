package problems;

import java.util.ArrayList;

import javax.management.RuntimeErrorException;

//Counting Digits.
public class Problem156 {
    
    private static final long SEARCH_LIMIT = 1_000_000_000_000L;
    public static void main(String[] args) {
        //recon
        //no solutions past 10^11
        
        //find all the zeroes
        /*
        for (int d = 1; d <= 1; d++) {
            HashMap<Long, Long> datapoints = new HashMap<>();
            for (long n = 1; n < 1_00_000_000_000L; n*= 2) {
                datapoints.put((long) n, n - f(n, d));
            }
            //find the zeroes 
            boolean foundAllTheZeroes = false;
            HashSet<Long> solutions = new HashSet<>();
            while (!foundAllTheZeroes) {
                boolean stillSearching = false;
                ArrayList<Long> nValues = new ArrayList<>(datapoints.keySet());
                Collections.sort(nValues);
                for (int i = 0; i < nValues.size() - 1; i++) {
                    if ((datapoints.get(nValues.get(i)) ^ datapoints.get(nValues.get(i + 1))) <= 0 && 
                    nValues.get(i + 1) - nValues.get(i) > 1) {
                        stillSearching = true;
                        long newN = (nValues.get(i)+ nValues.get(i + 1))/2;
                        datapoints.put(newN, newN - f(newN,  d));
                    //System.out.printf("added %d, with value %d\n", newN, newN - f(newN, d));
                    }
                    if (datapoints.get(nValues.get(i)) == 0) {
                        solutions.add(nValues.get(i));
                    }
                }
                if (!stillSearching) foundAllTheZeroes = true;
            }
            System.out.println(solutions);
            long total = 0;
            for (Long sol : solutions) {
                total += sol;
                if (f(sol, d) != sol) throw new RuntimeErrorException(null);
            }
            System.out.println(total);
        }
        */
    
        
        ArrayList<Long> solutions = new ArrayList<>();
        for (int d = 1; d <= 9; d++) {
            long n = 0;
            while (n < SEARCH_LIMIT) {
                if (f(n, d) == n) {
                    solutions.add(n);
                    n++;
                }
                else if (Math.abs(f(n, d) - n) < 1000) {
                    n++;
                } else {
                    n += Math.abs(f(n, d) - n)/100;
                }
            }
        }
        //System.out.println(solutions);
        long total = 0;
        for (Long sol : solutions) {
            total += sol;
        }
        System.out.println(total);
    }

    public static void test() {
        
        for (int n = 0; n <= 1100000; n++) {
            if (n%1000 == 0) System.out.println(n + " passed");
            for (int d = 1; d <= 9; d++) {
                if (f(n, d) != slowF(n, d)) {
                    System.out.printf("n: %d, d: %d. slowF: %d, fastF: %d\n", n, d, slowF(n, d), f(n, d));
                    throw new RuntimeErrorException(null);
                }
                
            }
        }
    }

    public static long slowF(int n, int d) {
        StringBuilder reconnaissance = new StringBuilder();
        for (int i = 0; i <= n; i++) {
            reconnaissance.append(i);
        }
        long count = 0;
        char dChar = String.valueOf(d).charAt(0);
        for (char c : reconnaissance.toString().toCharArray()) {
            if (c == dChar) {
                count++;
            }
        }
        return count;
    }
    /*
    public static long f(long n, int d) {
        if (n == 0) return 0;
        if (n < 10) return (n >= d ? 1 : 0);
        long count = 0;
        String numString = String.valueOf(n);
        for (int pos = 0; pos < numString.length(); pos++) {
            if (pos == 0) {
                if (getDigit(n, pos) == d) {
                    count += Long.parseLong(numString.substring(1)) + 1;
                } else if (getDigit(n, pos) > d) {
                    count += (long) Math.pow(10, numString.length() - 1);
                }
            } else {
                long dropDigit;
                if (getDigit(n, pos) >= d) dropDigit = Long.parseLong(numString.substring(0, pos) + numString.substring(pos + 1)) + 1;
                else dropDigit = Long.parseLong(numString.substring(0, pos)) * (long) Math.pow(10, numString.length() - pos - 1);
                count += dropDigit;
            }
        }

        return count;
    }
    */

    public static long f(long n, long d) {
        if (n == 0) return 0;
        long count = 0;
        long place = 1;
        while (place <= n) {
            if (place == 1) {
                count += n / 10 + (n%10 >= d ? 1 : 0);
            } else {
                int placeDigit = (int) (n % (10 * place) / place);
                if (placeDigit < d) {
                    count += n / (10 * place) * place;
                }
                else if (placeDigit == d) {
                    count += n / (10 * place) * place + (n%place + 1);
                }
                else if (placeDigit > d) {
                    count += n / (10 * place) * place + place;
                }
            }
            place*= 10;
            //System.out.println(count);
        }

        return count;
    }

    public static int getDigit(long num, int pos) {
        return Integer.parseInt(String.valueOf(num).substring(pos, pos + 1));
    }
 }



