package problems;


import java.util.ArrayList;
import java.util.Collections;

//Combinatoric Selections.
public class Problem053 {
    private static int combinationsOverMillion = 0;
    public static void main(String[] args){
        for (int n = 1; n <= 100; n++){
            for (int r = 1; r <= n; r++){
                if (chooseOverMillion(n, r)){
                    combinationsOverMillion++;
                }
            }
        }
        System.out.println("The number of values of n and r that give nCr a value over one million is: " + combinationsOverMillion);
    }

    private static boolean chooseOverMillion(int n, int r){
        ArrayList<Integer> multipliers = new ArrayList<>();
        ArrayList<Integer> divides = new ArrayList<>();
        multipliers.add(1);
        divides.add(1);
        for (int i = 1; i <= n; i++){
            multipliers.add(i);
            divides.add((Math.abs(r - i) == 0 ? r : Math.abs(r - i)));
        }
        Collections.sort(multipliers);
        Collections.reverse(multipliers);
        Collections.sort(divides);
        Collections.reverse(divides);
        double total = 1;
        for (int i = 0; i < multipliers.size(); i++){
            total *= multipliers.get(i);
            total /= divides.get(i);
            if (total > 1000000){
                return true;
            }
        }
        return false;
    }
}
