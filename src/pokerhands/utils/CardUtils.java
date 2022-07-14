package pokerhands.utils;

import pokerhands.Card;
import pokerhands.CardSuit;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * A collection of utility functions for working with {@link pokerhands.Card}s.
 **/
public class CardUtils {

    /**
     * This function is used to retrieve a subset of amount cards from the passed set of cards that all have the same value
     * {@link pokerhands.CardValue}. In case multiple pairs are found, the subset with the highest value is returned.
     **/
    public static List<Card> getValuePair(List<Card> cards, int amount) {
        //we assume that card is sorted in descending order

        //iterate from highest card to lowest and search for set of amount length. Since the set is ordered we remember
        //the last index of a new value and count how many entries of the same value follow. If we reach amount consecutive
        //values, we create a new list and return
        int lastSequenceStart = 0;
        int consecutiveEntries = 1;
        for (int i = 1; i < cards.size(); i++) {
            //in case this value is the same as the previous, we increment the consecutive entries length
            if (cards.get(i).getValue().ordinal() == cards.get(lastSequenceStart).getValue().ordinal()) {
                consecutiveEntries++;
                //check if we have reached the required amount of same entries
                if (consecutiveEntries == amount) return cards.subList(lastSequenceStart, lastSequenceStart + amount);
            } else {
                //else, if this value is not the same as the previous, we adjust the lastSequenceStart pointer and try
                //to look for a new sequence of amount many consecutive same values
                lastSequenceStart = i;
                consecutiveEntries = 0;
            }
        }

        //else, no pair of values found
        return null;
    }

    /**
     * This function is used to retrieve a subset of amount cards from the passed set of cards that for a consecutive,
     * descending order. If no such order is found, null is returned.
     **/
    public static List<Card> getConsecutiveValues(List<Card> cards, int amount) {
        //we assume hand to be ordered by card values in descending order

        //we iterate over the hand and remember the value as well as the index of the last card that could be the begin
        //of a new sequence of consecutive values. If the next entry is the next lower consecutive entry, we increase the
        //counter for the length of the found consecutive sequence. If this counter reaches amount, we return the subset
        //from the memorized index position.
        int lastSequenceStart = 0;
        int lastCardValue = cards.get(0).getValue().ordinal();
        int consecutiveEntries = 1;
        for (int i = 1; i < cards.size(); i++) {
            //in case this value is the next lower consecutive values of the previous one, we increment the counter
            if (cards.get(i).getValue().ordinal() == (lastCardValue - 1)) {
                consecutiveEntries++;
                //check if we have reached the required amount of same entries
                if (consecutiveEntries == amount) return cards.subList(lastSequenceStart, lastSequenceStart + amount);
            } else {
                //else, if this value is not the continuing the consecutive order, we adjust the lastSequenceStart pointer
                // and try to look for a new sequence of amount many consecutive values
                lastSequenceStart = i;
                consecutiveEntries = 0;
            }
            lastCardValue = cards.get(i).getValue().ordinal(); //remember new card value
        }

        //else, no sequence of consecutive values found
        return null;
    }

    /**
     * Function for retrieving a pair of amount many cards from the passed list that all share the same {@link pokerhands.CardSuit}.
     * If more then one such pairs exists, the pair with the suit of the highest value {@link pokerhands.CardValue} card
     * is returned.
     */
    public static List<Card> getSuitPair(List<Card> hand, int amount) {
        //note: we could also to this another way by generalizing the code from getValuePair to test for a passed BiPredicate for
        //addition to the set. Then, we could use the same code base for both, value pairs and suit pairs. However, I choose
        //this implementation since I think its easier to understand in just a read what this function is supposed to do.

        HashMap<CardSuit, List<Card>> suitOccurrences = new HashMap<>();
        //count occurrences for a  suits
        for (Card c : hand) {
            //first count
            if (!suitOccurrences.containsKey(c.getSuit())) {
                suitOccurrences.put(c.getSuit(), new LinkedList<>());
            }
            suitOccurrences.get(c.getSuit()).add(c);
        }

        //check if one list of length amount exists and return it
        for (CardSuit suit : suitOccurrences.keySet()) {
            if (suitOccurrences.get(suit).size() >= amount) return suitOccurrences.get(suit).subList(0, amount);
        }

        //no pair of suits found
        return null;
    }
}
