package pokerhands.strategies;

import pokerhands.Card;
import pokerhands.utils.CardUtils;

import java.util.List;

/**
 * A class representing the {@link PokerHandStrategy} for the straight flush strategy: 5 cards of the same suit with
 * consecutive values. Ranked by the highest card in the hand.
 */
public class StraightFlushStrategy extends PokerHandStrategy {

    public StraightFlushStrategy() {
        super("Straight Flush");
    }

    @Override
    public boolean isPermissible(List<Card> hand) {
        //this strategy is permissible if hand contains 5 cards of the same suit with consecutive values
        return hand != null && hand.size() > 0 && CardUtils.getSuitPair(hand, 5) != null && CardUtils.getConsecutiveValues(hand, 5) != null;
    }

    @Override
    public List<Card> evaluatePair(List<Card> hand1, List<Card> hand2) {
        //check for malformed input
        if (hand1 == null || hand2 == null || hand1.size() == 0 || hand2.size() == 0) return null;

        //ranked by the highest card in the hand
        if (hand1.get(0).getValue().ordinal() > hand2.get(0).getValue().ordinal()) return hand1;
        if (hand1.get(0).getValue().ordinal() < hand2.get(0).getValue().ordinal()) return hand2;

        //or resort to next lower strategy
        return null;
    }
}
