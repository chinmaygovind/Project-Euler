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

    //Roman Numeral Constants.
    private static final String[] unitDigits = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    private static final String[] tenDigits = new String[]{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    private static final String[] hundredDigits = new String[]{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};

    /**
     * Converts a number to its minimal Roman Numeral form. (e.g. 16 -> XVI)
     * @param num Number to be converted.
     * @return A String of the number as a roman numeral.
     */
    public static String romanNumeral(int num){
        StringBuilder romanNumeral = new StringBuilder();
        while (num > 1000){
            romanNumeral.append("M");
            num -= 1000;
        }
        romanNumeral.append(hundredDigits[num%1000/100]);
        romanNumeral.append(tenDigits[num%100/10]);
        romanNumeral.append(unitDigits[num%10]);
        return romanNumeral.toString();
    }

    /**
     * Parses a Roman Numeral. (e.g. XIX -> 19)
     * @param num A String of the Roman Numeral to be parsed.
     * @return The integer value of the Roman Numeral.
     */
    public static int parseRomanNumeral(String num){
        int total = 0;
        int currentDigit = 0;
        for (char digit : num.toCharArray()){
            switch(digit){
                case 'M':
                    total += 1000;
                    break;
                case 'D':
                    total += 500;
                    break;
                case 'C':
                    if (num.length() > currentDigit + 1 && (num.charAt(currentDigit + 1) == 'D' || num.charAt(currentDigit + 1) == 'M')){
                        total -= 100;
                    } else {
                        total += 100;
                    }
                    break;
                case 'L':
                    total += 50;
                    break;
                case 'X':
                    if (num.length() > currentDigit + 1 && (num.charAt(currentDigit + 1) == 'L' || num.charAt(currentDigit + 1) == 'C')){
                        total -= 10;
                    } else {
                        total += 10;
                    }
                    break;
                case 'V':
                    total += 5;
                    break;
                case 'I':
                    if (num.length() > currentDigit + 1 && (num.charAt(currentDigit + 1) == 'V' || num.charAt(currentDigit + 1) == 'X')){
                        total -= 1;
                    } else {
                        total += 1;
                    }
                    break;
            }
            currentDigit++;
        }
        return total;
    }
}
