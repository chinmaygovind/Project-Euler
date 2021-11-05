package util;

import java.util.Objects;
import java.util.Scanner;

public class Files {
    public static Scanner fileScanner(String fileName){
        return new Scanner(Objects.requireNonNull(Files.class.getResourceAsStream(fileName)));
    }
}