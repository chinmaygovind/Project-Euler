package problems;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//Monopoly Odds.
public class Problem084 {
    private static int player = 0;
    private static Integer[] squares = new Integer[40];
    private static int doubles = 0;
    private static int cc = 0;
    private static int ch = 0;
    private static String modalString = "";
    private static final int turns = 3000000;
    public static void main(String[] args) {
        for (int i = 0; i < 40; i++) squares[i] = 0;
        for (int i = 0; i < turns; i++){
            turn();
        }
        List<Integer> percentiles = Arrays.asList(squares.clone());
        Collections.sort(percentiles);
        Collections.reverse(percentiles);
        for (int i = 0; i < 3; i++){
            for (int percentile = 0; percentile < squares.length; percentile++){
                if (squares[percentile] == percentiles.get(i)){
                    if (percentile < 10){ modalString += "0" + percentile; }
                    else { modalString += percentile; }

                }
            }
        }
        System.out.println("The six-digit modal string of the probabilities of 4-sided dice monopoly is: " + modalString);
    }

    //Simulate a player turn.
    public static void turn(){
        int dice1 = (int) (Math.random() * 4) + 1;//generate dice rolls
        int dice2 = (int) (Math.random() * 4) + 1;
        doubles = (dice1 == dice2 ? doubles + 1 : 0);//update doubles

        player = (player + dice1 + dice2)%40;

        //square 30 / 3 doubles: go to jail
        if (player == 30 || doubles == 3){
            player = 10;
            doubles = 0;
        }
        //squares 2, 17, 33: community chest
        if (player == 2 || player == 17 || player == 33){
            if (cc == 0) player = 0;
            if (cc == 1) player = 10;
            cc = (cc + 1)%16;
        }
        //squares 7, 22, 36: chance
        if (player == 7 || player == 22 || player == 36){
            if (ch == 0) player = 0;
            if (ch == 1) player = 10;
            if (ch == 2) player = 11;
            if (ch == 3) player = 24;
            if (ch == 4) player = 39;
            if (ch == 5) player = 5;
            if (ch == 6 || ch == 7) player = (player + 10 - (player + 5)%10)%40;
            if (ch == 8){
                if (player == 22) player = 28;
                if (player == 7 || player == 36) player = 12;
            }
            if (ch == 9) player -= 3;
            ch = (ch + 1)%16;
        }
        squares[player]++;

    }

}
