package problems;


import util.Numbers;
import util.Sets;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

//Palindromic Sums.
public class Problem125 {
    private static final ArrayList<Point> radicals = new ArrayList<>();
    private static final int limit = 100000000;
    private static final int squareLimit = 10000;
    private static final HashSet<Long> palindromes = new HashSet<>();
    public static void main(String[] args) {
        for (int start = 1; start < squareLimit; start++) {
            long palindrome = start*start;
            for (long end = start + 1; end <= squareLimit; end++) {
                palindrome += end*end;
                if (palindrome >= limit) break;
                if (Numbers.isPalindrome(String.valueOf(palindrome))) {
                    palindromes.add(palindrome);
                }
            }
        }
        System.out.println("The sum of all palindromes under a hundred million that can be written as the sum of consecutive squares is: " + Sets.sumLongs(new ArrayList<>(palindromes)));
    }

}

