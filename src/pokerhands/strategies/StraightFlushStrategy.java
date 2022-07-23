package pokerhands.strategies;

import pokerhands.Card;
import pokerhands.HandView;
import pokerhands.utils.CardUtils;

import java.util.Optional;

/**
 * A class representing the {@link PokerHandStrategy} for the straight flush strategy. This strategy is permissible on a
 * {@link pokerhands.Hand} is the hand contains 5 {@link Card}s of the same {@link pokerhands.CardSuit} with consecutive values.
 * If two hands can be ranked by this strategy they are ranked comparing the the highest card in the hand.
 */
public class StraightFlushStrategy extends PokerHandStrategy {

    public StraightFlushStrategy() {
        super("Straight Flush");
    }

    @Override
    public boolean isPermissible(HandView hand) {
        return CardUtils.getSuitPair(hand, 5).isPresent() && CardUtils.getConsecutiveValues(hand, 5).isPresent();
    }

    @Override
    public Optional<HandView> evaluatePair(HandView hand1, HandView hand2) {
        if (hand1.compareValues(hand2, 0, 0) > 0) return Optional.of(hand1);
        if (hand1.compareValues(hand2, 0, 0) < 0) return Optional.of(hand2);

        return Optional.empty();
    }
}
