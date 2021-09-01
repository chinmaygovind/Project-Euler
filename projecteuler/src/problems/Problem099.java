package problems;

import util.Files;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

//Largest Exponential.
public class Problem099 {
    private static final Scanner fileReader = Files.fileScan("/problem099.txt");
    private static int maxLine = 0;
    private static double maxValue = 0;
    public static void main(String[] args) {
        int lineNum = 1;
        while (fileReader.hasNextLine()){
            String line = fileReader.nextLine();
            long a = Long.parseLong(line.split(",")[0]);
            long b = Long.parseLong(line.split(",")[1]);
            if (b * Math.log(a) > maxValue) {
                maxValue = b * Math.log(a);
                maxLine = lineNum;
            }
            lineNum++;
        }
        System.out.println("The line with the greatest numerical value in the 1000 base exponent pairs is: " + maxLine);
    }





}
