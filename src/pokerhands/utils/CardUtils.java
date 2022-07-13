package pokerhands.utils;

import pokerhands.Card;

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
}
