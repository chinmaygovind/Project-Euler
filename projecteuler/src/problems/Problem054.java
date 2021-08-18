package problems;


import util.Card;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

//Poker Hands.
public class Problem054 {
    private static Scanner hands = new Scanner(Objects.requireNonNull(Problem054.class.getResourceAsStream("/problem54.txt")));
    private static int player1wins = 0;
    public static void main(String[] args) {
        while (hands.hasNextLine()){
            String[] hand = hands.nextLine().split(" ");
            Card[] player1 = new Card[5];
            Card[] player2 = new Card[5];
            for (int cardNum = 0; cardNum < 10; cardNum++){
                if (cardNum < 5){
                    player1[cardNum] = new Card(hand[cardNum]);
                } else {
                    player2[cardNum - 5] = new Card(hand[cardNum]);
                }
            }
            System.out.println(Arrays.toString(player1) + ": " + Card.scorePokerHand(player1));
            if (Card.scorePokerHand(player1) > Card.scorePokerHand(player2)){
                player1wins++;
            }
        }
        System.out.println("The number of poker hands that Player 1 won is: " + player1wins);
    }
}
