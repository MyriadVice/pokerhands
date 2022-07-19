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
        return CardUtils.getSuitPair(hand, 5) != null && CardUtils.getConsecutiveValues(hand, 5) != null;
    }

    @Override
    public List<Card> evaluatePair(List<Card> hand1, List<Card> hand2) {
        if (hand1.get(0).getValue().compareTo(hand2.get(0).getValue()) > 0) return hand1;
        if (hand1.get(0).getValue().compareTo(hand2.get(0).getValue()) < 0) return hand2;

        return null;
    }
}
