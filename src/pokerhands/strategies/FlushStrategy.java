package pokerhands.strategies;

import javafx.util.Pair;
import pokerhands.Card;
import pokerhands.utils.CardUtils;

import java.util.List;
import java.util.Optional;

/**
 * A class representing the {@link PokerHandStrategy} for the flush strategy: pokerhands.Hand contains 5 cards of the same suit.
 * Hands which are both flushes are ranked using the rules for High Card.
 */
public class FlushStrategy extends PokerHandStrategy {

    public FlushStrategy() {
        super("Flush");
    }

    @Override
    public boolean isPermissible(List<Card> hand) {
        return CardUtils.getSuitPair(hand, 5) != null;
    }

    @Override
    public Optional<Pair<List<Card>, PokerHandStrategy>> evaluatePair(List<Card> hand1, List<Card> hand2) {
        return new HighCardStrategy().evaluatePair(hand1, hand2);
    }
}
