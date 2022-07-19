package pokerhands.utils;

import pokerhands.Card;
import pokerhands.CardSuit;

import java.util.Collections;
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
        if (amount == 0) return Collections.emptyList();

        int lastSequenceStart = 0;
        int consecutiveEntries = 1;
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getValue().compareTo(cards.get(lastSequenceStart).getValue()) == 0 || amount == 1) {
                consecutiveEntries++;
                if (consecutiveEntries == amount || amount == 1)
                    return new LinkedList<>(cards.subList(lastSequenceStart, lastSequenceStart + amount));
            } else {
                lastSequenceStart = i;
                consecutiveEntries = 1;
            }
        }

        return null;
    }

    /**
     * This function is used to retrieve a subset of amount cards from the passed set of cards that for a consecutive,
     * descending order. If no such order is found, null is returned.
     **/
    public static List<Card> getConsecutiveValues(List<Card> cards, int amount) {
        if (amount == 0) return Collections.emptyList();

        int lastSequenceStart = 0;
        int lastCardValue = cards.get(0).getValue().value;
        int consecutiveEntries = 1;
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getValue().value == (lastCardValue - 1)) {
                consecutiveEntries++;
                if (consecutiveEntries == amount) return cards.subList(lastSequenceStart, lastSequenceStart + amount);
            } else {
                lastSequenceStart = i;
                consecutiveEntries = 1;
            }
            lastCardValue = cards.get(i).getValue().value;
        }

        return null;
    }

    /**
     * Function for retrieving a pair of amount many cards from the passed list that all share the same {@link pokerhands.CardSuit}.
     * If more then one such pairs exists, we return one of them, making no assumptions about the contained values.
     */
    public static List<Card> getSuitPair(List<Card> hand, int amount) {
        if (amount < 0) return null;

        HashMap<CardSuit, List<Card>> suitOccurrences = new HashMap<>();
        for (Card c : hand) {
            if (!suitOccurrences.containsKey(c.getSuit())) {
                suitOccurrences.put(c.getSuit(), new LinkedList<>());
            }
            suitOccurrences.get(c.getSuit()).add(c);
        }

        for (CardSuit suit : suitOccurrences.keySet()) {
            if (suitOccurrences.get(suit).size() >= amount) return suitOccurrences.get(suit).subList(0, amount);
        }

        return null;
    }
}
