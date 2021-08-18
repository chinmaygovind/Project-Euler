package util;

import java.lang.Integer;
public class English {
    public static String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Gets the index of the letter of the English Alphabet (e.g. A -> 1, B -> 2, Z -> 26)
     * @param letter Letter to get the index of.
     * @return Index of letter.
     */
    public static int letterToNum(char letter){
        return letters.indexOf(letter) + 1;
    }
}
