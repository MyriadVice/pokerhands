package pokerhands.utils;

import pokerhands.*;

import java.util.*;

/**
 * A collection of utility functions for working with {@link pokerhands.Card}s
 **/
public class CardUtils {

    /**
     * Function used to retrieve a set of cards from the passed {@link Hand} that all have the same {@link CardValue}. The set
     * is returned as a {@link HandView} on the passed hand and contains as many cards of equal value as specified by the
     * amount parameter. In case multiple sets are found, the subset with the highest value card is returned. If no such pair
     * exists, we return no value.
     **/
    public static Optional<HandView> getValuePair(HandView cards, int amount) {
        if (amount < 0) return Optional.empty();
        if (amount == 0) return Optional.of(cards.createView(0, 0));

        int lastSequenceStart = 0;
        int consecutiveEntries = 1;
        for (int i = 1; i < cards.size(); i++) {
            if (cards.compareValues(cards, i, lastSequenceStart) == 0 || amount == 1) {
                consecutiveEntries++;
                if (consecutiveEntries >= amount || amount == 1)
                    return Optional.of(cards.createView(lastSequenceStart, lastSequenceStart + consecutiveEntries).narrowView(0, amount));
            } else {
                lastSequenceStart = i;
                consecutiveEntries = 1;
            }
        }

        return Optional.empty();
    }

    /**
     * Function used to retrieve a subset of cards from the passed {@link Hand} that forms a consecutive, descending order. The
     * set of cards is returned as a {@link HandView} on the passed hand and contains as many cards of equal value as
     * specified by the amount parameter. If no such order is found we return no result.
     **/
    public static Optional<HandView> getConsecutiveValues(HandView cards, int amount) {
        if (amount < 0) return Optional.empty();

        int lastSequenceStart = 0;
        int lastCardValue = cards.valueAt(0).value;
        int consecutiveEntries = 1;
        for (int i = 1; i < cards.size(); i++) {
            if (cards.valueAt(i).value == (lastCardValue - 1) || amount == 0) {
                consecutiveEntries++;
                if (consecutiveEntries >= amount || amount == 1)
                    return Optional.of(cards.createView().narrowView(lastSequenceStart, lastSequenceStart + amount));
            } else {
                lastSequenceStart = i;
                consecutiveEntries = 1;
            }
            lastCardValue = cards.valueAt(i).value;
        }

        return Optional.empty();
    }

    /**
     * Function used to retrieve a set of cards from the passed {@link Hand} that all have the same {@link CardSuit}. The set
     * is returned as a {@link HandView} on the passed hand and contains as many cards of equal suit as specified by the
     * amount parameter. In case multiple sets are found, wen make no assumption on the nature of the returned set beyond
     * these criteria. If no such pair exists, we return no value.
     **/
    public static Optional<HandView> getSuitPair(HandView hand, int amount) {

        if (amount < 0) return Optional.empty();
        if (amount == 0) return Optional.of(new HandView(hand, 0, 0));

        HashMap<CardSuit, List<Card>> suitOccurrences = new HashMap<>();
        for (Card c : hand.getCards()) {
            if (!suitOccurrences.containsKey(c.getSuit())) {
                suitOccurrences.put(c.getSuit(), new LinkedList<>());
            }
            suitOccurrences.get(c.getSuit()).add(c);
        }

        CardSuit pairSuit = null;
        Set<Card> toRemove = new HashSet<>();
        for (CardSuit suit : suitOccurrences.keySet()) {
            if (pairSuit == null && suitOccurrences.get(suit).size() >= amount) {
                pairSuit = suit;
                continue;
            }
            toRemove.addAll(suitOccurrences.get(suit));
        }

        if (pairSuit != null) return Optional.of(hand.createView().remove(toRemove).narrowView(0, amount));

        return Optional.empty();
    }
}
