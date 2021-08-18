package problems;


public class Problem15 {
    private static double totalMoves;
    public static void main(String args[]){
        for (int i = 1; i <= 20; i++){
            totalMoves = totalMoves * (i + 20) / i;
        }
        System.out.println("The total number of ways to move right and down a 20x20 grid is: " + String.format("%6f", totalMoves));
    }


}
