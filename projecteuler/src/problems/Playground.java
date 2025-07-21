package problems;


import util.Numbers;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

// Problem 954.
public class Playground {
    public static void main(String[] args) {
        System.out.println(Numbers.pollardRho(123049812304239L));
        System.out.println(Numbers.getFactors(123049812304239L));
        System.out.println(Numbers.pollardRho(new BigInteger("49213840123841232342987")));
        System.out.println(Numbers.getFactors(new BigInteger("49213840123841232342987")));
    }



}

