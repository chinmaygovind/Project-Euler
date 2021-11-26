package util;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A class for all card-relating functions. Includes a card object with a rank and a suit, and other
 * helpful methods.
 */
public class Card {
    /**
     * Suits. Suits of the card, Clubs, Diamonds, Hearts and Spades.
     */
    public enum Suits {
        CLUBS('♣'),
        DIAMONDS('♦'),
        HEARTS('♥'),
        SPADES('♠');

        private final char icon;
        Suits (char icon){
            this.icon = icon;
        }
        public String getIcon(){
            return String.valueOf(icon);
        }
    }

    /**
     * Ranks. Ranks of card from Ace-King, which return values from 2-14.
     */
    public enum Ranks {
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(11, "J"),
        QUEEN(12, "Q"),
        KING(13, "K"),
        ACE(14, "A");

        private int value;
        private String name;
        Ranks(int value){
            this.value = value;
            this.name = String.valueOf(value);
        }
        Ranks(int value, String name){
            this.value = value;
            this.name = name;
        }
        /**
         * Gets value of rank, from 2 - 14. Jack is 11, Queen is 12, King is 13, Ace is 14.
         * @return Value of rank.
         */
        public int getValue(){
            return value;
        }

        /**
         * Gets name of the rank. (e.g. "A" for Ace, "K" for King, etc.)
         * @return String of the name of rank.
         */
        public String getName() {return name;}
    }

    private Suits suit;
    private Ranks rank;

    /**
     * Creates a Card Object.
     * @param suit Suit of card, a Suits enum.
     * @param rank Rank of card, a Ranks enum.
     */
    public Card(Suits suit, Ranks rank){
        this.suit = suit;
        this.rank = rank;
    }
    public Card(String name){
        Suits[] suits = new Suits[]{Suits.CLUBS, Suits.DIAMONDS, Suits.HEARTS, Suits.SPADES};
        this.suit = suits["CDHS".indexOf(name.charAt(1))];
        Ranks[] ranks = new Ranks[]{Ranks.TWO, Ranks.THREE, Ranks.FOUR, Ranks.FIVE, Ranks.SIX, Ranks.SEVEN, Ranks.EIGHT, Ranks.NINE, Ranks.TEN, Ranks.JACK, Ranks.QUEEN, Ranks.KING, Ranks.ACE};
        this.rank = ranks["23456789TJQKA".indexOf(name.charAt(0))];

    }

    /**
     * Returns Suit of card.
     * @return Suits enum of suit of the card.
     */
    public Suits getSuit(){
        return suit;
    }
    /**
     * Returns Rank of card.
     * @return Ranks enum of rank of the card.
     */
    public Ranks getRank(){
        return rank;
    }

    @Override
    public String toString(){
        return getRank().getName() + getSuit().getIcon();
    }

