package problems;


import java.util.ArrayList;

//Reciprocal Cycles.
public class Problem26 {
    private static int longestRecurringDigits = 1;
    private static int longestRecurringDenominator = 1;
    public static void main(String[] args){
        for (int d = 1; d < 1000; d++){
            ArrayList<Integer> remainders = new ArrayList<>();
            int dividor = 1;
            while (dividor < d){
                dividor *= 10;
            }
            while (!remainders.contains(dividor%d)){
                dividor = dividor%d;

                remainders.add(dividor);
                if (dividor == 0){
                    break;
                }
                while (dividor < d){
                    dividor *= 10;
                }
            }
            if (remainders.size() - remainders.lastIndexOf(dividor) > longestRecurringDigits){
                longestRecurringDigits = remainders.size() - remainders.lastIndexOf(dividor);
                longestRecurringDenominator = d;
            }
        }
        System.out.println("The longest recurring cycle reciprocal of an integer under 1000 has a denominator of: " + longestRecurringDenominator);
        System.out.println("The cycle length is: " + longestRecurringDigits);
    }

}
