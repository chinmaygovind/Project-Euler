package problems;

import util.Numbers;

import java.util.*;

//Magic 5-Gon Ring.
public class Problem68 {
    private static String largestWorkingSequence;
    private static final ArrayList<String> sequences = Numbers.getPermutations("123456789A");
    //This is a mess. I don't care.
    public static void main(String[] args) {
        Collections.reverse(sequences);
        for (String n : sequences){
            if (checkNum(n)){
                List<String> num = Arrays.asList(n.split(""));
                largestWorkingSequence = (num.get(0) + num.get(1) + num.get(2) +
                        num.get(3) + num.get(2) + num.get(4) +
                        num.get(5) + num.get(4) + num.get(6) +
                        num.get(7) + num.get(6) + num.get(8) +
                        num.get(9) + num.get(8) + num.get(1)).replace("A", "10");
                 break;
            }
        }
        System.out.println("The largest 16-digit sequence describing a magic 5-gon is: " + largestWorkingSequence);
    }

    private static boolean checkNum(String n){
        List<String> num = Arrays.asList(n.split(""));
        int sum = parseDigit(num.get(0)) + parseDigit(num.get(1)) + parseDigit(num.get(2));
        String sequence = num.get(0) + num.get(1) + num.get(2) +
                num.get(3) + num.get(2) + num.get(4) +
                num.get(5) + num.get(4) + num.get(6) +
                num.get(7) + num.get(6) + num.get(8) +
                num.get(9) + num.get(8) + num.get(1);
        return sequence.chars().filter(ch -> ch == 'A').count() == 1 &&
                parseDigit(num.get(0)) < parseDigit(num.get(3)) &&
                parseDigit(num.get(0)) < parseDigit(num.get(5)) &&
                parseDigit(num.get(0)) < parseDigit(num.get(7)) &&
                parseDigit(num.get(0)) < parseDigit(num.get(9)) &&
                parseDigit(num.get(3)) + parseDigit(num.get(2)) + parseDigit(num.get(4)) == sum &&
                parseDigit(num.get(5)) + parseDigit(num.get(4)) + parseDigit(num.get(6)) == sum &&
                parseDigit(num.get(7)) + parseDigit(num.get(6)) + parseDigit(num.get(8)) == sum &&
                parseDigit(num.get(9)) + parseDigit(num.get(8)) + parseDigit(num.get(1)) == sum;
    }
    public static int parseDigit(String digit){
        if (digit.equals("A")) return 10;
        return Integer.parseInt(digit);
    }



}
