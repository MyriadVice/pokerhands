package pokerhands.strategies;

import pokerhands.Card;
import pokerhands.utils.CardUtils;

import java.util.List;

/**
 * A class representing the {@link PokerHandStrategy} for the flush strategy: Hand contains 5 cards of the same suit.
 * Hands which are both flushes are ranked using the rules for High Card.
 */
public class FlushStrategy extends PokerHandStrategy {

    public FlushStrategy() {
        super("Flush");
    }

    @Override
    public boolean isPermissible(List<Card> hand) {
        //this strategy is permissible if the hand contains 5 cards of the same suit
        return hand != null && hand.size() > 0 && CardUtils.getSuitPair(hand, 5) != null;
    }

    @Override
    public List<Card> evaluatePair(List<Card> hand1, List<Card> hand2) {
        //check for malformed input
        if (hand1 == null || hand2 == null || hand1.size() == 0 || hand2.size() == 0) return null;

        //hands which are both flushes are ranked using the rules for High Card
        return new HighCardStrategy().evaluatePair(hand1, hand2);
    }
}
