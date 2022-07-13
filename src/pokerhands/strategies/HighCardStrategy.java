package pokerhands.strategies;

import pokerhands.Card;

import java.util.List;

/**
 * A class representing the {@link PokerHandStrategy} for the high card strategy: Hands which do not fit any higher
 * category are ranked by the value of their highest card (high card strategy). If the highest cards have the same
 * value, the hands are ranked by the next highest, and so on
 */
public class HighCardStrategy extends PokerHandStrategy {
    @Override
    public boolean isPermissible(List<Card> hand) {
        //for very card it is always possible to evaluate the card under this strategy
        return true;
    }

    @Override
    public List<Card> evaluatePair(List<Card> hand1, List<Card> hand2) {
        //to compare two cards by this strategy, we compare the values of their highest cards. If the values for the
        // highest cards are equal, we continue with the second highest

        //we assume the hands sorted in descending order according to their cards values
        for (int i = 0; i < hand1.size(); i++) {
            //compare
            if (hand1.get(i).getValue().ordinal() > hand2.get(i).getValue().ordinal()) return hand1;
            if (hand1.get(i).getValue().ordinal() < hand2.get(i).getValue().ordinal()) return hand2;
            //else, continue with next cards
        }

        //no winning card could be found
        return null;
    }
}
