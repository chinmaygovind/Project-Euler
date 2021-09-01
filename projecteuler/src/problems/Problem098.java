package problems;

import util.Files;

import java.util.*;

//Anagramic Squares.
public class Problem098 {
    //This program doesn't even work properly, but it got the right answer. ¯\_(ツ)_/¯
    private static final Scanner fileReader = Files.fileScan("/problem098.txt");
    private static HashMap<String, ArrayList<String>> wordMap = new HashMap<>();
    private static int maxSquare = 0;
    private static ArrayList<String> poopyPile = new ArrayList<>();
    public static void main(String[] args) {
        for (String rawWord : fileReader.nextLine().split(",")) {
            String nextWord = rawWord.substring(1, rawWord.length() - 1);
            wordMap.put(nextWord, new ArrayList<>());
            char[] word1 = nextWord.toCharArray();
            Arrays.sort(word1);
            for (String word : wordMap.keySet()){
                char[] word2 = word.toCharArray();
                Arrays.sort(word2);
                if (Arrays.equals(word1, word2) && !word.equals(nextWord)){
                    wordMap.get(nextWord).add(word);
                    wordMap.get(word).add(nextWord);
                }
            }
        }
        for (String word : wordMap.keySet()){
            if (wordMap.get(word).size() > 0) {
                //NAIVE APPROACH
                int n = 0;
                while (n*n < Math.pow(10, word.length())){
                    n++;
                    if (String.valueOf(n*n).length() == word.length()) {
                        HashMap<Integer, Character> numMap = new HashMap<>();
                        int pos = 0;
                        boolean working = true;
                        char[] squareChars = String.valueOf(n * n).toCharArray();
                        for (char digit : squareChars){
                            if (!numMap.containsKey(Character.getNumericValue(digit))){
                                if (numMap.containsValue(word.charAt(pos))){
                                    working = false;
                                    break;
                                }
                                numMap.put(Character.getNumericValue(digit), word.charAt(pos));
                            } else {
                                if (word.charAt(pos) != numMap.get(Character.getNumericValue(digit))){
                                    working = false;
                                    break;
                                }
                            }
                            pos++;
                        }
                        if (working) {
                            for (String subword : wordMap.get(word)){
                                boolean working2 = true;
                                StringBuilder newSquare = new StringBuilder();
                                for (char subchar : subword.toCharArray()){
                                    for (int digit = 0; digit < 10; digit++){
                                        if (numMap.containsKey(digit) && numMap.get(digit) == subchar) {
                                            newSquare.append(digit);
                                            break;
                                        }
                                    }
                                }
                                if (Math.sqrt(Integer.parseInt(newSquare.toString()))%1 == 0 && newSquare.toString().charAt(0) != '0'){
                                    if (Math.max(Integer.parseInt(newSquare.toString()), n*n) > maxSquare){
                                        maxSquare = Math.max(Integer.parseInt(newSquare.toString()), n*n);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("The largest square that can be formed by mappings of an anagram pair in the two thousand words: " + maxSquare);
    }





}