    /**
     * Scores a poker hand of five card by standard poker hand rules.
     * @param hand Array of 5 Cards to be scored as a hand.
     * @return A double from 1-10 representing how good the hand is; a 10 is a royal flush, while a 1 is high card.
     */
    public static double scorePokerHand(Card[] hand){
        //Check for invalid hand.
        if (hand.length != 5){
            return 0;
        }

        ArrayList<Suits> uniqueSuits = new ArrayList<>();
        ArrayList<Ranks> uniqueRanks = new ArrayList<>();
        ArrayList<Ranks> ranks = new ArrayList<>();

        for (Card card : hand){
            if (!uniqueSuits.contains(card.getSuit())){
                uniqueSuits.add(card.getSuit());
            }
            if (!uniqueRanks.contains(card.getRank())){
                uniqueRanks.add(card.getRank());
            }
            ranks.add(card.getRank());

        }
        Collections.sort(ranks);

        //Check For Straight/Royal Flush
        if (uniqueSuits.size() == 1 &&
            (ranks.get(0).getValue() + 1)%13 == ranks.get(1).getValue()%13 &&
            ranks.get(1).getValue() + 1 == ranks.get(2).getValue() &&
            ranks.get(2).getValue() + 1 == ranks.get(3).getValue() &&
            (ranks.get(3).getValue() + 1)%13 == ranks.get(4).getValue()%13){
            return (ranks.get(4).getValue() == 14 ? 10 : 9 + ranks.get(4).getValue()/100.0);
        }
        //Check for Four of a Kind.
        if (uniqueSuits.size() == 4 &&
            ranks.get(1).getValue() == ranks.get(2).getValue() &&
            ranks.get(2).getValue() == ranks.get(3).getValue() &&
                (ranks.get(3).getValue() == ranks.get(4).getValue() || ranks.get(3).getValue() == ranks.get(0).getValue())){
            return 8 + ranks.get(3).getValue()/100.0 + (ranks.get(3).getValue() == ranks.get(4).getValue() ? ranks.get(4).getValue() : ranks.get(0).getValue())/10000.0;
        }
        //Check for Full House.
        if ((ranks.get(0).getValue() == ranks.get(1).getValue() &&
                ranks.get(2).getValue() == ranks.get(3).getValue() &&
                ranks.get(3).getValue() == ranks.get(4).getValue()) ||
                (ranks.get(3).getValue() == ranks.get(4).getValue() &&
                ranks.get(0).getValue() == ranks.get(1).getValue() &&
                ranks.get(1).getValue() == ranks.get(2).getValue())){
            return 7 + ranks.get(2).getValue()/100.0 + (ranks.get(2).getValue() == ranks.get(1).getValue() ? ranks.get(4).getValue() : ranks.get(0).getValue())/10000.0;
        }
        //Check for Flush.
        if (uniqueSuits.size() == 1){
            return 6 + ranks.get(4).getValue()/100.0 +
                    ranks.get(3).getValue()/10000.0 +
                    ranks.get(2).getValue()/1000000.0 +
                    ranks.get(1).getValue()/100000000.0 +
                    ranks.get(0).getValue()/10000000000.0;
        }
        //Check for Straight.
        if (ranks.get(4).getValue() == ranks.get(3).getValue() + 1  &&
            ranks.get(3).getValue() == ranks.get(2).getValue() + 1 &&
            ranks.get(2).getValue() == ranks.get(1).getValue() + 1 &&
            ranks.get(1).getValue() == ranks.get(0).getValue() + 1){
            return 5 + ranks.get(4).getValue()/100.0 +
                    ranks.get(3).getValue()/10000.0 +
                    ranks.get(2).getValue()/1000000.0 +
                    ranks.get(1).getValue()/100000000.0 +
                    ranks.get(0).getValue()/10000000000.0;
        }
        //Check for Three of a Kind.
        if (ranks.get(0).getValue() == ranks.get(1).getValue() &&
        ranks.get(0).getValue() == ranks.get(2).getValue()){
            return 4 + ranks.get(0).getValue()/100.0 +
                    ranks.get(4).getValue()/10000.0 +
                    ranks.get(3).getValue()/1000000.0;
        }
        if (ranks.get(2).getValue() == ranks.get(1).getValue() &&
                ranks.get(3).getValue() == ranks.get(2).getValue()){
            return 4 + ranks.get(1).getValue()/100.0 +
                    ranks.get(4).getValue()/10000.0 +
                    ranks.get(0).getValue()/1000000.0;
        }
        if (ranks.get(3).getValue() == ranks.get(2).getValue() &&
                ranks.get(4).getValue() == ranks.get(3).getValue()){
            return 4 + ranks.get(4).getValue()/100.0 +
                    ranks.get(1).getValue()/10000.0 +
                    ranks.get(0).getValue()/1000000.0;
        }
        //Check for Two Pair.
        if (uniqueRanks.size() == 3){
            uniqueRanks.remove(ranks.get(1));
            uniqueRanks.remove(ranks.get(3));
            return 3 + ranks.get(1).getValue()/100.0 +
                    ranks.get(3).getValue()/10000.0 +
                    uniqueRanks.get(0).getValue()/1000000.0;
        }

        //Check for One Pair.
        if (ranks.get(0).getValue() == ranks.get(1).getValue()) {
            return 2 + ranks.get(0).getValue() / 100.0 +
                    ranks.get(4).getValue() / 10000.0 +
                    ranks.get(3).getValue() / 1000000.0 +
                    ranks.get(2).getValue() / 100000000.0;
        }
        if (ranks.get(2).getValue() == ranks.get(1).getValue()) {
            return 2 + ranks.get(1).getValue() / 100.0 +
                    ranks.get(4).getValue() / 10000.0 +
                    ranks.get(3).getValue() / 1000000.0 +
                    ranks.get(0).getValue() / 100000000.0;
        }
        if (ranks.get(2).getValue() == ranks.get(3).getValue()) {
            return 2 + ranks.get(2).getValue() / 100.0 +
                    ranks.get(4).getValue() / 10000.0 +
                    ranks.get(1).getValue() / 1000000.0 +
                    ranks.get(0).getValue() / 100000000.0;
        }
        if (ranks.get(3).getValue() == ranks.get(4).getValue()) {
            return 2 + ranks.get(4).getValue() / 100.0 +
                    ranks.get(2).getValue() / 10000.0 +
                    ranks.get(1).getValue() / 1000000.0 +
                    ranks.get(0).getValue() / 100000000.0;
        }
        //Return High Card.
        return 1 + ranks.get(4).getValue() / 100.0 +
                ranks.get(3).getValue() / 10000.0 +
                ranks.get(2).getValue() / 1000000.0 +
                ranks.get(1).getValue() / 100000000.0 +
                ranks.get(0).getValue() / 10000000000.0;
    }


}
