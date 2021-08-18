package problems;


import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

//XOR Decryption.
public class Problem059 {
    private static String[] asciiValues = new Scanner(Objects.requireNonNull(Problem059.class.getResourceAsStream("/problem59.txt"))).nextLine().split(",");
    private static ArrayList<Integer> encrypted =  new ArrayList<>();
    private static String decrypted;
    private static String key = "";
    private static int decryptedAsciiSum = 0;

    public static void main(String[] args) {
        for (String asciiValue : asciiValues){
            encrypted.add(Integer.parseInt(asciiValue));
        }
        for (int i = 97; i <= 122; i++){
            for (int j = 97; j <= 122; j++){
                for (int k = 97; k <= 122; k++){
                    String output = "";
                    for (Integer chr : encrypted){
                        int newChr = chr ^ (new int[] {i, j, k}[output.length()%3]);
                        output += (char) newChr;
                    }
                    if (checkString(output)){
                        decrypted = output;
                        key += (char) i;
                        key += (char) j;
                        key += (char) k;
                    }
                }
            }
        }
        for (Character chr : decrypted.toCharArray()){
            decryptedAsciiSum += chr.hashCode();
        }
        System.out.println("The sum of the ascii values of the decrpyted message is: " + decryptedAsciiSum);
        System.out.println("The message is: " + decrypted);
        System.out.println("The key is: " + key);
    }

    private static boolean checkString(String str){
        return (str.contains("the") && !str.contains("$") && !str.contains("%") && !str.contains("|"));
    }
}
