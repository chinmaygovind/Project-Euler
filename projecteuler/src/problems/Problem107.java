package problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import util.Files;
import util.Sets;

//Minimal Network.
public class Problem107 {
    private static final Scanner fileScanner = Files.fileScanner("/problem107.txt");
    private static final int[][] network = new int[40][40];
    private static final ArrayList<Integer> locks = new ArrayList<>();
    private static int savings = 0;
    public static void main(String[] args) {
        int row = 0;
        while (fileScanner.hasNextLine()) {
            int[] newRow = Sets.stringArrayToIntList(fileScanner.nextLine().replace("-", "0").split(",")).stream().mapToInt(Integer::intValue).toArray();
            network[row] = newRow;
            row++;
        }
        while (true) {
            //find max value that can be removed without breaking the graph
            int maxI = -1, maxJ = -1, maxVal = -1;
            for (int i = 0; i < network.length; i++){
                for (int j = 0; j < network.length; j++){
                    if (network[i][j] > maxVal && !locks.contains(network[i][j])){
                        //check if removing this max val will not disconnect graph
                        int inRow = 0;
                        int inCol = 0;
                        for (int k = 0; k < network.length; k++){
                            if (network[i][k] != 0 && k != j){
                                inRow++;
                            }
                            if (network[k][j] != 0 && k != i){
                                inCol++;
                            }
                            if (inRow >= 1 && inCol >= 1) break;
                        }
                        if (inRow >= 1 && inCol >= 1){
                            maxVal = network[i][j];
                            maxI = i;
                            maxJ = j;
                        }
                    }
                }
            }
            if (maxVal <= 0) break;
            network[maxI][maxJ] = 0;
            network[maxJ][maxI] = 0;
            if (checkNetwork(network)){
                savings += maxVal;
            } else {
                network[maxI][maxJ] = maxVal;
                network[maxJ][maxI] = maxVal;
                locks.add(maxVal);
            }

        }

        System.out.println("The total savings from removing unneccesary edges of the graph is: " + savings);
        //System.out.println(Arrays.deepToString(network).replace("],", "],\n"));
    }

    private static boolean checkNetwork(int[][] network){
        HashSet<Integer> counted = new HashSet<>();
        ArrayList<Integer> unchecked = new ArrayList<>();
        for (int i = 0; i < network.length; i++){
            if (network[0][i] != 0) {
                unchecked.add(i);
                break;
            }
        }
        while (unchecked.size() >= 1){
            int toCheck = unchecked.get(0);
            if (counted.contains(toCheck)) {
                unchecked.remove((Integer) toCheck);
                continue;
            }
            for (int i = 0; i < network.length; i++){
                if (network[i][toCheck] != 0) unchecked.add(i);
                if (network[toCheck][i] != 0) unchecked.add(i);
            }
            counted.add(toCheck);

        }
        return counted.size() == network.length;

    }
}
