package util;

import problems.Problem096;

import java.util.Objects;
import java.util.Scanner;

public class Files {
    public static Scanner fileScan(String fileName){
        return new Scanner(Objects.requireNonNull(Files.class.getResourceAsStream(fileName)));
    }
}